package com.tengmei.trade.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.WechatUserService;
import com.tengmei.wechat.service.UserService;
import com.tengmei.wechat.vo.UserInfo;

@Component
@Configurable(autowire = Autowire.BY_TYPE)
public class WechatOAuth2Interceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(WechatOAuth2Interceptor.class);

	@Value("${wechat.fps.appID}")
	private String appID;
	@Value("${fps.url}")
	private String fpsUrl;
	@Autowired
	private WechatUserService wechatUserService;
	@Autowired
	private UserService userService;
	@Autowired
	Environment env;

	private String getFpsOAuth2Url(String host) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + env.getProperty("wechat.fps.appID")
				+ "&redirect_uri=" + "http://" + host + "/wechat/oauth2/fps&response_type=code&scope=snsapi_base&state="
				+ "STATE" + "#wechat_redirect";
		return url;
	}

	private String getPaymentOAuth2Url(String host) {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ env.getProperty("wechat.payment.appid") + "&redirect_uri=" + "http://" + host
				+ "/wechat/oauth2/payment&response_type=code&scope=snsapi_base&state=" + "STATE" + "#wechat_redirect";
		return url;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		String host = request.getServerName();
		logger.debug("host:" + host);

		if (user == null) {
			String path = request.getRequestURI();
			// 记录当前路径，以确保登录后返回到正确的地址
			if (path.indexOf("oauth2") < 0) {
				request.getSession().setAttribute("path", path);
			}
			// 用户授权，获取openid
			String url = getPaymentOAuth2Url(host);
			// TODO 此处需要识别是平台还是发品商，暂时只有发品商
			request.getSession().setAttribute("nextOAuth2Url", getFpsOAuth2Url(host));
			response.sendRedirect(url);
			return false;
		} else {
			logger.debug(user.toString());
		}
		return super.preHandle(request, response, handler);
	}
}

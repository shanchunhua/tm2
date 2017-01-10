package com.tengmei.trade.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${wechat.appID}")
	private String appID;

	@Autowired
	private WechatUserService wechatUserService;
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// test only
		//store owner
		//String openid = "oVxv2wFnV7u-PEHwOSHb7fJ_a00E";
		//supplier
		String openid="supplier";
		WechatUser testUser = wechatUserService.findByOpenid(openid);
		UserInfo userInfo = userService.getUserInfo(openid, null);
		testUser.setUserInfo(userInfo);
		request.getSession().setAttribute("user", testUser);
		//test end
		if (handler instanceof ResourceHttpRequestHandler) {
			logger.debug(request.getRequestURL().toString());
		}
		Object user = request.getSession().getAttribute("user");

		if (user == null) {
			String path = request.getRequestURI();
			// 记录当前路径，以确保登录后返回到正确的地址
			if (path.indexOf("oauth2") < 0) {
				request.getSession().setAttribute("path", path);
			}

			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri="
					+ "http://www.tengmei360.com/wechat/oauth2&response_type=code&scope=snsapi_base&state=" + "STATE"
					+ "#wechat_redirect";

			response.sendRedirect(url);
			return false;
		} else {
			logger.debug(user.toString());
		}
		return super.preHandle(request, response, handler);
	}
}

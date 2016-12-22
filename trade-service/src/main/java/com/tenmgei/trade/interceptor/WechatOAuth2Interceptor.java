package com.tenmgei.trade.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WechatOAuth2Interceptor extends HandlerInterceptorAdapter {

	@Value("${wechat.appID}")
	private String appID;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object user = request.getSession().getAttribute("user");
		if (user == null) {
			String path = request.getRequestURI();
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri="
					+ "http://www.tengmei360.com/" + path + "&response_type=code&scope=snsapi_base&state=" + "STATE"
					+ "#wechat_redirect";
			response.sendRedirect(url);
		}
		return super.preHandle(request, response, handler);
	}
}

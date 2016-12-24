package com.tengmei.trade.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.wechat.service.BasicService;
import com.tengmei.wechat.service.UserService;
import com.tengmei.wechat.vo.UserInfo;

@RestController
@RequestMapping("/entry")
public class EntryController {
	@Value("${wechat.appID}")
	String appID;

	@Autowired
	private BasicService basicService;
	@Autowired
	private UserService userService;

	@RequestMapping("/wechat/oauth2")
	public void oauth2(@RequestParam String code, @RequestParam String state, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		String path = (String) request.getSession().getAttribute("path");
		String openid = basicService.getOAuth2AccessToken(code, null).getOpenid();
		// query db first
		UserInfo userInfo = userService.getUserInfo(openid, null);
		request.getSession().setAttribute("user", userInfo);
		response.sendRedirect("http://www.tengmei360.com/" + path);
	}

	@RequestMapping("/productindex")
	public void productIndex(HttpServletResponse response) throws IOException {
		response.getWriter().append("ok");
	}

	@RequestMapping("/login/{menu}")
	public void redirectToOAuth2(@PathVariable String menu, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		if (request.getSession().getAttribute("user") == null) {
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri="
					+ "http://www.tengmei360.com/entry/success/" + menu + "&response_type=code&scope=snsapi_base&state="
					+ menu + "#wechat_redirect";
			response.sendRedirect(url);
		} else {

		}
	}

}

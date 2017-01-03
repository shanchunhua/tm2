package com.tengmei.trade.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.UserType;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.interceptor.WechatOAuth2Interceptor;
import com.tengmei.trade.service.WechatUserService;
import com.tengmei.wechat.service.BasicService;
import com.tengmei.wechat.service.UserService;
import com.tengmei.wechat.vo.UserInfo;

@Controller
@RequestMapping("/")
public class EntryController {
	private static final Logger logger = LoggerFactory.getLogger(EntryController.class);
	@Value("${wechat.appID}")
	String appID;

	@Autowired
	private BasicService basicService;
	@Autowired
	private UserService userService;
	@Autowired
	private WechatUserService wechatUserService;

	@RequestMapping("/wechat/oauth2")
	public String oauth2(@RequestParam String code, @RequestParam String state, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		String path = (String) request.getSession().getAttribute("path");
		logger.debug(path);
		String openid = basicService.getOAuth2AccessToken(code, null).getOpenid();
		UserInfo userInfo = userService.getUserInfo(openid, null);

		// query db first
		WechatUser user = wechatUserService.findByOpenid(openid);
		// 用户未注册发品商或店主
		if (user == null) {
			user = new WechatUser();
			user.setOpenid(openid);
			wechatUserService.create(user);
			user.setUserInfo(userInfo);
			request.getSession().setAttribute("user", user);
			return "redirect:http://www.tengmei360.com/index.html#!/choosetype";
		} else {
			user.setUserInfo(userInfo);
			request.getSession().setAttribute("user", user);
			if (user.getType() == null) {
				return "redirect:http://www.tengmei360.com/index.html#!/choosetype";
			} else {
				if (path.equals("main")) {
					if (user.getType().equals(UserType.SUPPLIER)) {
						return "redirect:http://www.tengmei360.com/index.html#!" + path;
					} else {
						return "redirect:http://www.tengmei360.com/index.html#!storeindex";
					}
				}
				return "redirect:http://www.tengmei360.com/index.html#!" + path;
			}
		}

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

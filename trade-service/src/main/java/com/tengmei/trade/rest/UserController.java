package com.tengmei.trade.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.UserType;
import com.tengmei.trade.domain.WechatUser;

@RestController
@RequestMapping("/rest/users")
public class UserController {
	@RequestMapping("/current")
	public RestResult<WechatUser> getCurrentUser(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");

		// TODO test only
		// WechatUser wechatUser=new WechatUser();
		// wechatUser.setOpenid("test");
		// wechatUser.setType(UserType.STORE_OWNER);

		return new RestResult<WechatUser>(user);
	}
}

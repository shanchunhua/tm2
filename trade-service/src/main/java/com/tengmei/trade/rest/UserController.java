package com.tengmei.trade.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.WechatUserService;

@RestController
@RequestMapping("/rest/users")
public class UserController {
	@Autowired
	WechatUserService wechatUserService;

	@RequestMapping("/current")
	public RestResult<WechatUser> getCurrentUser(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");

		// TODO test only
		// WechatUser wechatUser=new WechatUser();
		// wechatUser.setOpenid("test");
		// wechatUser.setType(UserType.STORE_OWNER);

		return new RestResult<WechatUser>(user);
	}

	@RequestMapping("/relations")
	public RestResult<Page<WechatUser>> getCurrentUserRelations(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size) {
		Pageable pageable = new PageRequest(page, size);
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Page<WechatUser> users = wechatUserService.findUserRelations(user, pageable);
		return new RestResult<Page<WechatUser>>(users);

	}
}

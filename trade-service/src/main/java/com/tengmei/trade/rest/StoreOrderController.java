package com.tengmei.trade.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.UserCardService;

@RestController
@RequestMapping("/rest/storeorders")
public class StoreOrderController {
	@Autowired
	UserCardService userCardService;
	@RequestMapping(method = RequestMethod.POST)
	public RestResult<?> getUserCards(@RequestBody Map<String, Object> params, HttpServletRequest request) {
		Long pid = (Long) params.get("pid");
		System.out.println(pid);
		int level = (int) params.get("level");
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		
		return null;
	}
}

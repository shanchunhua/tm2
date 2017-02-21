package com.tengmei.trade.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.ServiceService;

@RestController
public class ServiceController {
	@Autowired
	ServiceService serviceService;
	@RequestMapping(method = RequestMethod.POST)
	public RestResult<Store> create(@RequestBody Service service, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
//		store.setUser(user);
		serviceService.create(service);
		return new RestResult<Store>();
	}
}

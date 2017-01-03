package com.tengmei.trade.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tengmei.trade.domain.ProductOrder;
import com.tengmei.trade.domain.WechatUser;

@RequestMapping("/rest/orders")
public class ProductOrderController {
	@RequestMapping(method = RequestMethod.POST)
	public RestResult<ProductOrder> create(@RequestBody ProductOrder productOrder, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");

		return new RestResult<ProductOrder>();
	}   
}

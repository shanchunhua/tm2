package com.tengmei.trade.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.domain.WithdrawRequest;
import com.tengmei.trade.service.SupplierService;
import com.tengmei.trade.service.WithdrawRequestService;

@RestController
@RequestMapping("/rest/withdrawrequests")
public class WithdrawRequestController {
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private WithdrawRequestService withdrawRequestService;

	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<WithdrawRequest>> getWithdrawRequestBySupplier(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Supplier supplier = supplierService.findSupplier(user);
		return new RestResult<List<WithdrawRequest>>(withdrawRequestService.findBySupplier(supplier));
	}
}

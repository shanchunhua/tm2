package com.tengmei.trade.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.domain.WithdrawRequest;
import com.tengmei.trade.service.WithdrawRequestService;

@RestController
@RequestMapping("/rest/withdrawrequests")
public class WithdrawRequestController {
	@Autowired
	private WithdrawRequestService withdrawRequestService;

	@RequestMapping(method = RequestMethod.GET)
	public RestResult<Page<WithdrawRequest>> getWithdrawRequestByUser(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Pageable pageable = new PageRequest(page, size);
		Page<WithdrawRequest> withdrawRequests = withdrawRequestService.findByUser(user, pageable);
		for (WithdrawRequest withdrawRequest : withdrawRequests.getContent()) {
			System.out.println(withdrawRequest.getAmount());
		}
		return new RestResult<Page<WithdrawRequest>>(withdrawRequests);
	}

	@RequestMapping(method = RequestMethod.POST)
	public RestResult<Store> create(@RequestBody WithdrawRequest withdrawRequest, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		withdrawRequest.setUser(user);
		withdrawRequestService.create(withdrawRequest);
		return new RestResult<Store>();
	}
}

package com.tengmei.trade.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.WechatUser;
import com.tengmei.wechat.service.AccountService;
import com.tengmei.wechat.vo.QRCodeTicketResponse;

@RestController
@RequestMapping("/rest/qrcode")
public class QRCodeController {
	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public RestResult<String> getQRCodeUrl(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		QRCodeTicketResponse response = accountService.createQRCodeTicket(user.getId().toString());
		String url = accountService.getQRcodeUrl(response.getTicket());
		return new RestResult<String>(url);
	}

}

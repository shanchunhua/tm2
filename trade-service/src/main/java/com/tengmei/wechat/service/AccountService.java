package com.tengmei.wechat.service;

import com.tengmei.wechat.vo.QRCodeTicketResponse;

public interface AccountService {
	QRCodeTicketResponse createQRCodeTicket(String scene);

	String getQRcodeUrl(String ticket);
}

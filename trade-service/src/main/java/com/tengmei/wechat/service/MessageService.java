package com.tengmei.wechat.service;

import com.tengmei.wechat.vo.SubscribeResponse;

public interface MessageService {
	public SubscribeResponse getSubscribeMessage(String message);
}

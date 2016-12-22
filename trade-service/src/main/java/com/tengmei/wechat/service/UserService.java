package com.tengmei.wechat.service;

import com.tengmei.wechat.vo.SubscriberList;

public interface UserService {
	public String getUserInfo(String openid, String lang);

	public SubscriberList getSubscribers(String nextOpenid);
}

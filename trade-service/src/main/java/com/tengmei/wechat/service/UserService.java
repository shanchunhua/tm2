package com.tengmei.wechat.service;

import com.tengmei.wechat.vo.SubscriberList;
import com.tengmei.wechat.vo.UserInfo;

public interface UserService {
	public UserInfo getUserInfo(String openid, String lang);

	public SubscriberList getSubscribers(String nextOpenid);
}

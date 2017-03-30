package com.tengmei.wechat.service;

import com.tengmei.wechat.vo.AccessTokenReteriver;

public interface AccessTokenManager {
	public String getToken(String key, AccessTokenReteriver reteriver,boolean forceNew);
}
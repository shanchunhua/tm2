package com.tengmei.wechat.service;

import com.tengmei.wechat.vo.AccessToken;
import com.tengmei.wechat.vo.OAuth2AccessToken;

public interface BasicService {
	public AccessToken getAccessToken();

	public OAuth2AccessToken getOAuth2AccessToken(String code, String openid);
}

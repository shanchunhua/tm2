package com.tengmei.wechat.service;

import com.tengmei.wechat.vo.OAuth2AccessToken;

public interface AccessTokenService {
	public String getAccessToken(String appid, String appSceret);

	OAuth2AccessToken getOAuth2AccessToken(String appID, String appSecret, String code);

	String getFpsAccessToken();

	OAuth2AccessToken getFpsOAuth2AccessToken(String code);
}

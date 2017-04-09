package com.tengmei.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tengmei.wechat.service.AccessTokenService;
import com.tengmei.wechat.vo.AccessToken;
import com.tengmei.wechat.vo.OAuth2AccessToken;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {
	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;
	private static final Logger logger = LoggerFactory.getLogger(BasicServiceImpl.class);

	@Override
	public String getAccessToken(String appID, String appSecret) {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appID}&secret={appSecret}";
		ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, appID, appSecret);
		logger.debug(entity.getStatusCode() + "");
		AccessToken accessToken = restTemplate.getForObject(url, AccessToken.class, appID, appSecret);
		logger.debug(accessToken.getAccessToken());
		logger.debug(accessToken.getExpiresIn().toString());
		return accessToken.getAccessToken();
	}

	@Override
	public OAuth2AccessToken getOAuth2AccessToken(String appID, String appSecret, String code) {
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appID}&secret={appSecret}&code={code}&grant_type=authorization_code";
		OAuth2AccessToken oAuth2AccessToken = restTemplate.getForObject(url, OAuth2AccessToken.class, appID, appSecret,
				code);
		logger.debug(oAuth2AccessToken.getAccessToken());
		logger.debug(oAuth2AccessToken.getExpiresIn().toString());
		return oAuth2AccessToken;
	}

	@Override
	public String getFpsAccessToken() {
		String appID = env.getProperty("wechat.fps.appID");
		String appSecret = env.getProperty("wechat.fps.appSecret");
		return this.getAccessToken(appID, appSecret);
	}

	@Override
	public OAuth2AccessToken getFpsOAuth2AccessToken(String code) {
		String appID = env.getProperty("wechat.fps.appID");
		String appSecret = env.getProperty("wechat.fps.appSecret");
		return this.getOAuth2AccessToken(appID, appSecret,code);
	}
}

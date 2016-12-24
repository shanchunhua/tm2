package com.tengmei.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tengmei.wechat.service.BasicService;
import com.tengmei.wechat.vo.AccessToken;
import com.tengmei.wechat.vo.OAuth2AccessToken;

@Service
public class BasicServiceImpl implements BasicService {
	private static final Logger logger = LoggerFactory.getLogger(BasicServiceImpl.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${wechat.appID}")
	String appID;

	@Value("${wechat.appSecret}")
	String appSecret;

	private AccessToken accessToken;
	
	private Map<String,OAuth2AccessToken> map=new HashMap<String,OAuth2AccessToken>();

	@Override
	public AccessToken getAccessToken() {
		if (accessToken == null) {
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appID}&secret={appSecret}";
			ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, appID, appSecret);
			logger.debug(entity.getStatusCode() + "");
			accessToken = restTemplate.getForObject(url, AccessToken.class, appID, appSecret);
			logger.debug(accessToken.getAccessToken());
			logger.debug(accessToken.getExpiresIn().toString());
		}
		return accessToken;
	}

	@Override
	public OAuth2AccessToken getOAuth2AccessToken(String code, String openid) {
		OAuth2AccessToken oAuth2AccessToken = null;
		if (openid == null) {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appID}&secret={appSecret}&code={code}&grant_type=authorization_code";
//			ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class, appID, appSecret, code);
//			logger.debug(entity.getStatusCode() + "");
//			logger.debug(entity.getBody());
			oAuth2AccessToken = restTemplate.getForObject(url, OAuth2AccessToken.class, appID, appSecret, code);
			logger.debug(oAuth2AccessToken.getAccessToken());
			logger.debug(oAuth2AccessToken.getExpiresIn().toString()); 
		}
		return oAuth2AccessToken;
	}

}

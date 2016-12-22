package com.tengmei.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tengmei.wechat.service.BasicService;
import com.tengmei.wechat.vo.AccessToken;

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

	@Override
	public AccessToken getAccessToken() {
		if (accessToken == null) {
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appID}&secret={appSecret}1";
			ResponseEntity<String> entity=restTemplate.getForEntity(url,String.class, appID, appSecret);
			logger.debug(entity.getStatusCode()+"");
			accessToken = restTemplate.getForObject(url, AccessToken.class, appID, appSecret);
			logger.debug(accessToken.getAccessToken());
			logger.debug(accessToken.getExpiresIn().toString());
		}
		return accessToken;
	}

}

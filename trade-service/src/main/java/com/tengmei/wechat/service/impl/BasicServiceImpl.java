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
import com.tengmei.wechat.util.StringObjectConverter;
import com.tengmei.wechat.vo.AccessToken;
import com.tengmei.wechat.vo.OAuth2AccessToken;

@Service
public class BasicServiceImpl implements BasicService {
	private static final Logger logger = LoggerFactory.getLogger(BasicServiceImpl.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${wechat.fps.appID}")
	String appID;

	@Value("${wechat.fps.appSecret}")
	String appSecret;

	private AccessToken accessToken;

	private Map<String, OAuth2AccessToken> map = new HashMap<String, OAuth2AccessToken>();

	/**
	 * 获取api调用的token，这是所有api调用的基础
	 */
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

	/**
	 * TODO,深入研究一下
	 * 此方法的token目前不需要，可以用后端的token去获取用户的信息，这里要用的是openid，如果网页没在公众号里房屋，则需要用这个token获取用户信息？但用户信息貌似已经返回的？
	 */
	@Override
	public OAuth2AccessToken getOAuth2AccessToken(String code, String openid) {
		OAuth2AccessToken oAuth2AccessToken = null;
		if (openid == null) {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appID}&secret={appSecret}&code={code}&grant_type=authorization_code";
			// ResponseEntity<String> entity = restTemplate.getForEntity(url,
			// String.class, appID, appSecret, code);
			// logger.debug(entity.getStatusCode() + "");
			// logger.debug(entity.getBody());
			oAuth2AccessToken = restTemplate.getForObject(url, OAuth2AccessToken.class, appID, appSecret, code);
			logger.debug(oAuth2AccessToken.getAccessToken());
			logger.debug(oAuth2AccessToken.getExpiresIn().toString());
		}
		return oAuth2AccessToken;
	}

	/**
	 * 获取JSAPI的ticket
	 * 
	 * @return
	 */
	@Override
	public String getJsApiTicket() {
		String token = getAccessToken().getAccessToken();
		final String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={token}&type=jsapi";
		String reponse = restTemplate.getForObject(url, String.class, token);
		Map<String, String> responseMap = StringObjectConverter.convertJSON2Object(reponse);
		String ticket = responseMap.get("ticket");
		return ticket;
	}
}

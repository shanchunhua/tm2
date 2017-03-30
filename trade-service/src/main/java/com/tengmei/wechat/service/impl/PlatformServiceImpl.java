package com.tengmei.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.tengmei.trade.domain.Platform;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.repository.PlatformRepository;
import com.tengmei.wechat.service.AccessTokenManager;
import com.tengmei.wechat.service.PlatformService;
import com.tengmei.wechat.util.HttpTemplate;
import com.tengmei.wechat.util.StringObjectConverter;
import com.tengmei.wechat.vo.AccessTokenReteriver;
import com.tengmei.wechat.vo.CachedObject;

public class PlatformServiceImpl implements PlatformService {
	@Value("${platform.appid}")
	private String componentAppid;
	@Autowired
	private PlatformRepository platformRepository;
	@Autowired
	private RestTemplate restTemplate;
	private AccessTokenManager accessTokenManager;
	private static final Logger logger = LoggerFactory.getLogger(PlatformServiceImpl.class);
	@Value("${platform.appSecret}")
	private String componentSecret;

	private String componentVerifyTicket;
	@Value("${platform.certLocation}")
	private String certLocation;

	public void setComponentVerifyTicket(String componentVerifyTicket) {
		Platform platform = platformRepository.findOne(1L);
		if (platform == null) {
			platform = new Platform();
			platform.setId(1L);
		}
		platform.setComponentVerifyTicket(componentVerifyTicket);
		platformRepository.save(platform);
		this.componentVerifyTicket = componentVerifyTicket;
	}

	@Override
	public String getPreAuthCode(boolean forceNew) {
		final String url = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token="
				+ getComponentAccessToken();
		return accessTokenManager.getToken("PRE_AUTH_CODE_" + componentAppid, new AccessTokenReteriver<String>() {
			@Override
			public CachedObject<String> reterive() {

				HttpTemplate httpTemplate = new HttpTemplate();
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("component_appid", componentAppid);
				String json = StringObjectConverter.convertObject2JSON(params);
				logger.debug("json:{}", json);
				Map<String, String> result = httpTemplate.post(url, json);
				CachedObject<String> accessToken = new CachedObject<String>();
				accessToken.setObject(result.get("pre_auth_code"));
				accessToken.setTimeToLive(Long.valueOf(result.get("expires_in")));
				accessToken.setLastUpdate(System.currentTimeMillis());
				return accessToken;
			}
		}, forceNew);
	}

	@Override
	public String getComponentAccessToken() {
		return accessTokenManager.getToken(componentAppid, new AccessTokenReteriver<String>() {
			@Override
			public CachedObject<String> reterive() {
				String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
				HttpTemplate httpTemplate = new HttpTemplate();
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("component_appid", componentAppid);
				params.put("component_appsecret", componentSecret);
				params.put("component_verify_ticket", getComponentVerifyTicket());
				String json = StringObjectConverter.convertObject2JSON(params);
				logger.debug("json:{}", json);
				Map<String, String> result = httpTemplate.post(url, json);
				CachedObject<String> accessToken = new CachedObject<String>();
				String component_access_token = result.get("component_access_token");
				accessToken.setObject(component_access_token);
				accessToken.setTimeToLive(Long.valueOf(result.get("expires_in")));
				accessToken.setLastUpdate(System.currentTimeMillis());
				return accessToken;
			}
		}, false);

	}

	public String getComponentVerifyTicket() {
		if (componentVerifyTicket == null) {
			componentVerifyTicket = platformRepository.findOne(1L).getComponentVerifyTicket();
		}
		return componentVerifyTicket;
	}

	@Override
	public void initializeAppToken(Store store, String auth_code) {

	}

}

package com.tengmei.wechat.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tengmei.common.AppException;
import com.tengmei.trade.domain.Platform;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.repository.PlatformRepository;
import com.tengmei.trade.repository.StoreRepository;
import com.tengmei.wechat.service.AccessTokenManager;
import com.tengmei.wechat.service.PlatformService;
import com.tengmei.wechat.util.HttpTemplate;
import com.tengmei.wechat.util.StringObjectConverter;
import com.tengmei.wechat.vo.AccessTokenReteriver;
import com.tengmei.wechat.vo.CachedObject;

@Service
public class PlatformServiceImpl implements PlatformService {
	@Value("${platform.appID}")
	private String componentAppid;
	@Autowired
	private PlatformRepository platformRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private AccessTokenManager accessTokenManager;
	private static final Logger logger = LoggerFactory.getLogger(PlatformServiceImpl.class);
	@Value("${platform.appSecret}")
	private String componentSecret;

	private String componentVerifyTicket;
	@Value("${wechat.payment.certLocation}")
	private String certLocation;

	@Autowired
	private StoreRepository storeRepository;

	public void setComponentVerifyTicket(String componentVerifyTicket) {
		Platform platform = platformRepository.findOne(1L);
		if (platform == null) {
			platform = new Platform();
			platform.setId(1L);
		}
		platform.setComponentVerifyTicket(componentVerifyTicket);
		String componentAccessToken = this.getComponentAccessToken();
		platform.setComponentAccessToken(componentAccessToken);
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
		final String url = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token="
				+ getComponentAccessToken();
		HttpTemplate httpTemplate = new HttpTemplate();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("component_appid", componentAppid);
		params.put("authorization_code", auth_code);

		String json = StringObjectConverter.convertObject2JSON(params);
		logger.debug("json:{}", json);
		String content = httpTemplate.postRaw(url, json);
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map map = mapper.readValue(content, Map.class);
			Map authorizationInfo = (Map) map.get("authorization_info");
			String accessToken = authorizationInfo.get("authorizer_access_token").toString();
			String refreshToken = authorizationInfo.get("authorizer_refresh_token").toString();
			String appid = authorizationInfo.get("authorizer_appid").toString();
			store.setAppid(appid);
			store.setAccessToken(accessToken);
			store.setRefreshToken(refreshToken);
			storeRepository.save(store);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		}
	}

}

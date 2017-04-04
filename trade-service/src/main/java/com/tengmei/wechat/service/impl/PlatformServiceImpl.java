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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tengmei.common.AppException;
import com.tengmei.trade.domain.Platform;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.StoreMessageTemplate;
import com.tengmei.trade.repository.PlatformRepository;
import com.tengmei.trade.repository.StoreMessageTemplateRepository;
import com.tengmei.trade.repository.StoreRepository;
import com.tengmei.wechat.service.AccessTokenManager;
import com.tengmei.wechat.service.PlatformService;
import com.tengmei.wechat.util.HttpTemplate;
import com.tengmei.wechat.util.StringObjectConverter;
import com.tengmei.wechat.vo.AccessTokenReteriver;
import com.tengmei.wechat.vo.CachedObject;
import com.tengmei.wechat.vo.CreateMenuRequest;
import com.tengmei.wechat.vo.MenuCreateResponse;
import com.tengmei.wechat.vo.MenuItem;

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
	@Autowired
	private StoreMessageTemplateRepository storeMessageTemplateRepository;

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
			createMenu(store.getId());
			createMessageTemplate(store.getId(), "TM00015");
//			createMessageTemplate(store.getId(), "OPENTM207777500");
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

	public void createMenu(Long storeId) {
		Store store = storeRepository.findOne(storeId);
		String appid = store.getAppid();
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + store.getAccessToken();
		CreateMenuRequest request = new CreateMenuRequest();
		MenuItem item = new MenuItem();
		item.setName("消费指南");
		item.setUrl(url);
		request.getButton().add(item);
		item = new MenuItem();
		item.setName("我的业绩");
		item.setUrl(url);
		request.getButton().add(item);
		item = new MenuItem();
		item.setName("我要推广");
		item.setUrl(url);
		request.getButton().add(item);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(request));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		MenuCreateResponse response = restTemplate.postForObject(url, request, MenuCreateResponse.class);
		logger.debug(response.getErrcode());
		logger.debug(response.getErrmsg());

	}

	public void removeMenu(Long storeId) {
		Store store = storeRepository.findOne(storeId);
		String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + store.getAccessToken();
		HttpTemplate httpTemplate = new HttpTemplate();
		httpTemplate.get(url);
	}

	public String createMessageTemplate(Long storeId, String templateId) {
		Store store = storeRepository.findOne(storeId);
		StoreMessageTemplate storeMessageTemplate = storeMessageTemplateRepository.findByMessageTypeAndStore(templateId,
				store);
		if (storeMessageTemplate == null) {
			storeMessageTemplate = new StoreMessageTemplate();
			storeMessageTemplate.setStore(store);
			storeMessageTemplate.setMessageType(templateId);
			setIndustry(store.getAccessToken());
			String url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token="
					+ store.getAccessToken();
			HttpTemplate httpTemplate = new HttpTemplate();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("template_id_short", templateId);
			Map<String, String> results = httpTemplate.post(url, StringObjectConverter.convertObject2JSON(params));

			String tplid = results.get("template_id");
			storeMessageTemplate.setTemplateId(tplid);
			storeMessageTemplateRepository.save(storeMessageTemplate);
			return tplid;
		} else {
			return storeMessageTemplate.getTemplateId();
		}

	}

	public void setIndustry(String accessToken) {
		String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + accessToken;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("industry_id1", "1");
		data.put("industry_id2", "23");
		HttpTemplate template = new HttpTemplate();
		// 因为只允许调用一次，所以再次调用会出异常，所以catch住
		try {
			template.post(url, StringObjectConverter.convertObject2JSON(data));
		} catch (Exception e) {
			logger.info("异常被捕获，继续执行");
			logger.error(e.getMessage());
		}
	}
	public void sendTemplateMessage(Long storeId, String message) {
		Store store = storeRepository.findOne(storeId);
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
					+ store.getAccessToken();
			HttpTemplate template = new HttpTemplate();
			Map<String, String> result = template.post(url, message);
		} catch (Exception e) {
			logger.info("发送模板消息异常被捕获，继续执行");
			logger.error(e.getMessage());
		}
	}
}

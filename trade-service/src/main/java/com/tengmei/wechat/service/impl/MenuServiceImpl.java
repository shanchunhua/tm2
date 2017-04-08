package com.tengmei.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tengmei.wechat.service.MenuService;
import com.tengmei.wechat.vo.CreateMenuRequest;
import com.tengmei.wechat.vo.MenuCreateResponse;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MenuServiceImpl implements MenuService {
	@Autowired
	RestTemplate restTemplate;
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	private String accessToken;

	public MenuServiceImpl(String accessToken) {
		this.accessToken = accessToken;
	}

	public MenuServiceImpl() {
		super();
	}

	@Override
	public MenuCreateResponse createMenu(CreateMenuRequest request) {
		logger.debug(accessToken);
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(request));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		MenuCreateResponse response = restTemplate.postForObject(url, request, MenuCreateResponse.class);
		logger.debug(response.getErrcode());
		logger.debug(response.getErrmsg());
		return response;

	}

}

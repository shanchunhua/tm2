package com.tengmei.wechat.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tengmei.wechat.service.JSAPIService;
import com.tengmei.wechat.util.StringObjectConverter;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JSAPIServiceImpl implements JSAPIService {
	@Autowired
	RestTemplate restTemplate;
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public JSAPIServiceImpl() {
		super();
	}

	public JSAPIServiceImpl(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	@Override
	public String getJsApiTicket() {
		final String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={accessToken}&type=jsapi";
		String reponse = restTemplate.getForObject(url, String.class, accessToken);
		Map<String, String> responseMap = StringObjectConverter.convertJSON2Object(reponse);
		String ticket = responseMap.get("ticket");
		return ticket;
	}

}

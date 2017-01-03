package com.tengmei.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tengmei.wechat.service.AccountService;
import com.tengmei.wechat.service.BasicService;
import com.tengmei.wechat.vo.QRCodeTicketResponse;
@Service
public class AccountServiceImpl implements AccountService {
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	BasicService basicService;
	@Override
	public QRCodeTicketResponse createQRCodeTicket(String scene) {
		String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={token}";
		Map<String,Object> input=new HashMap<>();
		input.put("action_name", "QR_LIMIT_SCENE");
		Map<String,Object> sceneMap=new HashMap<>();
		Map<String,Object> sceneData=new HashMap<>();
		sceneData.put("scene_str", scene);
		sceneMap.put("scene", sceneData);
		input.put("action_info", sceneMap);
		String content=restTemplate.postForObject(url, input, String.class, basicService.getAccessToken().getAccessToken());
		logger.debug(content);
		QRCodeTicketResponse response=restTemplate.postForObject(url, input, QRCodeTicketResponse.class, basicService.getAccessToken().getAccessToken());
		logger.debug(response.getTicket());
		logger.debug(response.getUrl());
		logger.debug(response.getExpireSeconds().toString());
		return response;
	}
	@Override
	public String getQRcodeUrl(String ticket){
		String url="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
		return url;
	}
}

package com.tengmei.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tengmei.wechat.service.BasicService;
import com.tengmei.wechat.service.UserService;
import com.tengmei.wechat.vo.SubscriberList;
import com.tengmei.wechat.vo.UserInfo;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	BasicService basicService;

	public UserServiceImpl() {
		super();
	}

	public UserServiceImpl(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	private String accessToken;

	@Override
	public UserInfo getUserInfo(String openid, String lang) {
		if (lang == null) {
			lang = "zh-CN";
		}
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={accessToken}&openid={openid}&lang={lang}";
		String content = restTemplate.getForObject(url, String.class, accessToken, openid, lang);
		logger.debug(content);
		UserInfo userInfo = restTemplate.getForObject(url, UserInfo.class, accessToken, openid, lang);
		return userInfo;
	}

	public SubscriberList getSubscribers(String nextOpenid) {
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token={accessToken}&next_openid={nextOpenid}";
		String response = restTemplate.getForObject(url, String.class, accessToken, nextOpenid);
		logger.debug(response);

		SubscriberList subscriberList = restTemplate.getForObject(url, SubscriberList.class,
				basicService.getAccessToken().getAccessToken(), nextOpenid);
		logger.debug(subscriberList.getTotal() + "");
		logger.debug(subscriberList.getCount() + "");
		logger.debug(subscriberList.getOpenIDList().toString());
		logger.debug(subscriberList.getNextOpenid());

		return subscriberList;
	}

}

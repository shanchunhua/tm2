package com.tengmei.trade.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.repository.WechatUserRepository;
import com.tengmei.trade.service.WechatUserService;

@Service
@Transactional
public class WechatUserServiceImpl implements WechatUserService {

	@Autowired
	WechatUserRepository wechatUserRepository;

	@Override
	public void create(WechatUser entity) {
		wechatUserRepository.save(entity);
	}

	@Override
	public WechatUser findByOpenid(String openid) {
		return wechatUserRepository.findByOpenid(openid);
	}

}
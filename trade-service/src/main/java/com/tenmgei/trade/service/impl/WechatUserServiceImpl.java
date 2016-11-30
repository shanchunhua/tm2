package com.tenmgei.trade.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenmgei.trade.domain.WechatUser;
import com.tenmgei.trade.repository.WechatUserRepository;
import com.tenmgei.trade.service.WechatUserService;

@Service
@Transactional
public class WechatUserServiceImpl implements WechatUserService {

	@Autowired
	WechatUserRepository wechatUserRepository;

	@Override
	public void create(WechatUser entity) {
		wechatUserRepository.save(entity);
	}

}

package com.tengmei.trade.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		entity.getWallet().setUser(entity);
		if (entity.getUnionid() == null) {
			entity.setUnionid(System.nanoTime() + "");
		}
		wechatUserRepository.save(entity);
	}

	@Override
	public WechatUser findByOpenid(String openid) {
		return wechatUserRepository.findByOpenid(openid);
	}

	@Override
	public WechatUser findById(Long id) {
		return wechatUserRepository.findOne(id);
	}

	@Override
	public Page<WechatUser> findUserRelations(WechatUser user, Pageable pageable) {
		return wechatUserRepository.findByParent(user, pageable);
	}

}

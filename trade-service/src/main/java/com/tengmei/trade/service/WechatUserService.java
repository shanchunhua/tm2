package com.tengmei.trade.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.WechatUser;

@Repository
public interface WechatUserService {
	/**
	 * 创建微信用户
	 * @param entity
	 */
	public void create(WechatUser entity);
	
	public WechatUser findByOpenid(String openid);

	public WechatUser findById(Long id);

	Page<WechatUser> findUserRelations(WechatUser user,Pageable pageable);
}

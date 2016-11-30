package com.tenmgei.trade.service;

import org.springframework.stereotype.Repository;

import com.tenmgei.trade.domain.WechatUser;

@Repository
public interface WechatUserService {
	/**
	 * 创建微信用户
	 * @param entity
	 */
	public void create(WechatUser entity);
}

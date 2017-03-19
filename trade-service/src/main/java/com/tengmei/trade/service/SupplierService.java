package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.WechatUser;

public interface SupplierService {
	/**
	 * 创建发品商
	 * 
	 * @param model
	 */
	public void create(Supplier model);

	/**
	 * 审核通过发品商
	 * 
	 * @param id
	 */
	public void certificate(Long id);
	/**
	 * 根据当前微信用户加载发品商信息
	 * @param user
	 * @return
	 */
	public Supplier findSupplier(WechatUser user);

	public List<Supplier> findAll();
}

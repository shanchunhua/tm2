package com.tenmgei.trade.service;

import com.tenmgei.trade.domain.Supplier;
import com.tenmgei.trade.domain.WechatUser;

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
}
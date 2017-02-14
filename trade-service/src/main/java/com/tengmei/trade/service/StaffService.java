package com.tengmei.trade.service;

import com.tengmei.trade.domain.Store;

public interface StaffService {
	/**
	 * 统计店铺的总员工数量
	 * 
	 * @param store
	 * @return
	 */
	public int countByStore(Store store);
}

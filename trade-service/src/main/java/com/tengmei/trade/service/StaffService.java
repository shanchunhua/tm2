package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.Staff;
import com.tengmei.trade.domain.Store;

public interface StaffService {
	/**
	 * 统计店铺的总员工数量
	 * 
	 * @param store
	 * @return
	 */
	public int countByStore(Store store);

	public Staff create(Staff staff);

	public List<Staff> findByStore(Store store);

	public Staff findById(Long id);

	public void delete(Long id);
}

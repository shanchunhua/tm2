package com.tengmei.trade.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tengmei.trade.domain.CustomerOrder;
import com.tengmei.trade.domain.WechatUser;

public interface CustomerOrderService {
	public CustomerOrder create(CustomerOrder customerOrder);

	public CustomerOrder findById(Long id);
	
	public CustomerOrder pay(CustomerOrder order);

	public Page<CustomerOrder> findByUser(WechatUser user,Pageable pageable);

	public Page<CustomerOrder> findByStaff(WechatUser user, Pageable pageable);
}

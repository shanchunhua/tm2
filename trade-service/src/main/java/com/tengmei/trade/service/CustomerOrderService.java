package com.tengmei.trade.service;

import com.tengmei.trade.domain.CustomerOrder;

public interface CustomerOrderService {
	public CustomerOrder create(CustomerOrder customerOrder);

	public CustomerOrder findById(Long id);
	
	public CustomerOrder pay(CustomerOrder order);
}

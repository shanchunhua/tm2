package com.tengmei.trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.CustomerOrder;
import com.tengmei.trade.repository.CustomerOrderRepository;
import com.tengmei.trade.service.CustomerOrderService;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {
	@Autowired
	CustomerOrderRepository customerOrderRepository;

	@Override
	public CustomerOrder create(CustomerOrder customerOrder) {
		// if (customerOrder.getPtype() == 1) {
		// return createServiceOrder(customerOrder);
		// } else if (customerOrder.getPtype() == 2) {
		// return createStoreProductOrder(customerOrder);
		// } else if (customerOrder.getPtype() == 3) {
		// return createStoreCardOrder(customerOrder);
		// } else if (customerOrder.getPtype() == 4) {
		// return createPlatformProductOrder(customerOrder);
		// }
		return customerOrderRepository.save(customerOrder);
	}

	public CustomerOrder pay(CustomerOrder order) {

		return null;
	}
	// private CustomerOrder createPlatformProductOrder(CustomerOrder
	// customerOrder) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// private CustomerOrder createStoreCardOrder(CustomerOrder customerOrder) {
	// if(customerOrder.getTimesCard()){
	//
	// }
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// private CustomerOrder createStoreProductOrder(CustomerOrder
	// customerOrder) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// private CustomerOrder createServiceOrder(CustomerOrder customerOrder) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public CustomerOrder findById(Long id) {
		return customerOrderRepository.findOne(id);
	}

}

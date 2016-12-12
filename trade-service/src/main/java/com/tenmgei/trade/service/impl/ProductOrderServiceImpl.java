package com.tenmgei.trade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenmgei.trade.domain.LogisticsStatus;
import com.tenmgei.trade.domain.PaymentStatus;
import com.tenmgei.trade.domain.ProductOrder;
import com.tenmgei.trade.domain.Store;
import com.tenmgei.trade.domain.Supplier;
import com.tenmgei.trade.repository.ProductOrderRepository;
import com.tenmgei.trade.service.ProductOrderService;

@Service
@Transactional
public class ProductOrderServiceImpl implements ProductOrderService {

	@Autowired
	private ProductOrderRepository productOrderRepository;

	@Override
	public void create(ProductOrder order) {
		productOrderRepository.save(order);
	}

	@Override
	public void payOrder(ProductOrder order) {
		order = productOrderRepository.findOne(order.getId());
		if (order.getPaymentStatus() == PaymentStatus.NOT_PAID) {
			order.setPaymentStatus(PaymentStatus.PAID);
			productOrderRepository.save(order);
		}
	}

	@Override
	public void fulfillOrder(ProductOrder order) {
		order = productOrderRepository.findOne(order.getId());
		if (order.getLogisticsStatus() == LogisticsStatus.UNFULFILLED) {
			order.setLogisticsStatus(LogisticsStatus.FULFILLED);
			productOrderRepository.save(order);
		}
	}

	@Override
	public List<ProductOrder> findOrderByStore(Store store) {
		return productOrderRepository.findByStore(store);
	}

	@Override
	public List<ProductOrder> findOrderBySupplier(Supplier supplier) {
		return productOrderRepository.findByProduct_Supplier(supplier);
	}

}

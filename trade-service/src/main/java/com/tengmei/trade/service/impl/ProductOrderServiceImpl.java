package com.tengmei.trade.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.bo.OrderSummaryBySupplier;
import com.tengmei.trade.domain.LogisticsStatus;
import com.tengmei.trade.domain.PaymentStatus;
import com.tengmei.trade.domain.ProductOrder;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.repository.ProductOrderRepository;
import com.tengmei.trade.repository.ProductRepository;
import com.tengmei.trade.repository.StoreRepository;
import com.tengmei.trade.service.ProductOrderService;

@Service
@Transactional
public class ProductOrderServiceImpl implements ProductOrderService {

	@Autowired
	private ProductOrderRepository productOrderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StoreRepository storeRepository;

	@Override
	public void create(ProductOrder order) {
		order.setProduct(productRepository.findOne(order.getProduct().getId()));
		order.setStore(storeRepository.findOne(order.getStore().getId()));
		order.setPrice(order.getProduct().getPrice());
		order.setExperienceMoneyRate(order.getProduct().getCatalog().getExperienceMoneyRate());
		order.setTotal(order.getPrice().multiply(new BigDecimal(order.getQuantity())));
		order.setExperienceMoney(order.getExperienceMoneyRate().multiply(order.getPrice())
				.multiply(new BigDecimal(order.getQuantity())));
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

	@Override
	public List<OrderSummaryBySupplier> getOrderSummaryBySuppliers(Collection<Supplier> suppliers) {
		List<OrderSummaryBySupplier> summaries = new ArrayList<>();
		for (Supplier supplier : suppliers) {
			OrderSummaryBySupplier summary = new OrderSummaryBySupplier();
			int count = productOrderRepository.countByProduct_SupplierAndPaymentStatus(supplier, PaymentStatus.PAID);
			summary.setOrderCount(count);
			summary.setSupplier(supplier);
//			summary.setTotalAmount(productOrderRepository.totalBySupplier(supplier)[0]);
			// summary.setTotalExperienceMoney(totalExperienceMoney);
			summaries.add(summary);
		}
		return summaries;
	}

}

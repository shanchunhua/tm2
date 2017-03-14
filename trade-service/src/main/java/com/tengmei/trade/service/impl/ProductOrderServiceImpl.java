package com.tengmei.trade.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.bo.OrderSummaryByStore;
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

	/**
	 * 订单创建
	 */
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

	/**
	 * 订单支付
	 */
	@Override
	public void payOrder(ProductOrder order) {
		order = productOrderRepository.findOne(order.getId());
		if (order.getPaymentStatus() == PaymentStatus.NOT_PAID) {
			order.setPaymentStatus(PaymentStatus.PAID);
			productOrderRepository.save(order);
		}
	}

	/**
	 * 订单发货
	 */
	@Override
	public void fulfillOrder(ProductOrder order) {
		order = productOrderRepository.findOne(order.getId());
		if (order.getLogisticsStatus() == LogisticsStatus.UNFULFILLED) {
			order.setLogisticsStatus(LogisticsStatus.FULFILLED);
			productOrderRepository.save(order);
		}
	}

	/**
	 * 查询店铺的订单
	 */
	@Override
	public List<ProductOrder> findOrderByStore(Store store) {
		return productOrderRepository.findByStore(store);
	}

	/**
	 * 查询供应商的订单
	 */
	@Override
	public List<ProductOrder> findOrderBySupplier(Supplier supplier) {
		return productOrderRepository.findByProduct_Supplier(supplier);
	}

	/**
	 * 对不同供应商统计他们的订单金额和体验金
	 */
	@Override
	public List<OrderSummaryBySupplier> getOrderSummaryBySuppliers(Collection<Supplier> suppliers) {
		List<OrderSummaryBySupplier> summaries = new ArrayList<>();
		for (Supplier supplier : suppliers) {
			OrderSummaryBySupplier summary = new OrderSummaryBySupplier();
			int count = productOrderRepository.countByProduct_SupplierAndPaymentStatus(supplier, PaymentStatus.PAID);
			summary.setOrderCount(count);
			summary.setSupplier(supplier);
			Object[] totalAndExperienceMoney = (Object[]) productOrderRepository.totalBySupplier(supplier);
			summary.setTotalAmount((BigDecimal) totalAndExperienceMoney[0]);
			summary.setTotalExperienceMoney((BigDecimal) totalAndExperienceMoney[1]);
			summaries.add(summary);
		}
		return summaries;
	}

	/**
	 * 查询某个店铺的总销售金额
	 */
	@Override
	public BigDecimal getTotalOrderAmountByStore(Store store) {
		Object[] totalAndExperienceMoney = (Object[]) productOrderRepository.totalByStore(store);
		return (BigDecimal) totalAndExperienceMoney[0];
	}

	/**
	 * 查询某个店铺的总订单
	 */
	@Override
	public Integer getTotalOrderCountByStore(Store store) {
		return productOrderRepository.countByStoreAndPaymentStatus(store, PaymentStatus.PAID);
	}

	/**
	 * 查询某个供应商的总订单
	 */
	@Override
	public Integer getTotalOrderCountBySupplier(Supplier supplier) {
		return productOrderRepository.countByProduct_SupplierAndPaymentStatus(supplier, PaymentStatus.PAID);
	}

	/**
	 * 查询某个供应商的总销售金额
	 */
	@Override
	public BigDecimal getTotalOrderAmountBySupplier(Supplier supplier) {
		Object[] totalAmountAndExpMoney = (Object[]) productOrderRepository.totalBySupplier(supplier);
		return (BigDecimal) totalAmountAndExpMoney[0];
	}

	@Override
	public List<OrderSummaryByStore> getOrderSummaryByStores(List<Store> stores) {
		List<OrderSummaryByStore> summaries = new ArrayList<>();
		for (Store store : stores) {
			OrderSummaryByStore summary = new OrderSummaryByStore();
			int count = productOrderRepository.countByStoreAndPaymentStatus(store, PaymentStatus.PAID);
			summary.setOrderCount(count);
			summary.setStore(store);
			Object[] totalAndExperienceMoney = (Object[]) productOrderRepository.totalByStore(store);
			summary.setTotalAmount((BigDecimal) totalAndExperienceMoney[0]);
			summary.setTotalExperienceMoney((BigDecimal) totalAndExperienceMoney[1]);
			summaries.add(summary);
		}
		return summaries;
	}

	@Override
	public ProductOrder findById(Long id) {
		return productOrderRepository.findOne(id);
	}
}

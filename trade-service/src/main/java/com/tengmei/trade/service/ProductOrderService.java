package com.tengmei.trade.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.tengmei.trade.bo.OrderSummaryByStore;
import com.tengmei.trade.bo.OrderSummaryBySupplier;
import com.tengmei.trade.domain.ProductOrder;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;

public interface ProductOrderService {
	/**
	 * 创建订单
	 * 
	 * @param order
	 */
	public void create(ProductOrder order);

	/**
	 * 支付订单
	 * 
	 * @param order
	 */
	public void payOrder(ProductOrder order);

	/**
	 * 确认送货
	 * 
	 * @param order
	 */
	public void fulfillOrder(ProductOrder order);

	/**
	 * 查询某个店铺的订单
	 * 
	 * @param store
	 * @return
	 */
	public List<ProductOrder> findOrderByStore(Store store);

	public List<ProductOrder> findOrderBySupplier(Supplier supplier);

	public List<OrderSummaryBySupplier> getOrderSummaryBySuppliers(Collection<Supplier> suppliers);
	
	public BigDecimal getTotalOrderAmountByStore(Store store);
	
	public Integer getTotalOrderCountByStore(Store store);

	Integer getTotalOrderCountBySupplier(Supplier supplier);

	BigDecimal getTotalOrderAmountBySupplier(Supplier supplier);

	public List<OrderSummaryByStore> getOrderSummaryByStores(Set<Store> stores);
}

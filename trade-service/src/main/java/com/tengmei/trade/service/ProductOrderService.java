package com.tengmei.trade.service;

import java.util.List;

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
}

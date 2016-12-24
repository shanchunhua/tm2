package com.tengmei.trade.rest.vo;

import java.math.BigDecimal;

import com.tengmei.trade.domain.Store;

public class StoreSummary {
	private Store store;
	private Integer orderCount;
	private Integer supplierCount;
	private BigDecimal totalAmount;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getSupplierCount() {
		return supplierCount;
	}

	public void setSupplierCount(Integer supplierCount) {
		this.supplierCount = supplierCount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}

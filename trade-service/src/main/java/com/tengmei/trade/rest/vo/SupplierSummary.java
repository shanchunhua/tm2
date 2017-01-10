package com.tengmei.trade.rest.vo;

import java.math.BigDecimal;

import com.tengmei.trade.domain.Supplier;

public class SupplierSummary {
	private Supplier supplier;
	private Integer orderCount;
	private Integer storeCount;
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public Integer getStoreCount() {
		return storeCount;
	}
	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	private BigDecimal totalAmount;

}

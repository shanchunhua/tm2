package com.tengmei.trade.bo;

import java.math.BigDecimal;

import com.tengmei.trade.domain.Supplier;

public class OrderSummaryBySupplier {
	private Supplier supplier;
	private Integer orderCount;
	public BigDecimal totalAmount;
	private BigDecimal totalExperienceMoney;
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
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getTotalExperienceMoney() {
		return totalExperienceMoney;
	}
	public void setTotalExperienceMoney(BigDecimal totalExperienceMoney) {
		this.totalExperienceMoney = totalExperienceMoney;
	}
}

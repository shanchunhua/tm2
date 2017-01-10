package com.tengmei.trade.bo;

import java.math.BigDecimal;

import com.tengmei.trade.domain.Store;

public class OrderSummaryByStore {
	private Store store;
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
	private Integer orderCount;
	public BigDecimal totalAmount;
	private BigDecimal totalExperienceMoney;
}

package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

/**
 * 提现请求表
 * 
 * @author sam
 *
 */
@Entity
public class WithdrawRequest extends BaseEntity {
	private BigDecimal amount;

	private WithdrawRequestStatus status = WithdrawRequestStatus.NEW;
	
	private Supplier supplier;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public WithdrawRequestStatus getStatus() {
		return status;
	}

	public void setStatus(WithdrawRequestStatus status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
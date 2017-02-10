package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	@ManyToOne
	@JoinColumn(name = "suppiler_id")
	private Supplier supplier;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private WechatUser user;

	public WechatUser getUser() {
		return user;
	}

	public void setUser(WechatUser user) {
		this.user = user;
	}

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

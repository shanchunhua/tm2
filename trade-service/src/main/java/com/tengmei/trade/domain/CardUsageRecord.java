package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 划卡记录
 * 
 * @author sam
 *
 */
@Entity
public class CardUsageRecord extends BaseEntity {
	// 项目名称
	private String name;

	// 划卡扣减金额
	private BigDecimal deduction;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private CustomerOrder order;

	@ManyToOne
	@JoinColumn(name = "discount_card_id")
	private UserDiscountCard userDiscountCard;
	@ManyToOne
	@JoinColumn(name = "times_card_id")
	private UserTimesCard userTimesCard;
	// 折扣率
	private BigDecimal discount;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private WechatUser user;

	public BigDecimal getDeduction() {
		return deduction;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public String getName() {
		return name;
	}

	public CustomerOrder getOrder() {
		return order;
	}

	public UserDiscountCard getUserDiscountCard() {
		return userDiscountCard;
	}

	public UserTimesCard getUserTimesCard() {
		return userTimesCard;
	}

	public void setDeduction(BigDecimal deduction) {
		this.deduction = deduction;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrder(CustomerOrder order) {
		this.order = order;
	}

	public void setUserDiscountCard(UserDiscountCard userDiscountCard) {
		this.userDiscountCard = userDiscountCard;
	}

	public void setUserTimesCard(UserTimesCard userTimesCard) {
		this.userTimesCard = userTimesCard;
	}

	public WechatUser getUser() {
		return user;
	}

	public void setUser(WechatUser user) {
		this.user = user;
	}

}

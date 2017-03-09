package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserWallet extends CommonEntity {
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	// 订单数
	private Integer orderCount = 0;
	// 订单总金额
	private BigDecimal orderTotal = new BigDecimal(0);
	// 人脉数
	private Integer friendCount = 0;
	// 佣金
	private BigDecimal commission = new BigDecimal(0);
	// 体验金
	private BigDecimal experienceMoney = new BigDecimal(0);
	// 卡数量
	private Integer cardCount = 0;
	
	@JoinColumn(name = "ID")
	@OneToOne
	@MapsId
	@JsonIgnore
	private WechatUser user;

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getFriendCount() {
		return friendCount;
	}

	public void setFriendCount(Integer friendCount) {
		this.friendCount = friendCount;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getExperienceMoney() {
		return experienceMoney;
	}

	public void setExperienceMoney(BigDecimal experienceMoney) {
		this.experienceMoney = experienceMoney;
	}

	public Integer getCardCount() {
		return cardCount;
	}

	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public WechatUser getUser() {
		return user;
	}

	public void setUser(WechatUser user) {
		this.user = user;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

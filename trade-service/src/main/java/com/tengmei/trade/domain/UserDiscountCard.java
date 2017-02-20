package com.tengmei.trade.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class UserDiscountCard extends BaseEntity {
	@Column(precision = 10, scale = 2)
	private BigDecimal leftAmount;

	public BigDecimal getLeftAmount() {
		return leftAmount;
	}

	public void setLeftAmount(BigDecimal leftAmount) {
		this.leftAmount = leftAmount;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public CatalogDiscountCard getCard() {
		return card;
	}

	public void setCard(CatalogDiscountCard card) {
		this.card = card;
	}

	public WechatUser getUser() {
		return user;
	}

	public void setUser(WechatUser user) {
		this.user = user;
	}

	private Date validDate;

	@ManyToOne
	@JoinColumn(name = "card_id")
	private CatalogDiscountCard card;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private WechatUser user;
}

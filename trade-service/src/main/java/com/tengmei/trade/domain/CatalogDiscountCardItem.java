package com.tengmei.trade.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CatalogDiscountCardItem extends BaseEntity {

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "card_id")
	CatalogDiscountCard card;
	@ManyToOne
	@JoinColumn(name = "catalog_id")
	ServiceCatalog catalog;

	@Column(precision = 10, scale = 2)
	private BigDecimal discount;

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public CatalogDiscountCard getCard() {
		return card;
	}

	public void setCard(CatalogDiscountCard card) {
		this.card = card;
	}

	public ServiceCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(ServiceCatalog catalog) {
		this.catalog = catalog;
	}
}

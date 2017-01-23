package com.tengmei.trade.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CatalogDiscountCardItem extends BaseEntity {

	@ManyToOne
	@JoinColumn(name="card_id")
	CatalogDiscountCard card;
	@ManyToOne
	@JoinColumn(name="catalog_id")
	ServiceCatalog catalog;
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

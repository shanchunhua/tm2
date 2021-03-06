package com.tengmei.trade.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CatalogDiscountCard extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	private String name;
	@Column(precision = 10, scale = 2)
	private BigDecimal price;

	private Boolean deleted;

	private Date validDate;

	@OneToMany(mappedBy="card",cascade=CascadeType.ALL)
	private List<CatalogDiscountCardItem> items;
	public List<CatalogDiscountCardItem> getItems() {
		return items;
	}

	public void setItems(List<CatalogDiscountCardItem> items) {
		this.items = items;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}

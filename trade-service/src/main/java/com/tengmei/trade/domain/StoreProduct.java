package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 店铺的外卖产品
 * 
 * @author sam
 *
 */
@Entity
public class StoreProduct extends BaseEntity {
	private String name;
	private String image;
	@Column(precision = 10, scale = 2)
	private BigDecimal price;
	@Column(precision = 10, scale = 2)
	private BigDecimal commissionRate;
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	private boolean deleted = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}

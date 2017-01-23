package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class TimesCard extends BaseEntity {
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	private String name;
	@Column(precision = 10, scale = 2)
	private BigDecimal price;
	private Boolean deleted;
	
	private Integer times;
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public Store getStore() {
		return store;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public void setStore(Store store) {
		this.store = store;
	}
}

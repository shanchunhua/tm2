package com.tengmei.trade.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TimesCard extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;
	private String name;
	@Column(precision = 10, scale = 2)
	private BigDecimal price;
	private Boolean deleted=false;

	private Integer times;

	private Date validDate;
	
	//
	@ManyToOne
	@JoinColumn(name="service_id")
	private Service service;
	

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}


	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

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

package com.tenmgei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class ProductCatalog extends BaseEntity {
	private String name;
	/**
	 * 体验金比例
	 */
	@Column(precision = 5, scale = 2)
	private BigDecimal experienceMoneyRate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getExperienceMoneyRate() {
		return experienceMoneyRate;
	}

	public void setExperienceMoneyRate(BigDecimal experienceMoneyRate) {
		this.experienceMoneyRate = experienceMoneyRate;
	}
}

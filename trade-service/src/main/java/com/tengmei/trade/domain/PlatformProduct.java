package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

/**
 * 平台产品
 * 
 * @author sam
 *
 */
@Entity
public class PlatformProduct extends BaseEntity {
	private String name;
	private BigDecimal price;
	private String image;
	private String description;
	private BigDecimal experienceMoneyRate;
	private BigDecimal bossCommissionRate;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getExperienceMoneyRate() {
		return experienceMoneyRate;
	}
	public void setExperienceMoneyRate(BigDecimal experienceMoneyRate) {
		this.experienceMoneyRate = experienceMoneyRate;
	}
	public BigDecimal getBossCommissionRate() {
		return bossCommissionRate;
	}
	public void setBossCommissionRate(BigDecimal bossCommissionRate) {
		this.bossCommissionRate = bossCommissionRate;
	}
	public BigDecimal getStaffCommissionRate() {
		return staffCommissionRate;
	}
	public void setStaffCommissionRate(BigDecimal staffCommissionRate) {
		this.staffCommissionRate = staffCommissionRate;
	}
	private BigDecimal staffCommissionRate;
}

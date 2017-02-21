package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Service extends BaseEntity {
	private String name;
	private String image;
	@ManyToOne
	@JoinColumn(name="catalog_id")
	private ServiceCatalog catalog;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal levelOnePrice;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal levelThreePrice;
	@Column(precision = 10, scale = 2)
	private BigDecimal levelTwoPrice;
	
	//1級分销佣金比例
	@Column(precision = 5, scale = 2)
	private BigDecimal commissionRate1;
	//2級分销佣金比例
	@Column(precision = 5, scale = 2)
	private BigDecimal commissionRate2;
	//3級分销佣金比例
	@Column(precision = 5, scale = 2)
	private BigDecimal commissionRate3;
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;

	private Boolean deleted=false;	
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

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

	public ServiceCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(ServiceCatalog catalog) {
		this.catalog = catalog;
	}

	public BigDecimal getLevelOnePrice() {
		return levelOnePrice;
	}

	public void setLevelOnePrice(BigDecimal levelOnePrice) {
		this.levelOnePrice = levelOnePrice;
	}

	public BigDecimal getLevelThreePrice() {
		return levelThreePrice;
	}

	public void setLevelThreePrice(BigDecimal levelThreePrice) {
		this.levelThreePrice = levelThreePrice;
	}

	public BigDecimal getLevelTwoPrice() {
		return levelTwoPrice;
	}

	public void setLevelTwoPrice(BigDecimal levelTwoPrice) {
		this.levelTwoPrice = levelTwoPrice;
	}


	public BigDecimal getCommissionRate1() {
		return commissionRate1;
	}

	public void setCommissionRate1(BigDecimal commissionRate1) {
		this.commissionRate1 = commissionRate1;
	}

	public BigDecimal getCommissionRate2() {
		return commissionRate2;
	}

	public void setCommissionRate2(BigDecimal commissionRate2) {
		this.commissionRate2 = commissionRate2;
	}

	public BigDecimal getCommissionRate3() {
		return commissionRate3;
	}

	public void setCommissionRate3(BigDecimal commissionRate3) {
		this.commissionRate3 = commissionRate3;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	
}

package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 店铺产品类别体验金比例设置
 * 
 * @author sam
 *
 */
@Entity
public class StoreCatalogExperienceMoneyRateSetting extends BaseEntity {

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}


	public ServiceCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(ServiceCatalog catalog) {
		this.catalog = catalog;
	}

	public BigDecimal getExperienceMoneyRate() {
		return experienceMoneyRate;
	}

	public void setExperienceMoneyRate(BigDecimal experienceMoneyRate) {
		this.experienceMoneyRate = experienceMoneyRate;
	}

	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	@ManyToOne
	@JoinColumn(name = "catalog_id")
	private ServiceCatalog catalog;

	@Column(precision = 5, scale = 2)
	private BigDecimal experienceMoneyRate;
}

package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

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

	public ServiceCatalog getServiceCatalog() {
		return serviceCatalog;
	}

	public void setServiceCatalog(ServiceCatalog serviceCatalog) {
		this.serviceCatalog = serviceCatalog;
	}

	public BigDecimal getExperienceMoneyRate() {
		return experienceMoneyRate;
	}

	public void setExperienceMoneyRate(BigDecimal experienceMoneyRate) {
		this.experienceMoneyRate = experienceMoneyRate;
	}

	private Store store;

	private ServiceCatalog serviceCatalog;

	@Column(precision = 5, scale = 2)
	private BigDecimal experienceMoneyRate;
}

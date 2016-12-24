package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Product extends BaseEntity {
	private String name;
	@Column(precision = 10, scale = 2)
	private BigDecimal price;
	@ManyToOne
	@JoinColumn(name = "catalog_id")
	private ProductCatalog catalog;
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

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

	public ProductCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(ProductCatalog catalog) {
		this.catalog = catalog;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}

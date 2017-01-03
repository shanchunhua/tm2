package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Product extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "catalog_id")
	private ProductCatalog catalog;
	private String image;
	private String name;
	@Column(precision = 10, scale = 2)
	private BigDecimal price;
	
	private Integer soldCount=0;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	public ProductCatalog getCatalog() {
		return catalog;
	}

	public String getImage() {
		return image;
	}

	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}

	public Integer getSoldCount() {
		return soldCount;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setCatalog(ProductCatalog catalog) {
		this.catalog = catalog;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setSoldCount(Integer soldCount) {
		this.soldCount = soldCount;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}

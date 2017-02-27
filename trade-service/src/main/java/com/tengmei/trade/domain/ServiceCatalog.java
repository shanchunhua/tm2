package com.tengmei.trade.domain;

import javax.persistence.Entity;

@Entity
public class ServiceCatalog extends BaseEntity {

	private String name;

	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
}

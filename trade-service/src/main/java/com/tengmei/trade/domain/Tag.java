package com.tengmei.trade.domain;

import javax.persistence.Entity;

@Entity
public class Tag extends BaseEntity {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

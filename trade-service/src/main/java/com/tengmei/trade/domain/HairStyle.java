package com.tengmei.trade.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class HairStyle extends BaseEntity {
	private String image;
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	
	private LengthType length;
	
	private GenderType gender;
	
	private StyleType style;
	
	private Boolean deleted=false;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public LengthType getLength() {
		return length;
	}

	public void setLength(LengthType length) {
		this.length = length;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public StyleType getStyle() {
		return style;
	}

	public void setStyle(StyleType style) {
		this.style = style;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}

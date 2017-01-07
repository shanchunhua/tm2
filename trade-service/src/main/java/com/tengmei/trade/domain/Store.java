package com.tengmei.trade.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * 店铺实体类
 *
 */
@Entity

public class Store extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public Store() {
		super();
	}

	private String name;

	private String region;

	private String address;
	private String tel;

	private String contact;

	private String cellphone;
	
	private String banner1;
	private String banner2;
	private String banner3;
	
	public String getBanner1() {
		return banner1;
	}

	public void setBanner1(String banner1) {
		this.banner1 = banner1;
	}

	public String getBanner2() {
		return banner2;
	}

	public void setBanner2(String banner2) {
		this.banner2 = banner2;
	}

	public String getBanner3() {
		return banner3;
	}

	public void setBanner3(String banner3) {
		this.banner3 = banner3;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	private WechatUser user;
	@ManyToMany(targetEntity = Supplier.class)
	@JoinTable(name = "store_supplier", joinColumns = { @JoinColumn(name = "store_id") }, inverseJoinColumns = {
			@JoinColumn(name = "supplier_id") })
	private Set<Supplier> suppliers = new HashSet<Supplier>();

	public WechatUser getUser() {
		return user;
	}

	public void setUser(WechatUser user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}



	public Set<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Set<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

}

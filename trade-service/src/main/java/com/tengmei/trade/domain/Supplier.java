package com.tengmei.trade.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 发品商实体类
 *
 */
@Entity

public class Supplier extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public Supplier() {
		super();
	}

	private String name;

	private String region;

	private String address;

	/**
	 * 前台电话
	 */
	private String tel;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系人手机
	 */
	private String cellphone;
	private SupplierStatus status = SupplierStatus.UNCERTFIED;
	
	private String banner1;
	private String banner2;
	private String banner3;
	
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "supplier")
//	@Transient
	private SupplierWallet wallet = new SupplierWallet();
	
	@Transient
	private Integer productCount;
	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public SupplierWallet getWallet() {
		return wallet;
	}

	public void setWallet(SupplierWallet wallet) {
		this.wallet = wallet;
	}

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
	@JsonIgnore
	private WechatUser user;

	public WechatUser getUser() {
		return user;
	}

	public void setUser(WechatUser user) {
		this.user = user;
	}

	public String getName() {
		return name;
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

	public SupplierStatus getStatus() {
		return status;
	}

	public void setStatus(SupplierStatus status) {
		this.status = status;
	}

}

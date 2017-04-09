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

	private String address;

	private String banner1;

	private String banner2;

	private String banner3;

	/**
	 * 联系人手机
	 */
	private String cellphone;
	/**
	 * 联系人
	 */
	private String contact;
	private String name;
	@Transient
	private Integer productCount;
	
	private String region;
	private SupplierStatus status = SupplierStatus.UNCERTFIED;
	/**
	 * 前台电话
	 */
	private String tel;
	
	//供应商对应微信用户
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private WechatUser user;
	
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "supplier")
//	@Transient
	private SupplierWallet wallet = new SupplierWallet();
	public Supplier() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public String getBanner1() {
		return banner1;
	}

	public String getBanner2() {
		return banner2;
	}

	public String getBanner3() {
		return banner3;
	}

	public String getCellphone() {
		return cellphone;
	}

	public String getContact() {
		return contact;
	}

	public String getName() {
		return name;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public String getRegion() {
		return region;
	}

	public SupplierStatus getStatus() {
		return status;
	}

	public String getTel() {
		return tel;
	}

	public WechatUser getUser() {
		return user;
	}

	public SupplierWallet getWallet() {
		return wallet;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBanner1(String banner1) {
		this.banner1 = banner1;
	}

	public void setBanner2(String banner2) {
		this.banner2 = banner2;
	}

	public void setBanner3(String banner3) {
		this.banner3 = banner3;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setStatus(SupplierStatus status) {
		this.status = status;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setUser(WechatUser user) {
		this.user = user;
	}

	public void setWallet(SupplierWallet wallet) {
		this.wallet = wallet;
	}

}

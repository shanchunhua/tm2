package com.tenmgei.trade.domain;

import java.io.Serializable;
import javax.persistence.*;

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

	@ManyToOne
	@JoinColumn(name = "user_id")
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

	private SupplierStatus status = SupplierStatus.UNCERTFIED;
}

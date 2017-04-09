package com.tengmei.trade.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 店铺实体类
 *
 */
@Entity

public class Store extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String accessToken;

	private String address;

	private String appid;

	private String banner1;
	private String banner2;

	private String banner3;

	private String cellphone;

	@ManyToOne
	@JoinColumn(name = "chain_id")
	private Chain chain;
	private String contact;
	private String name;

	private String refreshToken;

	private String region;
	
	private String tel;
	//店长
	@ManyToOne
	@JoinColumn(name = "user_id")
	private WechatUser user;

	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "store")
	// @Transient
	private StoreWallet wallet = new StoreWallet();

	public Store() {
		super();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getAddress() {
		return address;
	}

	public String getAppid() {
		return appid;
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

	public Chain getChain() {
		return chain;
	}

	public String getContact() {
		return contact;
	}

	public String getName() {
		return name;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public String getRegion() {
		return region;
	}

	public String getTel() {
		return tel;
	}

	public WechatUser getUser() {
		return user;
	}

	public StoreWallet getWallet() {
		return wallet;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAppid(String appid) {
		this.appid = appid;
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

	public void setChain(Chain chain) {
		this.chain = chain;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setUser(WechatUser user) {
		this.user = user;
	}

	public void setWallet(StoreWallet wallet) {
		this.wallet = wallet;
	}

}

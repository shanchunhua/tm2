package com.tengmei.trade.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tengmei.wechat.vo.UserInfo;

/**
 * Entity implementation class for Entity: WechatUser
 *
 */
@Entity

public class WechatUser extends BaseEntity implements Serializable {

	private String openid;
	private static final long serialVersionUID = 1L;
	private UserType type;
	private String no;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private WechatUser parent;

	@ManyToOne
	@JoinColumn(name = "store_id")
	@JsonIgnore
	private Store store;
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public WechatUser getParent() {
		return parent;
	}

	public void setParent(WechatUser parent) {
		this.parent = parent;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@Transient
	private UserInfo userInfo;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public WechatUser() {
		super();
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}

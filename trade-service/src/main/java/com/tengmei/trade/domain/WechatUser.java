package com.tengmei.trade.domain;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: WechatUser
 *
 */
@Entity

public class WechatUser extends BaseEntity implements Serializable {

	private String openid;
	private static final long serialVersionUID = 1L;

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

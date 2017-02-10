package com.tengmei.trade.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private WechatUser parent;
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

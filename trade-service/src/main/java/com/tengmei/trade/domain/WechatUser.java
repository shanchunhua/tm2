package com.tengmei.trade.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.beans.BeanUtils;

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

	private Integer subscribe;
	private String nickname;
	private Integer sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headimgurl;
	private Long subscribeTime;
	private String unionid;
	private String remark;
	private Integer groupid;
	
	private CustomerLevel customerLevel;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private WechatUser parent;

	@ManyToOne
	@JoinColumn(name = "store_id")
	@JsonIgnore
	private Store store;

	@OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user")
	private UserWallet wallet = new UserWallet();

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public UserWallet getWallet() {
		return wallet;
	}

	public void setWallet(UserWallet wallet) {
		this.wallet = wallet;
	}

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

//	@Transient
//	private UserInfo userInfo;
//
//	public UserInfo getUserInfo() {
//		return userInfo;
//	}
//
	public void setUserInfo(UserInfo userInfo) {
		BeanUtils.copyProperties(userInfo, this);
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

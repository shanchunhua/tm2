package com.tengmei.trade.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class UserTimesCard extends BaseEntity {
	private Integer times;

	private Date validDate;

	 
	@ManyToOne
	@JoinColumn(name = "card_id")
	private TimesCard timesCard;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private WechatUser user;

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public TimesCard getTimesCard() {
		return timesCard;
	}

	public void setTimesCard(TimesCard timesCard) {
		this.timesCard = timesCard;
	}

	public WechatUser getUser() {
		return user;
	}

	public void setUser(WechatUser user) {
		this.user = user;
	}

}

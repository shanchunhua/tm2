package com.tengmei.trade.domain;

@javax.persistence.Entity
public class Platform extends BaseEntity {

	private String componentVerifyTicket;

	private String authCode;
	
	private String componentAccessToken;
	public String getComponentAccessToken() {
		return componentAccessToken;
	}

	public void setComponentAccessToken(String componentAccessToken) {
		this.componentAccessToken = componentAccessToken;
	}

	/**
	 * 每天重置的计数器，用于发红包
	 */
	private Integer counter = 0;

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getComponentVerifyTicket() {
		return componentVerifyTicket;
	}

	public void setComponentVerifyTicket(String componentVerifyTicket) {
		this.componentVerifyTicket = componentVerifyTicket;
	}

}

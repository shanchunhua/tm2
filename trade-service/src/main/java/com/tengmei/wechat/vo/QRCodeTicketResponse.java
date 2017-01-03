package com.tengmei.wechat.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true) 
public class QRCodeTicketResponse {
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Long getExpireSeconds() {
		return expireSeconds;
	}
	public void setExpireSeconds(Long expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private String ticket;
	private Long expireSeconds;
	private String url;
}

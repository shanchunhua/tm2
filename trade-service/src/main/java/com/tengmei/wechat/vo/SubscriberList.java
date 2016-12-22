package com.tengmei.wechat.vo;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubscriberList {
	private int count;
	private Map<String, List<String>> data;
	private String nextOpenid;
	private int total;

	public int getCount() {
		return count;
	}

	public String getNextOpenid() {
		return nextOpenid;
	}

	public int getTotal() {
		return total;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}

	public void setNextOpenid(String nextOpenid) {
		this.nextOpenid = nextOpenid;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<String> getOpenIDList() {
		return this.data.get("openid");
	}
}

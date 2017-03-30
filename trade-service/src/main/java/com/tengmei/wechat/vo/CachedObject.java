package com.tengmei.wechat.vo;

public class CachedObject<T> {
	private T object;
	private Long timeToLive;
	private Long lastUpdate;

	public Long getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T token) {
		this.object = token;
	}

	public Long getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(Long timeToLive) {
		this.timeToLive = timeToLive;
	}

	public boolean isExpired() {
		// 提前5分钟过期
		return System.currentTimeMillis() - lastUpdate > (timeToLive * 1000) - 600000;
	}
}

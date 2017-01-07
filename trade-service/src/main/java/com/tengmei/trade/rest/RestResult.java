package com.tengmei.trade.rest;

public class RestResult<T> {
	private T data;

	private boolean success = true;

	public RestResult() {
		super();
	}
	public RestResult(boolean success) {
		super();
		this.success = success;
	}

	public RestResult(T data) {
		super();
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}

package com.tengmei.trade.rest;

public class RestResult<T> {
	public RestResult(T data) {
		super();
		this.data = data;
	}

	public RestResult(boolean success) {
		super();
		this.success = success;
	}

	private boolean success = true;
	private T data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public RestResult() {
		super();
	}

	public void setData(T data) {
		this.data = data;
	}
}

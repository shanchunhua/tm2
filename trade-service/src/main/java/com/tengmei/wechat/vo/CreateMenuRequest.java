package com.tengmei.wechat.vo;

import java.util.ArrayList;
import java.util.List;

public class CreateMenuRequest {
	private List<MenuItem> button = new ArrayList<MenuItem>();

	public List<MenuItem> getButton() {
		return button;
	}

	public void setButton(List<MenuItem> button) {
		this.button = button;
	}

}

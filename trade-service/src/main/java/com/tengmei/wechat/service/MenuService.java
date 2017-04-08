package com.tengmei.wechat.service;

import com.tengmei.wechat.vo.CreateMenuRequest;
import com.tengmei.wechat.vo.MenuCreateResponse;

public interface MenuService {
	public MenuCreateResponse createMenu(CreateMenuRequest request);
}

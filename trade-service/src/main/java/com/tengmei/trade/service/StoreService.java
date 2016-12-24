package com.tengmei.trade.service;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;

public interface StoreService {

	void create(Store store);

	Store findStoreByUser(WechatUser user);

}

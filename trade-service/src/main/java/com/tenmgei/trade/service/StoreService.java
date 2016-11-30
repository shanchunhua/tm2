package com.tenmgei.trade.service;

import com.tenmgei.trade.domain.Store;
import com.tenmgei.trade.domain.WechatUser;

public interface StoreService {

	void create(Store store);

	Store findStoreByUser(WechatUser user);

}

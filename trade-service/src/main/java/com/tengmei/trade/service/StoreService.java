package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.Chain;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;

public interface StoreService {

	void create(Store store);

	Store findStoreByOwner(WechatUser user);
	
	List<Store> findByChain(Chain chain);

}

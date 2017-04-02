package com.tengmei.trade.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tengmei.trade.domain.Chain;
import com.tengmei.trade.domain.CustomerLevel;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;

public interface StoreService {

	void create(Store store);

	Store findStoreByOwner(WechatUser user);
	
	List<Store> findByChain(Chain chain);

	Store findById(Long id);

	Page<WechatUser> findUserByStoreCustomerLevel(Store store, CustomerLevel customerLevel, Pageable pageable);

	List<Store> findAll();

	void update(Store store);

}

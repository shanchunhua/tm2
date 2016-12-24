package com.tengmei.trade.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.repository.StoreRepository;
import com.tengmei.trade.service.StoreService;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepository storeRepository;

	@Override
	public void create(Store store) {
		storeRepository.save(store);
	}
	@Override
	public Store findStoreByUser(WechatUser user){
		return storeRepository.findByUser(user);
	}
}

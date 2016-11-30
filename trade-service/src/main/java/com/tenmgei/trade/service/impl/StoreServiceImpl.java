package com.tenmgei.trade.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenmgei.trade.domain.Store;
import com.tenmgei.trade.domain.WechatUser;
import com.tenmgei.trade.repository.StoreRepository;
import com.tenmgei.trade.service.StoreService;

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

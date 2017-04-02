package com.tengmei.trade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.tengmei.trade.domain.Chain;
import com.tengmei.trade.domain.CustomerLevel;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.UserType;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.repository.StoreRepository;
import com.tengmei.trade.repository.WechatUserRepository;
import com.tengmei.trade.service.StoreService;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private WechatUserRepository wechatUserRepository;

	@Override
	public void create(Store store) {
		store.getUser().setType(UserType.STORE_OWNER);
		wechatUserRepository.save(store.getUser());
		storeRepository.save(store);
	}

	@Override
	public Store findStoreByOwner(WechatUser user) {
		return storeRepository.findByUser(user);
	}

	@Override
	public List<Store> findByChain(Chain chain) {
		return storeRepository.findByChain(chain);
	}

	@Override
	public Store findById(Long id) {
		return storeRepository.findOne(id);
	}


	@Override
	public Page<WechatUser> findUserByStoreCustomerLevel(Store store, CustomerLevel customerLevel, Pageable pageable) {
		return wechatUserRepository.findByStoreAndCustomerLevel(store, customerLevel, pageable);
	}

	@Override
	public List<Store> findAll() {
		return storeRepository.findAll();
	}

	@Override
	public void update(Store store) {
		storeRepository.save(store);
	}
}

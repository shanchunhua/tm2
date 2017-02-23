package com.tengmei.trade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.StoreProduct;
import com.tengmei.trade.repository.StoreProductRepository;
import com.tengmei.trade.service.StoreProductService;

@Service
@Transactional
public class StoreProductServiceImpl implements StoreProductService {
	@Autowired
	StoreProductRepository storeProductRepository;

	@Override
	public StoreProduct create(StoreProduct storeProduct) {
		return storeProductRepository.save(storeProduct);
	}

	@Override
	public List<StoreProduct> findByStore(Store store) {
		return storeProductRepository.findByStoreAndDeleted(store, false);
	}

	@Override
	public StoreProduct findById(Long id) {
		return storeProductRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		StoreProduct storeProduct = storeProductRepository.findOne(id);
		storeProduct.setDeleted(true);
		storeProductRepository.save(storeProduct);
	}

}

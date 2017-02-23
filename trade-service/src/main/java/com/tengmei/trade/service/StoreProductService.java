package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.StoreProduct;

public interface StoreProductService {
	public StoreProduct create(StoreProduct storeProduct);
	public List<StoreProduct> findByStore(Store store);
	public StoreProduct findById(Long id);
	public void delete(Long id);
}

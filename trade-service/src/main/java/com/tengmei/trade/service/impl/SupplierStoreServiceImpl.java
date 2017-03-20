package com.tengmei.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.SupplierStore;
import com.tengmei.trade.repository.SupplierStoreRepository;
import com.tengmei.trade.service.SupplierStoreService;

@Service
@Transactional
public class SupplierStoreServiceImpl implements SupplierStoreService {
	@Autowired
	SupplierStoreRepository supplierStoreRepository;

	@Override
	public List<Supplier> findSuppliersByStore(Store store) {
		List<SupplierStore> supplierStores = supplierStoreRepository.findByStore(store);
		List<Supplier> suppliers = new ArrayList<Supplier>();
		for (SupplierStore supplierStore : supplierStores) {
			suppliers.add(supplierStore.getSupplier());
		}
		return suppliers;
	}

	@Override
	public List<Store> findStoresBySupplier(Supplier supplier) {
		List<SupplierStore> supplierStores = supplierStoreRepository.findBySupplier(supplier);
		List<Store> stores = new ArrayList<Store>();
		for (SupplierStore supplierStore : supplierStores) {
			stores.add(supplierStore.getStore());
		}
		return stores;
	}

	@Override
	public Integer countByStore(Store store) {
		return supplierStoreRepository.countByStore(store);
	}

	@Override
	public Integer countBySupplier(Supplier supplier) {
		return supplierStoreRepository.countBySupplier(supplier);
	}

}

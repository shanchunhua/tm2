package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;

public interface SupplierStoreService {
	public List<Supplier> findSuppliersByStore(Store store);

	public List<Store> findStoresBySupplier(Supplier supplier);

	public Integer countByStore(Store store);

	public Integer countBySupplier(Supplier supplier);

}

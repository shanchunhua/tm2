package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.SupplierStore;

@Repository
public interface SupplierStoreRepository extends JpaRepository<SupplierStore, Long> {

	List<SupplierStore> findByStore(Store store);

	List<SupplierStore> findBySupplier(Supplier supplier);

	Integer countByStore(Store store);

	Integer countBySupplier(Supplier supplier);

}

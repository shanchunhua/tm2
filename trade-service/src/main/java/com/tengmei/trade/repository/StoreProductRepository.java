package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.StoreProduct;

@Repository
public interface StoreProductRepository extends JpaRepository<StoreProduct, Long> {
	List<StoreProduct> findByStoreAndDeleted(Store store, Boolean deleted);
}

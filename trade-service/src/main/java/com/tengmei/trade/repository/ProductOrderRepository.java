package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.ProductOrder;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
	public List<ProductOrder> findByStore(Store store);

	public List<ProductOrder> findByProduct_Supplier(Supplier supplier);
	
}

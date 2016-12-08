package com.tenmgei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenmgei.trade.domain.ProductOrder;
import com.tenmgei.trade.domain.Store;
import com.tenmgei.trade.domain.Supplier;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
	public List<ProductOrder> findByStore(Store store);

	public List<ProductOrder> findByProduct_Supplier(Supplier supplier);
	
}

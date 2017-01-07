package com.tengmei.trade.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.PaymentStatus;
import com.tengmei.trade.domain.ProductOrder;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
	public List<ProductOrder> findByStore(Store store);

	public List<ProductOrder> findByProduct_Supplier(Supplier supplier);

	public Integer countByProduct_SupplierAndPaymentStatus(Supplier supplier, PaymentStatus paymentStatus);

	@Query("select sum(o.total) from ProductOrder o where o.product.supplier=?1 and o.paymentStatus=com.tengmei.trade.domain.PaymentStatus.PAID")
	public BigDecimal totalBySupplier(Supplier supplier);

	public Integer countByStoreAndPaymentStatus(Store store, PaymentStatus paymentStatus);

	@Query("select sum(o.total) from ProductOrder o where o.store=?1 and o.paymentStatus=com.tengmei.trade.domain.PaymentStatus.PAID")
	public BigDecimal totalByStore(Store store);

}

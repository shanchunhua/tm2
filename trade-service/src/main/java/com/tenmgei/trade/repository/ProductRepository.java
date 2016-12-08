package com.tenmgei.trade.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenmgei.trade.domain.Product;
import com.tenmgei.trade.domain.ProductCatalog;
import com.tenmgei.trade.domain.Supplier;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findBySupplierAndCatalog(Supplier supplier, ProductCatalog catalog);

	List<Product> findBySupplier(Supplier supplier);

	List<Product> findBySupplierInAndCatalog(Collection<Supplier> supplier, ProductCatalog catalog);

	List<Product> findBySupplierIn(Collection<Supplier> supplier);
}

package com.tengmei.trade.service;

import java.util.Collection;
import java.util.List;

import com.tengmei.trade.domain.Product;
import com.tengmei.trade.domain.ProductCatalog;
import com.tengmei.trade.domain.Supplier;

public interface ProductService {
	public List<Product> findBySupplier(Supplier supplier);

	public List<Product> findBySuppliers(Collection<Supplier> suppliers);

	public List<Product> findBySupplierAndProductCatalog(Supplier supplier, ProductCatalog catalog);

	public List<Product> findBySuppliersAndProductCatalog(Collection<Supplier> suppliers, ProductCatalog catalog);

	public Product findById(Long id);
	
	public Integer countBySupplier(Supplier supplier);

	public void save(Product product);

}

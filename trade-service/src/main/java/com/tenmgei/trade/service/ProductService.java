package com.tenmgei.trade.service;

import java.util.List;

import com.tenmgei.trade.domain.Product;
import com.tenmgei.trade.domain.ProductCatalog;
import com.tenmgei.trade.domain.Supplier;

public interface ProductService {
	public List<Product> findBySupplier(Supplier supplier);

	public List<Product> findBySuppliers(List<Supplier> suppliers);

	public List<Product> findBySupplierAndProductCatalog(Supplier supplier, ProductCatalog catalog);

	public List<Product> findBySuppliersAndProductCatalog(List<Supplier> suppliers, ProductCatalog catalog);

}

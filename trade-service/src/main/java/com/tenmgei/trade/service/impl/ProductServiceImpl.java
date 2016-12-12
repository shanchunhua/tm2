package com.tenmgei.trade.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenmgei.trade.domain.Product;
import com.tenmgei.trade.domain.ProductCatalog;
import com.tenmgei.trade.domain.Supplier;
import com.tenmgei.trade.repository.ProductRepository;
import com.tenmgei.trade.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findBySupplier(Supplier supplier) {
		return productRepository.findBySupplier(supplier);
	}

	@Override
	public List<Product> findBySuppliers(List<Supplier> suppliers) {
		return productRepository.findBySupplierIn(suppliers);
	}

	@Override
	public List<Product> findBySupplierAndProductCatalog(Supplier supplier, ProductCatalog catalog) {
		return productRepository.findBySupplierAndCatalog(supplier, catalog);
	}

	@Override
	public List<Product> findBySuppliersAndProductCatalog(List<Supplier> suppliers, ProductCatalog catalog) {
		return productRepository.findBySupplierInAndCatalog(suppliers, catalog);
	}

}

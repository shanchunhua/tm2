package com.tengmei.trade.service.impl;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.domain.Product;
import com.tengmei.trade.domain.ProductCatalog;
import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.repository.ProductCatalogRepository;
import com.tengmei.trade.repository.ProductRepository;
import com.tengmei.trade.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductCatalogRepository productCatalogRepository;

	@Override
	public List<Product> findBySupplier(Supplier supplier) {
		return productRepository.findBySupplier(supplier);
	}

	@Override
	public List<Product> findBySuppliers(Collection<Supplier> suppliers) {
		return productRepository.findBySupplierIn(suppliers);
	}

	@Override
	public List<Product> findBySupplierAndProductCatalog(Supplier supplier, ProductCatalog catalog) {
		return productRepository.findBySupplierAndCatalog(supplier, catalog);
	}

	@Override
	public List<Product> findBySuppliersAndProductCatalog(Collection<Supplier> suppliers, ProductCatalog catalog) {
		return productRepository.findBySupplierInAndCatalog(suppliers, catalog);
	}

	@Override
	public Product findById(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public Integer countBySupplier(Supplier supplier) {
		return productRepository.countBySupplier(supplier);
	}

	@Override
	public void save(Product product) {
		product.setCatalog(productCatalogRepository.findOne(product.getCatalog().getId()));
		productRepository.save(product);

	}

}

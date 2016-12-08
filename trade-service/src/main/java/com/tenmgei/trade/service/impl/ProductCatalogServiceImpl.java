package com.tenmgei.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tenmgei.trade.domain.ProductCatalog;
import com.tenmgei.trade.repository.ProductCatalogRepository;
import com.tenmgei.trade.service.ProductCatalogService;

public class ProductCatalogServiceImpl implements ProductCatalogService {

	@Autowired
	private ProductCatalogRepository productCatalogRepository;

	@Override
	public List<ProductCatalog> getAll() {
		return productCatalogRepository.findAll();
	}

}

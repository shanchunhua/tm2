package com.tengmei.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.domain.ProductCatalog;
import com.tengmei.trade.repository.ProductCatalogRepository;
import com.tengmei.trade.service.ProductCatalogService;
@Service 
public class ProductCatalogServiceImpl implements ProductCatalogService {

	@Autowired
	private ProductCatalogRepository productCatalogRepository;

	@Override
	public List<ProductCatalog> getAll() {
		return productCatalogRepository.findAll();
	}

}

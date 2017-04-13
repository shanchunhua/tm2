package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.ProductCatalog;

public interface ProductCatalogService {
	/**
	 * 查询所有的产品类别
	 * @return
	 */
	List<ProductCatalog> getAll();

	ProductCatalog findById(Long id);
}

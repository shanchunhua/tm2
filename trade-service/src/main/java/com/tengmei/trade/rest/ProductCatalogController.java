package com.tengmei.trade.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Product;
import com.tengmei.trade.domain.ProductCatalog;
import com.tengmei.trade.service.ProductCatalogService;

@RestController
@RequestMapping("/rest/catalogs")
public class ProductCatalogController {
	@Autowired
	private ProductCatalogService productCatalogService;
	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<ProductCatalog>> getCatalogs(HttpServletRequest request) {
		
		List<ProductCatalog> catalogs =productCatalogService.getAll();

//		// TODO test only
		RestResult<List<ProductCatalog>> result = new RestResult<>();
//		List<ProductCatalog> products = new ArrayList<>();
//		for (int i = 0; i < 15; i++) {
//			ProductCatalog product = new ProductCatalog();
//			product.setName("Test" + System.currentTimeMillis());
//			product.setId(Long.valueOf(i+1));
//			products.add(product);
//		}
		result.setData(catalogs);
		return result;
	}
}

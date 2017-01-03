package com.tengmei.trade.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Product;
import com.tengmei.trade.domain.ProductCatalog;

@RestController
@RequestMapping("/rest/catalogs")
public class ProductCatalogController {
	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<ProductCatalog>> getCatalogs(HttpServletRequest request) {

		// TODO test only
		RestResult<List<ProductCatalog>> result = new RestResult<>();
		List<ProductCatalog> products = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			ProductCatalog product = new ProductCatalog();
			product.setName("Test" + System.currentTimeMillis());
			products.add(product);
		}
		result.setData(products);
		return result;
	}
}

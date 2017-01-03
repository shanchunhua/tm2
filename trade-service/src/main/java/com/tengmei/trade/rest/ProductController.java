package com.tengmei.trade.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Product;

@RestController
@RequestMapping("/rest/products")
public class ProductController {
	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<Product>> getProducts(HttpServletRequest request) {
		
		
		// TODO test only
		RestResult<List<Product>> result = new RestResult<>();
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			Product product = new Product();
			product.setId(Long.valueOf(i+1));
			product.setName("Test" + System.currentTimeMillis());
			products.add(product);
		}
		result.setData(products);
		return result;
	}
}

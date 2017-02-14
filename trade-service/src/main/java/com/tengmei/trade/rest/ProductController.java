package com.tengmei.trade.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Product;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.ProductService;
import com.tengmei.trade.service.StoreService;

@RestController
@RequestMapping("/rest/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private StoreService storeService;
	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<Product>> getProducts(HttpServletRequest request) {
		
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Store store=storeService.findStoreByOwner(user);
		List<Product> products=productService.findBySuppliers(store.getSuppliers());
		
		// TODO test only
		RestResult<List<Product>> result = new RestResult<>();
//		List<Product> products = new ArrayList<>();
//		for (int i = 0; i < 15; i++) {
//			Product product = new Product();
//			product.setId(Long.valueOf(i+1));
//			product.setName("Test" + System.currentTimeMillis());
//			products.add(product);
//		}
		result.setData(products);
		return result;
	}
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public RestResult<Product> getProductById(@PathVariable Long id) {
		
		
		// TODO test only
		RestResult<Product> result = new RestResult<>();
		Product product=productService.findById(id);
		result.setData(product);
		return result;
	}
}

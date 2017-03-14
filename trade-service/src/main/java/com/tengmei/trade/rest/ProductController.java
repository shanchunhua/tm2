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
import com.tengmei.trade.service.SupplierStoreService;

@RestController
@RequestMapping("/rest/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private SupplierStoreService supplierStoreService;

	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<Product>> getProducts(HttpServletRequest request) {

		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Store store = storeService.findStoreByOwner(user);
		List<Product> products = productService.findBySuppliers(supplierStoreService.findSuppliersByStore(store));

		RestResult<List<Product>> result = new RestResult<>();

		result.setData(products);
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RestResult<Product> getProductById(@PathVariable Long id) {

		// TODO test only
		RestResult<Product> result = new RestResult<>();
		Product product = productService.findById(id);
		result.setData(product);
		return result;
	}
}

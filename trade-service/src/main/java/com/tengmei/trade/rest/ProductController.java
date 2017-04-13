package com.tengmei.trade.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Product;
import com.tengmei.trade.domain.ProductCatalog;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.UserType;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.ProductCatalogService;
import com.tengmei.trade.service.ProductService;
import com.tengmei.trade.service.StoreService;
import com.tengmei.trade.service.SupplierService;
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
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private ProductCatalogService productCatalogService;

	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<Product>> getProducts(HttpServletRequest request, @RequestParam(required = false) Long id) {
		RestResult<List<Product>> result = new RestResult<>();
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		if (user.getType().equals(UserType.STORE_OWNER)) {// 如果是店家
			Store store = storeService.findStoreByOwner(user);
			if (id != null) {
				ProductCatalog catalog = productCatalogService.findById(id);
				List<Product> products = productService
						.findBySuppliersAndProductCatalog(supplierStoreService.findSuppliersByStore(store), catalog);
				result.setData(products);
			} else {
				List<Product> products = productService
						.findBySuppliers(supplierStoreService.findSuppliersByStore(store));
				result.setData(products);
			}

		} else {// 如果是发品商
			Supplier supplier = supplierService.findSupplier(user);
			if (id != null) {
				ProductCatalog catalog = productCatalogService.findById(id);
				List<Product> products = productService.findBySupplierAndProductCatalog(supplier, catalog);
				result.setData(products);
			} else {
				List<Product> products = productService.findBySupplier(supplier);
				result.setData(products);
			}

		}

		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public RestResult<Product> save(@RequestBody Product product) {
		RestResult<Product> result = new RestResult<>();
		productService.save(product);
		result.setData(product);
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

package com.tengmei.trade.rest.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Product;
import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.rest.RestResult;
import com.tengmei.trade.service.ProductService;
import com.tengmei.trade.service.SupplierService;

@RequestMapping("/rest/admin/suppliers")
@RestController
public class SupplierAdminController {
	@Autowired
	private ProductService productService;
	@Autowired
	private SupplierService supplierService;

	@RequestMapping("/{id}/products")
	public RestResult<List<Product>> findProductsBySupplier(@PathVariable Long id) {
		Supplier supplier = supplierService.findById(id);
		List<Product> list = productService.findBySupplier(supplier);
		return new RestResult<>(list);
	}
}

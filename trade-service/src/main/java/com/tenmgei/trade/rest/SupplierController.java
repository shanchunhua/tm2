package com.tenmgei.trade.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tenmgei.trade.domain.Supplier;
import com.tenmgei.trade.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;

	@RequestMapping(method = RequestMethod.POST)
	public RestResult<Supplier> create(@RequestBody Supplier supplier) {
		supplierService.create(supplier);
		return new RestResult<Supplier>();
	}

	@RequestMapping("/certificate/{id}")
	public RestResult<Void> certificate(@PathVariable Long id) {
		supplierService.certificate(id);
		return new RestResult<Void>();
	}
}

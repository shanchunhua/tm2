package com.tengmei.trade.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.PlatformProduct;
import com.tengmei.trade.service.PlatformProductService;

@RestController
@RequestMapping("/rest/platformproducts")
public class PlatformProductController {
	@Autowired
	PlatformProductService platformProductService;

	@RequestMapping(method = RequestMethod.GET)
	public RestResult<Page<PlatformProduct>> getAll(@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size) {
		Pageable pageable = new PageRequest(page, size);
		return new RestResult<Page<PlatformProduct>>(platformProductService.getAll(pageable));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public RestResult<PlatformProduct> get(@PathVariable Long id) {
		return new RestResult<PlatformProduct>(platformProductService.find(id));
	}
}

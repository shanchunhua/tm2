package com.tengmei.trade.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.service.ServiceCatalogService;

@RestController
@RequestMapping("/rest/servicecatalogs")
public class ServiceCatalogController {
	@Autowired
	private ServiceCatalogService serviceCatalogService;

	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<ServiceCatalog>> getAll(HttpServletRequest request) {
		RestResult<List<ServiceCatalog>> result = new RestResult<>();
		result.setData(serviceCatalogService.findAll());
		return result;
	}
}

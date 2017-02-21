package com.tengmei.trade.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.ServiceCatalogService;
import com.tengmei.trade.service.ServiceService;
import com.tengmei.trade.service.WechatUserService;

@RestController
@RequestMapping("/rest/services")
public class ServiceController {
	@Autowired
	ServiceService serviceService;
	@Autowired
	WechatUserService wechatUserService;
	@Autowired
	ServiceCatalogService serviceCatalogService;

	@RequestMapping(method = RequestMethod.POST)
	public RestResult<Store> create(@RequestBody Service service, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Store store = wechatUserService.findById(user.getId()).getStore();
		service.setStore(store);
		serviceService.create(service);
		return new RestResult<Store>();
	}

	@RequestMapping(value = "/catalog/{id}", method = RequestMethod.GET)
	public RestResult<List<Service>> list(@PathVariable Long id, HttpServletRequest request) {

		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Store store = wechatUserService.findById(user.getId()).getStore();
		List<Service> services = serviceService.findByStoreAndCatalog(store, serviceCatalogService.findById(id));

		RestResult<List<Service>> result = new RestResult<>(services);

		return result;
	}
}

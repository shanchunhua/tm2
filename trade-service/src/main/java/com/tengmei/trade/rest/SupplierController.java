package com.tengmei.trade.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.service.SupplierService;

@RestController
@RequestMapping("/rest/suppliers")
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

	@RequestMapping("/login/{menu}")
	public void redirectToOAuth2(@PathVariable(value = "menu")  String menu, HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		if (request.getSession().getAttribute("user") == null) {
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="  + "&redirect_uri="
					+ "http://localhost:8080/entry/success/" + menu + "&response_type=code&scope=snsapi_base&state="
					+ menu + "#wechat_redirect";
			response.sendRedirect(url);
		} else {

		}
	}
}

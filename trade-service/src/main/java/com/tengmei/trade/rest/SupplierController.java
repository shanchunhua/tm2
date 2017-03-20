package com.tengmei.trade.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.rest.vo.SupplierSummary;
import com.tengmei.trade.service.ProductOrderService;
import com.tengmei.trade.service.SupplierService;
import com.tengmei.trade.service.SupplierStoreService;

@RestController
@RequestMapping("/rest/suppliers")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private ProductOrderService productOrderService;

	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<Supplier>> all(@RequestParam(name = "_page",required=false) Integer page,
			@RequestParam(name = "_limit",required=false) Integer limit) {
		List<Supplier> suppliers = supplierService.findAll();
		return new RestResult<List<Supplier>>(suppliers);
	}

	@Autowired
	private SupplierStoreService supplierStoreService;


	@RequestMapping(method = RequestMethod.POST)
	public RestResult<Supplier> create(@RequestBody Supplier supplier, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		supplier.setUser(user);
		supplierService.create(supplier);
		return new RestResult<Supplier>();
	}

	@RequestMapping("/current")
	public RestResult<Supplier> getSupplierByOnlineUser(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Supplier supplier = supplierService.findSupplier(user);
		return new RestResult<Supplier>(supplier);
	}

	@RequestMapping("/summary")
	public RestResult<SupplierSummary> getStoreSummaryByOnlineUser(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Supplier supplier = supplierService.findSupplier(user);
		SupplierSummary summary = new SupplierSummary();
		summary.setSupplier(supplier);
		summary.setStoreCount(supplierStoreService.countBySupplier(supplier));
		summary.setTotalAmount(productOrderService.getTotalOrderAmountBySupplier(supplier));
		summary.setOrderCount(productOrderService.getTotalOrderCountBySupplier(supplier));
		return new RestResult<SupplierSummary>(summary);
	}

	@RequestMapping("/certificate/{id}")
	public RestResult<Void> certificate(@PathVariable Long id) {
		supplierService.certificate(id);
		return new RestResult<Void>();
	}

	@RequestMapping("/login/{menu}")
	public void redirectToOAuth2(@PathVariable(value = "menu") String menu, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		if (request.getSession().getAttribute("user") == null) {
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + "&redirect_uri="
					+ "http://localhost:8080/entry/success/" + menu + "&response_type=code&scope=snsapi_base&state="
					+ menu + "#wechat_redirect";
			response.sendRedirect(url);
		} else {

		}
	}
}

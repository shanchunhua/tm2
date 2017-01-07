package com.tengmei.trade.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.ProductOrder;
import com.tengmei.trade.domain.UserType;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.ProductOrderService;
import com.tengmei.trade.service.StoreService;
import com.tengmei.trade.service.SupplierService;

@RestController
@RequestMapping("/rest/orders")
public class ProductOrderController {
	private static final Logger logger = LoggerFactory.getLogger(ProductOrderController.class);
	@Autowired
	private ProductOrderService productOrderService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private SupplierService supplierService;

	@RequestMapping(method = RequestMethod.POST)
	public RestResult<ProductOrder> create(@RequestBody ProductOrder productOrder, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		productOrderService.create(productOrder);
		logger.debug(productOrder.getOrderNo());
		return new RestResult<ProductOrder>(productOrder);
	}

	@RequestMapping("/current")
	public RestResult<List<ProductOrder>> getOrders(HttpServletRequest request) {
		RestResult<List<ProductOrder>> result = new RestResult<List<ProductOrder>>();
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		List<ProductOrder> orders = null;
		if (user.getType() == UserType.STORE_OWNER) {
			orders = productOrderService.findOrderByStore(storeService.findStoreByUser(user));
		} else {
			orders = productOrderService.findOrderBySupplier(supplierService.findSupplier(user));
		}
		result.setData(orders);
		return result;
	}
}

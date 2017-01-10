package com.tengmei.trade.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.rest.vo.StoreSummary;
import com.tengmei.trade.service.ProductOrderService;
import com.tengmei.trade.service.ProductService;
import com.tengmei.trade.service.StoreService;

@RestController
@RequestMapping("/rest/stores")
public class StoreController {
	@Autowired
	private StoreService storeService;
	@Autowired
	private ProductOrderService productOrderService;
	@Autowired
	private ProductService productService;

	/**
	 * 创建店铺申请
	 * 
	 * @param store
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public RestResult<Store> create(@RequestBody Store store, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		store.setUser(user);
		storeService.create(store);
		return new RestResult<Store>();
	}

	/**
	 * 当前登录用户店铺信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/current")
	public RestResult<Store> getStoreByOnlineUser(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Store store = storeService.findStoreByUser(user);
		return new RestResult<Store>(store);
	}

	@RequestMapping("/summary")
	public RestResult<StoreSummary> getStoreSummaryByOnlineUser(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Store store = storeService.findStoreByUser(user);
		StoreSummary storeSummary = new StoreSummary();
		storeSummary.setStore(store);
		storeSummary.setSupplierCount(store.getSuppliers().size());
		storeSummary.setTotalAmount(productOrderService.getTotalOrderAmountByStore(store));
		storeSummary.setOrderCount(productOrderService.getTotalOrderCountByStore(store));
		return new RestResult<StoreSummary>(storeSummary);
	}
	 @RequestMapping("/suppliers")
	 public RestResult<Collection<Supplier>> getStoreSuppliers(HttpServletRequest request) {
			WechatUser user = (WechatUser) request.getSession().getAttribute("user");
			Store store = storeService.findStoreByUser(user);
			for (Supplier supplier : store.getSuppliers()) {
				supplier.setProductCount(productService.countBySupplier(supplier));
			}
			return new RestResult<Collection<Supplier>> (store.getSuppliers());
	 }
}

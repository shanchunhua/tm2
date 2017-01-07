package com.tengmei.trade.rest;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.rest.vo.StoreSummary;
import com.tengmei.trade.service.StoreService;

@RestController
@RequestMapping("/rest/stores")
public class StoreController {
	@Autowired
	private StoreService storeService;

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
		storeSummary.setSupplierCount(10);
		storeSummary.setTotalAmount(new BigDecimal(1000));
		storeSummary.setOrderCount(100);
		return new RestResult<StoreSummary>(storeSummary);
	}
	// @RequestMapping("/certificate/{id}")
	// public RestResult<Void> certificate(@PathVariable Long id) {
	// storeService.certificate(id);
	// return new RestResult<Void>();
	// }
}

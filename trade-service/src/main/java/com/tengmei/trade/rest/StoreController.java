package com.tengmei.trade.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
import com.tengmei.trade.service.StaffService;
import com.tengmei.trade.service.StoreService;
import com.tengmei.trade.service.WechatUserService;
import com.tengmei.wechat.service.HairStyleService;

@RestController
@RequestMapping("/rest/stores")
public class StoreController {
	@Autowired
	private StoreService storeService;
	@Autowired
	private ProductOrderService productOrderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private WechatUserService wechatUserService;
	@Autowired
	private HairStyleService hairStyleService;

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
		Store store = storeService.findStoreByOwner(user);
		return new RestResult<Store>(store);
	}

	@RequestMapping("/summary")
	public RestResult<StoreSummary> getStoreSummaryByOnlineUser(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Store store = storeService.findStoreByOwner(user);
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
		Store store = storeService.findStoreByOwner(user);
		for (Supplier supplier : store.getSuppliers()) {
			supplier.setProductCount(productService.countBySupplier(supplier));
		}
		return new RestResult<Collection<Supplier>>(store.getSuppliers());
	}

	/**
	 * 前端店铺首页，员工，发型，连锁统计信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/main")
	public RestResult<Map<String, Object>> main(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findByOpenid(user.getOpenid());
		Store store = user.getStore();
		// 员工数量
		int staffCount = staffService.countByStore(store);
		// 时尚发型数量
		int globalHairStyleCount = hairStyleService.countGlobalHairStyle();
		// 本店发型数量
		int storeHairStyleCount = hairStyleService.countStoreHairStyle(store);
		// 连锁分店
		int chainCount = 0;
		if (store.getChain() != null) {
			chainCount = storeService.findByChain(store.getChain()).size();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("staffCount", staffCount);
		map.put("globalHairStyleCount", globalHairStyleCount);
		map.put("storeHairStyleCount", storeHairStyleCount);
		map.put("chainCount", chainCount);
		return new RestResult<Map<String, Object>>(map);
	}
}

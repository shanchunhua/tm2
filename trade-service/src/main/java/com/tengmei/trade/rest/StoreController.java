package com.tengmei.trade.rest;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.CustomerLevel;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.rest.vo.StoreSummary;
import com.tengmei.trade.service.HairStyleService;
import com.tengmei.trade.service.ProductOrderService;
import com.tengmei.trade.service.ProductService;
import com.tengmei.trade.service.StaffService;
import com.tengmei.trade.service.StoreService;
import com.tengmei.trade.service.SupplierStoreService;
import com.tengmei.trade.service.WechatUserService;

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
	@Autowired
	SupplierStoreService supplierStoreService;

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

	@RequestMapping(method = RequestMethod.PUT)
	public RestResult<Store> update(@RequestBody Store store) {
		storeService.update(store);
		return new RestResult<Store>();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<Store>> findAll() {
		List<Store> stores = storeService.findAll();
		return new RestResult<List<Store>>(stores);
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
		storeSummary.setSupplierCount(supplierStoreService.countByStore(store));
		storeSummary.setTotalAmount(productOrderService.getTotalOrderAmountByStore(store));
		storeSummary.setOrderCount(productOrderService.getTotalOrderCountByStore(store));
		return new RestResult<StoreSummary>(storeSummary);
	}

	@RequestMapping("/suppliers")
	public RestResult<Collection<Supplier>> getStoreSuppliers(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		Store store = storeService.findStoreByOwner(user);
		List<Supplier> suppliers = supplierStoreService.findSuppliersByStore(store);
		for (Supplier supplier : suppliers) {
			supplier.setProductCount(productService.countBySupplier(supplier));
		}
		return new RestResult<Collection<Supplier>>(suppliers);
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
		map.put("store", store);
		map.put("staffCount", staffCount);
		map.put("globalHairStyleCount", globalHairStyleCount);
		map.put("storeHairStyleCount", storeHairStyleCount);
		map.put("chainCount", chainCount);
		return new RestResult<Map<String, Object>>(map);
	}

	@RequestMapping("/chain")
	public RestResult<List<Store>> findChainStores(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findByOpenid(user.getOpenid());
		Store store = user.getStore();
		if (store.getChain() != null) {
			List<Store> stores = storeService.findByChain(store.getChain());
		}
		return null;
	}

	@RequestMapping("/users/{customerLevel}")
	public RestResult<Page<WechatUser>> findSubscriber(HttpServletRequest request,
			@PathVariable CustomerLevel customerLevel, @RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size) {
		Pageable pageable = new PageRequest(page, size);
		Store store = (Store) request.getSession().getAttribute("store");
		Page<WechatUser> users = storeService.findUserByStoreCustomerLevel(store, customerLevel, pageable);
		return new RestResult<Page<WechatUser>>(users);
	}

	@RequestMapping(path = "/expMoneyRateSetting", method = RequestMethod.POST)
	public RestResult<Store> updateExperienceMoneyRateSetting(@RequestBody Store store, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		store.setUser(user);
		storeService.create(store);
		return new RestResult<Store>();
	}

}

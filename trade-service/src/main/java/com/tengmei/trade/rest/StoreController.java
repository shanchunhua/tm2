package com.tengmei.trade.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.StoreService;

@RestController
@RequestMapping("/stores")
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
	public RestResult<Store> create(@RequestBody Store store) {
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

	// @RequestMapping("/certificate/{id}")
	// public RestResult<Void> certificate(@PathVariable Long id) {
	// storeService.certificate(id);
	// return new RestResult<Void>();
	// }
}

package com.tengmei.trade.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.HairStyle;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.StoreService;
import com.tengmei.wechat.service.HairStyleService;

@RestController
@RequestMapping("/rest/hairstyles")
public class HairStyleController {
	@Autowired
	HairStyleService hairStyleService;
	@Autowired
	StoreService storeService;
	@RequestMapping(method = RequestMethod.POST)
	public RestResult<Page<HairStyle>> getGlobalHairStyles(@RequestBody(required = false) HairStyle example,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "20") int size) {
		Pageable pageable = new PageRequest(page, size);
		RestResult<Page<HairStyle>> result = new RestResult<>(hairStyleService.getGlobalHairStyle(example, pageable));
		return result;

	}

//	@RequestMapping(method = RequestMethod.POST)
//	public RestResult<Store> create(@RequestBody HairStyle hairStyle, HttpServletRequest request) {
//		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
//		Store store = storeService.findStoreByUser(user);
//		hairStyle.setStore(store);
//		hairStyleService.create(hairStyle);
//		return new RestResult<Store>();
//	}

}

package com.tengmei.trade.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.service.StoreService;

/**
 * TODO 授权问题 店铺端微信的入口页面
 * 
 * @author sam
 *
 */
@Controller
@RequestMapping("/front")
public class StoreEntryController {
	@Autowired
	StoreService storeService;

	@RequestMapping("/store/{id}")
	public String storeEntry(@PathVariable Long id, HttpServletRequest request) {
		Store store = storeService.findById(id);
		request.getSession().setAttribute("store", store);
		return "redirect:http://www.tengmei360.com/index.html#!/storemain";
	}
}

package com.tengmei.trade.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.domain.UserDiscountCard;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.ServiceService;
import com.tengmei.trade.service.UserCardService;

@RestController
@RequestMapping("/rest/storeorders")
public class StoreOrderController {
	@Autowired
	UserCardService userCardService;
	@Autowired
	ServiceService serviceService;

	/**
	 * 获取用户可用的折扣卡，次卡
	 * 
	 * @param id:
	 *            服务id
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/card/{id}", method = RequestMethod.POST)
	public RestResult<?> getUserCards(@PathVariable Long id, HttpServletRequest request) {
		Service service = serviceService.findById(id);
		ServiceCatalog catalog = service.getCatalog();
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		List<UserDiscountCard> userDiscountCards = userCardService.findDiscountCardByUserAndCatalog(user, catalog);

		return null;
	}
}

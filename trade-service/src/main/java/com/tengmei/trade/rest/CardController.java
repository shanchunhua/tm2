package com.tengmei.trade.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.CatalogDiscountCard;
import com.tengmei.trade.domain.CatalogDiscountCardItem;
import com.tengmei.trade.domain.TimesCard;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.CardService;
import com.tengmei.trade.service.ServiceCatalogService;
import com.tengmei.trade.service.WechatUserService;

@RestController
@RequestMapping("/rest/card")
public class CardController {
	@Autowired
	CardService cardService;
	@Autowired
	private WechatUserService wechatUserService;
	@Autowired
	private ServiceCatalogService serviceCatalogService;

	/**
	 * 创建次卡
	 * 
	 * @param card
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/timescard", method = RequestMethod.POST)
	public RestResult<TimesCard> create(@RequestBody TimesCard card, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());
		card.setStore(user.getStore());
		cardService.createTimesCard(card);
		return new RestResult<TimesCard>();
	}

	/**
	 * 加载次卡，用于编辑
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/timescard/{id}", method = RequestMethod.GET)
	public RestResult<TimesCard> loadTimesCard(@PathVariable Long id, HttpServletRequest request) {
		return new RestResult<TimesCard>(cardService.loadTimesCardById(id));
	}

	/**
	 * 创建分类折扣卡
	 * 
	 * @param card
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/discountcard", method = RequestMethod.POST)
	public RestResult<CatalogDiscountCard> create(@RequestBody CatalogDiscountCard card, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());
		card.setStore(user.getStore());
		for (CatalogDiscountCardItem item : card.getItems()) {
			item.setCard(card);
			item.setCatalog(serviceCatalogService.findById(item.getCatalog().getId()));
		}
		cardService.createDiscountCard(card);
		return new RestResult<CatalogDiscountCard>();
	}

	/**
	 * 加载分类折扣卡，用于编辑
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/discountcard/{id}", method = RequestMethod.GET)
	public RestResult<CatalogDiscountCard> loadDiscountCard(@PathVariable Long id, HttpServletRequest request) {
		return new RestResult<CatalogDiscountCard>(cardService.loadDiscountCardById(id));
	}

	/**
	 * 加载店铺所有分类折扣卡，用于购买
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/discountcard", method = RequestMethod.GET)
	public RestResult<List<CatalogDiscountCard>> loadStoreDiscountCard(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());
		return new RestResult<List<CatalogDiscountCard>>(cardService.loadDiscountCardByStore(user.getStore()));
	}
	
	@RequestMapping(path = "/timescard", method = RequestMethod.GET)
	public RestResult<List<TimesCard>> loadStoreTimesCard(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());
		return new RestResult<List<TimesCard>>(cardService.loadTimesCardByStore(user.getStore()));
	}
}

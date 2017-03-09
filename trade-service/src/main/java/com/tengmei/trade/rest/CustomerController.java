package com.tengmei.trade.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.CardType;
import com.tengmei.trade.domain.CardUsageRecord;
import com.tengmei.trade.domain.UserDiscountCard;
import com.tengmei.trade.domain.UserTimesCard;
import com.tengmei.trade.domain.UserWallet;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.UserCardService;

@RestController
@RequestMapping("/rest/customer")
public class CustomerController {
	@Autowired
	UserCardService userCardService;

	/**
	 * 我的卡包-会员卡列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/timescard", method = RequestMethod.GET)
	public RestResult<?> getUserTimesCards(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		List<UserTimesCard> userTimesCards = userCardService.findTimesCardByUser(user);
		return new RestResult<List<UserTimesCard>>(userTimesCards);
	}

	/**
	 * 我的卡包-会员卡列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/discountcard", method = RequestMethod.GET)
	public RestResult<?> getUserDiscountCards(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		List<UserDiscountCard> userDiscountCards = userCardService.findDiscountCardByUser(user);

		return new RestResult<List<UserDiscountCard>>(userDiscountCards);
	}

	/**
	 * 我的卡包-会员卡列表-划卡记录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/cardrecord/{type}/{id}", method = RequestMethod.GET)
	public RestResult<List<CardUsageRecord>> getCardUsageRecord(@PathVariable CardType type, @PathVariable Long id,
			HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		return new RestResult<List<CardUsageRecord>>(userCardService.findCardUsageRecord(id, type));
	}

}

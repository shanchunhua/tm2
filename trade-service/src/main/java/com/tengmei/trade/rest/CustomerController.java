package com.tengmei.trade.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.UserDiscountCard;
import com.tengmei.trade.domain.UserTimesCard;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.UserCardService;

@RestController
@RequestMapping("/rest/customer")
public class CustomerController {
	@Autowired
	UserCardService userCardService;

	@RequestMapping(path = "/timescard", method = RequestMethod.GET)
	public RestResult<?> getUserTimesCards(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		List<UserTimesCard> userTimesCards = userCardService.findTimesCardByUser(user);
		return new RestResult<List<UserTimesCard>>(userTimesCards);
	}

	@RequestMapping(path = "/discountcard", method = RequestMethod.GET)
	public RestResult<?> getUserDiscountCards(HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		List<UserDiscountCard> userDiscountCards = userCardService.findDiscountCardByUser(user);

		return new RestResult<List<UserDiscountCard>>(userDiscountCards);
	}
}

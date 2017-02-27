package com.tengmei.trade.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.TimesCard;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.CardService;
import com.tengmei.trade.service.WechatUserService;

@RestController
@RequestMapping("/rest/card")
public class CardController {
	@Autowired
	CardService cardService;
	@Autowired
	private WechatUserService wechatUserService;

	@RequestMapping(path = "/timescard", method = RequestMethod.POST)
	public RestResult<TimesCard> create(@RequestBody TimesCard card, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());
		card.setStore(user.getStore());
		cardService.createTimesCard(card);
		return new RestResult<TimesCard>();
	}
}

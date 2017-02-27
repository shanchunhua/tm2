package com.tengmei.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.domain.UserDiscountCard;
import com.tengmei.trade.domain.UserTimesCard;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.repository.UserDiscountCardRepository;
import com.tengmei.trade.repository.UserTimesCardRepository;
import com.tengmei.trade.service.UserCardService;

public class UserCardServiceImpl implements UserCardService {
	@Autowired
	UserTimesCardRepository userTimesCardRepository;
	@Autowired
	UserDiscountCardRepository userDiscountCardRepository;

	@Override
	public List<UserTimesCard> getValidTimesCards(WechatUser user, Service service, int level) {
		return userTimesCardRepository.findByUserAndServiceAndLevel(user, service, level);
	}

	@Override
	public List<UserDiscountCard> getValidCatalogDiscountCard(WechatUser user, ServiceCatalog catalog) {
//		return userDiscountCardRepository.findByUser;
		return null;
	}

}

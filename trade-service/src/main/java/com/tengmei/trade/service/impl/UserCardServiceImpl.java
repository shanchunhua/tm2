package com.tengmei.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.domain.UserDiscountCard;
import com.tengmei.trade.domain.UserTimesCard;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.repository.UserDiscountCardRepository;
import com.tengmei.trade.repository.UserTimesCardRepository;
import com.tengmei.trade.service.UserCardService;
@org.springframework.stereotype.Service
@Transactional
public class UserCardServiceImpl implements UserCardService {
	@Autowired
	UserTimesCardRepository userTimesCardRepository;
	@Autowired
	UserDiscountCardRepository userDiscountCardRepository;

	@Override
	public List<UserTimesCard> getValidTimesCards(WechatUser user, Service service) {
		return userTimesCardRepository.findByUserAndTimesCard_Service(user, service);
	}

	@Override
	public List<UserDiscountCard> getValidCatalogDiscountCard(WechatUser user, ServiceCatalog catalog) {
//		return userDiscountCardRepository.findByUser;
		return null;
	}

	@Override
	public List<UserDiscountCard> findDiscountCardByUserAndCatalog(WechatUser user, ServiceCatalog catalog) {
		
		return userDiscountCardRepository.findByUserAndCardItems_catalog(user,catalog);
	}

	@Override
	public List<UserTimesCard> findTimesCardByUserAndService(WechatUser user, Service service) {
		return userTimesCardRepository.findByUserAndTimesCard_Service(user, service);
	}

	@Override
	public List<UserTimesCard> findTimesCardByUser(WechatUser user) {
		return userTimesCardRepository.findByUser(user);
	}

	@Override
	public List<UserDiscountCard> findDiscountCardByUser(WechatUser user) {
		return userDiscountCardRepository.findByUser(user);
	}

}

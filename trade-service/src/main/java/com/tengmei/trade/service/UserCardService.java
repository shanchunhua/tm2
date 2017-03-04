package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.domain.UserDiscountCard;
import com.tengmei.trade.domain.UserTimesCard;
import com.tengmei.trade.domain.WechatUser;

public interface UserCardService {
	public List<UserTimesCard> getValidTimesCards(WechatUser user, Service service);

	public List<UserDiscountCard> getValidCatalogDiscountCard(WechatUser user, ServiceCatalog catalog);

	public List<UserDiscountCard> findDiscountCardByUserAndCatalog(WechatUser user, ServiceCatalog catalog);
}

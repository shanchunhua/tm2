package com.tengmei.trade.service;

import com.tengmei.trade.domain.CatalogDiscountCard;
import com.tengmei.trade.domain.TimesCard;

public interface CardService {
	public TimesCard createTimesCard(TimesCard card);

	public CatalogDiscountCard createDiscountCard(CatalogDiscountCard card);
}

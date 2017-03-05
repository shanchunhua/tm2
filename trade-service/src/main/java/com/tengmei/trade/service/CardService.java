package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.CatalogDiscountCard;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.TimesCard;

public interface CardService {
	public TimesCard createTimesCard(TimesCard card);

	public CatalogDiscountCard createDiscountCard(CatalogDiscountCard card);

	public TimesCard loadTimesCardById(Long id);

	public CatalogDiscountCard loadDiscountCardById(Long id);

	public List<CatalogDiscountCard> loadDiscountCardByStore(Store store);

	public List<TimesCard> loadTimesCardByStore(Store store);
}

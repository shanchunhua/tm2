package com.tengmei.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.CatalogDiscountCard;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.TimesCard;
import com.tengmei.trade.repository.CatalogDiscountCardRepository;
import com.tengmei.trade.repository.ServiceRepository;
import com.tengmei.trade.repository.TimesCardRepository;
import com.tengmei.trade.service.CardService;

@Service
@Transactional
public class CardServiceImpl implements CardService {
	@Autowired
	TimesCardRepository timesCardRepository;
	@Autowired
	CatalogDiscountCardRepository catalogDiscountCardRepository;
	@Autowired
	ServiceRepository serviceRepository;

	@Override
	public TimesCard createTimesCard(TimesCard card) {
		card.setService(serviceRepository.findOne(card.getService().getId()));
		return timesCardRepository.save(card);
	}

	@Override
	public CatalogDiscountCard createDiscountCard(CatalogDiscountCard card) {
		return catalogDiscountCardRepository.save(card);
	}

	@Override
	public TimesCard loadTimesCardById(Long id) {
		return timesCardRepository.findOne(id);
	}

	@Override
	public CatalogDiscountCard loadDiscountCardById(Long id) {
		return catalogDiscountCardRepository.findOne(id);
	}

	@Override
	public List<CatalogDiscountCard> loadDiscountCardByStore(Store store) {
		return catalogDiscountCardRepository.findByStore(store);
	}

	@Override
	public List<TimesCard> loadTimesCardByStore(Store store) {
		return timesCardRepository.findByStore(store);
	}

}

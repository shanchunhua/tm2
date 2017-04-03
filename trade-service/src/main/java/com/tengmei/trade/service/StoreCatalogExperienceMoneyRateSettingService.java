package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.StoreCatalogExperienceMoneyRateSetting;

public interface StoreCatalogExperienceMoneyRateSettingService {
	public void save(List<StoreCatalogExperienceMoneyRateSetting> settings);
	public List<StoreCatalogExperienceMoneyRateSetting> findByStore(Long storeID);
}

package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.StoreCatalogExperienceMoneyRateSetting;

public interface StoreCatalogExperienceMoneyRateSettingService {
	public void save(StoreCatalogExperienceMoneyRateSetting setting);
	public List<StoreCatalogExperienceMoneyRateSetting> findByStore(Long storeID);
}

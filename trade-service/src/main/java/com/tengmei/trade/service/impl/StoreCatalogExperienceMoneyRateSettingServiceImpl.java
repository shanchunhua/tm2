package com.tengmei.trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.StoreCatalogExperienceMoneyRateSetting;
import com.tengmei.trade.repository.StoreCatalogExperienceMoneyRateSettingRepository;
import com.tengmei.trade.service.StoreCatalogExperienceMoneyRateSettingService;

@Service
@Transactional
public class StoreCatalogExperienceMoneyRateSettingServiceImpl
		implements StoreCatalogExperienceMoneyRateSettingService {
	@Autowired
	private StoreCatalogExperienceMoneyRateSettingRepository storeCatalogExperienceMoneyRateSettingRepository;

	@Override
	public void save(StoreCatalogExperienceMoneyRateSetting setting) {
		storeCatalogExperienceMoneyRateSettingRepository.save(setting);
	}

}

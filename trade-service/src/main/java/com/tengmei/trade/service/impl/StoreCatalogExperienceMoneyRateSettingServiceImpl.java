package com.tengmei.trade.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.StoreCatalogExperienceMoneyRateSetting;
import com.tengmei.trade.repository.ServiceCatalogRepository;
import com.tengmei.trade.repository.StoreCatalogExperienceMoneyRateSettingRepository;
import com.tengmei.trade.repository.StoreRepository;
import com.tengmei.trade.service.StoreCatalogExperienceMoneyRateSettingService;

@Service
@Transactional
public class StoreCatalogExperienceMoneyRateSettingServiceImpl
		implements StoreCatalogExperienceMoneyRateSettingService {
	@Autowired
	private StoreCatalogExperienceMoneyRateSettingRepository storeCatalogExperienceMoneyRateSettingRepository;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private ServiceCatalogRepository serviceCatalogRepository;

	@Override
	public void save(StoreCatalogExperienceMoneyRateSetting setting) {
		storeCatalogExperienceMoneyRateSettingRepository.save(setting);
	}

	@Override
	public List<StoreCatalogExperienceMoneyRateSetting> findByStore(Long storeID) {
		Store store = storeRepository.findOne(storeID);
		List<ServiceCatalog> catalogs = serviceCatalogRepository.findAll();
		for (ServiceCatalog serviceCatalog : catalogs) {
			StoreCatalogExperienceMoneyRateSetting setting = storeCatalogExperienceMoneyRateSettingRepository
					.findOneByStoreAndCatalog(store, serviceCatalog);
			if (setting == null) {
				setting = new StoreCatalogExperienceMoneyRateSetting();
				setting.setStore(store);
				setting.setServiceCatalog(serviceCatalog);
				setting.setExperienceMoneyRate(new BigDecimal(0));
				storeCatalogExperienceMoneyRateSettingRepository.save(setting);
			}
		}

		return storeCatalogExperienceMoneyRateSettingRepository.findByStore(store);
	}

}

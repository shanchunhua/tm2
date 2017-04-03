package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.StoreCatalogExperienceMoneyRateSetting;

@Repository
public interface StoreCatalogExperienceMoneyRateSettingRepository extends JpaRepository<StoreCatalogExperienceMoneyRateSetting, Long> {

	List<StoreCatalogExperienceMoneyRateSetting> findByStore(Store store);

	StoreCatalogExperienceMoneyRateSetting findOneByStoreAndCatalog(Store store, ServiceCatalog serviceCatalog);

}

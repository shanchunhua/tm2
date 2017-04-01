package com.tengmei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.StoreCatalogExperienceMoneyRateSetting;

@Repository
public interface StoreCatalogExperienceMoneyRateSettingRepository extends JpaRepository<StoreCatalogExperienceMoneyRateSetting, Long> {

}

package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.CardUsageRecord;

@Repository
public interface CardUsageRecordRepository extends JpaRepository<CardUsageRecord, Long> {

	List<CardUsageRecord> findByUserDiscountCard_Id(Long id);

	List<CardUsageRecord> findByUserTimesCard_Id(Long id);

}

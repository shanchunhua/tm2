package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.TimesCard;

@Repository
public interface TimesCardRepository extends JpaRepository<TimesCard, Long> {

	List<TimesCard> findByStore(Store store);

}

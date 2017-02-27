package com.tengmei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.CatalogDiscountCard;
import com.tengmei.trade.domain.TimesCard;

@Repository
public interface CatalogDiscountCardRepository extends JpaRepository<CatalogDiscountCard, Long> {

}

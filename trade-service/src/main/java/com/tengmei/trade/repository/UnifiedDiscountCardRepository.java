package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.UnifiedDiscountCard;

@Repository
public interface UnifiedDiscountCardRepository extends JpaRepository<UnifiedDiscountCard, Long> {
	List<UnifiedDiscountCard> findByStoreAndDeleted(Store store,Boolean deleted);

}

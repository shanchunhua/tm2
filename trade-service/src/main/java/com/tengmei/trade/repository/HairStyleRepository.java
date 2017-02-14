package com.tengmei.trade.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.HairStyle;
import com.tengmei.trade.domain.Store;

@Repository
public interface HairStyleRepository extends JpaRepository<HairStyle, Long> {
	Page<HairStyle> findByStoreAndDeleted(Store store, Boolean deleted, Pageable pageable);

	Page<HairStyle> findByStoreIsNullAndDeleted(Boolean deleted, Pageable pageable);

	int countByStoreIsNullAndDeleted(boolean b);

	int countByStoreAndDeleted(Store store,boolean deleted);

}

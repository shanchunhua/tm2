package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.HairStyle;
import com.tengmei.trade.domain.Store;

@Repository
public interface HairStyleRepository extends JpaRepository<HairStyle, Long> {
	List<HairStyle> findByStoreAndDeleted(Store store,Boolean deleted);
	List<HairStyle> findByStoreIsNullAndDeleted(Boolean deleted);
	

}

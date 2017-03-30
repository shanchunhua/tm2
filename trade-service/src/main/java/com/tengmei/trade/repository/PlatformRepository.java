package com.tengmei.trade.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Platform;

@Repository
public interface PlatformRepository extends
		PagingAndSortingRepository<Platform, Long> {

}

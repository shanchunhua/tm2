package com.tengmei.trade.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.StoreMessageTemplate;

@Repository
public interface StoreMessageTemplateRepository extends PagingAndSortingRepository<StoreMessageTemplate, Long> {

	StoreMessageTemplate findByMessageTypeAndStore(String messageType,Store store);

}

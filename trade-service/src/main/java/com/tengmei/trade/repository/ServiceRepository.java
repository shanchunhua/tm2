package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.Store;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
	List<Service> findByStoreAndDeleted(Store store,Boolean deleted);

}

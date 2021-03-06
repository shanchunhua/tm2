package com.tengmei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.ServiceCatalog;

@Repository
public interface ServiceCatalogRepository extends JpaRepository<ServiceCatalog, Long> {

}

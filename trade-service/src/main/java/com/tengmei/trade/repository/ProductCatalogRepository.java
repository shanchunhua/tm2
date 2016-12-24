package com.tengmei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.ProductCatalog;

@Repository
public interface ProductCatalogRepository extends JpaRepository<ProductCatalog, Long> {

}

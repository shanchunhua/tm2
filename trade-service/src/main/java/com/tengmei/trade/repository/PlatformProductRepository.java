package com.tengmei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.PlatformProduct;

@Repository
public interface PlatformProductRepository extends JpaRepository<PlatformProduct, Long> {

}

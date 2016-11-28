package com.tenmgei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenmgei.trade.domain.Supplier;
import com.tenmgei.trade.domain.WechatUser;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	Supplier findByUser(WechatUser user);

}

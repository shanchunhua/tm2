package com.tenmgei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenmgei.trade.domain.Store;
import com.tenmgei.trade.domain.WechatUser;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

	Store findByUser(WechatUser user);

}

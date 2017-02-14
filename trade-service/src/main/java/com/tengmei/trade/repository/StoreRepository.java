package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Chain;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

	Store findByUser(WechatUser user);

	List<Store> findByChain(Chain chain);

}

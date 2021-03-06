package com.tengmei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.StoreWallet;

@Repository
public interface StoreWalletRepository extends JpaRepository<StoreWallet, Long> {

}

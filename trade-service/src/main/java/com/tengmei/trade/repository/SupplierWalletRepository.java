package com.tengmei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.SupplierWallet;

@Repository
public interface SupplierWalletRepository extends JpaRepository<SupplierWallet, Long> {

}

package com.tengmei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.WithdrawRequest;

@Repository
public interface WithdrawRequestRepository extends JpaRepository<WithdrawRequest, Long> {

}

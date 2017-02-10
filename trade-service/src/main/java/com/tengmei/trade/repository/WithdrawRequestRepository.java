package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.domain.WithdrawRequest;

@Repository
public interface WithdrawRequestRepository extends JpaRepository<WithdrawRequest, Long> {

	List<WithdrawRequest> findBySupplier(Supplier supplier);

	Page<WithdrawRequest> findByUser(WechatUser user, Pageable pageable);

}

package com.tengmei.trade.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.CustomerOrder;
import com.tengmei.trade.domain.WechatUser;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

	Page<CustomerOrder> findByCustomer(WechatUser user,Pageable pageable);

	Page<CustomerOrder> findByStaff(WechatUser user, Pageable pageable);

}

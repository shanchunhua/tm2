package com.tengmei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.WechatUser;

@Repository
public interface WechatUserRepository extends JpaRepository<WechatUser, Long> {

}

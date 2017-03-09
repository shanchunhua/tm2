package com.tengmei.trade.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.WechatUser;

@Repository
public interface WechatUserRepository extends JpaRepository<WechatUser, Long> {

	WechatUser findByOpenid(String openid);

	WechatUser findByStore(Store store);

	Page<WechatUser> findByParent(WechatUser user, Pageable pageable);

	Integer countByParent(WechatUser user);

}

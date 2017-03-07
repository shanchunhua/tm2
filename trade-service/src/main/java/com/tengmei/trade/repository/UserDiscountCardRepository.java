package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.domain.UserDiscountCard;
import com.tengmei.trade.domain.WechatUser;

@Repository
public interface UserDiscountCardRepository extends JpaRepository<UserDiscountCard, Long> {

	List<UserDiscountCard> findByUserAndCardItems_catalog(WechatUser user, ServiceCatalog catalog);

	List<UserDiscountCard> findByUser(WechatUser user);

}

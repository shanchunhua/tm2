package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.UserTimesCard;
import com.tengmei.trade.domain.WechatUser;

@Repository
public interface UserTimesCardRepository extends JpaRepository<UserTimesCard, Long> {
	List<UserTimesCard> findByUserAndTimesCard_Service(WechatUser user, Service service);

}

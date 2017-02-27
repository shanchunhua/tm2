package com.tengmei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.UserDiscountCard;

@Repository
public interface UserDiscountCardRepository extends JpaRepository<UserDiscountCard, Long> {

}

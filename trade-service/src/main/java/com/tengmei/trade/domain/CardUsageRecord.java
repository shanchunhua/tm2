package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CardUsageRecord {

	@ManyToOne
	@JoinColumn(name = "order_id")
	private CustomerOrder order;
	@ManyToOne
	@JoinColumn(name = "discount_card_id")
	private UserDiscountCard userDiscountCard;
	@ManyToOne
	@JoinColumn(name = "times_card_id")
	private UserTimesCard userTimesCard;
	private  BigDecimal discount;
	
//	private 
	
}

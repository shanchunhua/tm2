package com.tengmei.trade.domain;

import java.math.BigDecimal;

public class CustomerOrder extends BaseEntity {

	private String orderNo;

	private String productName;

	private WechatUser staff;

	private String customerName;

	private String cellphone;

	private WechatUser customer;

	private UserDiscountCard discountCard;

	private UserTimesCard timesCard;

	private BigDecimal price;
	private Integer quantity;
	private BigDecimal total;
	
	private BigDecimal experienceMoney;

	private BigDecimal actualPay;

	private Service service;

	private StoreProduct storeProduct;

	private PlatformProduct platformProduct;

}

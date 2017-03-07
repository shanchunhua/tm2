package com.tengmei.wechat.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.trade.domain.CustomerOrder;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.CustomerOrderService;
import com.tengmei.trade.service.WechatUserService;
@RunWith(SpringRunner.class)
@SpringBootTest(classes={TradeServiceApplication.class})
public class CustomerOrderServiceTest {
	@Autowired
	private CustomerOrderService customerOrderService;
	@Autowired
	private WechatUserService wechatUserService;
	@Test
	@Rollback(false)
	public void testPay() {
		customerOrderService.pay(customerOrderService.findById(5L));
	}
	@Test
	public void testMyOrders(){
		Pageable pageable = new PageRequest(0, 20);
		WechatUser user=wechatUserService.findById(4L);
		 Page<CustomerOrder> result = customerOrderService.findByUser(user, pageable);
		 System.out.println(result);
	}
}

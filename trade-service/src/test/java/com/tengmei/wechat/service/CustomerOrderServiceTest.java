package com.tengmei.wechat.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.trade.service.CustomerOrderService;
@RunWith(SpringRunner.class)
@SpringBootTest(classes={TradeServiceApplication.class})
public class CustomerOrderServiceTest {
	@Autowired
	private CustomerOrderService customerOrderService;
	@Test
	@Rollback(false)
	public void testPay() {
		customerOrderService.pay(customerOrderService.findById(5L));
	}

}

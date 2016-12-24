package com.tengmei.wechat.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
@RunWith(SpringRunner.class)
@SpringBootTest(classes={TradeServiceApplication.class})
public class BasicServiceTest {
	@Autowired
	BasicService basicService;
	@Test
	public void testGetToken() {
		basicService.getAccessToken();
	}

}

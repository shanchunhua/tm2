package com.tengmei.wechat.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TradeServiceApplication.class })
public class MenuServiceTest {
	@Autowired
	BeanFactory beanFactory;
	@Test
	public void test(){
		MenuService menuService=beanFactory.getBean(MenuService.class, "test");
		System.out.println(menuService);
		menuService.createMenu("");
	}
}

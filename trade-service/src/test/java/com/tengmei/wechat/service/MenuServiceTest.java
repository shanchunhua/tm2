package com.tengmei.wechat.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.wechat.vo.CreateMenuRequest;
import com.tengmei.wechat.vo.MenuItem;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TradeServiceApplication.class })
public class MenuServiceTest {
	@Autowired
	BeanFactory beanFactory;

	@Test
	public void test() {
		MenuService menuService = beanFactory.getBean(MenuService.class,
				"MUc9nuSlc-kGPCEB9-Gb6zcEWOUvmf759aTTO1bHP021hQFu2DNLAz00zs4EXOPJAIO8Ls-5Np0JKf8DNKD2FkbwgMHOzQrKGWKxIGQMJYgxtKljPYpkd55eNew6kxV_OCEbAAAUIK");
		System.out.println(menuService);

		CreateMenuRequest request = new CreateMenuRequest();
		MenuItem item = new MenuItem();
		item.setName("商品");
		item.setUrl("http://fps.baizhuanmao.com/productindex");
		request.getButton().add(item);
		item = new MenuItem();
		item.setName("我的");
		item.setUrl("http://fps.baizhuanmao.com/main");
		request.getButton().add(item);
		item = new MenuItem();
		item.setName("二维码");
		item.setUrl("http://fps.baizhuanmao.com/qrcode");
		request.getButton().add(item);

		menuService.createMenu(request);
	}
}

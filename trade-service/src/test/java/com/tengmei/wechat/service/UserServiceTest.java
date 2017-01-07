package com.tengmei.wechat.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.wechat.service.UserService;
import com.tengmei.wechat.vo.UserInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TradeServiceApplication.class })
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	public void testGetUserInfo() {

		UserInfo userInfo = userService.getUserInfo("oVxv2wJh3_HGSqs5gQMvAtR3k8rQ", null);
		String image=userInfo.getHeadimgurl();
		System.out.println(image);
	}

	@Test
	public void testGetSubscribers() {

		userService.getSubscribers(null);
	}
}

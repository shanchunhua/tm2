package com.tengmei.wechat.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.repository.WechatUserRepository;
import com.tengmei.wechat.service.UserService;
import com.tengmei.wechat.vo.SubscriberList;
import com.tengmei.wechat.vo.UserInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TradeServiceApplication.class })
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Autowired
	WechatUserRepository wechatUserRepository;
	@Test
	public void testGetUserInfo() {

		UserInfo userInfo = userService.getUserInfo("oVxv2wJh3_HGSqs5gQMvAtR3k8rQ", null);
		String image=userInfo.getHeadimgurl();
		System.out.println(image);
	}

	@Test
	public void testGetSubscribers() {

		SubscriberList subscriberList=userService.getSubscribers(null);
		Map<String, List<String>> data = subscriberList.getData();
		for (String openid  : data.get("openid")) {
			UserInfo userInfo = userService.getUserInfo(openid, null);
			WechatUser user=new WechatUser();
			user.setUserInfo(userInfo);
			user.getWallet().setUser(user);
			wechatUserRepository.save(user);
		}
	}
}

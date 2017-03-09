package com.tenmgei.trade.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.trade.domain.UserType;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.repository.WechatUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TradeServiceApplication.class })
@Transactional
public class WechatUserRepositoryTest {
	@Autowired
	private WechatUserRepository wechatUserRepository;

	@Test
	@Rollback
	public void testCreateRelation() {
		WechatUser user=new WechatUser();
		user.setType(UserType.CUSTOMER);
		user.setParent(wechatUserRepository.findOne(4L));
		
	}



}

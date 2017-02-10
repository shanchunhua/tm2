package com.tengmei.wechat.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.domain.WithdrawRequest;
import com.tengmei.trade.repository.WechatUserRepository;
import com.tengmei.trade.service.WithdrawRequestService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TradeServiceApplication.class })
public class WithdrawRequestServiceTest {
	@Autowired
	WithdrawRequestService withdrawRequestService;
	@Autowired
	private WechatUserRepository wechatUserRepository;

	@Test
	public void testFindByUser() {
		WechatUser user = wechatUserRepository.findOne(4L);

		PageRequest pageable = new PageRequest(0, 10);
		Page<WithdrawRequest> withdrawRequests = withdrawRequestService.findByUser(user, pageable);
		for (WithdrawRequest withdrawRequest : withdrawRequests.getContent()) {
			System.out.println(withdrawRequest.getAmount());
		}
	}

}

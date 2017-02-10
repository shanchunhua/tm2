package com.tenmgei.trade.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.trade.domain.GenderType;
import com.tengmei.trade.domain.HairStyle;
import com.tengmei.trade.domain.LengthType;
import com.tengmei.trade.domain.StyleType;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.domain.WithdrawRequest;
import com.tengmei.trade.repository.HairStyleRepository;
import com.tengmei.trade.repository.WechatUserRepository;
import com.tengmei.trade.repository.WithdrawRequestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TradeServiceApplication.class })
@Transactional
public class WithdrawRequestRepositoryTest {
	@Autowired
	private WithdrawRequestRepository withdrawRequestRepository;
	@Autowired
	private WechatUserRepository wechatUserRepository;
	@Test
	public void testFindByUser() {
		WechatUser user=wechatUserRepository.findOne(4L);
		
		PageRequest pageable = new PageRequest(0, 10);
		Page<WithdrawRequest> result = withdrawRequestRepository.findByUser(user, pageable);
		for (WithdrawRequest withdrawRequest : result.getContent()) {
			System.out.println(withdrawRequest.getAmount());
		}
	}

	@Test
	@Rollback(false)
	public void testCreate() {
		for (int i = 0; i < 25; i++) {
			HairStyle hairStyle = new HairStyle();
			hairStyle.setGender(GenderType.MALE);
			hairStyle.setLength(LengthType.LONG);
			hairStyle.setStyle(StyleType.STRAIGHT);
			hairStyle.setImage("http://n.sinaimg.cn/sports/transform/20170207/rLPf-fyafcyx7396355.jpg");
//			hairStyleRepository.save(hairStyle);
		}
	}

}

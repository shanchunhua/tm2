package com.tenmgei.trade.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.trade.domain.GenderType;
import com.tengmei.trade.domain.HairStyle;
import com.tengmei.trade.repository.HairStyleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={TradeServiceApplication.class})
@Transactional
public class HairStyleRepositoryTest {
	@Autowired
	private HairStyleRepository hairStyleRepository;


	@Test
	public void testFindByExample() {
		HairStyle hairStyle=new HairStyle();
		hairStyle.setGender(GenderType.FEMALE);
		Example<HairStyle> example=Example.of(hairStyle);
		PageRequest pageable=new PageRequest(0, 10);
		hairStyleRepository.findAll(example, pageable);
	}


}

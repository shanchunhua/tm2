package com.tenmgei.trade.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.SupplierStatus;
import com.tengmei.trade.repository.SupplierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SupplierRepositoryTest {
	@Autowired
	private SupplierRepository supplierRepository;

	@Test
	@Rollback(true)
	public void testSave() {
		Supplier entity = new Supplier();
		entity.setName("測試");
		entity.setCellphone("123456789");
		entity.setRegion("100000,100010,100001");
		entity.setAddress("測試地址");
		entity.setContact("張三");
		supplierRepository.save(entity);
	}
	@Test
	public void testUpdate(){
		Supplier entity  =supplierRepository.findOne(1L);
		entity.setStatus(SupplierStatus.CERTIFED);
		supplierRepository.save(entity);
	}
}

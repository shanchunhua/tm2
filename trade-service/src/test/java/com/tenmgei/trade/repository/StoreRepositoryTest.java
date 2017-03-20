package com.tenmgei.trade.repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.repository.StoreRepository;
import com.tengmei.trade.repository.SupplierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StoreRepositoryTest {
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private SupplierRepository supplierRepository;

	@Test
	public void testSave() {
		Store entity = new Store();
		entity.setName("測試");
		entity.setCellphone("123456789");
		entity.setRegion("100000,100010,100001");
		entity.setAddress("測試地址");
		entity.setContact("張三");
		storeRepository.save(entity);
	}

	@Test
	public void testUpdate() {
		Store entity = storeRepository.findOne(1L);
		entity.setName("updated");
		storeRepository.save(entity);
	}

//	@Test
//	public void testFindById() {
//		Store entity = storeRepository.findOne(1L);
//		Set<Supplier> suppliers=entity.getSuppliers();
//		for (Supplier supplier : suppliers) {
//			System.out.println(supplier.getName());
//		}
//	}
//
//	@Test
//	@Rollback(false)
//	public void testAddSuppliers() {
//		Store entity = storeRepository.findOne(1L);
//		entity.getSuppliers().add(supplierRepository.findOne(2L));
//		storeRepository.save(entity);
//	}

//	@Test
//	@Rollback(false)
//	public void testDeleteSuppliers() {
//		Store entity = storeRepository.findOne(1L);
//		entity.getSuppliers().remove(supplierRepository.findOne(1L));
//		storeRepository.save(entity);
//	}
}

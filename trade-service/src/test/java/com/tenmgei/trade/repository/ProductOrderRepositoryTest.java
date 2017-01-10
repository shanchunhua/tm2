package com.tenmgei.trade.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.trade.domain.PaymentStatus;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.repository.ProductOrderRepository;
import com.tengmei.trade.repository.StoreRepository;
import com.tengmei.trade.repository.SupplierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={TradeServiceApplication.class})
@Transactional
public class ProductOrderRepositoryTest {
	@Autowired
	private ProductOrderRepository productOrderRepository;
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private StoreRepository storeRepository;

	@Test
	public void testCountByProduct_SupplierAndPaymentStatus() {
		Supplier supplier=supplierRepository.findOne(2L);
		Integer count=productOrderRepository.countByProduct_SupplierAndPaymentStatus(supplier, PaymentStatus.NOT_PAID);
		System.out.println(count);	
	}
	@Test
	public void testTotalBySupplier() {
		Supplier supplier=supplierRepository.findOne(2L);
		Object totalAndExperience= productOrderRepository.totalBySupplier(supplier);
		System.out.println(totalAndExperience);
		Object[] temp=(Object[])totalAndExperience;
		System.out.println(temp.length);
		System.out.println(temp[0]);
		System.out.println(temp[1]);
	}
	@Test
	public void testTotalByStore() {
		Store store=storeRepository.findOne(6L);
		Object[] totalAndExperience= (Object[]) productOrderRepository.totalByStore(store);
		System.out.println(totalAndExperience.getClass().getName());
		System.out.println(totalAndExperience.length);
		System.out.println(totalAndExperience[0]);
		System.out.println(totalAndExperience[1]);
	}
}

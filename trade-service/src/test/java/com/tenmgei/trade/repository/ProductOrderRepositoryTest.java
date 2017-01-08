package com.tenmgei.trade.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.trade.domain.PaymentStatus;
import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.repository.ProductOrderRepository;
import com.tengmei.trade.repository.SupplierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={TradeServiceApplication.class})
@Transactional
public class ProductOrderRepositoryTest {
	@Autowired
	private ProductOrderRepository productOrderRepository;
	@Autowired
	private SupplierRepository supplierRepository;

	@Test
	public void testCountByProduct_SupplierAndPaymentStatus() {
		Supplier supplier=supplierRepository.findOne(2L);
		Integer count=productOrderRepository.countByProduct_SupplierAndPaymentStatus(supplier, PaymentStatus.NOT_PAID);
		System.out.println(count);	
	}
	@Test
	public void testTotalBySupplier() {
		Supplier supplier=supplierRepository.findOne(2L);
		BigDecimal[] totalAndExperience=(BigDecimal[]) productOrderRepository.totalBySupplier(supplier);
		System.out.println(totalAndExperience.length);
		System.out.println(totalAndExperience[0]);
		System.out.println(totalAndExperience[1]);
	}
}

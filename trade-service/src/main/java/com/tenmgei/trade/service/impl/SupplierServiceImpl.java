package com.tenmgei.trade.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenmgei.trade.domain.Supplier;
import com.tenmgei.trade.domain.SupplierStatus;
import com.tenmgei.trade.domain.WechatUser;
import com.tenmgei.trade.repository.SupplierRepository;
import com.tenmgei.trade.service.SupplierService;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public void create(Supplier entity) {
		supplierRepository.save(entity);
	}

	@Override
	public void certificate(Long id) {
		Supplier supplier = supplierRepository.findOne(id);
		supplier.setStatus(SupplierStatus.CERTIFED);
		supplierRepository.save(supplier);
	}

	@Override
	public Supplier findSupplier(WechatUser user) {

		return supplierRepository.findByUser(user);
	}

}

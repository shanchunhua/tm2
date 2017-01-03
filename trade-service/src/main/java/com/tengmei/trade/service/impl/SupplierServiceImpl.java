package com.tengmei.trade.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.SupplierStatus;
import com.tengmei.trade.domain.UserType;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.repository.SupplierRepository;
import com.tengmei.trade.repository.WechatUserRepository;
import com.tengmei.trade.service.SupplierService;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;
	@Autowired
	private WechatUserRepository wechatUserRepository;

	@Override
	public void create(Supplier entity) {
		entity.getUser().setType(UserType.SUPPLIER);
		wechatUserRepository.save(entity.getUser());
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

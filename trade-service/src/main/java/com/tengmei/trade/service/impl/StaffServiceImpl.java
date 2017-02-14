package com.tengmei.trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.domain.Store;
import com.tengmei.trade.repository.StaffRepository;
import com.tengmei.trade.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepository;

	@Override
	public int countByStore(Store store) {
		return staffRepository.countByStoreAndDeleted(store, false);
	}

}

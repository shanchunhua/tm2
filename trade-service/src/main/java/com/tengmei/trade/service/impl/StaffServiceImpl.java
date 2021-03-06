package com.tengmei.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.Staff;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.repository.StaffRepository;
import com.tengmei.trade.service.StaffService;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffRepository staffRepository;

	@Override
	public int countByStore(Store store) {
		return staffRepository.countByStoreAndDeleted(store, false);
	}

	@Override
	public Staff create(Staff staff) {
		return staffRepository.save(staff);
	}

	@Override
	public List<Staff> findByStore(Store store) {
		return staffRepository.findByStoreAndDeleted(store, false);
	}

	@Override
	public Staff findById(Long id) {
		return staffRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		Staff staff = staffRepository.findOne(id);
		staff.setDeleted(true);
		staffRepository.save(staff);
	}

}

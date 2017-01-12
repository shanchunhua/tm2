package com.tengmei.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.WithdrawRequest;
import com.tengmei.trade.repository.WithdrawRequestRepository;
import com.tengmei.trade.service.WithdrawRequestService;
@Service
public class WithdrawRequestServiceImpl implements WithdrawRequestService {

	@Autowired
	private WithdrawRequestRepository withdrawRequestRepository;
	@Override
	public List<WithdrawRequest> findBySupplier(Supplier supplier) {
		return withdrawRequestRepository.findBySupplier(supplier);
	}

}

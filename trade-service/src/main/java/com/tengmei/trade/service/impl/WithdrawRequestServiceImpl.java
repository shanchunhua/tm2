package com.tengmei.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.domain.WithdrawRequest;
import com.tengmei.trade.repository.WechatUserRepository;
import com.tengmei.trade.repository.WithdrawRequestRepository;
import com.tengmei.trade.service.WithdrawRequestService;

@Service
@Transactional
public class WithdrawRequestServiceImpl implements WithdrawRequestService {

	@Autowired
	private WithdrawRequestRepository withdrawRequestRepository;

	@Override
	public List<WithdrawRequest> findBySupplier(Supplier supplier) {
		return withdrawRequestRepository.findBySupplier(supplier);
	}

	@Override
	public void create(WithdrawRequest withdrawRequest) {
		withdrawRequestRepository.save(withdrawRequest);
	}

	@Override
	public Page<WithdrawRequest> findByUser(WechatUser user, Pageable pageable) {
		return withdrawRequestRepository.findByUser(user, pageable);
	}

}

package com.tengmei.trade.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.domain.WithdrawRequest;

public interface WithdrawRequestService {
	public List<WithdrawRequest> findBySupplier(Supplier supplier);
	public Page<WithdrawRequest> findByUser(WechatUser user,Pageable pageable);
	public void create(WithdrawRequest withdrawRequest);
}

package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.Supplier;
import com.tengmei.trade.domain.WithdrawRequest;

public interface WithdrawRequestService {
	public List<WithdrawRequest> findBySupplier(Supplier supplier);
}

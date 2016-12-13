package com.tenmgei.trade.service;

import java.util.List;

import com.tenmgei.trade.domain.Supplier;
import com.tenmgei.trade.domain.WithdrawRequest;

public interface WithdrawRequestService {
	public List<WithdrawRequest> findBySupplier(Supplier supplier);
}

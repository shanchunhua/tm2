package com.tengmei.wechat.service;

import java.util.Map;

import com.tengmei.wechat.vo.UnifiedOrderRequest;
import com.tengmei.wechat.vo.UnifiedOrderResponse;

public interface PaymentService {

	public UnifiedOrderResponse createUnifiedOrder(UnifiedOrderRequest request);

	public Map<String, String> createUnifiedOrder(Map<String, Object> request);
	
	public Map<String,Object> transfer(Map<String,Object> request);
}

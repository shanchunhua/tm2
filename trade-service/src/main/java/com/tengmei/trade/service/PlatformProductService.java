package com.tengmei.trade.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tengmei.trade.domain.PlatformProduct;

public interface PlatformProductService {
	Page<PlatformProduct> getAll(Pageable pageable);
}

package com.tengmei.trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.PlatformProduct;
import com.tengmei.trade.repository.PlatformProductRepository;
import com.tengmei.trade.service.PlatformProductService;

@Service
@Transactional
public class PlatformProductServiceImpl implements PlatformProductService {
	@Autowired
	PlatformProductRepository platformProductRepository;

	@Override
	public Page<PlatformProduct> getAll(Pageable pageable) {
		return platformProductRepository.findAll(pageable);
	}

	@Override
	public PlatformProduct find(Long id) {
		return platformProductRepository.findOne(id);
	}

}

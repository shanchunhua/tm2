package com.tengmei.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.HairStyle;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.repository.HairStyleRepository;
import com.tengmei.wechat.service.HairStyleService;

@Service
@Transactional
public class HairStyleServiceImpl implements HairStyleService {
	@Autowired
	private HairStyleRepository hairStyleRepository;

	@Override
	public Page<HairStyle> getGlobalHairStyle(HairStyle example, Pageable pageable) {
		if (example == null) {
			return hairStyleRepository.findByStoreIsNullAndDeleted(false, pageable);
		} else {
			Example<HairStyle> e = Example.of(example);
			return hairStyleRepository.findAll(e, pageable);
		}

	}

	@Override
	public Page<HairStyle> getStoreHairStyle(Store store, Pageable pageable) {
		
		return hairStyleRepository.findByStoreAndDeleted(store, false,pageable);
	}

	@Override
	public void create(HairStyle hairStyle) {
		hairStyleRepository.save(hairStyle);
	}

}

package com.tengmei.wechat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tengmei.trade.domain.HairStyle;
import com.tengmei.trade.domain.Store;

public interface HairStyleService {
	Page<HairStyle> getGlobalHairStyle(HairStyle example, Pageable pageable);

	Page<HairStyle> getStoreHairStyle(Store store, Pageable pageable);

	void create(HairStyle hairStyle);

}

package com.tengmei.trade.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tengmei.trade.domain.HairStyle;
import com.tengmei.trade.domain.Store;

public interface HairStyleService {
	Page<HairStyle> getGlobalHairStyle(HairStyle example, Pageable pageable);

	Page<HairStyle> getStoreHairStyle(Store store, Pageable pageable);

	void create(HairStyle hairStyle);

	/**
	 * 查询全局的时尚发型数量
	 * 
	 * @return
	 */
	int countGlobalHairStyle();
	/**
	 * 统计店铺发型数量
	 * @param store
	 * @return
	 */
	int countStoreHairStyle(Store store);

}

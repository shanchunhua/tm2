package com.tengmei.wechat.service;

import java.util.Map;

public interface SignatureService {

	/**
	 * 签名算法
	 * 
	 * @param o
	 *            要参与签名的数据对象
	 * @return 签名
	 * @throws IllegalAccessException
	 */
	String getSign(Object o) throws IllegalAccessException;

	String getSign(Map<String, Object> map);

}
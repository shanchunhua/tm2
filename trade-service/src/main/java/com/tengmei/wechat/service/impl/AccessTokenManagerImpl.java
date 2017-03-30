package com.tengmei.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tengmei.common.AppException;
import com.tengmei.wechat.service.AccessTokenManager;
import com.tengmei.wechat.vo.AccessTokenReteriver;
import com.tengmei.wechat.vo.CachedObject;

@Service
public class AccessTokenManagerImpl implements AccessTokenManager {
	private static Map<String, CachedObject<String>> tokenMap = new HashMap<String, CachedObject<String>>();
	private static final Logger logger = LoggerFactory.getLogger(AccessTokenManagerImpl.class);

	public String getToken(String key, AccessTokenReteriver reteriver, boolean forceNew) {
		if (tokenMap.get(key) != null && !forceNew) {
			CachedObject<String> object = tokenMap.get(key);
			if (!object.isExpired()) {
				return object.getObject();
			} else {
				if (reteriver != null) {
					object = reteriver.reterive();
					tokenMap.put(key, object);
					return object.getObject();
				}
			}
		} else {
			if (reteriver != null) {
				CachedObject<String> object = reteriver.reterive();
				tokenMap.put(key, object);
				return object.getObject();
			}
		}
		throw new AppException("Token does not exist or is expired");
	}
}

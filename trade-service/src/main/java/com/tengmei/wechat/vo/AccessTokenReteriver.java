package com.tengmei.wechat.vo;

/**
 * 实现此借口提供获取access token的方法，用于AccessTokenManager获取不到token或者token过期时回调
 * 
 * @author sam
 *
 */
public interface AccessTokenReteriver<T> {
	public CachedObject<T> reterive();
}

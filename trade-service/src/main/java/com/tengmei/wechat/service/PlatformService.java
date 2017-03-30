package com.tengmei.wechat.service;

import com.tengmei.trade.domain.Store;

public interface PlatformService {

	void setComponentVerifyTicket(String componentVerifyTicket);

	String getPreAuthCode(boolean b);

	void initializeAppToken(Store store, String auth_code);

	String getComponentAccessToken();

}

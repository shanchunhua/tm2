package com.tenmgei.trade.domain;

public enum WithdrawRequestStatus {

	NEW, // 新创建
	PROCESSING, // 处理中
	SUCCEED, // 成功
	ROLLBACK, // 回退
	FAILED// 失败

}

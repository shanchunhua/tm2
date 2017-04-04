package com.tengmei.trade.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
public class StoreMessageTemplate extends BaseEntity {

	private String name;

	public String getName() {
		return this.name;
	};

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "STORE_ID")
	private Store store;
	private String templateId;
	private String messageType;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

}

package com.tengmei.trade.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@MappedSuperclass
public class CommonEntity {

	@Column(updatable = false)
	private Date createdTime;
	@Column(insertable = false)
	private Date modifiedTime;
	@Column(name = "create_by")
	private Long createBy;
	@Column(name = "update_by")
	private Long updateBy;
	@Version
	private Long version;

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public CommonEntity() {
		super();
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@PrePersist
	public void prePersist() {
		this.createdTime = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.modifiedTime = new Date();
	}

}
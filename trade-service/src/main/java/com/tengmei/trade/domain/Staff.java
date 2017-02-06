package com.tengmei.trade.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Staff extends BaseEntity {

	private String name;
	private String image;
	private GenderType gender;
	private Date birthday;
	private Integer level;
	private String no;
	private String description;
	private Boolean workOnMonday;
	private Boolean workOnTuesday;
	private Boolean workOnWednesday;
	private Boolean workOnThursday;
	private Boolean workOnFriday;
	private Boolean workOnStaurday;
	private Boolean workOnSunday;
	private Boolean deleted;
	
	@ManyToOne
	private Store store;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public GenderType getGender() {
		return gender;
	}
	public void setGender(GenderType gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getWorkOnMonday() {
		return workOnMonday;
	}
	public void setWorkOnMonday(Boolean workOnMonday) {
		this.workOnMonday = workOnMonday;
	}
	public Boolean getWorkOnTuesday() {
		return workOnTuesday;
	}
	public void setWorkOnTuesday(Boolean workOnTuesday) {
		this.workOnTuesday = workOnTuesday;
	}
	public Boolean getWorkOnWednesday() {
		return workOnWednesday;
	}
	public void setWorkOnWednesday(Boolean workOnWednesday) {
		this.workOnWednesday = workOnWednesday;
	}
	public Boolean getWorkOnThursday() {
		return workOnThursday;
	}
	public void setWorkOnThursday(Boolean workOnThursday) {
		this.workOnThursday = workOnThursday;
	}
	public Boolean getWorkOnFriday() {
		return workOnFriday;
	}
	public void setWorkOnFriday(Boolean workOnFriday) {
		this.workOnFriday = workOnFriday;
	}
	public Boolean getWorkOnStaurday() {
		return workOnStaurday;
	}
	public void setWorkOnStaurday(Boolean workOnStaurday) {
		this.workOnStaurday = workOnStaurday;
	}
	public Boolean getWorkOnSunday() {
		return workOnSunday;
	}
	public void setWorkOnSunday(Boolean workOnSunday) {
		this.workOnSunday = workOnSunday;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}

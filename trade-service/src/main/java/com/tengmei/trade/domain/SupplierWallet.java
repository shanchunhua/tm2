package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 店铺的收益统计
 * 
 * @author sam
 *
 */
@Entity
public class SupplierWallet extends CommonEntity {
	@Id
	@GeneratedValue
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	@JoinColumn(name = "ID")
	@OneToOne
	@MapsId
	@JsonIgnore
	private Supplier supplier;
	@Column(precision = 10, scale = 2)
	private BigDecimal total=new BigDecimal(0);
	@Column(precision = 10, scale = 2)
	private BigDecimal withdrawed=new BigDecimal(0);
	/**
	 * left是关键字，改成leftAmount
	 */
	@Column(precision = 10, scale = 2,name="leftAmount")
	private BigDecimal left=new BigDecimal(0);
	
	
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getWithdrawed() {
		return withdrawed;
	}
	public void setWithdrawed(BigDecimal withdrawed) {
		this.withdrawed = withdrawed;
	}
	public BigDecimal getLeft() {
		return left;
	}
	public void setLeft(BigDecimal left) {
		this.left = left;
	}

}

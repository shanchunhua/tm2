package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductOrder extends BaseEntity {

	@Column(precision = 5, scale = 2)
	private BigDecimal experienceMoneyRate;
	private LogisticsStatus logisticsStatus = LogisticsStatus.UNFULFILLED;
	@Column
	private String orderNo = System.nanoTime() + "";
	private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	@Column(precision = 10, scale = 2)
	private BigDecimal total;

	public BigDecimal getExperienceMoneyRate() {
		return experienceMoneyRate;
	}
	public LogisticsStatus getLogisticsStatus() {
		return logisticsStatus;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Product getProduct() {
		return product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Store getStore() {
		return store;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setExperienceMoneyRate(BigDecimal experienceMoneyRate) {
		this.experienceMoneyRate = experienceMoneyRate;
	}

	public void setLogisticsStatus(LogisticsStatus logisticsStatus) {
		this.logisticsStatus = logisticsStatus;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}

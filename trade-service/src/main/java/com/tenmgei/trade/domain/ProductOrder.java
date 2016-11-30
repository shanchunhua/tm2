package com.tenmgei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductOrder extends BaseEntity {

	private Integer quantity;
	@Column(precision = 10, scale = 2)
	private BigDecimal price;
	@Column(precision = 5, scale = 2)
	private BigDecimal experienceMoneyRate;
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;
	private LogisticsStatus logisticsStatus = LogisticsStatus.UNFULFILLED;



	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getExperienceMoneyRate() {
		return experienceMoneyRate;
	}

	public void setExperienceMoneyRate(BigDecimal experienceMoneyRate) {
		this.experienceMoneyRate = experienceMoneyRate;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LogisticsStatus getLogisticsStatus() {
		return logisticsStatus;
	}

	public void setLogisticsStatus(LogisticsStatus logisticsStatus) {
		this.logisticsStatus = logisticsStatus;
	}
}

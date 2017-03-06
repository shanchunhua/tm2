package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 店铺前端的用户订单实体
 * 
 * @author sam
 *
 */
@Entity
public class CustomerOrder extends BaseEntity {

	private BigDecimal actualPay;

	private String cellphone;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private WechatUser customer;

	private String customerName;

	@ManyToOne
	@JoinColumn(name = "discount_card_id")
	private CatalogDiscountCard discountCard;
	private BigDecimal experienceMoney;
	private BigDecimal fromDiscountCard;
	private String orderNo = System.nanoTime() + "";
	@ManyToOne
	@JoinColumn(name = "platform_product_id")
	private PlatformProduct platformProduct;
	private BigDecimal price;
	private String productName;
	// 1 店内服务，2：店内外卖，3：店内卡，4，平台产品
	private Integer ptype;
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;
	// 服务员工
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private WechatUser staff;
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private StoreProduct storeProduct;

	@ManyToOne
	@JoinColumn(name = "times_card_id")
	private TimesCard timesCard;
	private BigDecimal total;
	@ManyToOne
	@JoinColumn(name = "user_discount_card_id")
	private UserDiscountCard userDiscountCard;
	@ManyToOne
	@JoinColumn(name = "user_times_card_id")
	private UserTimesCard userTimesCard;

	private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;

	public BigDecimal getActualPay() {
		return actualPay;
	}

	public String getCellphone() {
		return cellphone;
	}

	public WechatUser getCustomer() {
		return customer;
	}

	public String getCustomerName() {
		return customerName;
	}

	public CatalogDiscountCard getDiscountCard() {
		return discountCard;
	}

	public BigDecimal getExperienceMoney() {
		return experienceMoney;
	}

	public BigDecimal getFromDiscountCard() {
		return fromDiscountCard;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public PlatformProduct getPlatformProduct() {
		return platformProduct;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getProductName() {
		return productName;
	}

	public Integer getPtype() {
		return ptype;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Service getService() {
		return service;
	}

	public WechatUser getStaff() {
		return staff;
	}

	public Store getStore() {
		return store;
	}

	public StoreProduct getStoreProduct() {
		return storeProduct;
	}

	public TimesCard getTimesCard() {
		return timesCard;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public UserDiscountCard getUserDiscountCard() {
		return userDiscountCard;
	}

	public UserTimesCard getUserTimesCard() {
		return userTimesCard;
	}

	public void setActualPay(BigDecimal actualPay) {
		this.actualPay = actualPay;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public void setCustomer(WechatUser customer) {
		this.customer = customer;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setDiscountCard(CatalogDiscountCard discountCard) {
		this.discountCard = discountCard;
	}

	public void setExperienceMoney(BigDecimal experienceMoney) {
		this.experienceMoney = experienceMoney;
	}

	public void setFromDiscountCard(BigDecimal fromDiscountCard) {
		this.fromDiscountCard = fromDiscountCard;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setPlatformProduct(PlatformProduct platformProduct) {
		this.platformProduct = platformProduct;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void setStaff(WechatUser staff) {
		this.staff = staff;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public void setStoreProduct(StoreProduct storeProduct) {
		this.storeProduct = storeProduct;
	}

	public void setTimesCard(TimesCard timesCard) {
		this.timesCard = timesCard;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setUserDiscountCard(UserDiscountCard userDiscountCard) {
		this.userDiscountCard = userDiscountCard;
	}

	public void setUserTimesCard(UserTimesCard userTimesCard) {
		this.userTimesCard = userTimesCard;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}

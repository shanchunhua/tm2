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

	private String orderNo = System.nanoTime() + "";

	private String productName;
	// 服务员工
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private WechatUser staff;

	private String customerName;

	private String cellphone;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private WechatUser customer;
	@ManyToOne
	@JoinColumn(name = "user_discount_card_id")
	private UserDiscountCard userDiscountCard;
	@ManyToOne
	@JoinColumn(name = "user_times_card_id")
	private UserTimesCard userTimesCard;
	@ManyToOne
	@JoinColumn(name = "times_card_id")
	private TimesCard timesCard;
	@ManyToOne
	@JoinColumn(name = "discount_card_id")
	private CatalogDiscountCard discountCard;
	private BigDecimal price;
	private Integer quantity;
	private BigDecimal total;

	private BigDecimal experienceMoney;

	private BigDecimal actualPay;
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private StoreProduct storeProduct;
	@ManyToOne
	@JoinColumn(name = "platform_product_id")
	private PlatformProduct platformProduct;
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public WechatUser getStaff() {
		return staff;
	}

	public void setStaff(WechatUser staff) {
		this.staff = staff;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public WechatUser getCustomer() {
		return customer;
	}

	public void setCustomer(WechatUser customer) {
		this.customer = customer;
	}

	public UserDiscountCard getUserDiscountCard() {
		return userDiscountCard;
	}

	public void setUserDiscountCard(UserDiscountCard userDiscountCard) {
		this.userDiscountCard = userDiscountCard;
	}

	public UserTimesCard getUserTimesCard() {
		return userTimesCard;
	}

	public void setUserTimesCard(UserTimesCard userTimesCard) {
		this.userTimesCard = userTimesCard;
	}

	public TimesCard getTimesCard() {
		return timesCard;
	}

	public void setTimesCard(TimesCard timesCard) {
		this.timesCard = timesCard;
	}

	public CatalogDiscountCard getDiscountCard() {
		return discountCard;
	}

	public void setDiscountCard(CatalogDiscountCard discountCard) {
		this.discountCard = discountCard;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getExperienceMoney() {
		return experienceMoney;
	}

	public void setExperienceMoney(BigDecimal experienceMoney) {
		this.experienceMoney = experienceMoney;
	}

	public BigDecimal getActualPay() {
		return actualPay;
	}

	public void setActualPay(BigDecimal actualPay) {
		this.actualPay = actualPay;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public StoreProduct getStoreProduct() {
		return storeProduct;
	}

	public void setStoreProduct(StoreProduct storeProduct) {
		this.storeProduct = storeProduct;
	}

	public PlatformProduct getPlatformProduct() {
		return platformProduct;
	}

	public void setPlatformProduct(PlatformProduct platformProduct) {
		this.platformProduct = platformProduct;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Integer getPtype() {
		return ptype;
	}

	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}

	// 1 店内服务，2：店内外卖，3：店内卡，4，平台产品
	private Integer ptype;

}

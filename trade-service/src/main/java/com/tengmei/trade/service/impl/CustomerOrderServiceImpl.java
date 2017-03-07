package com.tengmei.trade.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.CatalogDiscountCard;
import com.tengmei.trade.domain.CatalogDiscountCardItem;
import com.tengmei.trade.domain.CustomerOrder;
import com.tengmei.trade.domain.PaymentStatus;
import com.tengmei.trade.domain.TimesCard;
import com.tengmei.trade.domain.UserDiscountCard;
import com.tengmei.trade.domain.UserDiscountCardItem;
import com.tengmei.trade.domain.UserTimesCard;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.repository.CatalogDiscountCardRepository;
import com.tengmei.trade.repository.CustomerOrderRepository;
import com.tengmei.trade.repository.UserDiscountCardRepository;
import com.tengmei.trade.repository.UserTimesCardRepository;
import com.tengmei.trade.service.CustomerOrderService;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {
	@Autowired
	CustomerOrderRepository customerOrderRepository;
	@Autowired
	UserTimesCardRepository userTimesCardRepository;
	@Autowired
	UserDiscountCardRepository userDiscountCardRepository;
	@Autowired
	CatalogDiscountCardRepository catalogDiscountCardRepository;

	@Override
	public CustomerOrder create(CustomerOrder customerOrder) {
		return customerOrderRepository.save(customerOrder);
	}

	public CustomerOrder pay(CustomerOrder order) {
		// 如果是卡类，需要给用户创建他们的卡实例
		if (order.getPtype() == 3) {
			createUserCardInstance(order);
		}
		// 如果用户使用次卡结算
		if (order.getUserTimesCard() != null) {
			UserTimesCard userTimesCard = order.getUserTimesCard();
			userTimesCard.setTimes(userTimesCard.getTimes() - 1);
			userTimesCardRepository.save(userTimesCard);
		}
		// 如果用户使用折扣卡结算
		if (order.getUserDiscountCard() != null) {
			UserDiscountCard userDiscountCard = order.getUserDiscountCard();
			userDiscountCard.setLeftAmount(userDiscountCard.getLeftAmount().subtract(order.getFromDiscountCard()));
			userDiscountCardRepository.save(userDiscountCard);
		}
		order.setPaymentStatus(PaymentStatus.PAID);
		customerOrderRepository.save(order);
		return order;
	}

	private void createUserCardInstance(CustomerOrder order) {
		if (order.getTimesCard() != null) {
			UserTimesCard userTimesCard = new UserTimesCard();
			TimesCard timesCard = order.getTimesCard();
			userTimesCard.setTimesCard(timesCard);
			userTimesCard.setUser(order.getCustomer());
			// TODO 计算有效期
			userTimesCard.setValidDate(new Date());
			userTimesCard.setTimes(timesCard.getTimes());
			userTimesCardRepository.save(userTimesCard);
		}
		if (order.getDiscountCard() != null) {
			UserDiscountCard userDiscountCard = new UserDiscountCard();
			CatalogDiscountCard discountCard = catalogDiscountCardRepository.findOne(order.getDiscountCard().getId());
			userDiscountCard.setCard(discountCard);
			userDiscountCard.setUser(order.getCustomer());
			// TODO 计算有效期
			userDiscountCard.setValidDate(new Date());
			userDiscountCard.setLeftAmount(discountCard.getPrice());
			for (CatalogDiscountCardItem cardItem : discountCard.getItems()) {
				UserDiscountCardItem item = new UserDiscountCardItem();
				item.setCard(userDiscountCard);
				item.setCatalog(cardItem.getCatalog());
				item.setDiscount(cardItem.getDiscount());
				userDiscountCard.getCardItems().add(item);
			}
			userDiscountCardRepository.save(userDiscountCard);
		}
	}

	@Override
	public CustomerOrder findById(Long id) {
		return customerOrderRepository.findOne(id);
	}

	@Override
	public Page<CustomerOrder> findByUser(WechatUser user,Pageable pageable) {
		return customerOrderRepository.findByCustomer(user,pageable);
	}

	@Override
	public Page<CustomerOrder> findByStaff(WechatUser user, Pageable pageable) {
		return customerOrderRepository.findByStaff(user,pageable);
	}

}

package com.tengmei.trade.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.CustomerOrder;
import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.domain.UserDiscountCard;
import com.tengmei.trade.domain.UserTimesCard;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.CustomerOrderService;
import com.tengmei.trade.service.ServiceService;
import com.tengmei.trade.service.UserCardService;
import com.tengmei.trade.service.WechatUserService;

@RestController
@RequestMapping("/rest/storeorders")
public class StoreOrderController {
	@Autowired
	UserCardService userCardService;
	@Autowired
	ServiceService serviceService;
	@Autowired
	WechatUserService wechatUserService;
	@Autowired
	CustomerOrderService customerOrderService;

	/**
	 * 获取用户可用的折扣卡
	 * 
	 * @param id:
	 *            服务id
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/discountcard/{id}", method = RequestMethod.GET)
	public RestResult<?> getUserDiscountCards(@PathVariable Long id, HttpServletRequest request) {
		Service service = serviceService.findById(id);
		ServiceCatalog catalog = service.getCatalog();
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		List<UserDiscountCard> userDiscountCards = userCardService.findDiscountCardByUserAndCatalog(user, catalog);

		return new RestResult<List<UserDiscountCard>>(userDiscountCards);
	}

	/**
	 * 获取用户可用的次卡
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/timescard/{id}", method = RequestMethod.GET)
	public RestResult<?> getUserTimesCards(@PathVariable Long id, HttpServletRequest request) {
		Service service = serviceService.findById(id);
		ServiceCatalog catalog = service.getCatalog();
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		List<UserTimesCard> userTimesCards = userCardService.findTimesCardByUserAndService(user, service);

		return new RestResult<List<UserTimesCard>>(userTimesCards);
	}

	/**
	 * 创建订单
	 * 
	 * @param customerOrder
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public RestResult<CustomerOrder> create(@RequestBody CustomerOrder customerOrder, HttpServletRequest request) {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());
		customerOrder.setStore(user.getStore());
		customerOrder.setCustomer(user);
		customerOrderService.create(customerOrder);
		return new RestResult<CustomerOrder>(customerOrder);
	}

	/**
	 * 查寻当前用户的订单
	 * 
	 * @param customerOrder
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public RestResult<Page<CustomerOrder>> myOrders(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size) {
		Pageable pageable = new PageRequest(page, size);
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());
		return new RestResult<Page<CustomerOrder>>(customerOrderService.findByUser(user, pageable));
	}
	/**
	 * 查寻当前员工服务的订单
	 * 
	 * @param customerOrder
	 * @param request
	 * @return
	 */
	@RequestMapping(path="/myserved",method = RequestMethod.GET)
	public RestResult<Page<CustomerOrder>> myServedOrders(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "20") int size) {
		Pageable pageable = new PageRequest(page, size);
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());
		return new RestResult<Page<CustomerOrder>>(customerOrderService.findByStaff(user, pageable));
	}
}

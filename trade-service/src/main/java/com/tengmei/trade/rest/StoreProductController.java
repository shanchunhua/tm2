package com.tengmei.trade.rest;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tengmei.trade.domain.GenderType;
import com.tengmei.trade.domain.Staff;
import com.tengmei.trade.domain.StoreProduct;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.StaffService;
import com.tengmei.trade.service.StoreProductService;
import com.tengmei.trade.service.WechatUserService;

@RestController
@RequestMapping("/rest/storeproducts")
public class StoreProductController {
	@Autowired
	StoreProductService storeProductService;
	@Autowired
	WechatUserService wechatUserService;

	@RequestMapping(method = RequestMethod.POST)
	public RestResult<StoreProduct> create(MultipartHttpServletRequest request) throws ParseException {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		System.out.println(mpf.getOriginalFilename() + " uploaded!");
		try {
			FileUtils.copyInputStreamToFile(mpf.getInputStream(), new File("/home/sam/1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		StoreProduct storeProduct = new StoreProduct();
		String id = request.getParameter("id");
		if (id != null) {
			storeProduct = storeProductService.findById(Long.valueOf(id));
		}
		storeProduct.setStore(user.getStore());
		storeProduct.setName(request.getParameter("name"));
		storeProduct.setCommissionRate(new BigDecimal(request.getParameter("commissionRate")));
		storeProduct.setPrice(new BigDecimal(request.getParameter("price")));
		storeProduct = storeProductService.create(storeProduct);

		RestResult<StoreProduct> result = new RestResult<StoreProduct>(storeProduct);
		return result;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<StoreProduct>> list(HttpServletRequest request) {

		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());

		RestResult<List<StoreProduct>> result = new RestResult<>();

		result.setData(storeProductService.findByStore(user.getStore()));
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RestResult<StoreProduct> load(@PathVariable Long id) {

		RestResult<StoreProduct> result = new RestResult<StoreProduct>();

		result.setData(storeProductService.findById(id));
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public RestResult<Staff> del(@PathVariable Long id) {

		RestResult<Staff> result = new RestResult<Staff>();
		storeProductService.delete(id);

		return result;
	}
}

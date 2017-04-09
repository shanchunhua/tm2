package com.tengmei.wechat.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.ProductOrder;
import com.tengmei.trade.service.ProductOrderService;
import com.tengmei.wechat.util.StringObjectConverter;

@RestController
@RequestMapping("/wechat/payment")
public class PaymentController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	@Autowired
	private ProductOrderService productOrderService;
	@RequestMapping(path = "/notify", method = RequestMethod.POST)
	public String notify(@RequestBody String content) {
		logger.debug(content);
		Map<String, String> data = StringObjectConverter.convertXML2Object(content);
		String orderNo = data.get("out_trade_no");
		String result_code = data.get("result_code");
		String return_code = data.get("return_code");
		
		if("SUCCESS".equals(return_code)&&"SUCCESS".equals(result_code)){
			//发品商的订单
			if(orderNo.startsWith("FPS_")){
				ProductOrder productOrder=productOrderService.findByOrderNo(orderNo);
				productOrderService.payOrder(productOrder);
			}
			
			return "success";
		}
		return "error";
	}
}

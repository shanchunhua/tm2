package com.tengmei.wechat.rest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.ProductOrder;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.ProductOrderService;
import com.tengmei.wechat.service.PaymentService;
import com.tengmei.wechat.util.RandomStringGenerator;

@RestController
@RequestMapping("/wechat")
public class WechatController {
	private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
	@Value("${wechat.payment.appid}")
	private String appID;
	@Value("${wechat.payment.mchid}")
	private String mchid;
	@Autowired
	ProductOrderService productOrderService;
	@Autowired
	PaymentService paymentService;
	
	@RequestMapping("/payment/unifiedorder")
	public void unifiedOrder(@RequestParam Long id, HttpServletRequest httpServletRequest) {
		WechatUser user = (WechatUser) httpServletRequest.getSession().getAttribute("user");
		ProductOrder order = productOrderService.findById(id);
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("appid", appID);
		request.put("mch_id", mchid);
		request.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
		request.put("body", order.getProduct().getName());
		request.put("out_trade_no", order.getOrderNo());
		request.put("total_fee", order.getTotal().multiply(new BigDecimal(100)).intValue());

		request.put("spbill_create_ip", "127.0.0.1");
		request.put("notify_url", "https://www.tengmei360.com/skeleton-web/notify");
		request.put("trade_type", "JSAPI");
		request.put("openid", user.getUserInfo().getOpenid());

//		HttpTemplate httpTemplate = new HttpTemplate();
//		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//		request.put("sign", sign);
//		String xml = createXML(request);
//		logger.debug(xml);
	}
}

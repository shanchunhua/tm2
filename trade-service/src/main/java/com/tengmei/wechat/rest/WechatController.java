package com.tengmei.wechat.rest;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
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

import com.tengmei.trade.domain.CustomerOrder;
import com.tengmei.trade.domain.ProductOrder;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.CustomerOrderService;
import com.tengmei.trade.service.ProductOrderService;
import com.tengmei.wechat.service.BasicService;
import com.tengmei.wechat.service.PaymentService;
import com.tengmei.wechat.service.SignatureService;
import com.tengmei.wechat.util.RandomStringGenerator;

@RestController
@RequestMapping("/rest/wechat")
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
	@Autowired
	SignatureService signatureService;
	@Autowired
	BasicService basicService;
	@Autowired
	private CustomerOrderService customerOrderService;

	/**
	 * 
	 * @param id
	 * @param type
	 *            1:发品商的订单，2：客户的订单
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping("/payment/unifiedorder")
	public Map<String, Object> unifiedOrder(@RequestParam Long id, @RequestParam(defaultValue = "1") Integer type,
			HttpServletRequest httpServletRequest) {
		WechatUser user = (WechatUser) httpServletRequest.getSession().getAttribute("user");
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("appid", appID);
		request.put("mch_id", mchid);
		request.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
		request.put("spbill_create_ip", "127.0.0.1");
		request.put("notify_url", "https://www.tengmei360.com/wechat/payment/notify");
		request.put("trade_type", "JSAPI");
		request.put("openid", user.getOpenid());

		if (type == 1) {
			ProductOrder order = productOrderService.findById(id);
			request.put("body", order.getProduct().getName());
			request.put("out_trade_no", order.getOrderNo());
			request.put("total_fee", order.getTotal().multiply(new BigDecimal(100)).intValue());
		} else {
			CustomerOrder order = customerOrderService.findById(id);
			request.put("body", order.getProductName());
			request.put("out_trade_no", order.getOrderNo());
			request.put("total_fee", order.getActualPay().multiply(new BigDecimal(100)).intValue());
		}
		String sign = signatureService.getSign(request);
		request.put("sign", sign);
		Map<String, String> unifyOrderResponseMap = paymentService.createUnifiedOrder(request);

		Map<String, Object> result = new HashMap<>();
		result.put("appId", appID);

		result.put("nonceStr", RandomStringGenerator.getRandomStringByLength(32));
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		result.put("timeStamp", timestamp);
		result.put("signType", "MD5");
		result.put("package", "prepay_id=" + unifyOrderResponseMap.get("prepay_id"));
		String paySign = signatureService.getSign(result);
		result.put("paySign", paySign);
		// 页面采用小写的timestamp
		result.put("timestamp", timestamp);
		result.remove("timeStamp");
		return result;
	}

	@RequestMapping("/payment/config")
	public Map<String, String> config(HttpServletRequest httpServletRequest) {
		String nonceStr = RandomStringGenerator.getRandomStringByLength(32);
		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		String jsapiTicket = basicService.getJsApiTicket();
		String url = "http://www.tengmei360.com/index.html";
		String string1 = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url="
				+ url;
		logger.debug(string1);
		String signature = null;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			byte[] digest = crypt.digest();
			signature = byteToHex(digest);
			logger.debug("signature：" + signature);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, String> ret = new HashMap<>();
		// ret.put("url", url);
		ret.put("appId", appID);
		// ret.put("jsapi_ticket", jsapiTicket);
		ret.put("nonceStr", nonceStr);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		return ret;
	}

	@RequestMapping("/payment/notify")
	public void notify(@RequestParam Long id, HttpServletRequest httpServletRequest) {
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
		request.put("notify_url", "https://www.tengmei360.com/wechat/payment/notify");
		request.put("trade_type", "JSAPI");
		request.put("openid", user.getOpenid());

		// HttpTemplate httpTemplate = new HttpTemplate();
		// String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		// request.put("sign", sign);
		// String xml = createXML(request);
		// logger.debug(xml);
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}

package com.tengmei.wechat.service;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tengmei.trade.TradeServiceApplication;
import com.tengmei.trade.domain.ProductOrder;
import com.tengmei.trade.service.ProductOrderService;
import com.tengmei.wechat.util.RandomStringGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TradeServiceApplication.class })
public class PaymentServiceTest {
	@Value("${wechat.payment.appid}")
	private String appID;
	@Value("${wechat.payment.mchid}")
	private String mchid;
	@Autowired
	PaymentService paymentService;
	@Autowired
	ProductOrderService productOrderService;
	@Autowired
	SignatureService signatureService;

	@Test
	public void testCreateUnifiedOrderUnifiedOrderRequest() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateUnifiedOrderMapOfStringObject() {

		ProductOrder order = productOrderService.findById(2L);
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
		request.put("openid", "oVxv2wFnV7u-PEHwOSHb7fJ_a00E");
		String sign = signatureService.getSign(request);
		request.put("sign", sign);
		Map<String,String> result=paymentService.createUnifiedOrder(request);
		System.out.println(result.get("prepay_id"));
	}
	
	@Test
	public void testTransfer() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mch_appid", appID);
		map.put("mchid", mchid);
		map.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
		map.put("partner_trade_no", "T0001");
		map.put("openid", "oVxv2wFnV7u-PEHwOSHb7fJ_a00E");

		//	map.put("check_name", "OPTION_CHECK");// 填写姓名，可提额度至2w
			map.put("check_name", "FORCE_CHECK");
			map.put("re_user_name", "单春华");

		map.put("amount", 100);
		map.put("desc", "百赚猫测试");
		map.put("spbill_create_ip", "127.0.0.1");

		String sign = signatureService.getSign(map);
		map.put("sign", sign);
		paymentService.transfer(map);
	}

}

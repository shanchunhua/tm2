package com.tengmei.wechat.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.service.StoreService;
import com.tengmei.wechat.service.PlatformService;
import com.tengmei.wechat.util.StringObjectConverter;

@RestController
@RequestMapping("/wechat/authorize")
public class PlatformController {
	private static final Logger logger = LoggerFactory.getLogger(PlatformController.class);

	@Autowired
	PlatformService platformService;
	@Autowired
	StoreService storeService;
	@Value("${platform.appid}")
	private String platformAppid;

	/**
	 * 此方法用于接收微信服务器没10分钟发送过来的component verify
	 * ticket，这个用于获取第三方平台的调用凭据，将返回的内容解密，并将其ComponentVerifyTicket注入Platform
	 * Service，已便其调用各种服务
	 * 
	 * @param xml
	 * @param msgSignature
	 * @param timeStamp
	 * @param nonce
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String authorize(@RequestBody String xml, @RequestParam("msg_signature") String msgSignature,
			@RequestParam("timestamp") String timeStamp, @RequestParam("nonce") String nonce) {
		logger.debug("xml{}", xml);
		logger.debug("msgSignature:{}", msgSignature);
		logger.debug("timeStamp:{}", timeStamp);
		logger.debug("nonce:{}", nonce);
		logger.debug("platform appid:{}", platformAppid);
		Map<String, String> data = StringObjectConverter.convertXML2Object(xml);
		WXBizMsgCrypt pc = new WXBizMsgCrypt("weixin", "WLQm30tgc6xWfh4jWWOrnulQIeS2SSWnhsHRRz2c28j", platformAppid);
		String message = pc.decryptEncryptMsg(msgSignature, timeStamp, nonce, data.get("Encrypt"));
		logger.debug(message);
		Map<String, String> object = StringObjectConverter.convertXML2Object(message);
		String componentVerifyTicket = object.get("ComponentVerifyTicket");
		// 将ticket注入平台服务
		platformService.setComponentVerifyTicket(componentVerifyTicket);

		return "success";
	}

	/**
	 * 跳转到公共号授权页面,需要给出appid作为授权页面回调参数，因为公共号的授权码和公共号对应的，不能跨号调用
	 * 
	 */
	@RequestMapping(value = "/store/{id}", method = RequestMethod.GET)
	public ModelAndView redirectToAuthrizedUrl(@PathVariable Long id) {
		String url = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=" + platformAppid
				+ "&pre_auth_code=" + platformService.getPreAuthCode(true)
				+ "&redirect_uri=http://www.tengmei360.com/skeleton-web/authorize/authcode?id=" + id;
		return new ModelAndView("redirect:" + url);

	}

	@RequestMapping(value = "/authcode", method = RequestMethod.GET)
	public String getAuthCode(@RequestParam("auth_code") String auth_code, @RequestParam("id") Long id,
			@RequestParam("expires_in") Integer expires_in) {
		Store store = storeService.findById(id);
		platformService.initializeAppToken(store, auth_code);
		return auth_code;
	}

}

package com.tengmei.wechat.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.WechatUserService;
import com.tengmei.wechat.service.UserService;
import com.tengmei.wechat.util.XmlUtil;
import com.tengmei.wechat.vo.UserInfo;
import com.tengmei.wechat.vo.WechatMessage;

@RestController
@RequestMapping("/wechat/message")
public class MessageController {
	@Value("${wechat.fps.token}")
	private String token;
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private WechatUserService wechatUserService;

	@RequestMapping(method = RequestMethod.GET)
	public String verify(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce,
			@RequestParam(required = false) String echostr) {
		logger.debug(signature);
		logger.debug(timestamp);
		logger.debug(nonce);
		logger.debug(echostr);

		return echostr;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String doMessage(@RequestBody String message) {
		logger.debug(message);
		WechatMessage wechatMessage = XmlUtil.fromXML(message, WechatMessage.class);
		if (wechatMessage.getEvent() != null && wechatMessage.getEvent().equals("subscribe")) {
			onSubscribe(wechatMessage);
		}
		return message;
	}

	protected void onSubscribe(WechatMessage wechatMessage) {
		String openid = wechatMessage.getFromUserName();
		UserInfo userInfo = userService.getUserInfo(openid, null);
		WechatUser user = wechatUserService.findByOpenid(openid);

		if (user == null) {
			user = new WechatUser();
			String pid = wechatMessage.getEventKey();
			if (pid != null) {
				WechatUser parent = wechatUserService.findById(Long.valueOf(pid));
				user.setParent(parent);
			}
			user.setOpenid(openid);
			user.setUserInfo(userInfo);
		} else {
			user.setUserInfo(userInfo);
		}
		wechatUserService.create(user);
	}
}

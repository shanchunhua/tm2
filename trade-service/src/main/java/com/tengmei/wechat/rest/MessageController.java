package com.tengmei.wechat.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat/message")
public class MessageController {
	@Value("${wechat.fps.token}")
	private String token;
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

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

		return message;
	}
}

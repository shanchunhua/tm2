package com.tengmei.wechat.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wechat")
public class WechatController {

	@RequestMapping("/oauth2")
	public void oAuth2(@RequestParam String code, @RequestParam String state) {
		
	}
}

package com.tenmgei.trade.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EntryController {
	@Value("${wechat.appID}")
	String appID;

	@RequestMapping("/wechat/oauth2")
	public void oauth2(@RequestParam String code, @RequestParam String state) {

	}

	public void entry(HttpServletResponse response) {

	}

	@RequestMapping("/redirect/{path}")
	public void redirectToOAuth2(@PathVariable String path, HttpServletResponse response) throws IOException {
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appID + "&redirect_uri="
				+ "http://www.tengmei360.com/" + path + "&response_type=code&scope=snsapi_base&state=" + path
				+ "#wechat_redirect";
		response.sendRedirect(url);
	}

}

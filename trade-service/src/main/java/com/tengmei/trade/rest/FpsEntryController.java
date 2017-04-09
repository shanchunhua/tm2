package com.tengmei.trade.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tengmei.trade.domain.UserType;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.WechatUserService;

@Controller
@RequestMapping("/")
public class FpsEntryController {
	private static final Logger logger = LoggerFactory.getLogger(FpsEntryController.class);
	@Autowired
	private WechatUserService wechatUserService;

	@Value("${fps.url}")
	private String fpsUrl;

	@RequestMapping("/productindex")
	public String productIndex(HttpServletResponse response) throws IOException {
		return "redirect:" + fpsUrl + "index.html#!productindex";
	}

	@RequestMapping("/main")
	public String main(HttpServletRequest request) throws IOException {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		if (user.getType().equals(UserType.SUPPLIER)) {
			return "redirect:" + fpsUrl + "index.html#!supplierindex";
		} else {
			return "redirect:" + fpsUrl + "index.html#!storeindex";
		}
	}

	@RequestMapping("/qrcode")
	public String qrcode(HttpServletRequest request) throws IOException {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		return "redirect:" + fpsUrl + "index.html#!qrcode";
	}

}

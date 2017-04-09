package com.tengmei.trade.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.WechatUserService;
import com.tengmei.wechat.service.AccessTokenService;
import com.tengmei.wechat.service.UserService;
import com.tengmei.wechat.vo.OAuth2AccessToken;
import com.tengmei.wechat.vo.UserInfo;

/**
 * 所有的OAuth2授权操作都在这里
 * 
 * @author sam
 *
 */
@Controller
@RequestMapping("/wechat/oauth2")
public class OAuthController {
	private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);
	@Value("${fps.url}")
	private String fpsUrl;
	@Autowired
	BeanFactory beanFactory;
	@Autowired
	AccessTokenService accessTokenService;

	@Autowired
	private Environment env;
	@Autowired
	private WechatUserService wechatUserService;

	/**
	 * 发品商的OAuth2授权
	 * 
	 * @param code
	 * @param state
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/fps")
	public String oauth2(@RequestParam String code, @RequestParam String state, HttpServletRequest request)
			throws IOException {
		String path = (String) request.getSession().getAttribute("path");
		logger.debug(path);
		// 根据code去那openid
		String openid = accessTokenService.getFpsOAuth2AccessToken(code).getOpenid();

		// query db first
		WechatUser user = wechatUserService.findByOpenid(openid);
		// 用户未注册发品商或店主
		if (user == null) {
			UserService userService = beanFactory.getBean(UserService.class, accessTokenService.getFpsAccessToken());
			UserInfo userInfo = userService.getUserInfo(openid, null);
			user = new WechatUser();
			user.setOpenid(openid);
			user.setUserInfo(userInfo);
			WechatUser paymentUser = (WechatUser) request.getSession().getAttribute("paymentUser");
			user.setUnionid(paymentUser.getUnionid());
			wechatUserService.create(user);
			user.setUserInfo(userInfo);
			request.getSession().setAttribute("user", user);
			return "redirect:" + fpsUrl + "index.html#!/choosetype";
		} else {
			request.getSession().setAttribute("user", user);
			if (user.getType() == null) {
				return "redirect:" + fpsUrl + "index.html#!/choosetype";
			} else {
				return "redirect:" + path;
			}
		}

	}

	@RequestMapping("/payment")
	public String oauth2ForPayment(@RequestParam String code, @RequestParam String state, HttpServletResponse response,
			HttpServletRequest request) throws IOException {

		String appID = env.getProperty("wechat.payment.appid");
		String appSecret = env.getProperty("wechat.payment.appSecret");
		logger.debug(appID);
		logger.debug(appSecret);
		OAuth2AccessToken oAuth2AccessToken = accessTokenService.getOAuth2AccessToken(appID, appSecret, code);
		String openid = oAuth2AccessToken.getOpenid();
		logger.debug(openid);
		WechatUser paymentUser = wechatUserService.findByOpenid(openid);
		if (paymentUser == null) {
			paymentUser = new WechatUser();
			UserService userService = beanFactory.getBean(UserService.class,
					accessTokenService.getAccessToken(appID, appSecret));
			UserInfo userInfo = userService.getUserInfo(openid, null);
			paymentUser.setUserInfo(userInfo);
			wechatUserService.create(paymentUser);
		}
		request.getSession().setAttribute("paymentUser", paymentUser);
		String url = (String) request.getSession().getAttribute("nextOAuth2Url");
		return "redirect:" + url;
	}
}

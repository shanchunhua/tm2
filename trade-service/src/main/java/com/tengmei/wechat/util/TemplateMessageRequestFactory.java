package com.tengmei.wechat.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tengmei.common.AppException;

public class TemplateMessageRequestFactory {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String createPaySuccessMessage(String toUser, String templateId, String url, String first,
			String orderMoneySum, String orderProductName, String remark) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("touser", toUser);
		map.put("template_id", templateId);
		map.put("url", url);
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, String> content = new HashMap<String, String>();
		content.put("value", first);
		content.put("color", "#173177");
		data.put("first", content);
		content = new HashMap<String, String>();
		content.put("value", orderMoneySum);
		content.put("color", "#173177");
		data.put("orderMoneySum", content);
		content = new HashMap<String, String>();
		content.put("value", orderProductName);
		content.put("color", "#173177");
		data.put("orderProductName", content);
		content = new HashMap<String, String>();
		content.put("value", remark);
		content.put("color", "#173177");
		data.put("Remark", content);
		map.put("data", data);
		try {
			String message = objectMapper.writeValueAsString(map);
			System.out.println(message);
			return message;
		} catch (JsonProcessingException e) {

			e.printStackTrace();
			throw new AppException(e);
		}

	}

	public static String createSubscribeSuccessMessage(String toUser, String templateId, String url, String first,
			String keyword1, String keyword2,  String keyword3, String remark) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("touser", toUser);
		map.put("template_id", templateId);
		map.put("url", url);
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, String> content = new HashMap<String, String>();
		content.put("value", first);
		content.put("color", "#173177");
		data.put("first", content);
		content = new HashMap<String, String>();
		content.put("value", keyword1);
		content.put("color", "#173177");
		data.put("keyword1", content);
		content = new HashMap<String, String>();
		content.put("value", keyword2);
		content.put("color", "#173177");
		data.put("keyword2", content);
		content = new HashMap<String, String>();
		content.put("value", keyword3);
		content.put("color", "#173177");
		data.put("keyword3", content);
		content = new HashMap<String, String>();
		content.put("value", remark);
		content.put("color", "#173177");
		data.put("Remark", content);
		map.put("data", data);
		try {
			String message = objectMapper.writeValueAsString(map);
			System.out.println(message);
			return message;
		} catch (JsonProcessingException e) {

			e.printStackTrace();
			throw new AppException(e);
		}

	}

}

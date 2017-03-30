package com.tengmei.wechat.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tengmei.common.AppException;

public class HttpTemplate {
	private static final Logger logger = LoggerFactory.getLogger(HttpTemplate.class);

	public HttpTemplate() {
		super();
	}

	CloseableHttpClient httpclient = HttpClients.createDefault();

	public Map<String, String> get(String url) {
		logger.debug("url:{}", url);
		try {
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpget);
			return processResponse(response);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new AppException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new AppException(e);
		}

	}

	public String getRaw(String url) {
		logger.debug("url:{}", url);
		try {
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(httpget);
			String content = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.debug(content);
			return content;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new AppException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new AppException(e);
		}

	}

	public Map<String, String> post(String url, String data) {
		logger.debug("url:{}", url);
		logger.debug("data:{}", data);
		try {
			HttpPost httppost = new HttpPost(url);
			// httppost.addHeader("content-encoding","utf-8");
			httppost.setEntity(new StringEntity(data, "utf-8"));
			CloseableHttpResponse response = httpclient.execute(httppost);
			return processResponse(response);
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		}
	}

	/**
	 * 返回原始的内容，用于特殊的数据结构
	 * 
	 * @param url
	 * @param data
	 * @return
	 */
	public String postRaw(String url, String data) {
		try {
			HttpPost httppost = new HttpPost(url);
			// httppost.addHeader("content-encoding","utf-8");
			httppost.setEntity(new StringEntity(data, "utf-8"));
			CloseableHttpResponse response = httpclient.execute(httppost);
			String content = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.debug(content);
			return content;
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		}
	}

	private Map<String, String> processResponse(CloseableHttpResponse response) {
		int statusCode = response.getStatusLine().getStatusCode();
		logger.debug("Status Code:{}", statusCode);
		if (statusCode != HttpStatus.SC_OK) {
			logger.error("Incorrect Status Code:" + statusCode);
			throw new AppException("Incorrect Status Code:" + statusCode);
		}

		try {
			String content = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.debug(content);
			ContentType contentType = ContentType.getOrDefault(response.getEntity());
			if (contentType.getMimeType().equals(ContentType.APPLICATION_JSON.getMimeType())
					|| content.startsWith("{")) {
				Map<String, String> result = StringObjectConverter.convertJSON2Object(content);
				// 如果包含errorcode，并且errcode不等于0，说明有错
				if (result.containsKey("errcode") && !result.get("errcode").equals("0")) {
					logger.error("Business Error:" + content);
					throw new AppException("Business Error:" + content);
				}
				return result;
			} else if (contentType.getMimeType().equals(ContentType.APPLICATION_XML.getMimeType())
					|| content.startsWith("<")) {
				Map<String, String> result = StringObjectConverter.convertXML2Object(content);
				// 如果包含return_code是FAIL，说明失败,如果有err_code，说明失败
				if (result.get("return_code").equals("FAIL") || result.containsKey("err_code")) {
					logger.error("Business Error:" + content);
					throw new AppException("Business Error:" + content);
				}
				return result;
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		}
		return null;
	}

}

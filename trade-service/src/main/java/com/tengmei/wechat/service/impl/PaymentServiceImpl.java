package com.tengmei.wechat.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.security.KeyStore;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tengmei.wechat.service.PaymentService;
import com.tengmei.wechat.vo.UnifiedOrderRequest;
import com.tengmei.wechat.vo.UnifiedOrderResponse;

@Service
public class PaymentServiceImpl implements PaymentService {
	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	@Autowired

	RestTemplate restTemplate;
	@Value("${wechat.payment.mchid}")
	private String mchid;
	@Value("${wechat.payment.certLocation}")
	private String certLocation;

	@Override
	public UnifiedOrderResponse createUnifiedOrder(UnifiedOrderRequest request) {
		return null;
	}

	@Override
	public Map<String, String> createUnifiedOrder(Map<String, Object> request) {
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		String xml = createXML(request);
		logger.debug(xml);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> reqEntity = new HttpEntity<String>(xml, headers);
		HttpEntity<String> resEntity = restTemplate.postForEntity(url, reqEntity, String.class);
		logger.debug(resEntity.getBody());
		logger.debug(resEntity.toString());
		return null;
	}

	private static String createXML(Map<String, Object> request) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("xml");
			doc.appendChild(rootElement);
			for (Map.Entry<String, Object> rentry : request.entrySet()) {
				Element e = doc.createElement(rentry.getKey());
				e.setTextContent(rentry.getValue().toString());
				rootElement.appendChild(e);
			}
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> transfer(Map<String, Object> request) {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(certLocation));
			System.out.println(">>>>>>>>>>>>>"+mchid);
			keyStore.load(instream, mchid.toCharArray());//
			instream.close();
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchid.toCharArray()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			HttpPost httpost = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers"); //
			httpost.addHeader("Connection", "keep-alive");
			httpost.addHeader("Accept", "*/*");
			httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			httpost.addHeader("Host", "api.mch.weixin.qq.com");
			httpost.addHeader("X-Requested-With", "XMLHttpRequest");
			httpost.addHeader("Cache-Control", "max-age=0");
			httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
			httpost.setEntity(new StringEntity(createXML(request), "UTF-8"));

			CloseableHttpResponse response = httpclient.execute(httpost);
			org.apache.http.HttpEntity entity = response.getEntity();
			String responseXML = EntityUtils.toString(entity);
			logger.debug(responseXML);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return null;
	}
}

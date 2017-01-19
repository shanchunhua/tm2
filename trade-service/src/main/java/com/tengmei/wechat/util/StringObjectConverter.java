package com.tengmei.wechat.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tengmei.common.AppException;

public class StringObjectConverter {
	private static final Logger logger = LoggerFactory.getLogger(StringObjectConverter.class);

	/**
	 * JSON转换成java的map对象
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, String> convertJSON2Object(String json) {
		logger.debug("json:{}", json);
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
		};
		Map<String, String> data = null;
		try {
			data = objectMapper.readValue(json, typeRef);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		}
		return data;
	}

	public static <T> T convertJSON2Object(String json, Class<T> clazz) {
		logger.debug("json:{}", json);
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<T> typeRef = new TypeReference<T>() {
		};
		T data = null;
		try {
			data = objectMapper.readValue(json, typeRef);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		}
		return data;
	}

	/**
	 * Java的Map对象转换成json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String convertObject2JSON(Map<String, Object> object) {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		}
		logger.debug("json：{}", json);
		return json;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static String convertObject2XML(Map<String, Object> request) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("xml");
			doc.appendChild(rootElement);
			for (Map.Entry<String, Object> rentry : request.entrySet()) {
				Element element = doc.createElement(rentry.getKey());
				element.setTextContent(rentry.getValue().toString());
				rootElement.appendChild(element);
			}
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String xml = writer.getBuffer().toString();// .replaceAll("\n|\r",
														// "");
			logger.debug("xml:{}", xml);
			return xml;
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (TransformerException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		}

	}

	public static Map<String, String> convertXML2Object(String xml) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(xml.getBytes()));
			// 获取到document里面的全部结点
			NodeList allNodes = document.getFirstChild().getChildNodes();
			Node node;
			Map<String, String> map = new HashMap<String, String>();
			int i = 0;
			while (i < allNodes.getLength()) {
				node = allNodes.item(i);
				if (node instanceof Element) {
					map.put(node.getNodeName(), node.getTextContent());
				}
				i++;
			}
			return map;
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (SAXException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AppException(e);
		}

	}

}

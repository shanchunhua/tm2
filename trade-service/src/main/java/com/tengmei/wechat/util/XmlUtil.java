package com.tengmei.wechat.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tengmei.common.AppException;
import com.tengmei.wechat.vo.WechatMessage;


public class XmlUtil {

    public static String toXML(Object obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String createXML(Map<String, Object> request) {
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
			String output = writer.getBuffer().toString();//.replaceAll("\n|\r", "");
			return output;
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
			throw new AppException(e1);
		} catch (TransformerException e) {
			e.printStackTrace();
			throw new AppException(e);
		}

	}
    @SuppressWarnings("unchecked")
    public static <T> T fromXMLFile(String xml, Class<T> valueType) {
        try {
            JAXBContext context = JAXBContext.newInstance(valueType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new FileInputStream(new File(xml)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public static <T> T fromXML(String xml, Class<T> valueType) {
        try {
            JAXBContext context = JAXBContext.newInstance(valueType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
    	WechatMessage res=fromXMLFile("/home/sam/Documents/workspace-sts-3.7.2.RELEASE2/xml2java/src/NewFile.xml", WechatMessage.class);
    	System.out.println(res);
    	 res=fromXML("/home/sam/Documents/workspace-sts-3.7.2.RELEASE2/xml2java/src/msg.xml", WechatMessage.class);
    	System.out.println(res);
	}
}

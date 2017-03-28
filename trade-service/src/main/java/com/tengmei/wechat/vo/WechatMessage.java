package com.tengmei.wechat.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="xml")
public class WechatMessage {
	@XmlElement(name="ToUserName")
	private String ToUserName;
	
	@XmlElement(name="FromUserName")
	private String FromUserName;
	
	@XmlElement(name="CreateTime")
	private String CreateTime;
	
	@XmlElement(name="MsgType")
	private String MsgType;

	@XmlElement(name="Event")
	private String Event;
	
	@XmlElement(name="Content")
	private String Content;
	
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	@XmlElement(name="MsgId")
	private String MsgId;	
	
	
	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	@XmlElement(name="EventKey")
	private String EventKey;
	
	
}

package net.chat.integration.vo;

import java.io.File;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "xml")
public class WeChatRespTextBean {
	private String toUserName;

	private String fromUserName;

	private Long createTime;

	private String msgType;

	private String content;

	public String getToUserName() {
		return toUserName;
	}

	@XmlElement(name = "ToUserName")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	@XmlElement(name = "FromUserName")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	@XmlElement(name = "CreateTime")
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	@XmlElement(name = "MsgType")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	@XmlElement(name = "Content")
	@XmlJavaTypeAdapter(value = CDATAdapter.class)
	public void setContent(String content) {
		this.content = content;
	}

	// 把对象转化成xml
	public void JAXBmarshal(File fRootDir) {
		if (!fRootDir.exists()) {
			fRootDir.mkdirs();
		}
		JAXB.marshal(this, new File(fRootDir, toUserName + ".xml"));
	}

}

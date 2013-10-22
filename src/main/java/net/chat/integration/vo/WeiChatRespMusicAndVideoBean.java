package net.chat.integration.vo;

import java.io.File;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "xml")
public class WeiChatRespMusicAndVideoBean {
	private String toUserName;

	private String fromUserName;

	private Long createTime;

	private String msgType;

	private Music musics;

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

	public Music getMusics() {
		return musics;
	}

	@XmlElement(name = "Music")
	public void setMusics(Music musics) {
		this.musics = musics;
	}

	public static class Music {

		private String title;

		private String description;

		private String musicUrl;

		private String hQMusicUrl;

		public String getTitle() {
			return title;
		}

		@XmlElement(name = "Title")
		@XmlJavaTypeAdapter(value = CDATAdapter.class)
		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		@XmlElement(name = "Description")
		@XmlJavaTypeAdapter(value = CDATAdapter.class)
		public void setDescription(String description) {
			this.description = description;
		}

		public String getMusicUrl() {
			return musicUrl;
		}

		@XmlElement(name = "MusicUrl")
		@XmlJavaTypeAdapter(value = CDATAdapter.class)
		public void setMusicUrl(String musicUrl) {
			this.musicUrl = "http://www.yidia.cn/WeiChat" + musicUrl;
		}

		public String gethQMusicUrl() {
			return hQMusicUrl;
		}

		@XmlElement(name = "HQMusicUrl")
		@XmlJavaTypeAdapter(value = CDATAdapter.class)
		public void sethQMusicUrl(String hQMusicUrl) {
			this.hQMusicUrl = "http://www.yidia.cn/WeiChat" + hQMusicUrl;
		}

	}

	// 把对象转化成xml
	public void JAXBmarshal(File fRootDir) {
		if (!fRootDir.exists()) {
			fRootDir.mkdirs();
		}
		JAXB.marshal(this, new File(fRootDir, toUserName + ".xml"));
	}
}

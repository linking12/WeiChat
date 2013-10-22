package net.chat.integration.vo;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "xml")
public class WeiChatRespImageBean {

	private String toUserName;

	private String fromUserName;

	private Long createTime;

	private String msgType;

	private int articleCount;

	private List<Article> articles;

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

	public int getArticleCount() {
		return articleCount;
	}

	@XmlElement(name = "ArticleCount")
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	@XmlElementWrapper(name = "Articles")
	@XmlElement(name = "item")
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public static class Article {

		private String title;

		private String description;

		private String picUrl;

		private String url;

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

		public String getPicUrl() {
			return picUrl;
		}

		@XmlElement(name = "PicUrl")
		@XmlJavaTypeAdapter(value = CDATAdapter.class)
		public void setPicUrl(String picUrl) {

			this.picUrl = "http://www.yidia.cn/WeiChat" + picUrl;
		}

		public String getUrl() {
			return url;
		}

		@XmlElement(name = "Url")
		@XmlJavaTypeAdapter(value = CDATAdapter.class)
		public void setUrl(String url) {
			this.url = "http://www.yidia.cn/WeiChat" + url;
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

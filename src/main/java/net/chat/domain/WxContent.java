/**
 * 
 */
package net.chat.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bo
 * 
 */
@Entity
@Table(name = "wx_content")
public class WxContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669469035983334839L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "picurl")
	private String picUrl;

	@Column(name = "url")
	private String url;

	@Column(name = "messageid")
	private Long messageId;

	@Column(name = "musicurl")
	private String musicUrl;

	@Column(name = "hqmusicurl")
	private String hqmusicUrl;

	@Column(name = "msgtype")
	private String msgType;

	@Column(name = "baseContentId")
	private Long baseContentId;

	@Column(name = "userId")
	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqmusicUrl() {
		return hqmusicUrl;
	}

	public void setHqmusicUrl(String hqmusicUrl) {
		this.hqmusicUrl = hqmusicUrl;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Long getBaseContentId() {
		return baseContentId;
	}

	public void setBaseContentId(Long baseContentId) {
		this.baseContentId = baseContentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}

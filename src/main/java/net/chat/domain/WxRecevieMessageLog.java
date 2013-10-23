package net.chat.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author apple
 * 
 */
@Entity
@Table(name = "wx_recevie_message_log")
public class WxRecevieMessageLog implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "fromuser")
	private String fromuser;

	@Column(name = "touser")
	private String touser;

	@Column(name = "msgtype")
	private String msgtype;

	@Column(name = "msgid")
	private String msgid;

	@Column(name = "content")
	private String content;

	@Column(name = "picurl")
	private String picurl;

	@Column(name = "picname")
	private String picname;

	@Column(name = "locationx")
	private String locationx;

	@Column(name = "locationy")
	private String locationy;

	@Column(name = "scale")
	private String scale;

	@Column(name = "lable")
	private String lable;

	@Column(name = "url")
	private String url;

	@Column(name = "description")
	private String description;

	@Column(name = "title")
	private String title;

	@Column(name = "eventkey")
	private String eventkey;

	@Column(name = "dtime")
	private String dtime;

	@Column(name = "mediaId")
	private String mediaId;

	@Column(name = "format")
	private String format;

	@Column(name = "recognition")
	private String recognition;

	@Column(name = "thumbMediaId")
	private String thumbMediaId;

	@Column(name = "reqUrl")
	private String reqUrl;

	public String getFromuser() {
		return fromuser;
	}

	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getPicname() {
		return picname;
	}

	public void setPicname(String picname) {
		this.picname = picname;
	}

	public String getLocationx() {
		return locationx;
	}

	public void setLocationx(String locationx) {
		this.locationx = locationx;
	}

	public String getLocationy() {
		return locationy;
	}

	public void setLocationy(String locationy) {
		this.locationy = locationy;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEventkey() {
		return eventkey;
	}

	public void setEventkey(String eventkey) {
		this.eventkey = eventkey;
	}

	public String getDtime() {
		return dtime;
	}

	public void setDtime(String dtime) {
		this.dtime = dtime;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String mormat) {
		this.format = mormat;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}

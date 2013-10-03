/**
 * 
 */
package net.chat.formbean;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Administrator
 * 
 */
public class PictureForm extends AccountForm {

	private Long messageId;

	@NotEmpty(message = "标题不能为空！")
	private String title;

	@NotEmpty(message = "描述不能为空！")
	private String description;

	@NotEmpty(message = "图片不能为空！")
	private String picUrl;

	@NotEmpty(message = "原文链接不能为空！")
	private String url;

	

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
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



	
}

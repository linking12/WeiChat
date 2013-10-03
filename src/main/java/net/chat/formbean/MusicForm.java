/**
 * 
 */
package net.chat.formbean;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Administrator
 *
 */
public class MusicForm extends AccountForm{

	private Long messageId;
	
	@NotEmpty(message = "标题不能为空！")
	private String title;
	
	@NotEmpty(message = "描述不能为空！")
	private String description;
	
	@NotEmpty(message = "音乐地址不能为空！")
	private String musicUrl;
	
	@NotEmpty(message = "高清音乐地址不能为空！")
	private String hqmusicUrl;


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
	
	
}

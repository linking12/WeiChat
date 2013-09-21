/**
 * 
 */
package net.chat.domain;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "wx_message")
public class WxMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8411918579633653216L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "msgtype")
	private String msgType;

	@Column(name = "content")
	private String content;

	@Column(name = "msgname")
	private String msgName;

	@Column(name = "createtime")
	private Date createTime;

	@Column(name = "accountid")
	private Long accountId;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the msgType
	 */
	public String getMsgType() {
		return msgType;
	}

	/**
	 * @param msgType
	 *            the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the msgName
	 */
	public String getMsgName() {
		return msgName;
	}

	/**
	 * @param msgName
	 *            the msgName to set
	 */
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

}

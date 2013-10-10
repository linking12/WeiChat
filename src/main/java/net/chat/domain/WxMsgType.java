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
@Table(name = "wx_msgtype")
public class WxMsgType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4743417733386755228L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "accountid")
	private Long accountId;

	@Column(name = "msgtype")
	private String msgType;

	@Column(name = "action")
	private String action;

	@Column(name = "sourceid")
	private Long sourceId;

	@Column(name = "name")
	private String name;

	@Column(name = "istatus")
	private Integer istatus=0;

	public WxMsgType() {

	}

	public WxMsgType(Long accountId, String msgType, String action, Long sourceId, String name) {
		this.accountId = accountId;
		this.msgType = msgType;
		this.action = action;
		this.sourceId = sourceId;
		this.name = name;

	}

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
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the sourceId
	 */
	public Long getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId
	 *            the sourceId to set
	 */
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the istatus
	 */
	public Integer getIstatus() {
		return istatus;
	}

	/**
	 * @param istatus
	 *            the istatus to set
	 */
	public void setIstatus(Integer istatus) {
		if (null == istatus)
			istatus = 0;
		this.istatus = istatus;
	}

}

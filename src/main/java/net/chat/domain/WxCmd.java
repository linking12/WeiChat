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

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author bo
 * 
 */
@Entity
@Table(name = "wx_cmd")
public class WxCmd implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1665146503915451384L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;
	@NotEmpty(message = "关键字必须输入")
	@Column(name = "cmd")
	private String cmd;

	@Column(name = "messageid")
	private Long messageId;

	@Column(name = "accountid")
	private Long accountId;

	@Column(name = "ctype")
	private String ctype;

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
	 * @return the cmd
	 */
	public String getCmd() {
		return cmd;
	}

	/**
	 * @param cmd
	 *            the cmd to set
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	/**
	 * @return the messageId
	 */
	public Long getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId
	 *            the messageId to set
	 */
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
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
	 * @return the ctype
	 */
	public String getCtype() {
		return ctype;
	}

	/**
	 * @param ctype
	 *            the ctype to set
	 */
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

}

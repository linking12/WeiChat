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
@Table(name = "wx_account_game")
public class WxAccountGame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3607273945993283630L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "accountid")
	private Long accountId;

	@Column(name = "gameid")
	private Long gameId;

	@Column(name = "createtime")
	private Date createTime;

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
	 * @return the gameId
	 */
	public Long getGameId() {
		return gameId;
	}

	/**
	 * @param gameId
	 *            the gameId to set
	 */
	public void setGameId(Long gameId) {
		this.gameId = gameId;
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

}

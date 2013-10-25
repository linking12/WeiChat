package net.chat.domain.mall;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商城定义表
 * 
 * @author bo.chen
 * 
 */
@Entity
@Table(name = "wx_mall")
public class WxMall implements Serializable {

	private static final long serialVersionUID = -5721005604493881049L;

	/**
	 * id 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	/**
	 * 账户ID，关联account表
	 */
	@Column(name = "accountid")
	private Long accountId;

	/**
	 * 商城名称
	 */
	@Column(name = "mallname")
	private String mallName;
	/**
	 * 有效期-起
	 */
	@Column(name = "effectivedate")
	private Date effectiveDate;
	/**
	 * 有效期-终止
	 */
	@Column(name = "expirydate")
	private Date expiryDate;
	/**
	 * 描述
	 */
	@Column(name = "description")
	private String description;
	/**
	 * 商场首页图片url
	 */
	@Column(name = "picurl")
	private String picUrl;
	/**
	 * 创建时间
	 */
	@Column(name = "createdate")
	private Date createDate = new Date();

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
	 * @return the mallName
	 */
	public String getMallName() {
		return mallName;
	}

	/**
	 * @param mallName
	 *            the mallName to set
	 */
	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl
	 *            the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}

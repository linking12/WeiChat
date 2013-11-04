package net.chat.domain.mall;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 购物车
 * 
 * @author bo.chen
 * 
 */
@Entity
@Table(name = "wx_mall_cart")
public class WxMallCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -401347592967663613L;

	/**
	 * id 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;

	/**
	 * 商品id
	 */
	@Column(name = "productid")
	private long productId;

	/**
	 * 件数
	 */
	@Column(name = "count")
	private long count;
	/**
	 * 商场用户id
	 */
	@Column(name = "malluserid")
	private long mallUserId;

	/**
	 * mall id
	 */
	@Column(name = "mallid")
	private long mallId;

	/**
	 * 创建时间
	 */
	@Column(name = "createdate")
	private Date createDate = new Date();

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the productId
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}

	/**
	 * @return the mallUserId
	 */
	public long getMallUserId() {
		return mallUserId;
	}

	/**
	 * @param mallUserId
	 *            the mallUserId to set
	 */
	public void setMallUserId(long mallUserId) {
		this.mallUserId = mallUserId;
	}

	/**
	 * @return the mallId
	 */
	public long getMallId() {
		return mallId;
	}

	/**
	 * @param mallId
	 *            the mallId to set
	 */
	public void setMallId(long mallId) {
		this.mallId = mallId;
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

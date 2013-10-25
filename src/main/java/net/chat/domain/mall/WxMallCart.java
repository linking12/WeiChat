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
@Table(name = "wx_product_price")
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
	private Long id;

	/**
	 * 商品id
	 */
	@Column(name = "productid")
	private Long productId;

	/**
	 * 件数
	 */
	@Column(name = "count")
	private Long count;
	/**
	 * 商场用户id
	 */
	@Column(name = "malluserid")
	private Long mallUserId;

	/**
	 * mall id
	 */
	@Column(name = "mallid")
	private Long mallId;

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
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * @return the mallUserId
	 */
	public Long getMallUserId() {
		return mallUserId;
	}

	/**
	 * @param mallUserId
	 *            the mallUserId to set
	 */
	public void setMallUserId(Long mallUserId) {
		this.mallUserId = mallUserId;
	}

	/**
	 * @return the mallId
	 */
	public Long getMallId() {
		return mallId;
	}

	/**
	 * @param mallId
	 *            the mallId to set
	 */
	public void setMallId(Long mallId) {
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

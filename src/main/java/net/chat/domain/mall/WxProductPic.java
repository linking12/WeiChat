package net.chat.domain.mall;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品图片关系表
 * 
 * @author bo.chen
 * 
 */
@Entity
@Table(name = "wx_product_pic")
public class WxProductPic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8102920540346536436L;

	/**
	 * id 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	/**
	 * 图片名称
	 */
	@Column(name = "picname")
	private String picName;

	/**
	 * 图片url
	 */
	@Column(name = "picurl")
	private String picUrl;

	/**
	 * 商品id
	 */
	@Column(name = "productid")
	private String productId;

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
	 * @return the picName
	 */
	public String getPicName() {
		return picName;
	}

	/**
	 * @param picName
	 *            the picName to set
	 */
	public void setPicName(String picName) {
		this.picName = picName;
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
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
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

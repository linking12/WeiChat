package net.chat.domain.mall;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品和类别绑定表
 * 
 * @author bo.chen
 * 
 */
@Entity
@Table(name = "wx_prdt_category")
public class WxPrdtCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5299471817502129427L;

	/**
	 * id 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	/**
	 * productid
	 */
	@Column(name = "productid")
	private Long productId;

	/**
	 * subcategoryid
	 */
	@Column(name = "subcategoryid")
	private long subCategoryId;

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
	 * @return the subCategoryId
	 */
	public long getSubCategoryId() {
		return subCategoryId;
	}

	/**
	 * @param subCategoryId
	 *            the subCategoryId to set
	 */
	public void setSubCategoryId(long subCategoryId) {
		this.subCategoryId = subCategoryId;
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

package net.chat.domain.mall;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品最终售价表
 * 
 * @author bo.chen
 * 
 */
@Entity
@Table(name = "wx_product_price")
public class WxProductPrice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 526107477181641310L;

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
	 * 单价
	 */
	@Column(name = "saleprice")
	private BigDecimal salePrice;

	/**
	 * 其他费用-运费
	 */
	@Column(name = "expenses")
	private BigDecimal expenses;

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
	 * @return the expenses
	 */
	public BigDecimal getExpenses() {
		return expenses;
	}

	/**
	 * @param expenses
	 *            the expenses to set
	 */
	public void setExpenses(BigDecimal expenses) {
		this.expenses = expenses;
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

	/**
	 * @return the salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice
	 *            the salePrice to set
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

}

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
 * 商品表
 * 
 * @author bo.chen
 * 
 */
@Entity
@Table(name = "wx_product")
public class WxProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2200316987188440595L;

	/**
	 * id 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	/**
	 * 商品名称
	 */
	@Column(name = "productname")
	private String productName;

	/**
	 * 商品价格
	 */
	@Column(name = "productprice")
	private BigDecimal productPrice;

	/**
	 * 库存数量--订单生成后减1
	 */
	@Column(name = "stock")
	private Long stock;

	/**
	 * 商品描述
	 */
	@Column(name = "descrpiton")
	private String descrpiton;

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
	 * 商场id，关联wxmall表
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
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productPrice
	 */
	public BigDecimal getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice
	 *            the productPrice to set
	 */
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the stock
	 */
	public Long getStock() {
		return stock;
	}

	/**
	 * @param stock
	 *            the stock to set
	 */
	public void setStock(Long stock) {
		this.stock = stock;
	}

	/**
	 * @return the descrpiton
	 */
	public String getDescrpiton() {
		return descrpiton;
	}

	/**
	 * @param descrpiton
	 *            the descrpiton to set
	 */
	public void setDescrpiton(String descrpiton) {
		this.descrpiton = descrpiton;
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

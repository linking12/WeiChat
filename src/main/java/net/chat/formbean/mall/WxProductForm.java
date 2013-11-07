/**
 * 
 */
package net.chat.formbean.mall;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author bo.chen
 *
 */
public class WxProductForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3872214138785968333L;

	private long productId;
	
	private String productName;
	
	private String defaultPic;
	
	private List<String> picUrl;
	
	private BigDecimal salePrice;
	
	private BigDecimal expenses;
	
	private BigDecimal originalPrice;
	
	private String descrpiton;
	
	private long stock;

	/**
	 * @return the productId
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(long productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the defaultPic
	 */
	public String getDefaultPic() {
		return defaultPic;
	}

	/**
	 * @param defaultPic the defaultPic to set
	 */
	public void setDefaultPic(String defaultPic) {
		this.defaultPic = defaultPic;
	}

	/**
	 * @return the picUrl
	 */
	public List<String> getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(List<String> picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @return the expenses
	 */
	public BigDecimal getExpenses() {
		return expenses;
	}

	/**
	 * @param expenses the expenses to set
	 */
	public void setExpenses(BigDecimal expenses) {
		this.expenses = expenses;
	}

	/**
	 * @return the originalPrice
	 */
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	/**
	 * @param originalPrice the originalPrice to set
	 */
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	/**
	 * @return the descrpiton
	 */
	public String getDescrpiton() {
		return descrpiton;
	}

	/**
	 * @param descrpiton the descrpiton to set
	 */
	public void setDescrpiton(String descrpiton) {
		this.descrpiton = descrpiton;
	}

	/**
	 * @return the stock
	 */
	public long getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(long stock) {
		this.stock = stock;
	}
	
}

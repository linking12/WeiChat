/**
 * 
 */
package net.chat.formbean.mall;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author bo.chen
 * 
 */
public class WxOrderForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7448013312036162815L;

	private long orderId;

	private String orderNo;

	private int status;

	private String receiver;

	private String phone;

	private String address;

	private long mallId;

	private Date createDate = new Date();

	private BigDecimal salePrice;

	private int expensesType;

	private int payType;

	private long userId;

	private List<WxProductForm> productList;

	/**
	 * @return the orderId
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo
	 *            the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver
	 *            the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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

	/**
	 * @return the expensesType
	 */
	public int getExpensesType() {
		return expensesType;
	}

	/**
	 * @param expensesType
	 *            the expensesType to set
	 */
	public void setExpensesType(int expensesType) {
		this.expensesType = expensesType;
	}

	/**
	 * @return the payType
	 */
	public int getPayType() {
		return payType;
	}

	/**
	 * @param payType
	 *            the payType to set
	 */
	public void setPayType(int payType) {
		this.payType = payType;
	}

	/**
	 * @return the productList
	 */
	public List<WxProductForm> getProductList() {
		return productList;
	}

	/**
	 * @param productList
	 *            the productList to set
	 */
	public void setProductList(List<WxProductForm> productList) {
		this.productList = productList;
	}

	/**
	 * @return the mallId
	 */
	public long getMallId() {
		return mallId;
	}

	/**
	 * @param mallId the mallId to set
	 */
	public void setMallId(long mallId) {
		this.mallId = mallId;
	}

}

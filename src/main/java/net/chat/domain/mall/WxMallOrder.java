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
 * 订单表
 * 
 * @author bo.chen
 * 
 */
@Entity
@Table(name = "wx_mall_order")
public class WxMallOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6059813217372093639L;

	/**
	 * id 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;

	/**
	 * 订单号
	 */
	@Column(name = "orderno")
	private String orderNo;

	/**
	 * 订单状态 (0:待支付,1:支付完成,2:发货,3:已签收,99:已取消)
	 */
	@Column(name = "status")
	private int status=0;

	/**
	 * 收件人
	 */
	@Column(name = "receiver")
	private String receiver;

	/**
	 * 收件人-电话
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * 收件人-地址
	 */
	@Column(name = "address")
	private String address;

	/**
	 * userid
	 */
	@Column(name = "userId")
	private long userId;

	/**
	 * mallid
	 */
	@Column(name = "mallId")
	private long mallId;

	/**
	 * 创建时间
	 */
	@Column(name = "createdate")
	private Date createDate = new Date();


	/**
	 * 创建时间
	 */
	@Column(name = "paydate")
	private Date payDate ;

	
	/**
	 * 售价
	 */
	@Column(name = "saleprice")
	private BigDecimal salePrice;

	/**
	 * 其他费用-运费类型
	 */
	@Column(name = "expressType")
	private int expressType;
	
	/**
	 * 支付类型
	 */
	@Column(name = "payType")
	private int payType;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * @param orderNo the orderNo to set
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
	 * @param status the status to set
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
	 * @param receiver the receiver to set
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
	 * @param phone the phone to set
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
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
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

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
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
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * @return the expressType
	 */
	public int getExpressType() {
		return expressType;
	}

	/**
	 * @param expressType the expressType to set
	 */
	public void setExpressType(int expressType) {
		this.expressType = expressType;
	}

	/**
	 * @return the payType
	 */
	public int getPayType() {
		return payType;
	}

	/**
	 * @param payType the payType to set
	 */
	public void setPayType(int payType) {
		this.payType = payType;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

}

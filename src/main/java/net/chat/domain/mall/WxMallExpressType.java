/**
 * 
 */
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
 * 快递方式
 * @author bo.chen
 *
 */
@Entity
@Table(name = "wx_mall_express_type")
public class WxMallExpressType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6092840935151131057L;

	/**
	 * id 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;
	
	/**
	 * 快递名称
	 */
	@Column(name = "expressname")
	private String expressName;
	
	/**
	 * 快递费用
	 */
	@Column(name = "price")
	private BigDecimal price;
	
	/**
	 * 商城id
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
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the expressName
	 */
	public String getExpressName() {
		return expressName;
	}

	/**
	 * @param expressName the expressName to set
	 */
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	
	
}

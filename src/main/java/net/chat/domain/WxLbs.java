/**
 * 
 */
package net.chat.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author bo.chen
 * 
 */
@Entity
@Table(name = "WX_LBS")
public class WxLbs implements Serializable {

	private static final long serialVersionUID = -5489305700047855877L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private long id;

	@Column(name = "companyname")
	private String companyName;

	@Column(name = "provincecode")
	private String provinceCode;

	@Column(name = "citycode")
	private String cityCode;

	@Column(name = "areacode")
	private String areaCode;

	@Column(name = "address")
	private String address;

	@Column(name = "xpoint")
	private String xPoint;
	
	@Column(name = "ypoint")
	private String yPoint;
	
	@Column(name = "accountid")
	private long accountId;
		
	@Column(name = "createDate")
	private Date createDate;

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
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
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
	 * @return the xPoint
	 */
	public String getxPoint() {
		return xPoint;
	}

	/**
	 * @param xPoint the xPoint to set
	 */
	public void setxPoint(String xPoint) {
		this.xPoint = xPoint;
	}

	/**
	 * @return the yPoint
	 */
	public String getyPoint() {
		return yPoint;
	}

	/**
	 * @param yPoint the yPoint to set
	 */
	public void setyPoint(String yPoint) {
		this.yPoint = yPoint;
	}

	/**
	 * @return the provinceCode
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * @param provinceCode the provinceCode to set
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}

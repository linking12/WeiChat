package net.chat.domain.mall;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 商场用户表
 * 
 * @author bo.chen
 * 
 */
@Entity
@Table(name = "wx_mall_user")
public class WxMallUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8229575912755037119L;

	/**
	 * id 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	/**
	 * 用户名
	 */
	@NotEmpty(message = "姓名不能为空")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "登录名必须是英文或者数字")
	@Column(name = "username")
	private String userName;

	/**
	 * 密码
	 */
	@NotEmpty(message = "用户密码不能为空！")
	@Column(name = "password")
	private String password;

	/**
	 * phoneno
	 */
	@NotEmpty(message = "手机号码不能为空！")
	@Pattern(regexp = "^1[358]\\d{9}$", message = "11位数字，前2位是13开头 或15 开头 或18 开头")
	@Column(name = "phoneno")
	private String phoneNo;

	/**
	 * 密码
	 */
	@Column(name = "address")
	private String address;
	/**
	 * mallid
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo
	 *            the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	 * @return the mallId
	 */
	public long getMallId() {
		return mallId;
	}

	/**
	 * @param mallId
	 *            the mallId to set
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
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}

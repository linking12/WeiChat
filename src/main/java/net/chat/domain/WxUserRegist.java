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
 * @author bo
 * 
 */
@Entity
@Table(name = "wx_user_regist")
public class WxUserRegist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2244110213737034011L;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "phone")
	private String phone;

	@Column(name = "nickname")
	private String nickName;

	@Column(name = "name")
	private String name;

	@Column(name = "sex")
	private String sex;

	@Column(name = "bornyear")
	private String bornYear;

	@Column(name = "birthday")
	private Date birthDay;

	@Column(name = "age")
	private Long age;

	@Column(name = "address")
	private String address;

	@Column(name = "Education")
	private String education;

	@Column(name = "job")
	private String job;

	@Column(name = "income")
	private Long income;

	@Column(name = "dtime")
	private Date dtime;

	@Column(name = "question")
	private String question;

	@Column(name = "`from`")
	private String from;

	@Column(name = "seq")
	private String seq;

	@Column(name = "lastlogon")
	private Date lastLogon;

	@Column(name = "browserseq")
	private String browserSeq;

	@Column(name = "openid")
	private String openId;

	@Column(name = "programid")
	private String programId;

	@Column(name = "status")
	private String status;

	@Column(name = "spassword")
	private String spassword;

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
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the bornYear
	 */
	public String getBornYear() {
		return bornYear;
	}

	/**
	 * @param bornYear
	 *            the bornYear to set
	 */
	public void setBornYear(String bornYear) {
		this.bornYear = bornYear;
	}

	/**
	 * @return the birthDay
	 */
	public Date getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay
	 *            the birthDay to set
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * @return the age
	 */
	public Long getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(Long age) {
		this.age = age;
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
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education
	 *            the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job
	 *            the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}

	/**
	 * @return the income
	 */
	public Long getIncome() {
		return income;
	}

	/**
	 * @param income
	 *            the income to set
	 */
	public void setIncome(Long income) {
		this.income = income;
	}

	/**
	 * @return the dtime
	 */
	public Date getDtime() {
		return dtime;
	}

	/**
	 * @param dtime
	 *            the dtime to set
	 */
	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the seq
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * @param seq
	 *            the seq to set
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}

	/**
	 * @return the lastLogon
	 */
	public Date getLastLogon() {
		return lastLogon;
	}

	/**
	 * @param lastLogon
	 *            the lastLogon to set
	 */
	public void setLastLogon(Date lastLogon) {
		this.lastLogon = lastLogon;
	}

	/**
	 * @return the browserSeq
	 */
	public String getBrowserSeq() {
		return browserSeq;
	}

	/**
	 * @param browserSeq
	 *            the browserSeq to set
	 */
	public void setBrowserSeq(String browserSeq) {
		this.browserSeq = browserSeq;
	}

	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId
	 *            the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}

	/**
	 * @param programId
	 *            the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the spassword
	 */
	public String getSpassword() {
		return spassword;
	}

	/**
	 * @param spassword
	 *            the spassword to set
	 */
	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}

}

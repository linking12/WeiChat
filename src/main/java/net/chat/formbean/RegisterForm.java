package net.chat.formbean;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm {

	private Long userId;

	@NotEmpty(message = "手机号码不能为空！")
	private String phone;

	@NotEmpty(message = "昵称不能为空")
	private String nickName;

	@NotEmpty(message = "姓名不能为空")
	private String name;

	private String sex;

	private String bornYear;

	private Date birthDay;

	private Long age;

	private String address;

	private String education;

	private String job;

	private Long income;

	private Date dtime;

	private String question;

	private String from;

	private String seq;

	private Date lastLogon;

	private String browserSeq;

	private String openId;

	private String programId;

	private String status;

	@NotEmpty(message = "用户密码不能为空！")
	@Size(min = 4, max = 8, message = "密码在4~8位之间")
	private String spassword1;

	@NotEmpty(message = "确认密码不能为空！")
	private String spassword2;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBornYear() {
		return bornYear;
	}

	public void setBornYear(String bornYear) {
		this.bornYear = bornYear;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Long getIncome() {
		return income;
	}

	public void setIncome(Long income) {
		this.income = income;
	}

	public Date getDtime() {
		return dtime;
	}

	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public Date getLastLogon() {
		return lastLogon;
	}

	public void setLastLogon(Date lastLogon) {
		this.lastLogon = lastLogon;
	}

	public String getBrowserSeq() {
		return browserSeq;
	}

	public void setBrowserSeq(String browserSeq) {
		this.browserSeq = browserSeq;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSpassword1() {
		return spassword1;
	}

	public void setSpassword1(String spassword1) {
		this.spassword1 = spassword1;
	}

	public String getSpassword2() {
		return spassword2;
	}

	public void setSpassword2(String spassword2) {
		this.spassword2 = spassword2;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}

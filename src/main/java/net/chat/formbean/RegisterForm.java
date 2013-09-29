package net.chat.formbean;

import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm {
	@NotEmpty(message = "手机号不能为空！")
	private String phone;
	@NotEmpty(message = "用户名不能为空！")
	private String userName;
	@NotEmpty(message = "密码不能为空！")
	private String password;
	@NotEmpty(message = "密码1不能为空！")
	private String password1;
	@NotEmpty(message = "请同意条款")
	private String[] status;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		this.status = status;
	}

}

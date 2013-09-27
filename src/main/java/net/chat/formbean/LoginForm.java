package net.chat.formbean;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
	@NotEmpty(message = "用户名不能为空！")
	private String userName;
	@NotEmpty(message = "密码不能为空！")
	private String password;

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

}

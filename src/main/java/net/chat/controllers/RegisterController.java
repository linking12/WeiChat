package net.chat.controllers;

import javax.validation.Valid;

import net.chat.Constants.PageConstants;
import net.chat.formbean.RegisterForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

	@ModelAttribute("registerForm")
	public RegisterForm createFormBean() {
		return new RegisterForm();
	}

	@RequestMapping("/register")
	public String init() {
		return PageConstants.PAGE_REGISTER;
	}

	@RequestMapping("/register/submit")
	public String submit(@Valid RegisterForm bean, BindingResult result, Model model) {
		if (result.hasErrors()) {

			StringBuffer sb = new StringBuffer();
			if (!bean.getPassword().equals(bean.getPassword1())) {
				sb.append("两次输入的密码必须相同！");
			}
			model.addAttribute("errorMsg", sb);
			model.addAttribute("registerForm", bean);
			return PageConstants.PAGE_REGISTER;
		}
		return PageConstants.PAGE_LOGIN_1;
	}
}

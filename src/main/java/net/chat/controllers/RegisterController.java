package net.chat.controllers;

import javax.validation.Valid;

import net.chat.constants.PageConstants;
import net.chat.domain.User;
import net.chat.formbean.RegisterForm;
import net.chat.service.UserSerivce;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

	@Autowired
	UserSerivce userService;

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
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotBlank(bean.getUserName())) {
			User user = userService.findByName(bean.getUserName());
			if (null != user) {
				sb.append("该用户已存在！\n");
			}
		}
		if (!bean.getPassword1().equals(bean.getPassword())) {
			sb.append("两次输入密码必须一致!\n");
		}

		if (!result.hasErrors() && StringUtils.isBlank(sb.toString())) {
			User user = new User();
			user.setAccount(bean.getUserName());
			user.setPassWord(bean.getPassword());
			user.setEnable(1l);
			try {
				userService.save(user);
			} catch (Exception e) {
				sb.append("用户注册失败，请联系管理员！");
				model.addAttribute("errorMsg", sb);
				model.addAttribute("registerForm", bean);
				return PageConstants.PAGE_REGISTER;
			}
			return PageConstants.PAGE_LOGIN;
		}

		model.addAttribute("errorMsg", sb);
		model.addAttribute("registerForm", bean);
		return PageConstants.PAGE_REGISTER;

	}
}

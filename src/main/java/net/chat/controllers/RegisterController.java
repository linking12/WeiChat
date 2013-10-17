package net.chat.controllers;

import javax.validation.Valid;

import net.chat.constants.PageConstants;
import net.chat.formbean.RegisterForm;
import net.chat.service.RegeditUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

	@Autowired
	private RegeditUserService regeditUser;

	@ModelAttribute("registerForm")
	public RegisterForm createFormBean() {
		return new RegisterForm();
	}

	@RequestMapping("/register")
	public String init() {
		return PageConstants.PAGE_REGISTER;
	}

	@RequestMapping("/register/submit")
	public String submit(@Valid RegisterForm bean, BindingResult result,
			Model model) {
		try {
			regeditUser.regeditUser(bean);
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("registerForm", bean);
			return PageConstants.PAGE_REGISTER;
		}
		return PageConstants.PAGE_LOGIN;
	}

	@RequestMapping("/index")
	public String index() {
		return PageConstants.PAGE_INDEX;
	}

}

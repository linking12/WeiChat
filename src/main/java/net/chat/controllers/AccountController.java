package net.chat.controllers;

import java.util.List;

import javax.validation.Valid;

import net.chat.constants.PageConstants;
import net.chat.dao.WxAccountDao;
import net.chat.domain.WxAccount;
import net.chat.formbean.RegisterForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	// @ModelAttribute("registerForm")
	// public RegisterForm createFormBean() {
	// return new RegisterForm();
	// }

	@Autowired
	private WxAccountDao accountDao;

	@RequestMapping("/list")
	public String list(Model model) {
		List<WxAccount> accounts = accountDao.findAll();
		model.addAttribute("accounts", accounts);
		// return PageConstants.PAGE_ACCOUNT_LIST;
		return PageConstants.PAGE_ACCOUNT_LIST;
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("title", "添加帐号");
		model.addAttribute("accountForm", new WxAccount());
		return PageConstants.PAGE_ACCOUNT_DETAIL;
	}

	@RequestMapping("/edit")
	public String edit(Model model) {
		model.addAttribute("title", "修改帐号");
		model.addAttribute("accountForm", new WxAccount());
		return PageConstants.PAGE_ACCOUNT_DETAIL;
	}

	@RequestMapping("/delete")
	public String delete() {
		return "redirect:/account/list";
	}

	@RequestMapping("/submit")
	public String submit(@Valid RegisterForm bean, BindingResult result, Model model) {
		// if (result.hasErrors()) {
		//
		// StringBuffer sb = new StringBuffer();
		// if (!bean.getPassword().equals(bean.getPassword1())) {
		// sb.append("两次输入的密码必须相同！");
		// }
		// model.addAttribute("errorMsg", sb);
		// model.addAttribute("registerForm", bean);
		// return PageConstants.PAGE_REGISTER;
		// }
		return "redirect:/account/list";
	}
}

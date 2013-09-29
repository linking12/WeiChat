package net.chat.controllers;

import java.util.List;

import javax.validation.Valid;

import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.formbean.RegisterForm;
import net.chat.service.AccountService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	// @ModelAttribute("registerForm")
	// public RegisterForm createFormBean() {
	// return new RegisterForm();
	// }

	@Autowired
	private AccountService accountService;

	@RequestMapping("/list")
	public String list(Model model) {
		List<WxAccount> accounts = accountService.listAllAcount();
		model.addAttribute("accounts", accounts);
		// return PageConstants.PAGE_ACCOUNT_LIST;
		return PageConstants.PAGE_ACCOUNT_LIST;
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("title", "添加帐号");
		WxAccount account = new WxAccount();
		account.setSeq(RandomStringUtils.randomAlphabetic(20));
		account.setUrl("/wxserv/" + RandomStringUtils.randomAlphabetic(20) + ".jsp");
		model.addAttribute("accountForm", account);
		return PageConstants.PAGE_ACCOUNT_DETAIL;
	}

	@RequestMapping("/edit/{accountId}")
	public String edit(@PathVariable("accountId") Long accountId, Model model) {
		model.addAttribute("title", "修改帐号");
		WxAccount account = accountService.findAcountById(accountId);
		model.addAttribute("accountForm", account);
		return PageConstants.PAGE_ACCOUNT_DETAIL;
	}

	@RequestMapping("/delete/{accountId}")
	public String delete(@PathVariable("accountId") Long accountId) {
		accountService.deleteAccount(accountId);
		return "redirect:/account/list";
	}

	@RequestMapping("/submit")
	public String submit(@Valid WxAccount account, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("accountForm", account);
			return PageConstants.PAGE_REGISTER;
		}
		if (null == account.getId() || 0 == account.getId()) {
			accountService.saveAccount(account);
		} else {
			accountService.updateAccount(account.getId(), account.getName(), account.getNote());
		}
		return "redirect:/account/list";
	}
}

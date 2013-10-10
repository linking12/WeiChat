package net.chat.controllers;

import javax.validation.Valid;

import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.service.AccountService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping("/list")
	public String list(Model model) {
		Page<WxAccount> accounts = accountService.listAllAcount(0, 0);
		model.addAttribute("accounts", accounts.getContent());
		return PageConstants.PAGE_ACCOUNT_LIST;
	}

	@RequestMapping("/add")
	public String add(Model model) {
		WxAccount account = new WxAccount();
		account.setSeq(RandomStringUtils.randomAlphabetic(20));
		account.setUrl("/wxserv/" + RandomStringUtils.randomAlphabetic(20)
				+ ".jsp");
		model.addAttribute("wxAccount", account);
		return PageConstants.PAGE_ACCOUNT_DETAIL;
	}

	@RequestMapping("/edit/{accountId}")
	public String edit(@PathVariable("accountId") Long accountId, Model model) {
		WxAccount account = accountService.findAcountById(accountId);
		model.addAttribute("wxAccount", account);
		return PageConstants.PAGE_ACCOUNT_DETAIL;
	}

	@RequestMapping("/delete/{accountId}")
	public String delete(@PathVariable("accountId") Long accountId) {
		accountService.deleteAccount(accountId);
		return "redirect:/account/list";
	}

	@RequestMapping("/submit")
	public String submit(@Valid WxAccount account, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("wxAccount", account);
			return PageConstants.PAGE_ACCOUNT_DETAIL;
		}
		if (null == account.getId() || 0 == account.getId()) {
			accountService.saveAccount(account);
		} else {
			accountService.editAccount(account);

		}
		return "redirect:/account/list";
	}
}

package net.chat.controllers;

import java.util.List;

import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.service.AccountService;
import net.chat.utils.AppContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/custommenu")
public class CustomMenuController {
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/init")
	public String init(@RequestParam(value = "accountId", required = false)Long accountId,Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		if (CollectionUtils.isEmpty(accounts)) {
			return "redirect:/account/add";
		}
		return PageConstants.PAGE_CUSTOMMENU_LIST;
	}

	@RequestMapping("/add/{accountId}")
	public String add(@PathVariable("accountId") Long accountId,Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		return PageConstants.PAGE_CUSTOMMENU_DETAIL;
	}

	@RequestMapping("/edit")
	public String edit(Model model) {
		return PageConstants.PAGE_CUSTOMMENU_DETAIL;
	}

	@RequestMapping("/delete")
	public String delete(Model model) {
		return PageConstants.PAGE_CUSTOMMENU_LIST;
	}
}

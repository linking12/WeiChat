package net.chat.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.domain.WxCmd;
import net.chat.domain.WxMessage;
import net.chat.formbean.SimpleBean;
import net.chat.service.AccountService;
import net.chat.service.CmdService;
import net.chat.service.MessageService;
import net.chat.utils.AppContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cmd")
public class CmdController {
	@Autowired
	AccountService accountService;
	@Autowired
	CmdService cmdService;
	@Autowired
	MessageService messageService;

	@RequestMapping("/init")
	public String init(@RequestParam(value = "accountId", required = false) Long accountId, @RequestParam(value = "condition", required = false) String condition,
			@RequestParam(value = "page", defaultValue = "1") int pageNo, Model model) throws UnsupportedEncodingException {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		if (CollectionUtils.isEmpty(accounts)) {
			return "redirect:/account/add";
		} else {
			if (null == accountId) {
				accountId = accounts.get(0).getId();
			}
			if (StringUtils.isNotBlank(condition)) {
				condition = new String(condition.getBytes("iso-8859-1"), "utf-8");
			}
			List<WxCmd> cmds = cmdService.findCmdByAccountId(accountId, condition);
			model.addAttribute("accountId", accountId);
			model.addAttribute("condition", condition);
			model.addAttribute("cmds", cmds);
			model.addAttribute("condition", condition);

			List<WxMessage> messages = messageService.findMessageByAccountId(accountId);
			model.addAttribute("messages", messages);

			List<SimpleBean> ctypeList = PageConstants.buildCtypeList();
			model.addAttribute("ctypeList", ctypeList);

			return PageConstants.PAGE_KEYWORD_LIST;
		}

	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		WxCmd cmd = cmdService.findOne(id);
		Long accountId = cmd.getAccountId();
		WxAccount account = accountService.findAcountById(accountId);
		List<WxAccount> accounts = new ArrayList<WxAccount>();
		accounts.add(account);
		model.addAttribute("accounts", accounts);
		List<WxMessage> messages = messageService.findMessageByAccountId(accountId);
		model.addAttribute("messages", messages);
		model.addAttribute("wxCmd", cmd);

		List<SimpleBean> ctypeList = PageConstants.buildCtypeList();
		model.addAttribute("ctypeList", ctypeList);

		return PageConstants.PAGE_KEYWORD_DETAIL;
	}

	@RequestMapping("/add")
	public String add(@RequestParam(value = "accountId", required = true) Long accountId, Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		if (CollectionUtils.isEmpty(accounts)) {
			return "redirect:/account/add";
		} else {
			if (null == accountId) {
				accountId = accounts.get(0).getId();
			}
			List<WxMessage> messages = messageService.findMessageByAccountId(accountId);
			model.addAttribute("messages", messages);
			WxCmd cmd = new WxCmd();
			cmd.setAccountId(accountId);
			model.addAttribute("wxCmd", cmd);

			List<SimpleBean> ctypeList = PageConstants.buildCtypeList();
			model.addAttribute("ctypeList", ctypeList);

			return PageConstants.PAGE_KEYWORD_DETAIL;
		}
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		cmdService.delete(id);
		return "redirect:/cmd/init";
	}

	@RequestMapping("/submit")
	public String submit(@Valid WxCmd cmd, BindingResult result,Model model) {
		if (result.hasErrors()) {
			Long userId = AppContext.getUserId();
			List<WxAccount> accounts = accountService.findAccountByUserId(userId);
			model.addAttribute("accounts", accounts);
			
			List<WxMessage> messages = messageService.findMessageByAccountId(cmd.getAccountId());
			model.addAttribute("messages", messages);
			
			List<SimpleBean> ctypeList = PageConstants.buildCtypeList();
			model.addAttribute("ctypeList", ctypeList);
			
			model.addAttribute("wxCmd",cmd);
			return PageConstants.PAGE_KEYWORD_DETAIL;
		}
		cmd = cmdService.save(cmd);
		return "redirect:/cmd/init?accountId="+cmd.getAccountId();
	}
}

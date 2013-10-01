package net.chat.controllers;

import java.util.List;

import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.domain.WxGame;
import net.chat.domain.WxMessage;
import net.chat.domain.WxMsgType;
import net.chat.formbean.ReplyMsgForm;
import net.chat.service.AccountService;
import net.chat.service.GameService;
import net.chat.service.MessageService;
import net.chat.service.MsgTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/replymsg")
public class ReplyMsgController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private MsgTypeService msgTypeService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private GameService gameService;

	@RequestMapping("/init")
	public String init(
			@RequestParam(value = "accountId", required = false) Long accountId,
			Model model) {
		Page<WxAccount> Pageaccounts = accountService.listAllAcount(0, 0);
		List<WxAccount> accounts = Pageaccounts.getContent();
		model.addAttribute("accounts", accounts);
		ReplyMsgForm form = new ReplyMsgForm();
		if (!CollectionUtils.isEmpty(accounts)) {
			if (!isExistingAccount(accountId, accounts)) {
				accountId = accounts.get(0).getId();
			}
			form.setAccountId(accountId);
			if (null != accountId) {
				List<WxMessage> messages = messageService
						.findMessageByAccountId(accountId);
				model.addAttribute("messages", messages);

				List<WxGame> games = gameService.finaAll();
				model.addAttribute("games", games);

				List<WxMsgType> msgTypes = msgTypeService
						.findMsgTypeByAccountId(accountId);
				model.addAttribute("msgTypes", msgTypes);

				form.setMsgTypes(msgTypes);
			}

			model.addAttribute("replyMsgForm", form);
		}
		return PageConstants.PAGE_RELPY_MSG;
	}

	@RequestMapping("/save")
	public String save(ReplyMsgForm bean, Model model) {
		Long accountId = bean.getAccountId();
		List<WxMsgType> msgTypes = msgTypeService
				.findMsgTypeByAccountId(accountId);

		List<WxMsgType> pmsg = bean.getMsgTypes();
		this.mergeViewData(msgTypes, pmsg);
		msgTypeService.save(msgTypes);
		return "redirect:/replymsg/init?accountid=" + accountId;
	}

	private void mergeViewData(List<WxMsgType> source, List<WxMsgType> viewdata) {
		for (WxMsgType msg : source) {
			l: for (WxMsgType view : viewdata) {
				if (msg.getId() == view.getId()) {
					msg.setAction(view.getAction());
					msg.setSourceId(view.getSourceId());
					break l;
				}
			}

		}
	}

	private boolean isExistingAccount(Long accountId, List<WxAccount> accounts) {
		if (null == accountId) {
			return false;
		} else {
			for (WxAccount acc : accounts) {
				if (acc.getId() == accountId) {
					return true;
				}
			}
		}
		return false;
	}

}

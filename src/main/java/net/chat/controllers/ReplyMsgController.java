package net.chat.controllers;

import java.util.List;

import javax.validation.Valid;

import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.domain.WxGame;
import net.chat.domain.WxMessage;
import net.chat.domain.WxMsgType;
import net.chat.formbean.ReplyMsgForm;
import net.chat.formbean.SimpleBean;
import net.chat.service.AccountService;
import net.chat.service.GameService;
import net.chat.service.MessageService;
import net.chat.service.MsgTypeService;
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
	public String init(@RequestParam(value = "accountId", required = false) Long accountId, Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		ReplyMsgForm form = new ReplyMsgForm();

		if (CollectionUtils.isEmpty(accounts)) {
			return "redirect:/account/add";
		}

		if (null == accountId)
			accountId = accounts.get(0).getId();

		form.setAccountId(accountId);
		if (null != accountId) {
			List<WxMessage> messages = messageService.findMessageByAccountId(accountId);
			model.addAttribute("messages", messages);

			List<WxGame> games = gameService.findGameByAccountId(accountId);
			model.addAttribute("games", games);

			List<WxMsgType> msgTypes = msgTypeService.findMsgTypeByAccountId(accountId);
			model.addAttribute("msgTypes", msgTypes);

			form.setMsgTypes(msgTypes);

			List<SimpleBean> actionTypes = PageConstants.buildActionTypesList();
			model.addAttribute("actionTypes", actionTypes);

		}

		model.addAttribute("replyMsgForm", form);

		return PageConstants.PAGE_RELPY_MSG;
	}

	@RequestMapping("/save")
	public String save(ReplyMsgForm bean, Model model) {
		Long accountId = bean.getAccountId();
		List<WxMsgType> msgTypes = msgTypeService.findMsgTypeByAccountId(accountId);
		List<WxMsgType> pmsg = bean.getMsgTypes();
		this.mergeViewData(msgTypes, pmsg);
		msgTypeService.save(msgTypes);
		return "redirect:/replymsg/init?accountId=" + accountId;
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		msgTypeService.delete(id);
		return "redirect:/replymsg/init";
	}

	@RequestMapping("/add/{accountId}")
	public String add(@PathVariable("accountId") Long accountId, Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		WxMsgType wxMsgType = new WxMsgType();
		if (CollectionUtils.isEmpty(accounts)) {
			return "redirect:/account/add";
		}
		wxMsgType.setAccountId(accountId);

		List<WxMessage> messages = messageService.findMessageByAccountId(accountId);
		model.addAttribute("messages", messages);

		List<WxGame> games = gameService.findGameByAccountId(accountId);
		model.addAttribute("games", games);

		List<SimpleBean> actionTypes = PageConstants.buildActionTypesList();
		model.addAttribute("actionTypes", actionTypes);

		List<SimpleBean> sendTypes = PageConstants.buildSendTypesList();
		model.addAttribute("sendTypes", sendTypes);

		model.addAttribute("wxMsgType", wxMsgType);

		return PageConstants.PAGE_RELPY_MSG_DETAIL;
	}

	@RequestMapping("/submit")
	public String submit(@Valid WxMsgType wxMsgType, BindingResult result,Model model) {
		if (result.hasErrors()) {
			Long userId = AppContext.getUserId();
			List<WxAccount> accounts = accountService.findAccountByUserId(userId);
			model.addAttribute("accounts", accounts);
			model.addAttribute("wxMsgType", wxMsgType);
			List<WxMessage> messages = messageService.findMessageByAccountId(wxMsgType.getAccountId());
			model.addAttribute("messages", messages);

			List<WxGame> games = gameService.findGameByAccountId(wxMsgType.getAccountId());
			model.addAttribute("games", games);

			List<SimpleBean> actionTypes = PageConstants.buildActionTypesList();
			model.addAttribute("actionTypes", actionTypes);

			List<SimpleBean> sendTypes = PageConstants.buildSendTypesList();
			model.addAttribute("sendTypes", sendTypes);

			return PageConstants.PAGE_RELPY_MSG_DETAIL;
		}
		wxMsgType.setIstatus(0);
		wxMsgType.setName(this.getMsgTypeName(wxMsgType.getMsgType()));
		wxMsgType = msgTypeService.save(wxMsgType);
		return "redirect:/replymsg/init?accountId=" +wxMsgType.getAccountId();
	}

	private String getMsgTypeName(String msgType) {
		List<SimpleBean> sendTypes = PageConstants.buildSendTypesList();
		for (SimpleBean bean : sendTypes) {
			if (bean.getKey().equals(msgType))
				return bean.getValue();
		}
		return StringUtils.EMPTY;
	}

	private void mergeViewData(List<WxMsgType> source, List<WxMsgType> viewdata) {
		for (WxMsgType msg : source) {
			l: for (WxMsgType view : viewdata) {
				if (msg.getId() == view.getId()) {
					msg.setAction(view.getAction());
					msg.setSourceId(view.getSourceId());
					msg.setIstatus(view.getIstatus());
					break l;
				}
			}

		}
	}

}

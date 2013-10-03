package net.chat.controllers;

import java.util.List;

import javax.validation.Valid;

import net.chat.constants.MessageTypeConstants;
import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.domain.WxMessage;
import net.chat.service.AccountService;
import net.chat.service.MessageService;
import net.chat.utils.AppContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private AccountService accountService;

	@RequestMapping("/init")
	public String init(
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		if (accounts.isEmpty()) {
			return "redirect:/account//add";
		}
		model.addAttribute("accounts", accounts);
		Page<WxMessage> messages = messageService.listALlMessageByAccountId(
				accounts.get(0).getId(), page, 0);
		model.addAttribute("messages", messages);
		return PageConstants.PAGE_MESSAGE_LIST;
	}

	@RequestMapping("/addtext")
	public String addText(Model model) {
		WxMessage message = new WxMessage();
		message.setMsgType("text");
		message.setMsgName("首次关注");
		message.setContent("亲，您已成功关注！");
		model.addAttribute("messageForm", message);
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		model.addAttribute("messageTypes",
				MessageTypeConstants.getMessageTypeList());
		return PageConstants.PAGE_MESSAGE_TEXT;
	}

	@RequestMapping("/addimg")
	public String addImg() {
		return PageConstants.PAGE_MESSAGE_IMG;
	}

	@RequestMapping("/addmusic")
	public String addMusic() {
		return PageConstants.PAGE_MESSAGE_MUSIC;
	}

	@RequestMapping("/edittext")
	public String editText() {
		return PageConstants.PAGE_MESSAGE_TEXT;
	}

	@RequestMapping("/editimg")
	public String editImg() {
		return PageConstants.PAGE_MESSAGE_IMG;
	}

	@RequestMapping("/editmusic")
	public String editMusic() {
		return PageConstants.PAGE_MESSAGE_MUSIC;
	}

	@RequestMapping("/sumit")
	public String submit(@Valid WxMessage message, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("messageForm", message);
			return PageConstants.PAGE_REGISTER;
		}
		if (null == message.getId() || 0 == message.getId()) {
			messageService.saveMessage(message);
		} else {
			messageService.editMessage(message);

		}
		return PageConstants.PAGE_MESSAGE_LIST;
	}

	@RequestMapping("/delete")
	public String delete() {

		return PageConstants.PAGE_MESSAGE_LIST;
	}

}

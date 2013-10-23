package net.chat.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.domain.WxContent;
import net.chat.domain.WxMessage;
import net.chat.formbean.MessageForm;
import net.chat.formbean.MultimediaMessageForm;
import net.chat.service.AccountService;
import net.chat.service.ContentService;
import net.chat.service.MessageService;
import net.chat.utils.AppContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private ContentService contentService;

	@RequestMapping("/init")
	public String init(@RequestParam(value = "accountId", required = false) Long accountId, @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		Page<WxMessage> messages = null;
		if (CollectionUtils.isEmpty(accounts)) {
			return "redirect:/account/add";
		}
		if (null == accountId) {
			accountId = accounts.get(0).getId();
		}
		messages = messageService.listALlMessageByAccountId(accountId, page, 0);
		model.addAttribute("accountId", accountId);
		model.addAttribute("accounts", accounts);
		model.addAttribute("messages", messages);
		return PageConstants.PAGE_MESSAGE_LIST;
	}

	@RequestMapping("/addtext/{accountId}")
	public String addText(@PathVariable("accountId")Long accountId,Model model) {
		WxMessage message = new WxMessage();
		message.setMsgType("text");
		message.setMsgName("首次关注");
		message.setContent("亲，您已成功关注！");
		message.setAccountId(accountId);
		model.addAttribute("wxMessage", message);
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		model.addAttribute("messageTypes",PageConstants.buildMessageTypesList());
		return PageConstants.PAGE_MESSAGE_TEXT;
	}

	@RequestMapping("/addMultimedia/{msgType}")
	public String addMultimedia(@PathVariable("msgType") String msgType, Model model) {
		WxMessage message = new WxMessage();
		message.setMsgType(msgType);
		MultimediaMessageForm messageForm = new MultimediaMessageForm();
		messageForm.setMessage(message);
		model.addAttribute("messageForm", messageForm);
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		
		List<WxContent> wxContents = contentService.findAllMultimedia(msgType);
		model.addAttribute("wxContents", wxContents);
		model.addAttribute("msgType", msgType);
		model.addAttribute("messageTypes",  PageConstants.buildMessageTypesList());
		
		model.addAttribute("contentTypes",  PageConstants.buildContentTypesList());
		return PageConstants.PAGE_MESSAGE_MULTIMEDIA;

	}

	@RequestMapping("/edittext/{id}")
	public String editText(@PathVariable("id") Long id, Model model) {
		WxMessage msg = messageService.findyMessageByMessageId(id);
		model.addAttribute("wxMessage", msg);
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		model.addAttribute("messageTypes", PageConstants
				.buildContentTypesListByType(msg.getMsgType()));
		
		return PageConstants.PAGE_MESSAGE_TEXT;
	}

	@RequestMapping("/editMultimedia/{id}")
	public String editMultimedia(@PathVariable("id") Long id, Model model) {
		WxMessage message = messageService.findyMessageByMessageId(id);
		List<WxContent> wxContentsSelected = contentService.findByMessageId(id);
		List<Long> selectIdContents = new ArrayList<Long>();
		for (WxContent wxcontentselectd : wxContentsSelected)
			selectIdContents.add(wxcontentselectd.getBaseContentId());
		MultimediaMessageForm messageForm = new MultimediaMessageForm();
		messageForm.setSelectContents(selectIdContents);
		messageForm.setMessage(message);
		model.addAttribute("messageForm", messageForm);
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		model.addAttribute("messageTypes", PageConstants
				.buildContentTypesListByType(message.getMsgType()));
		List<WxContent> wxContents = contentService.findAllMultimedia(message.getMsgType());
		model.addAttribute("wxContents", wxContents);
		model.addAttribute("msgType", message.getMsgType());
		model.addAttribute("contentTypes", PageConstants
				.buildContentTypesListByType(message.getMsgType()));
		return PageConstants.PAGE_MESSAGE_MULTIMEDIA;

	}

	@RequestMapping("/submit")
	public String submit(@Valid WxMessage message, BindingResult result, Model model) {

		if (result.hasErrors()) {
			Long userId = AppContext.getUserId();
			List<WxAccount> accounts = accountService.findAccountByUserId(userId);
			model.addAttribute("accounts", accounts);
			model.addAttribute("messageTypes", PageConstants.buildMessageTypesList());
			model.addAttribute("wxMessage", message);
			return PageConstants.PAGE_MESSAGE_TEXT;
		}
		if (null == message.getId() || 0 == message.getId()) {
			messageService.saveMessage(message);
		} else {
			messageService.editMessage(message);
		}
		return "redirect:/message/init?accountId=" + message.getAccountId();
	}

	@RequestMapping("/submitMultimedia")
	public String submitMultimedia(@Valid MultimediaMessageForm messageForm, BindingResult result, Model model) {
		WxMessage message = messageForm.getMessage();
		List<Long> selectContentIds = messageForm.getSelectContents();
		if (result.hasErrors()) {
			model.addAttribute("messageForm", message);
			return PageConstants.PAGE_REGISTER;
		}
		if (null == message.getId() || 0 == message.getId()) {
			messageService.saveMessage(message);

		} else {
			messageService.editMessage(message);
		}
		List<WxContent> newContents = new ArrayList<WxContent>();
		Iterable<WxContent> selectContents = contentService.findByContentIds(selectContentIds);
		Iterator<WxContent> it = selectContents.iterator();
		while (it.hasNext()) {
			WxContent selectContent = it.next();
			WxContent newContent = new WxContent();
			BeanUtils.copyProperties(selectContent, newContent);
			newContent.setBaseContentId(selectContent.getId());
			newContent.setId(null);
			newContent.setMessageId(message.getId());
			newContent.setUrl("message/viewMsg/"+message.getId());
			newContents.add(newContent);
		}
		contentService.deleteByMessageId(message.getId());
		contentService.saveContents(newContents);
		return "redirect:/message/init?accountId=" + message.getAccountId();
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		messageService.delteMessage(id);
		return "redirect:/message/init";
	}
	
	@ResponseBody
	@RequestMapping(value="/ajaxMessage" ,method=RequestMethod.POST)
	public MessageForm ajaxMessage(Long messageId){
		WxMessage message=messageService.findyMessageByMessageId(messageId);
		if(!"text".equals(message.getMsgType())){
			List<WxContent> contents=contentService.findAllMultimedia(message.getMsgType());
			List<WxContent> wxContentsSelected = contentService.findByMessageId(messageId);
			List<Long> selectIdContents = new ArrayList<Long>();
			for (WxContent wxcontentselectd : wxContentsSelected)
				selectIdContents.add(wxcontentselectd.getBaseContentId());
			return new MessageForm(message,selectIdContents,contents);
		}
		return new MessageForm(message,null,null);
	}
	
	@RequestMapping("/viewMsg/{id}")
	public String viewMsg(@PathVariable("id") Long id,Model model) {
		WxMessage message=messageService.findyMessageByMessageId(id);
		if(!"text".equals(message.getMsgType())){	
			List<WxContent> contents = contentService.findByMessageId(id);
			model.addAttribute("contents", contents);
		}
		model.addAttribute("message", message);
		model.addAttribute("msgType", message.getMsgType());
		return PageConstants.PAGE_MESSAGE_VIEW;
	}
}

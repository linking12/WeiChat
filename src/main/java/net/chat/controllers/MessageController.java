package net.chat.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import net.chat.constants.MessageTypeConstants;
import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.domain.WxContent;
import net.chat.domain.WxMessage;
import net.chat.formbean.MultimediaMessageForm;
import net.chat.service.AccountService;
import net.chat.service.ContentService;
import net.chat.service.MessageService;
import net.chat.utils.AppContext;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private ContentService contentService;

	@Value("${upload.file.path}")
	private String uploadpath;

	@RequestMapping("/init")
	public String init(
			@RequestParam(value = "accountId", required = false) Long accountId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		Page<WxMessage> messages = null;
		if (CollectionUtils.isEmpty(accounts)) {
			return "redirect:/account/add";
		} else {
			WxAccount accountform = new WxAccount();
			accountform.setId(accountId);
			model.addAttribute("accountForm", accountform);

			if (accountId != null)
				messages = messageService.listALlMessageByAccountId(accountId,
						page, 0);
			else
				messages = messageService.listALlMessageByAccountId(accounts
						.get(0).getId(), page, 0);
		}
		model.addAttribute("accounts", accounts);
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

	@RequestMapping("/addMultimedia/{msgType}")
	public String addMultimedia(@PathVariable("msgType") String msgType,
			Model model) {
		WxMessage message = new WxMessage();
		message.setMsgType(msgType);
		MultimediaMessageForm messageForm = new MultimediaMessageForm();
		messageForm.setMessage(message);
		model.addAttribute("messageForm", messageForm);
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		model.addAttribute("messageTypes",
				MessageTypeConstants.getMessageTypeList());
		List<WxContent> wxContents = contentService.findAllMultimedia();
		model.addAttribute("wxContents", wxContents);
		return PageConstants.PAGE_MESSAGE_MULTIMEDIA;

	}

	@RequestMapping("/edittext/{id}")
	public String editText(@PathVariable("id") Long id, Model model) {
		WxMessage msg = messageService.findyMessageByMessageId(id);
		model.addAttribute("wxMessage", msg);
		List<WxAccount> accounts = new ArrayList<WxAccount>();
		accounts.add(accountService.findAcountById(msg.getAccountId()));
		model.addAttribute("accounts", accounts);
		return PageConstants.PAGE_MESSAGE_TEXT;
	}

	@RequestMapping("/editMultimedia/{id}")
	public String editMultimedia(@PathVariable("id") Long id, Model model) {
		WxMessage message = messageService.findyMessageByMessageId(id);
		List<WxContent> selectContents = contentService.findByMessageId(id);
		MultimediaMessageForm messageForm = new MultimediaMessageForm();
		List<Long> selectContentIds = new ArrayList<Long>();
		for (WxContent selectContent : selectContents)
			selectContentIds.add(selectContent.getId());
		messageForm.setSelectContents(selectContentIds);
		messageForm.setMessage(message);
		model.addAttribute("messageForm", messageForm);
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		model.addAttribute("messageTypes",
				MessageTypeConstants.getMessageTypeList());
		List<WxContent> wxContents = contentService.findAllMultimedia();
		model.addAttribute("wxContents", wxContents);
		return PageConstants.PAGE_MESSAGE_MULTIMEDIA;

	}

	@RequestMapping("/submit")
	public String submit(@Valid WxMessage message,
			@Valid List<Long> selectContentIds, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("messageForm", message);
			return PageConstants.PAGE_REGISTER;
		}
		if (null == message.getId() || 0 == message.getId()) {
			messageService.saveMessage(message);
			Iterable<WxContent> selectContents = contentService
					.findByContentIds(selectContentIds);
			Iterator<WxContent> it = selectContents.iterator();
			while (it.hasNext()) {
				WxContent selectContent = it.next();
				selectContent.setMessageId(message.getId());
			}
			contentService.saveContents(selectContents);

		} else {
			messageService.editMessage(message);
			contentService.deleteByMessageId(message.getId());
			Iterable<WxContent> selectContents = contentService
					.findByContentIds(selectContentIds);
			Iterator<WxContent> it = selectContents.iterator();
			while (it.hasNext()) {
				WxContent selectContent = it.next();
				selectContent.setMessageId(message.getId());
			}
			contentService.saveContents(selectContents);

		}
		return "redirect:/message/init?accountId=" + message.getAccountId();
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		messageService.delteMessage(id);

		return "redirect:/message/init";
	}

	@RequestMapping("/upload")
	@ResponseBody
	public String upload(
			@RequestParam(value = "accountId", required = false) Long accountId,
			MultipartHttpServletRequest request, Model model) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String fname = StringUtils.EMPTY;
		if (isMultipart) {
			try {
				String filename = request.getFileNames().next();
				MultipartFile file = request.getFile(filename);
				File newFile = new File(uploadpath
						+ File.separator
						+ RandomStringUtils.randomAlphabetic(15)
						+ "."
						+ StringUtils.substringAfterLast(
								file.getOriginalFilename(), "."));

				FileUtils.writeStringToFile(newFile, null);
				newFile.createNewFile();
				file.transferTo(newFile);
				File backFile = new File(this.getClass().getResource("/")
						.getFile()
						+ File.separator + "upload");
				FileUtils.copyFileToDirectory(newFile, backFile);
				fname = newFile.getName();
			} catch (Exception e) {

			}
		}
		return "" + fname;
	}

	@ModelAttribute("uploadpath")
	public String setUploadPath() {
		return uploadpath;
	}

}

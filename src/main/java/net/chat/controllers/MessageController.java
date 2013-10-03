package net.chat.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.domain.WxContent;
import net.chat.domain.WxMessage;
import net.chat.formbean.AccountForm;
import net.chat.formbean.MusicForm;
import net.chat.formbean.PictureForm;
import net.chat.service.AccountService;
import net.chat.service.ContentService;
import net.chat.service.MessageService;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	public String init(@RequestParam(value = "accountId", required = false) Long accountId, Model model) {
		List<WxAccount> accounts = accountService.findAll();
		model.addAttribute("accounts", accounts);
		if (!CollectionUtils.isEmpty(accounts)) {
			if (!isExistingAccount(accountId, accounts)) {
				accountId = accounts.get(0).getId();
			}
			List<WxMessage> messages = messageService.findMessageByAccountId(accountId);
			model.addAttribute("messages", messages);
			AccountForm form = new AccountForm();
			form.setAccountId(accountId);
			model.addAttribute("formbean", form);
		}

		return PageConstants.PAGE_MESSAGE_LIST;
	}

	@RequestMapping("/addtext")
	public String addText(Model model) {
		List<WxAccount> accounts = accountService.findAll();
		model.addAttribute("accounts", accounts);
		WxMessage msg = new WxMessage();
		model.addAttribute("wxMessage", msg);
		return PageConstants.PAGE_MESSAGE_TEXT;
	}

	@RequestMapping("/addimg")
	public String addImg(Model model) {
		List<WxAccount> accounts = accountService.findAll();
		model.addAttribute("accounts", accounts);
		PictureForm form = new PictureForm();
		model.addAttribute("pictureForm", form);
		return PageConstants.PAGE_MESSAGE_IMG;
	}

	@RequestMapping("/addmusic")
	public String addMusic(Model model) {

		List<WxAccount> accounts = accountService.findAll();
		model.addAttribute("accounts", accounts);

		model.addAttribute("musicForm", new MusicForm());

		return PageConstants.PAGE_MESSAGE_MUSIC;
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

	@RequestMapping("/editimg/{id}")
	public String editImg(@PathVariable("id") Long id, Model model) {
		WxMessage msg = messageService.findyMessageByMessageId(id);
		WxContent content = contentService.findByMessageId(msg.getId());
		List<WxAccount> accounts = new ArrayList<WxAccount>();
		accounts.add(accountService.findAcountById(msg.getAccountId()));
		model.addAttribute("accounts", accounts);
		PictureForm pf = new PictureForm();
		BeanUtils.copyProperties(content, pf);
		pf.setAccountId(msg.getAccountId());
		model.addAttribute("pictureForm", pf);
		return PageConstants.PAGE_MESSAGE_IMG;
	}

	@RequestMapping("/editmusic/{id}")
	public String editMusic(@PathVariable("id") Long id, Model model) {
		WxMessage msg = messageService.findyMessageByMessageId(id);
		WxContent content = contentService.findByMessageId(msg.getId());
		List<WxAccount> accounts = new ArrayList<WxAccount>();
		accounts.add(accountService.findAcountById(msg.getAccountId()));
		model.addAttribute("accounts", accounts);
		MusicForm mf = new MusicForm();
		BeanUtils.copyProperties(content, mf);
		mf.setAccountId(msg.getAccountId());
		model.addAttribute("musicForm", mf);
		return PageConstants.PAGE_MESSAGE_MUSIC;
	}

	@RequestMapping("/sumittext")
	public String submitText(@Valid WxMessage wxMessage, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<WxAccount> accounts = accountService.findAll();
			model.addAttribute("accounts", accounts);
			model.addAttribute("wxMessage", wxMessage);
			return PageConstants.PAGE_MESSAGE_TEXT;
		}

		wxMessage.setMsgType("text");
		messageService.saveMessage(wxMessage);

		return "redirect:/message/init?accountId=" + wxMessage.getAccountId();
	}

	@RequestMapping("/sumitimg")
	public String submiImg(@Valid PictureForm pictureForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<WxAccount> accounts = accountService.findAll();
			model.addAttribute("accounts", accounts);
			model.addAttribute("pictureForm", pictureForm);
			return PageConstants.PAGE_MESSAGE_IMG;
		}
		WxMessage msg = new WxMessage();

		msg.setMsgName(pictureForm.getTitle());
		msg.setMsgType("multimedia");
		msg.setContent(pictureForm.getDescription());
		msg.setAccountId(pictureForm.getAccountId());

		WxContent content = new WxContent();

		BeanUtils.copyProperties(pictureForm, content);
		if (null != pictureForm.getMessageId()) {
			msg.setId(pictureForm.getMessageId());
			WxContent scontent = contentService.findByMessageId(pictureForm.getMessageId());
			content.setId(scontent.getId());
		}

		msg = messageService.saveMessage(msg);
		content.setMessageId(msg.getId());
		content.setMsgType("multimedia");
		contentService.save(content);
		return "redirect:/message/init?accountId=" + msg.getAccountId();

	}

	@RequestMapping("/sumitmusic")
	public String sumitMusic(@Valid MusicForm musicForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<WxAccount> accounts = accountService.findAll();
			model.addAttribute("accounts", accounts);
			model.addAttribute("musicForm", musicForm);
			return PageConstants.PAGE_MESSAGE_MUSIC;
		}
		WxMessage msg = new WxMessage();

		msg.setMsgName(musicForm.getTitle());
		msg.setMsgType("music");
		msg.setContent(musicForm.getDescription());
		msg.setAccountId(musicForm.getAccountId());

		WxContent content = new WxContent();

		BeanUtils.copyProperties(musicForm, content);
		if (null != musicForm.getMessageId()) {
			msg.setId(musicForm.getMessageId());
			WxContent scontent = contentService.findByMessageId(musicForm.getMessageId());
			content.setId(scontent.getId());
		}

		msg = messageService.saveMessage(msg);
		content.setMessageId(msg.getId());
		content.setMsgType("music");
		contentService.save(content);
		return "redirect:/message/init?accountId=" + msg.getAccountId();
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		messageService.delteMessage(id);

		return "redirect:/message/init";
	}


	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam(value = "accountId", required = false) Long accountId, MultipartHttpServletRequest request, Model model) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String fname = StringUtils.EMPTY;
		if (isMultipart) {
			try {
				String filename = request.getFileNames().next();
				MultipartFile file = request.getFile(filename);
				File newFile = new File(uploadpath + File.separator + RandomStringUtils.randomAlphabetic(15) + "." + StringUtils.substringAfterLast(file.getOriginalFilename(), "."));

				FileUtils.writeStringToFile(newFile, null);
				newFile.createNewFile();
				file.transferTo(newFile);
				File backFile = new File(this.getClass().getResource("/").getFile()  + File.separator + "upload");
				FileUtils.copyFileToDirectory(newFile, backFile);
				fname = newFile.getName();
			} catch (Exception e) {

			}
		}
		return "" + fname;
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

	@ModelAttribute("uploadpath")
	public String setUploadPath() {
		return uploadpath;
	}

}

package net.chat.controllers;

import java.util.List;

import net.chat.constants.PageConstants;
import net.chat.dao.WxAccountDao;
import net.chat.dao.WxMessageDao;
import net.chat.domain.WxAccount;
import net.chat.domain.WxMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private WxMessageDao messageDao;
	@Autowired
	private WxAccountDao accountDao;

	@RequestMapping("/init")
	public String init(Model model) {
		List<WxAccount> accounts = accountDao.findAll();
		model.addAttribute("accounts", accounts);
		
		List<WxMessage> messages = messageDao.findAll();
		model.addAttribute("messages", messages);
		return PageConstants.PAGE_MESSAGE_LIST;
	}

	@RequestMapping("/addtext")
	public String addText() {
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
	public String submit() {
		return PageConstants.PAGE_MESSAGE_LIST;
	}

	@RequestMapping("/delete")
	public String delete() {
		return PageConstants.PAGE_MESSAGE_LIST;
	}
	// @RequestMapping("/register/submit")
	// public String submit(@Valid RegisterForm bean, BindingResult result,
	// Model model) {
	// if (result.hasErrors()) {
	//
	// StringBuffer sb = new StringBuffer();
	// if (!bean.getPassword().equals(bean.getPassword1())) {
	// sb.append("两次输入的密码必须相同！");
	// }
	// model.addAttribute("errorMsg", sb);
	// model.addAttribute("registerForm", bean);
	// return PageConstants.PAGE_REGISTER;
	// }
	// return PageConstants.PAGE_LOGIN_1;
	// }
}

package net.chat.controllers;

import java.util.List;

import net.chat.constants.PageConstants;
import net.chat.dao.WxAccountDao;
import net.chat.dao.WxGameDao;
import net.chat.dao.WxMessageDao;
import net.chat.dao.WxMsgTypeDao;
import net.chat.domain.WxAccount;
import net.chat.domain.WxGame;
import net.chat.domain.WxMessage;
import net.chat.domain.WxMsgType;
import net.chat.formbean.ReplyMsgForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/replymsg")
public class ReplyMsgController {
	@Autowired
	private WxAccountDao accountDao;

	@Autowired
	private WxMsgTypeDao msgTypeDao;

	@Autowired
	private WxMessageDao messageDao;

	@Autowired
	private WxGameDao gameDao;

	@RequestMapping("/init")
	public String init(Model model) {
		List<WxAccount> accounts = accountDao.findAll();
		model.addAttribute("accounts", accounts);

		List<WxMsgType> msgTypes = msgTypeDao.findAll();
		model.addAttribute("msgTypes", msgTypes);

		List<WxMessage> messages = messageDao.findAll();
		model.addAttribute("messages", messages);

		List<WxGame> games = gameDao.findAll();
		model.addAttribute("games", games);

		
		ReplyMsgForm form=new ReplyMsgForm();
		form.setAccountId("1");
		form.setMsgTypes(msgTypes);
		model.addAttribute("replyMsgForm", form);
		return PageConstants.PAGE_RELPY_MSG;
	}

	@RequestMapping("/save")
	public String save(Model model) {

		return PageConstants.PAGE_RELPY_MSG;
	}
	
	@RequestMapping("/msgtype")
	@ResponseBody
	public List<WxMsgType> msgType(Model model) {

		List<WxMsgType> msgTypes = msgTypeDao.findAll();
		return msgTypes;
	}
}

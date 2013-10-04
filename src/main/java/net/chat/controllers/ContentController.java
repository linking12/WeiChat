package net.chat.controllers;

import net.chat.constants.MessageTypeConstants;
import net.chat.constants.PageConstants;
import net.chat.domain.WxContent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/content")
public class ContentController {

	@RequestMapping("/addContent")
	public String addText(Model model) {
		model.addAttribute("messageTypes",
				MessageTypeConstants.getMessageTypeMultimediaList());
		model.addAttribute("contentForm", new WxContent());
		return PageConstants.PAGE_CONTENT_DETAIL;
	}

}

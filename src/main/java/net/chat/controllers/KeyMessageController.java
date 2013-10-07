package net.chat.controllers;

import net.chat.constants.PageConstants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/keyMessage")
public class KeyMessageController {

	@RequestMapping("/init")
	public String init(
			@RequestParam(value = "accountId", required = false) Long accountId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {
		return PageConstants.PAGE_MESSAGE_LIST;
	}

}

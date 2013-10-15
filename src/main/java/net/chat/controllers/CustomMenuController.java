package net.chat.controllers;

import net.chat.constants.PageConstants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custommenu")
public class CustomMenuController {

	@RequestMapping("/init")
	public String init() {
		return PageConstants.PAGE_CUSTOMMENU_LIST;
	}

	@RequestMapping("/add")
	public String add() {
		return PageConstants.PAGE_CUSTOMMENU_DETAIL;
	}

	@RequestMapping("/edit")
	public String edit() {
		return PageConstants.PAGE_CUSTOMMENU_DETAIL;
	}

	@RequestMapping("/delete")
	public String delete() {
		return PageConstants.PAGE_CUSTOMMENU_LIST;
	}
}

package net.chat.controllers;

import net.chat.constants.PageConstants;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class RegisterUserController {

	@RequestMapping("/init")
	public String init(Model model) {

		return PageConstants.PAGE_REGISTER_USER;
	}

}

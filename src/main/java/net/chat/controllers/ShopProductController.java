package net.chat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopProductController {


	@RequestMapping("/welcome")
	public String index(Model model) {
		model.addAttribute("CREATE_STATIC_HTML", true);
		model.addAttribute("name", "网站标题");
		return "welcome";
	}

}

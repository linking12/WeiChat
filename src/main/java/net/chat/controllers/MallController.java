package net.chat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mall")
public class MallController {
	
	@RequestMapping("/index")
	public String init(Model model) {
		return "mall/index";
	}

	@RequestMapping("/all")
	public String all(Model model) {
		return "mall/all";
	}
	
	@RequestMapping("/chaoliu")
	public String chaoliu(Model model) {
		return "mall/chaoliu";
	}
	
	@RequestMapping("/detail")
	public String detail(Model model) {
		return "mall/detail";
	}
	
	@RequestMapping("/newup")
	public String newup(Model model) {
		return "mall/newup";
	}
	
	@RequestMapping("/huodong")
	public String huodong(Model model) {
		return "mall/huodong";
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		return "mall/list";
	}
}

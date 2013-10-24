package net.chat.controllers;

import net.chat.constants.PageConstants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class GameController {

	@RequestMapping("/init")
	public String init() {
		return PageConstants.PAGE_GAME_LIST;
	}

	@RequestMapping("/vote")
	public String vote() {
		return PageConstants.PAGE_GAME_VOTE;
	}

	@RequestMapping("/robot")
	public String robot() {
		return PageConstants.PAGE_GAME_ROBOT;
	}

	@RequestMapping("/lottery")
	public String lottery() {
		return PageConstants.PAGE_GAME_LOTTERY;
	}

}

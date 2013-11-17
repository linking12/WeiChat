package net.chat.controllers;

import java.util.ArrayList;
import java.util.List;

import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.domain.WxCustomMenu;
import net.chat.domain.WxMessage;
import net.chat.integration.vo.Menu;
import net.chat.service.AccountService;
import net.chat.service.CustomMenuService;
import net.chat.service.MessageService;
import net.chat.utils.AppContext;
import net.chat.utils.WeiChatUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/custommenu")
public class CustomMenuController {

	private static Logger log = LoggerFactory.getLogger(CustomMenuController.class);
	@Autowired
	AccountService accountService;

	@Autowired
	MessageService messageService;

	@Autowired
	CustomMenuService customMenuService;

	@RequestMapping("/init")
	public String init(@RequestParam(value = "accountId", required = false) Long accountId, Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		if (CollectionUtils.isEmpty(accounts)) {
			return "redirect:/account/add";
		}

		if (null == accountId) {
			accountId = accounts.get(0).getId();
		}

		model.addAttribute("accountId", accountId);
		model.addAttribute("eventTypes", PageConstants.buildEventTypesList());

		List<WxMessage> msgs = messageService.findMessageByAccountId(accountId);
		model.addAttribute("msgs", msgs);

		List<WxCustomMenu> parentMenus = customMenuService.findParentMenuByAccountId(accountId);
		model.addAttribute("parentMenus", parentMenus);
		List<WxCustomMenu> menus = customMenuService.findCustomMenuByAccountId(accountId);
		model.addAttribute("menus", menus);
		return PageConstants.PAGE_CUSTOMMENU_LIST;
	}

	@RequestMapping("/add/{accountId}")
	public String add(@PathVariable("accountId") Long accountId, Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		model.addAttribute("eventTypes", PageConstants.buildEventTypesList());

		List<WxMessage> msgs = messageService.findMessageByAccountId(accountId);
		model.addAttribute("msgs", msgs);
		List<WxCustomMenu> parentMenus = customMenuService.findParentMenuByAccountId(accountId);
		model.addAttribute("parentMenus", parentMenus);
		WxCustomMenu wxCustomMenu = new WxCustomMenu();
		wxCustomMenu.setAccountId(accountId);
		model.addAttribute("wxCustomMenu", wxCustomMenu);
		return PageConstants.PAGE_CUSTOMMENU_DETAIL;
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		WxCustomMenu wxCustomMenu = customMenuService.findCustomMenuById(id);
		model.addAttribute("eventTypes", PageConstants.buildEventTypesList());

		Long accountId = wxCustomMenu.getAccountId();
		WxAccount account = accountService.findAcountById(accountId);
		List<WxAccount> accounts = new ArrayList<WxAccount>();

		accounts.add(account);
		model.addAttribute("accounts", accounts);
		List<WxMessage> msgs = messageService.findMessageByAccountId(accountId);

		model.addAttribute("msgs", msgs);
		if (null != wxCustomMenu.getParentId() && 0 != wxCustomMenu.getParentId()) {
			List<WxCustomMenu> parentMenus = customMenuService.findParentMenuByAccountId(accountId);
			model.addAttribute("parentMenus", parentMenus);
		}

		model.addAttribute("wxCustomMenu", wxCustomMenu);

		return PageConstants.PAGE_CUSTOMMENU_DETAIL;
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		customMenuService.delete(id);
		return "redirect:/custommenu/init";
	}

	@RequestMapping("/submit")
	public String submit(WxCustomMenu wxCustomMenu, Model model) {
		if (null == wxCustomMenu.getParentId()) {
			long accountId = wxCustomMenu.getAccountId();
			int count = customMenuService.findParentMenuByAccountId(accountId).size();
			if (count > 2) {
				model.addAttribute("msg", "最多添加3个一级菜单!");

				List<WxAccount> accounts = accountService.findAccountByUserId(AppContext.getUserId());
				model.addAttribute("accounts", accounts);
				model.addAttribute("eventTypes", PageConstants.buildEventTypesList());

				List<WxCustomMenu> parentMenus = customMenuService.findParentMenuByAccountId(accountId);
				model.addAttribute("parentMenus", parentMenus);

				model.addAttribute("wxCustomMenu", wxCustomMenu);
				return PageConstants.PAGE_CUSTOMMENU_DETAIL;
			}
		}
		customMenuService.save(wxCustomMenu);
		return "redirect:/custommenu/init?accountId=" + wxCustomMenu.getAccountId();
	}

	/*
	 * 
	 * 这里需要ajax调用，bobo搞一下？
	 */
	@ResponseBody
	@RequestMapping("/create/{accountId}")
	public int integration(@PathVariable("accountId") Long accountId) {
		try{
		String accessToken= WeiChatUtil.getAccessToken("wx593827365bbc380b", "11a6f7612a77f19525b37072a65660d0").getToken();
		Menu menu = customMenuService.createMenu(accountId);
//		int result=0;
		int result = WeiChatUtil.createMenu(menu,accessToken);
		// 判断菜单创建结果
		if (0 == result)
			log.info("菜单创建成功！");
		else
			log.info("菜单创建失败，错误码：" + result);
		return result;
		}catch(Exception e){
			log.info("菜单创建失败!" ,e);
			return 1;
		}
	}
}

package net.chat.controllers;

import net.chat.domain.User;
import net.chat.service.SecurityService;
import net.chat.utils.AppContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/ggl")
public class GglController {

	@Autowired
	SecurityService userSerivce;

	@RequestMapping("/init")
	public RedirectView init(ModelMap model) {
		// String userName = AppContext.getUsername();
		// User user = userSerivce.findUserByName(userName);

		RedirectView view = new RedirectView(
				"http://yidia.cn/bali/ggl/login_check.jsp?author=vargobox&pass=123456vargobox");
		view.setExposeModelAttributes(false);// post
		return view;
	}
}

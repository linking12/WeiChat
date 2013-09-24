package net.chat.controllers;

import javax.validation.Valid;

import net.chat.Constants.PageConstants;
import net.chat.formbean.LoginForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@ModelAttribute("loginForm")
	public LoginForm createFormBean() {
		return new LoginForm();
	}

	@RequestMapping("/login")
	public String init() {
		return PageConstants.PAGE_LOGIN;
	}

	@RequestMapping("/submit")
	public String submit(@Valid LoginForm bean, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("loginForm",bean);
			return PageConstants.PAGE_LOGIN;
		}
		return PageConstants.PAGE_LOGIN_1;
	}

	//
	// @RequestMapping("ajax")
	// public @ResponseBody
	// String simple() {
	// return "Hello world!";
	// }

	// @RequestMapping("submit")
	// public String processSubmit(@Valid TestBean formBean, BindingResult
	// result) {
	// if (result.hasErrors()) {
	// return "index";
	// }
	// return "index1";
	// }
	// Invoked on every request

	// @ModelAttribute
	// public void ajaxAttribute(WebRequest request, Model model) {
	// model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	// }

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see
	// @SessionAttributes)

	// @ModelAttribute("formBean")
	// public FormBean createFormBean() {
	// return new FormBean();
	// }

	// @RequestMapping(method=RequestMethod.GET)
	// public void form() {
	// }

	// @RequestMapping(method=RequestMethod.POST)
	// public String processSubmit(@Valid FormBean formBean, BindingResult
	// result,
	// @ModelAttribute("ajaxRequest") boolean ajaxRequest,
	// Model model, RedirectAttributes redirectAttrs) {
	// if (result.hasErrors()) {
	// return null;
	// }
	// // Typically you would save to a db and clear the "form" attribute from
	// the session
	// // via SessionStatus.setCompleted(). For the demo we leave it in the
	// session.
	// String message = "Form submitted successfully.  Bound " + formBean;
	// // Success response handling
	// if (ajaxRequest) {
	// // prepare model for rendering success message in this request
	// model.addAttribute("message", message);
	// return null;
	// } else {
	// // store a success message for rendering on the next request after
	// redirect
	// // redirect back to the form to render the success message along with
	// newly bound values
	// redirectAttrs.addFlashAttribute("message", message);
	// return "redirect:/form";
	// }
	// }

}

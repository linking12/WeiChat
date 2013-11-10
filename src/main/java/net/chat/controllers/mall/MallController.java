package net.chat.controllers.mall;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import net.chat.constants.PageConstants;
import net.chat.domain.mall.WxMall;
import net.chat.domain.mall.WxMallUser;
import net.chat.domain.mall.WxPrdtSubCategory;
import net.chat.domain.mall.WxProductCategory;
import net.chat.formbean.mall.WxProductForm;
import net.chat.service.mall.MallService;
import net.chat.utils.AppContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mall")
public class MallController {

	@Autowired
	private MallService mallService;

	@Autowired
	HttpSession session;

	@RequestMapping("/index/{accountId}")
	public String index(@PathVariable("accountId") long accountId, Model model) {
		WxMall wxMall = mallService.findMallByAccountId(accountId);
		/**
		 * 手机中是没有session的，这种变量放到APPContext中，里面有一个threadLocal
		 */
		session.setAttribute("accountId", accountId);
		session.setAttribute("mallId", wxMall.getId());
		AppContext.put("accountId", accountId);
		AppContext.put("mallId", wxMall.getId());

		model.addAttribute("wxMall", wxMall);
		long mallId = wxMall.getId();
		List<WxProductCategory> categoryList = mallService
				.findProductCategoryByMallId(mallId);
		model.addAttribute("categoryList", categoryList);
		return PageConstants.PAGE_MALL_INDEX;

	}

	@RequestMapping("/subcategory/{categoryId}")
	public String subcategory(@PathVariable("categoryId") long categoryId,
			Model model) {
		WxProductCategory category = mallService
				.findWxProductCategoryById(categoryId);
		if (null != category) {
			List<WxPrdtSubCategory> subCategoryList = mallService
					.findSubCategoryByCategoryId(categoryId);
			model.addAttribute("subCategoryList", subCategoryList);
			model.addAttribute("categoryStyle", category.getStyle());
		}
		return PageConstants.PAGE_MALL_CATEGORY;
	}

	@RequestMapping("/list/{subCategoryId}")
	public String list(@PathVariable("subCategoryId") long subCategoryId,
			Model model) {
		List<WxProductForm> formList = mallService
				.findPrdtListBySubCategoryId(subCategoryId);
		model.addAttribute("formList", formList);
		return PageConstants.PAGE_MALL_LIST;
	}

	@RequestMapping("/detail/{productId}")
	public String detail(@PathVariable("productId") long productId, Model model) {
		WxProductForm form = mallService.findProductById(productId);
		model.addAttribute("form", form);
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		if (null == mallUser) {
			model.addAttribute("needlogin", 1);
			model.addAttribute("url", "/mall/detail/" + productId);
		}
		return PageConstants.PAGE_MALL_DETAIL;
	}

	@RequestMapping("/login")
	public String login(
			@RequestParam(value = "fromUrl", required = false) String fromUrl,
			Model model) {
		WxMallUser mallUser = new WxMallUser();
		model.addAttribute("mallUser", mallUser);
		model.addAttribute("fromUrl", fromUrl);
		return PageConstants.PAGE_MALL_LOGIN;
	}

	@RequestMapping("/regist")
	public String regist(
			@RequestParam(value = "fromUrl", required = false) String fromUrl,
			Model model) {
		WxMallUser mallUser = new WxMallUser();
		model.addAttribute("wxMallUser", mallUser);
		model.addAttribute("fromUrl", fromUrl);
		return PageConstants.PAGE_MALL_REGIST;
	}

	@RequestMapping("/doregist")
	public String doRegist(@Valid WxMallUser mallUser, BindingResult result,
			@RequestParam(value = "fromUrl", required = false) String fromUrl,
			Model model) {
		try {
			mallUser.setMallId((Long) session.getAttribute("mallId"));
			mallUser = mallService.addMallUser(mallUser);
			session.setAttribute("mallUser", mallUser);
			if (StringUtils.isNotBlank(fromUrl))
				return "redirect:" + fromUrl;
			else {
				return "redirect:/mall/index/"
						+ session.getAttribute("accountId");
			}
		} catch (IllegalArgumentException e) {
			model.addAttribute("wxMallUser", mallUser);
			model.addAttribute("errmsg", e.getMessage());
			return "redirect:/mall/regist";
		}

	}

	@RequestMapping("/mall_security_check")
	public String doLogin(
			@RequestParam(value = "fromUrl", required = false) String fromUrl,
			WxMallUser mallUser, Model model) throws ServletException,
			IOException {
		mallUser = mallService.dologin(mallUser);

		if (null != mallUser) {
			AppContext.put("mallUser", mallUser);
			session.setAttribute("mallUser", mallUser);
			if (StringUtils.isNotBlank(fromUrl))
				return "redirect:" + fromUrl;

			else {
				return "redirect:/mall/index/"
						+ session.getAttribute("accountId");
			}
		} else {
			return "redirect:/mall/regist";
		}
	}

	@RequestMapping("/myaccount")
	public String myaccount() {
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		if (null == mallUser) {
			return "redirect:/mall/login?fromUrl=";
		} else {
			return "redirect:/mall/index/" + session.getAttribute("accountId");
		}
	}
}

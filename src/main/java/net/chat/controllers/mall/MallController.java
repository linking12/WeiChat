package net.chat.controllers.mall;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.validation.Valid;

import net.chat.constants.PageConstants;
import net.chat.domain.mall.WxMall;
import net.chat.domain.mall.WxMallCart;
import net.chat.domain.mall.WxMallUser;
import net.chat.domain.mall.WxPrdtSubCategory;
import net.chat.domain.mall.WxProductCategory;
import net.chat.formbean.mall.WxCartForm;
import net.chat.formbean.mall.WxProductForm;
import net.chat.service.mall.MallService;
import net.chat.utils.AppContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/mall")
@SessionAttributes("accountId")
public class MallController {
	@Autowired
	private MallService mallService;

	// @Autowired
	// HttpSession session;

	@RequestMapping("/index/{accountId}")
	public String init(@PathVariable("accountId") long accountId, Model model) {
		WxMall wxMall = mallService.findMallByAccountId(accountId);
		/**
		 * 手机中是没有session的，这种变量放到APPContext中，里面有一个threadLocal
		 */
		// session.setAttribute("accountId", accountId);
		// session.setAttribute("mallId", wxMall.getId());
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
		return PageConstants.PAGE_MALL_DETAIL;
	}

	@RequestMapping("/addcart/{productId}/{count}")
	@ResponseBody
	public int addcart(@PathVariable("productId") long productId,
			@PathVariable("count") long count, Model model) {
		WxMallCart wxMallCart = new WxMallCart();
		wxMallCart.setCount(count);
		wxMallCart.setProductId(productId);
		wxMallCart.setMallId((Long) AppContext.get("mallId"));
		wxMallCart.setMallUserId(1l);
		try {
			mallService.addToCart(wxMallCart);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	@RequestMapping("/cart")
	public String cart(Model model) {
		long mallUserId = 1;
		List<WxCartForm> cartList = mallService.findCartList(mallUserId);
		model.addAttribute("cartList", cartList);
		return PageConstants.PAGE_MALL_CART;
	}

	@RequestMapping("/deletecart/{productId}")
	public String deletecart(@PathVariable("productId") long productId,
			Model model) {
		mallService.deleteCartByProductId(productId);
		return "redirect:/mall/cart";
	}

	@RequestMapping("/createorder")
	public String createorder(String[] productIds, String[] counts, Model model) {
		return PageConstants.PAGE_MALL_ORDER;
	}

	@RequestMapping("/login")
	public String login(
			@RequestParam(value = "fromUrl", required = false) String fromUrl,
			Model model) {
		WxMallUser mallUser = new WxMallUser();
		model.addAttribute("mallUser", mallUser);
		//fromUrl = "/mall/cart";
		model.addAttribute("fromUrl", fromUrl);
		return PageConstants.PAGE_MALL_LOGIN;
	}

	@RequestMapping("/mall_security_check")
	public String doLogin(
			@RequestParam(value = "fromUrl", required = false) String fromUrl,
			@Valid WxMallUser mallUser, Model model) throws ServletException,
			IOException {
		boolean logined = mallService.dologin(mallUser);
		//boolean logined = true;
		AppContext.put("mallUser", mallUser);
		if (logined) {
			if (fromUrl != null)
				return "redirect:" + fromUrl;
			else {
				Long accountId = AppContext.get("accountId");
				return "redirect:/mall/index/" + accountId;
			}
		} else {
			Long accountId = AppContext.get("accountId");
			return "redirect:/mall/index/" + accountId;
		}

	}
}

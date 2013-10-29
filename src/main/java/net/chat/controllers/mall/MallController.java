package net.chat.controllers.mall;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.chat.constants.PageConstants;
import net.chat.domain.mall.WxMall;
import net.chat.domain.mall.WxPrdtSubCategory;
import net.chat.domain.mall.WxProductCategory;
import net.chat.formbean.mall.WxProductForm;
import net.chat.service.mall.MallService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/mall")
@SessionAttributes("accountId")
public class MallController {
	@Autowired
	private MallService mallService;
	
	@Autowired
	HttpSession session;

	@RequestMapping("/index/{accountId}")
	public String init(@PathVariable("accountId")long accountId, Model model) {
		WxMall wxMall = mallService.findMallByAccountId(accountId);
		session.setAttribute("accountId", accountId);
		if (null != wxMall) {
			model.addAttribute("wxMall", wxMall);
			long mallId = wxMall.getId();
			List<WxProductCategory> categoryList = mallService.findProductCategoryByMallId(mallId);
			model.addAttribute("categoryList", categoryList);
			return PageConstants.PAGE_MALL_INDEX;
		} else
			return PageConstants.PAGE_LOGIN;
	}

	@RequestMapping("/subcategory/{categoryId}")
	public String subcategory(@PathVariable("categoryId") long categoryId, Model model) {
		WxProductCategory category = mallService.findWxProductCategoryById(categoryId);
		if (null != category) {
			List<WxPrdtSubCategory> subCategoryList = mallService.findSubCategoryByCategoryId(categoryId);
			model.addAttribute("subCategoryList", subCategoryList);
			model.addAttribute("categoryStyle", category.getStyle());
		}
		return PageConstants.PAGE_MALL_CATEGORY;
	}

	@RequestMapping("/list/{subCategoryId}")
	public String list(@PathVariable("subCategoryId") long subCategoryId, Model model) {
		List<WxProductForm> formList=mallService.findPrdtListBySubCategoryId(subCategoryId);
		model.addAttribute("formList",formList);
		return PageConstants.PAGE_MALL_LIST;
	}
	
	@RequestMapping("/detail/{productId}")
	public String detail(@PathVariable("productId") long productId,Model model) {
		WxProductForm form=mallService.findProductById(productId);
		model.addAttribute("form",form);
		return PageConstants.PAGE_MALL_DETAIL;
	}

	@RequestMapping("/all")
	public String all(Model model) {
		return "mall/all";
	}

	@RequestMapping("/chaoliu")
	public String chaoliu(Model model) {
		return "mall/chaoliu";
	}

	

	@RequestMapping("/newup")
	public String newup(Model model) {
		return "mall/newup";
	}

	@RequestMapping("/huodong")
	public String huodong(Model model) {
		return "mall/huodong";
	}

}

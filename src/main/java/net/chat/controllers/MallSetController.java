/**
 * 
 */
package net.chat.controllers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.domain.mall.WxMall;
import net.chat.domain.mall.WxProductCategory;
import net.chat.formbean.SimpleBean;
import net.chat.service.AccountService;
import net.chat.service.mall.MallService;
import net.chat.utils.AppContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author bo.chen
 * 
 */
@Controller
@RequestMapping("/mallset")
public class MallSetController {

	@Autowired
	private MallService mallService;

	@Autowired
	AccountService accountService;

	@RequestMapping("/mall")
	public String mall(@RequestParam(value = "accountId", required = false) Long accountId, Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		if (CollectionUtils.isEmpty(accounts))
			return "redirect:/account/add";
		if (null == accountId)
			accountId = accounts.get(0).getId();

		WxMall wxmall = mallService.findMallByAccountId(accountId);
		if (null == wxmall) {
			wxmall = new WxMall();
			wxmall.setAccountId(accountId);
		}
		model.addAttribute("wxmall", wxmall);

		return PageConstants.PAGE_MALL_SET;
	}

	@RequestMapping("/savemall")
	public String saveMall(WxMall wxmall, @RequestParam(required = false) MultipartFile imageFile, Model model, HttpServletRequest req) throws IOException {
		wxmall = mallService.saveMall(wxmall);
		if (null != imageFile && !imageFile.isEmpty()) {
			long mallId = wxmall.getId();
			String realpath = req.getSession().getServletContext().getRealPath("/mallimg/images/");
			String suffix = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf("."));
			String imageUrl = mallId + File.separator + UUID.randomUUID() + suffix;
			File file = new File(realpath + File.separator + mallId);
			if (!file.exists())
				file.mkdir();
			FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File(realpath + File.separator + imageUrl));
			wxmall.setPicUrl(imageUrl);
			mallService.saveMall(wxmall);
		}

		return "redirect:/mallset/init?accountId=" + wxmall.getAccountId();
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, dateEditor);
		
	}

	@RequestMapping("/category")
	public String category(@RequestParam(value = "mallId", required = false) Long mallId, Model model) {
		Long userId = AppContext.getUserId();
		List<WxMall> malls = mallService.findMallByUserId(userId);
		model.addAttribute("malls", malls);
		if (CollectionUtils.isEmpty(malls))
			return "redirect:/mallset/mall";

		if (null == mallId)
			mallId = malls.get(0).getId();
		
		List<WxProductCategory> categorys=mallService.findProductCategoryByMallId(mallId);
		model.addAttribute("categorys", categorys);
		
		List<SimpleBean> styles=PageConstants.buildCategoryStyleList();
		model.addAttribute("styles", styles);

		return PageConstants.PAGE_MALL_CATE;
	}
	
	@RequestMapping("/categorydetail/{mallId}")
	public String categoryDetail(@PathVariable("mallId") Long mallId, @RequestParam(value = "cateId", required = false) Long cateId,Model model) {
		List<SimpleBean> styles=PageConstants.buildCategoryStyleList();
		model.addAttribute("styles", styles);
		WxProductCategory category=new WxProductCategory();
		category.setMallId(mallId);
		if(null!=cateId){
			category=mallService.findWxProductCategoryById(cateId);
		}
		model.addAttribute("category", category);
		return PageConstants.PAGE_MALL_CATEDETAIL;
	}
	@ResponseBody
	@RequestMapping("/savecategory")
	public long saveCategory(WxProductCategory category,Model model) {
		mallService.save(category);
		return  category.getMallId();
	}
	
}

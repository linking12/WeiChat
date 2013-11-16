package net.chat.controllers;

import java.util.List;

import net.chat.constants.PageConstants;
import net.chat.domain.WxAccount;
import net.chat.domain.WxArea;
import net.chat.domain.WxCity;
import net.chat.domain.WxLbs;
import net.chat.domain.WxProvince;
import net.chat.service.AccountService;
import net.chat.service.LbsService;
import net.chat.utils.AppContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/lbs")
public class LbsController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	LbsService lbsService;
	
	@RequestMapping("/init")
	public String init(@RequestParam(value = "accountId", required = false) Long accountId, Model model) {
		Long userId = AppContext.getUserId();
		List<WxAccount> accounts = accountService.findAccountByUserId(userId);
		model.addAttribute("accounts", accounts);
		if (CollectionUtils.isEmpty(accounts)) {
			return "redirect:/account/add";
		}
		if(null==accountId){
			accountId=accounts.get(0).getId();
		}
		WxLbs wxlbs=lbsService.findLbsByAccountId(accountId);
		if(null==wxlbs){
			wxlbs=new WxLbs();
		}
		List<WxProvince> provinceList=lbsService.findProvinceList();
		model.addAttribute("provinceList", provinceList);
		
		String provinceCode=StringUtils.isBlank(wxlbs.getProvinceCode())?provinceList.get(0).getProvinceCode():wxlbs.getProvinceCode();
		List<WxCity> cityList=lbsService.findCityList(provinceCode);
		model.addAttribute("cityList", cityList);
		
		String cityCode=StringUtils.isBlank(wxlbs.getCityCode())?cityList.get(0).getCityCode():wxlbs.getCityCode();
		List<WxArea> areaList=lbsService.findAreaList(cityCode);
		model.addAttribute("areaList", areaList);
		model.addAttribute("wxlbs", wxlbs);
		return PageConstants.PAGE_LBS;
	}

	@RequestMapping("/save")
	public String save(WxLbs wxlbs, Model model) {
		lbsService.saveLbs(wxlbs);
		return "redirect:/lbs/init?accountId="+wxlbs.getAccountId();
	}
	
	@ResponseBody
	@RequestMapping("/getcity/{parentCode}")
	public List<WxCity> getCityList(@PathVariable("parentCode")String parentCode) {
		return lbsService.findCityList(parentCode);
	}
	
	@ResponseBody
	@RequestMapping("/getarea/{parentCode}")
	public List<WxArea> getAreaList(@PathVariable("parentCode")String parentCode) {
		return lbsService.findAreaList(parentCode);
	}
}

/**
 * 
 */
package net.chat.controllers.mall;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.chat.constants.PageConstants;
import net.chat.domain.mall.WxMallExpressType;
import net.chat.domain.mall.WxMallUser;
import net.chat.formbean.SimpleBean;
import net.chat.formbean.mall.WxOrderForm;
import net.chat.formbean.mall.WxProductForm;
import net.chat.service.mall.MallService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bo.chen
 * 
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private MallService mallService;

	@Autowired
	HttpSession session;

	@RequestMapping("/preadd")
	public String preAdd(long[] productIds, long[] counts, Model model) {
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		if (null == mallUser) {
			return "redirect:/mall/login?fromUrl=/cart/show";
		}
		List<WxMallExpressType> expressList = mallService.findExpressTypeListByMall((Long) session.getAttribute("mallId"));
		model.addAttribute("expressList", expressList);

		List<SimpleBean> payTypeList = PageConstants.buildPayTypesList();
		model.addAttribute("payTypeList", payTypeList);
		WxOrderForm order = new WxOrderForm();

		order.setReceiver(mallUser.getUserName());
		order.setPhone(mallUser.getPhoneNo());
		order.setAddress(mallUser.getAddress());
		List<WxProductForm> productList = new ArrayList<WxProductForm>();
		BigDecimal orderPrice = BigDecimal.ZERO;
		for (int i = 0; i < productIds.length; i++) {
			long productId = productIds[i];
			WxProductForm product = mallService.findProductById(productId);
			// 件数
			product.setStock(counts[i]);
			orderPrice = orderPrice.add(product.getSalePrice().multiply(new BigDecimal(counts[i])));
			productList.add(product);
		}
		order.setProductList(productList);
		order.setSalePrice(orderPrice);
		model.addAttribute("order", order);
		return PageConstants.PAGE_MALL_ORDER;
	}

	@RequestMapping("/getexpressprice/{expressId}")
	@ResponseBody
	public BigDecimal getExpressPrice(@PathVariable("expressId") long expressId) {
		return mallService.getExpressPriceById(expressId);

	}
	
	@RequestMapping("/add")
	public String add(WxOrderForm order,Model model) {
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		if (null == mallUser) {
			return "redirect:/mall/login?fromUrl=/cart/show";
		}
		order.setMallId(mallUser.getMallId());
		order.setUserId(mallUser.getId());
		mallService.addOrder(order);
		return "redirect:/cart/show";
	}
}

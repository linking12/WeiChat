/**
 * 
 */
package net.chat.controllers.mall;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.chat.constants.PageConstants;
import net.chat.domain.mall.WxMallCart;
import net.chat.domain.mall.WxMallUser;
import net.chat.formbean.mall.WxCartForm;
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
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private MallService mallService;

	@Autowired
	HttpSession session;
	
	@RequestMapping(value="/show")
	public String show(Model model) {
		
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		if (null == mallUser) {
			return "redirect:/mall/login?fromUrl=/cart/show";
		} else {
			List<WxCartForm> cartList = mallService.findCartList(mallUser.getId());
			model.addAttribute("cartList", cartList);
			return PageConstants.PAGE_MALL_CART;
		}
	}

	@RequestMapping(value="/delete/{productId}")
	public String delete(@PathVariable("productId") long productId, Model model) {
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		if (null == mallUser) {
			return "redirect:/mall/login?fromUrl=/cart/show";
		} else {
			mallService.deleteCartByProductId(productId, mallUser.getId());			
			return "redirect:/cart/show";
		}
	}

	@RequestMapping("/add/{productId}/{count}")
	@ResponseBody
	public int add(@PathVariable("productId") long productId, @PathVariable("count") long count, Model model) {
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		WxMallCart wxMallCart = new WxMallCart();
		wxMallCart.setCount(count);
		wxMallCart.setProductId(productId);
		wxMallCart.setMallId((Long) session.getAttribute("mallId"));
		wxMallCart.setMallUserId(mallUser.getId());
		try {
			mallService.addToCart(wxMallCart);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}
}

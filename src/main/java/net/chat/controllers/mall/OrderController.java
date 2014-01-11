/**
 * 
 */
package net.chat.controllers.mall;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.chat.constants.PageConstants;
import net.chat.domain.mall.WxMall;
import net.chat.domain.mall.WxMallExpressType;
import net.chat.domain.mall.WxMallOrder;
import net.chat.domain.mall.WxMallUser;
import net.chat.formbean.SimpleBean;
import net.chat.formbean.mall.TenpayForm;
import net.chat.formbean.mall.WxOrderForm;
import net.chat.formbean.mall.WxProductForm;
import net.chat.service.mall.MallService;
import net.chat.tenpay.RequestHandler;
import net.chat.tenpay.util.TenpayUtil;

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

	private static final String PARTNER = "1900000113";
	private static final String KEY = "e82573dc7e6136ba414f2e2affbe39fa";
//	private static final String returnUrl = "http://www.yidia.cn:8080/";
	private static final String returnUrl = "http://www.yidia.cn/";

	@RequestMapping("/preadd")
	public String preAdd(long[] productIds, long[] counts, Model model) {
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		if (null == mallUser) {
			return "redirect:/mall/login?fromUrl=/cart/show";
		}
		List<WxMallExpressType> expressList = mallService
				.findExpressTypeListByMall(mallUser.getMallId());
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
			orderPrice = orderPrice.add(product.getSalePrice().multiply(
					new BigDecimal(counts[i])));
			productList.add(product);
		}
		order.setProductList(productList);
		order.setSalePrice(orderPrice);
		model.addAttribute("order", order);
		return PageConstants.PAGE_MALL_PREORDER;
	}

	@RequestMapping("/getexpressprice/{expressId}")
	@ResponseBody
	public BigDecimal getExpressPrice(@PathVariable("expressId") long expressId) {
		return mallService.getExpressPriceById(expressId);

	}

	@RequestMapping("/add")
	public String add(WxOrderForm order, Model model) {
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		if (null == mallUser) {
			return "redirect:/mall/login?fromUrl=/cart/show";
		}
		order.setMallId(mallUser.getMallId());
		order.setUserId(mallUser.getId());
		mallService.addOrder(order);
		return "redirect:/order/myorder";
	}

	@RequestMapping("/myorder")
	public String myorder(Model model) {
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		if (null == mallUser) {
			return "redirect:/mall/login?fromUrl=/order/myorder";
		}
		List<WxMallOrder> orderList = mallService.findOrderList(
				mallUser.getMallId(), mallUser.getId());
		model.addAttribute("orderList", orderList);

		// List<WxMallExpressType> expressList =
		// mallService.findExpressTypeListByMall((Long)
		// session.getAttribute("mallId"));
		// model.addAttribute("expressList", expressList);
		//
		// List<SimpleBean> payTypeList = PageConstants.buildPayTypesList();
		// model.addAttribute("payTypeList", payTypeList);
		List<SimpleBean> statusList = PageConstants.buildOrderStatusList();
		model.addAttribute("statusList", statusList);

		return PageConstants.PAGE_MALL_MYORDER;
	}

	@RequestMapping("/view/{orderId}")
	public String view(@PathVariable("orderId") long orderId, Model model) {
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		if (null == mallUser) {
			return "redirect:/mall/login?fromUrl=/order/myorder";
		}
		WxOrderForm order = mallService.findOrderByOrderId(
				mallUser.getMallId(), mallUser.getId(), orderId);
		model.addAttribute("order", order);
		model.addAttribute("isView", true);
		List<WxMallExpressType> expressList = mallService
				.findExpressTypeListByMall(mallUser.getMallId());
		model.addAttribute("expressList", expressList);

		List<SimpleBean> payTypeList = PageConstants.buildPayTypesList();
		model.addAttribute("payTypeList", payTypeList);
		return PageConstants.PAGE_MALL_ORDER;
	}

	@RequestMapping("/pay/{orderId}")
	public String pay(@PathVariable("orderId") long orderId, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		WxMallUser mallUser = (WxMallUser) session.getAttribute("mallUser");
		if (null == mallUser) {
			return "redirect:/mall/login?fromUrl=/order/myorder";
		}
		WxOrderForm order = mallService.findOrderByOrderId(
				mallUser.getMallId(), mallUser.getId(), orderId);
		// 财付通
		if (1 == order.getPayType()) {
			WxMall mall = mallService.findMallById(mallUser.getMallId());
			TenpayForm form = new TenpayForm();
			form.setSpname(mall.getMallName());
			form.setPartner(PARTNER);
			form.setKey(KEY);
			form.setReturn_url(returnUrl + request.getContextPath()
					+ "/order/payedurl/" + order.getId() + "/"
					+ mallUser.getId());
			form.setNotify_url(returnUrl + request.getContextPath()
					+ "/order/paynotice/" + order.getId() + "/"
					+ mallUser.getId());
			form.setOrder_price(order.getSalePrice().toString());
			List<WxProductForm> prdtList = order.getProductList();
			String prdtName = "";
			for (WxProductForm prdt : prdtList) {
				prdtName += prdt.getProductName();
			}
			if (prdtName.length() > 40) {
				prdtName = prdtName.substring(0, 30) + "...";
			}
			form.setProduct_name(prdtName);
			form.setOut_trade_no(order.getOrderNo());
			form.setRemarkexplain(prdtName);
			model.addAttribute("form", form);
			return PageConstants.PAGE_MALL_TENPAY;
//			RequestHandler req = this.buildRequestHandler(form, request,
//					response);
//			System.out.println(req.getRequestURL());
//			return "redirect:" + req.getRequestURL();
		} else
			return null;
	}

	@RequestMapping("/payedurl/{orderId}/{userId}")
	public String payedurl(@PathVariable("orderId") Long orderId,
			@PathVariable("userId") Long userId, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		WxMallUser mallUser = mallService.findByMallUserId(userId);
		if (null == mallUser) {
			return "redirect:/mall/login?fromUrl=/order/myorder";
		} else {
			session.setAttribute("mallUser", mallUser);
		}
		WxOrderForm order = mallService.findOrderByOrderId(
				mallUser.getMallId(), mallUser.getId(), orderId);
		WxMall mall = mallService.findMallById(mallUser.getMallId());
		TenpayForm form = new TenpayForm();
		form.setSpname(mall.getMallName());
		form.setPartner(PARTNER);
		form.setKey(KEY);

		form.setOrder_price(order.getSalePrice().toString());
		List<WxProductForm> prdtList = order.getProductList();
		String prdtName = "";
		for (WxProductForm prdt : prdtList) {
			prdtName += prdt.getProductName();
		}
		if (prdtName.length() > 40) {
			prdtName = prdtName.substring(0, 30) + "...";
		}
		form.setProduct_name(prdtName);
		form.setOut_trade_no(order.getOrderNo());
		form.setRemarkexplain(prdtName);
		model.addAttribute("form", form);
		model.addAttribute("orderId", orderId);
		model.addAttribute("userId", userId);
		return PageConstants.PAGE_MALL_PAY_RETURN;
	}

	@RequestMapping("/paynotice/{orderId}/{userId}")
	public String paynotice(@PathVariable("orderId") Long orderId,
			@PathVariable("userId") Long userId, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		WxMallUser mallUser = mallService.findByMallUserId(userId);
		WxOrderForm order = mallService.findOrderByOrderId(
				mallUser.getMallId(), mallUser.getId(), orderId);
		WxMall mall = mallService.findMallById(mallUser.getMallId());
		TenpayForm form = new TenpayForm();
		form.setSpname(mall.getMallName());
		form.setPartner(PARTNER);
		form.setKey(KEY);

		form.setOrder_price(order.getSalePrice().toString());
		List<WxProductForm> prdtList = order.getProductList();
		String prdtName = "";
		for (WxProductForm prdt : prdtList) {
			prdtName += prdt.getProductName();
		}
		if (prdtName.length() > 40) {
			prdtName = prdtName.substring(0, 30) + "...";
		}
		form.setProduct_name(prdtName);
		form.setOut_trade_no(order.getOrderNo());
		form.setRemarkexplain(prdtName);
		model.addAttribute("form", form);
		model.addAttribute("orderId", orderId);
		model.addAttribute("userId", userId);
		return PageConstants.PAGE_MALL_PAY_NOTICE;
	}
	@ResponseBody
	@RequestMapping("/successpay/{orderId}/{userId}")
	public String successpay(@PathVariable("orderId") Long orderId,
			@PathVariable("userId") Long userId, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		mallService.payOrder(userId, orderId);
		WxMallUser mallUser = mallService.findByMallUserId(userId);
		session.setAttribute("mallUser", mallUser);
		return "1";
	}

	private RequestHandler buildRequestHandler(TenpayForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init();
		// 设置密钥
		reqHandler.setKey(form.getKey());
		// 设置支付网关
		reqHandler.setGateUrl("https://gw.tenpay.com/gateway/pay.htm");

		// -----------------------------
		// 设置支付参数
		// -----------------------------
		reqHandler.setParameter("partner", form.getPartner()); // 商户号
		reqHandler.setParameter("out_trade_no", form.getOut_trade_no()); // 商家订单号
		String order_price = form.getOrder_price();
		double total_fee = (Double.valueOf(order_price) * 100);
		int fee = (int) total_fee;
		reqHandler.setParameter("total_fee", String.valueOf(fee)); // 商品金额,以分为单位
		reqHandler.setParameter("return_url", form.getReturn_url()); // 交易完成后跳转的URL
		reqHandler.setParameter("notify_url", form.getNotify_url()); // 接收财付通通知的URL
		reqHandler.setParameter("body", form.getRemarkexplain()); // 商品描述
		reqHandler.setParameter("bank_type", "DEFAULT"); // 银行类型(中介担保时此参数无效)
		reqHandler.setParameter("spbill_create_ip", request.getRemoteAddr()); // 用户的公网ip，不是商户服务器IP
		reqHandler.setParameter("fee_type", "1"); // 币种，1人民币
		reqHandler.setParameter("subject", form.getProduct_name()); // 商品名称(中介交易时必填)

		// 系统可选参数
		reqHandler.setParameter("sign_type", "MD5"); // 签名类型,默认：MD5
		reqHandler.setParameter("service_version", "1.0"); // 版本号，默认为1.0
		reqHandler.setParameter("input_charset", "GBK"); // 字符编码
		reqHandler.setParameter("sign_key_index", "1"); // 密钥序号

		// 业务可选参数
		reqHandler.setParameter("attach", ""); // 附加数据，原样返回
		reqHandler.setParameter("product_fee", ""); // 商品费用，必须保证transport_fee +
													// product_fee=total_fee
		reqHandler.setParameter("transport_fee", "0"); // 物流费用，必须保证transport_fee
														// +
														// product_fee=total_fee

		reqHandler.setParameter("time_start", TenpayUtil.getCurrTime()); // 订单生成时间，格式为yyyymmddhhmmss
		reqHandler.setParameter("time_expire", ""); // 订单失效时间，格式为yyyymmddhhmmss
		reqHandler.setParameter("buyer_id", ""); // 买方财付通账号
		reqHandler.setParameter("goods_tag", ""); // 商品标记
		reqHandler.setParameter("trade_mode", form.getTrade_mode()); // 交易模式，1即时到账(默认)，2中介担保，3后台选择（买家进支付中心列表选择）
		reqHandler.setParameter("transport_desc", ""); // 物流说明
		reqHandler.setParameter("trans_type", "1"); // 交易类型，1实物交易，2虚拟交易
		reqHandler.setParameter("agentid", ""); // 平台ID
		reqHandler.setParameter("agent_type", ""); // 代理模式，0无代理(默认)，1表示卡易售模式，2表示网店模式
		reqHandler.setParameter("seller_id", ""); // 卖家商户号，为空则等同于partner
		return reqHandler;

	}
}

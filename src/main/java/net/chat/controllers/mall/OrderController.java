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
import net.chat.tenpay.ResponseHandler;
import net.chat.tenpay.client.ClientResponseHandler;
import net.chat.tenpay.client.TenpayHttpClient;
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
	// private static final String returnUrl = "http://www.yidia.cn:8080/";
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
//			return PageConstants.PAGE_MALL_TENPAY;
			 RequestHandler req = this.buildRequestHandler(form, request,
			 response);
			 System.out.println(req.getRequestURL());
			 return "redirect:" + req.getRequestURL();
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
			throws Exception {
		WxMallUser mallUser = mallService.findByMallUserId(userId);
		WxOrderForm order = mallService.findOrderByOrderId(
				mallUser.getMallId(), mallUser.getId(), orderId);
		WxMall mall = mallService.findMallById(mallUser.getMallId());
		TenpayForm form = new TenpayForm();
		form.setSpname(mall.getMallName());
		form.setPartner(PARTNER);
		form.setKey(KEY);

		// form.setOrder_price(order.getSalePrice().toString());
		// List<WxProductForm> prdtList = order.getProductList();
		// String prdtName = "";
		// for (WxProductForm prdt : prdtList) {
		// prdtName += prdt.getProductName();
		// }
		// if (prdtName.length() > 40) {
		// prdtName = prdtName.substring(0, 30) + "...";
		// }
		// form.setProduct_name(prdtName);
		// form.setOut_trade_no(order.getOrderNo());
		// form.setRemarkexplain(prdtName);
		// model.addAttribute("form", form);
		// model.addAttribute("orderId", orderId);
		// model.addAttribute("userId", userId);
		ResponseHandler resHandler = new ResponseHandler(request, response);
		resHandler.setKey(form.getKey());

		System.out.println("后台回调返回参数:" + resHandler.getAllParameters());
		if (resHandler.isTenpaySign()) {

			// 通知id
			String notify_id = resHandler.getParameter("notify_id");

			// 创建请求对象
			RequestHandler queryReq = new RequestHandler(null, null);
			// 通信对象
			TenpayHttpClient httpClient = new TenpayHttpClient();
			// 应答对象
			ClientResponseHandler queryRes = new ClientResponseHandler();

			// 通过通知ID查询，确保通知来至财付通
			queryReq.init();
			queryReq.setKey(form.getKey());
			queryReq.setGateUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml");
			queryReq.setParameter("partner", form.getPartner());
			queryReq.setParameter("notify_id", notify_id);

			// 通信对象
			httpClient.setTimeOut(5);
			// 设置请求内容
			httpClient.setReqContent(queryReq.getRequestURL());
			System.out.println("验证ID请求字符串:" + queryReq.getRequestURL());

			// 后台调用
			if (httpClient.call()) {
				// 设置结果参数
				queryRes.setContent(httpClient.getResContent());
				System.out.println("验证ID返回字符串:" + httpClient.getResContent());
				queryRes.setKey(form.getKey());

				// 获取id验证返回状态码，0表示此通知id是财付通发起
				String retcode = queryRes.getParameter("retcode");

				// 商户订单号
				String out_trade_no = resHandler.getParameter("out_trade_no");
				// 财付通订单号
				String transaction_id = resHandler
						.getParameter("transaction_id");
				// 金额,以分为单位
				String total_fee = resHandler.getParameter("total_fee");
				// 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
				String discount = resHandler.getParameter("discount");
				// 支付结果
				String trade_state = resHandler.getParameter("trade_state");
				// 交易模式，1即时到账，2中介担保
				String trade_mode = resHandler.getParameter("trade_mode");

				// 判断签名及结果queryRes.isTenpaySign()&& "0".equals(retcode)
				if (queryRes.isTenpaySign() && "0".equals(retcode)) {
					System.out.println("id验证成功");

					if ("1".equals(trade_mode)) { // 即时到账
						if ("0".equals(trade_state)) {
							// ------------------------------
							// 即时到账处理业务开始
							// ------------------------------

							// 处理数据库逻辑
							// 注意交易单不要重复处理
							// 注意判断返回金额

							// ------------------------------
							// 即时到账处理业务完毕
							// ------------------------------
							System.out.println("即时到账支付成功");
							mallService.payOrder(userId, orderId);
							resHandler.sendToCFT("success");
							// %>
							// <script type="text/javascript"
							// src="${ctx}/js/jquery/jquery-1.9.1.js"></script>
							// <script type="text/javascript">
							// $.post("${ctx}/order/successpay/<%=orderId%>/<%=userId%>",
							// function(data){
							// if("1"==data){
							// <%
							// %>
							// }
							// });
							// </script>
							// <%

							// 给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
							// resHandler.sendToCFT("success");

						} else {
							System.out.println("即时到账支付失败");
							resHandler.sendToCFT("fail");
						}
					} else if ("2".equals(trade_mode)) { // 中介担保
						// ------------------------------
						// 中介担保处理业务开始
						// ------------------------------

						// 处理数据库逻辑
						// 注意交易单不要重复处理
						// 注意判断返回金额

						int iStatus = TenpayUtil.toInt(trade_state);
						switch (iStatus) {
						case 0: // 付款成功

							break;
						case 1: // 交易创建

							break;
						case 2: // 收获地址填写完毕

							break;
						case 4: // 卖家发货成功

							break;
						case 5: // 买家收货确认，交易成功

							break;
						case 6: // 交易关闭，未完成超时关闭

							break;
						case 7: // 修改交易价格成功

							break;
						case 8: // 买家发起退款

							break;
						case 9: // 退款成功

							break;
						case 10: // 退款关闭

							break;
						default:
						}

						// ------------------------------
						// 中介担保处理业务完毕
						// ------------------------------

						System.out.println("trade_state = " + trade_state);
						// 给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
						mallService.payOrder(userId, orderId);
						resHandler.sendToCFT("success");
					}
				} else {
					// 错误时，返回结果未签名，记录retcode、retmsg看失败详情。
					System.out.println("查询验证签名失败或id验证失败" + ",retcode:"
							+ queryRes.getParameter("retcode"));
				}
			} else {
				System.out.println("后台调用通信失败");
				System.out.println(httpClient.getResponseCode());
				System.out.println(httpClient.getErrInfo());
				// 有可能因为网络原因，请求已经处理，但未收到应答。
			}
		} else {
			System.out.println("通知签名验证失败");
		}
		return null;
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

/**
 * 
 */
package net.chat.formbean.mall;

import java.io.Serializable;

/**
 * @author think
 *
 */
public class TenpayForm  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8797709283659786501L;

	//收款方
	private String spname="测试商户号" ;  

	//商户号 测试
	private String partner = "1900000113";
	//String partner = "1217743001";
	//密钥 测试
	private String key = "e82573dc7e6136ba414f2e2affbe39fa";
	//String key = "Wadaomi8888";

	//交易完成后跳转的URL
	private String return_url = "http://*/tenpay_api_b2c/payReturnUrl.jsp";

	//接收财付通通知的URL
	private String notify_url = "http://*/tenpay_api_b2c/payNotifyUrl.jsp";
	//获取提交的商品价格
	private String order_price;
	
	//获取提交的商品名称
	private String product_name;

	//获取提交的备注信息
	private String remarkexplain;
	
	//获取提交的订单号
	private String out_trade_no;  

	//支付方式 即时到帐
	private String trade_mode="1";

	public String getSpname() {
		return spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getOrder_price() {
		return order_price;
	}

	public void setOrder_price(String order_price) {
		this.order_price = order_price;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getRemarkexplain() {
		return remarkexplain;
	}

	public void setRemarkexplain(String remarkexplain) {
		this.remarkexplain = remarkexplain;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_mode() {
		return trade_mode;
	}

	public void setTrade_mode(String trade_mode) {
		this.trade_mode = trade_mode;
	} 
	
	
}

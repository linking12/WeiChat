<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF-8"%>
<%@ page import="net.chat.tenpay.util.TenpayUtil" %>
<%@ page import="net.chat.tenpay.RequestHandler"%>
<%@ page import="net.chat.formbean.mall.TenpayForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="mall.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
TenpayForm form=(TenpayForm)request.getAttribute("form");
//收款方
String spname =form.getSpname();

//商户号
String partner =  form.getPartner();
//String partner = "1217743001";
//密钥
String key =form.getKey();
//String key = "Wadaomi8888";

//交易完成后跳转的URL
String return_url =form.getReturn_url();

//接收财付通通知的URL
String notify_url =form.getNotify_url();

//---------------------------------------------------------
//财付通网关支付请求示例，商户按照此文档进行开发即可
//---------------------------------------------------------
request.setCharacterEncoding("GBK");

//获取提交的商品价格
String order_price= form.getOrder_price();
/* 商品价格（包含运费），以分为单位 */
double total_fee = (Double.valueOf(order_price) * 100);
int fee = (int)total_fee;
  
//获取提交的商品名称
String product_name=form.getProduct_name();

//获取提交的备注信息
String remarkexplain=form.getRemarkexplain();

String desc =remarkexplain;

//获取提交的订单号
String out_trade_no=form.getOut_trade_no();

//支付方式
String trade_mode=form.getTrade_mode();

//---------------生成订单号 开始------------------------
//当前时间 yyyyMMddHHmmss
//String currTime = TenpayUtil.getCurrTime();
//8位日期
//String strTime = currTime.substring(8, currTime.length());
//四位随机数
//String strRandom = TenpayUtil.buildRandom(4) + "";
//10位序列号,可以自行调整。
//String strReq = strTime + strRandom;
//订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
//String out_trade_no = strReq;
//---------------生成订单号 结束------------------------


String currTime = TenpayUtil.getCurrTime();
//创建支付请求对象
RequestHandler reqHandler = new RequestHandler(request, response);
reqHandler.init();
//设置密钥
reqHandler.setKey(key);
//设置支付网关
reqHandler.setGateUrl("https://gw.tenpay.com/gateway/pay.htm");


//-----------------------------
//设置支付参数
//-----------------------------
reqHandler.setParameter("partner", partner);		        //商户号
reqHandler.setParameter("out_trade_no", out_trade_no);		//商家订单号
reqHandler.setParameter("total_fee", String.valueOf(fee));			        //商品金额,以分为单位
reqHandler.setParameter("return_url", return_url);		    //交易完成后跳转的URL
reqHandler.setParameter("notify_url", notify_url);		    //接收财付通通知的URL
reqHandler.setParameter("body", desc);	                    //商品描述
reqHandler.setParameter("bank_type", "DEFAULT");		    //银行类型(中介担保时此参数无效)
reqHandler.setParameter("spbill_create_ip",request.getRemoteAddr());   //用户的公网ip，不是商户服务器IP
reqHandler.setParameter("fee_type", "1");                    //币种，1人民币
reqHandler.setParameter("subject", form.getProduct_name());              //商品名称(中介交易时必填)

//系统可选参数
reqHandler.setParameter("sign_type", "MD5");                //签名类型,默认：MD5
reqHandler.setParameter("service_version", "1.0");			//版本号，默认为1.0
reqHandler.setParameter("input_charset", "GBK");            //字符编码
reqHandler.setParameter("sign_key_index", "1");             //密钥序号


//业务可选参数
reqHandler.setParameter("attach", "");                      //附加数据，原样返回
reqHandler.setParameter("product_fee", "");                 //商品费用，必须保证transport_fee + product_fee=total_fee
reqHandler.setParameter("transport_fee", "0");               //物流费用，必须保证transport_fee + product_fee=total_fee
reqHandler.setParameter("time_start", currTime);            //订单生成时间，格式为yyyymmddhhmmss
reqHandler.setParameter("time_expire", "");                 //订单失效时间，格式为yyyymmddhhmmss
reqHandler.setParameter("buyer_id", "");                    //买方财付通账号
reqHandler.setParameter("goods_tag", "");                   //商品标记
reqHandler.setParameter("trade_mode", trade_mode);                 //交易模式，1即时到账(默认)，2中介担保，3后台选择（买家进支付中心列表选择）
reqHandler.setParameter("transport_desc", "");              //物流说明
reqHandler.setParameter("trans_type", "1");                  //交易类型，1实物交易，2虚拟交易
reqHandler.setParameter("agentid", "");                     //平台ID
reqHandler.setParameter("agent_type", "");                  //代理模式，0无代理(默认)，1表示卡易售模式，2表示网店模式
reqHandler.setParameter("seller_id", "");                   //卖家商户号，为空则等同于partner

//请求的url
String requestUrl = reqHandler.getRequestURL();

%>
<html>
<head>

<script language="javascript" type="text/javascript">
	window.location.href="<%=requestUrl%>";
</script>

</head>
<body ></body>
</html>

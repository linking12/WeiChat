<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<% 

//收款方
String spname = "财付通双接口测试";  

//商户号
String partner = "1900000113";
//String partner = "1217743001";
//密钥
String key = "e82573dc7e6136ba414f2e2affbe39fa";
//String key = "Wadaomi8888";

//交易完成后跳转的URL
String return_url = "http://*/tenpay_api_b2c/payReturnUrl.jsp";

//接收财付通通知的URL
String notify_url = "http://*/tenpay_api_b2c/payNotifyUrl.jsp";

%>

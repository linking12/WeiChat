<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="js" value="${ctx}/js"/>
<c:set var="css" value="${ctx}/css"/>
<c:set var="img" value="${ctx}/mimg"/>
<script type="text/javascript" src="${js}/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).bind('mobileinit',function(e){
   $.mobile.ajaxEnabled = false;
});
</script>
<script type="text/javascript" src="${js}/jquery/jquery.mobile-1.3.2.min.js"></script>
<script type="text/javascript" src="${js}/mall.js"></script>
<link href="${css }/jquery.mobile-1.3.2.min.css" rel="stylesheet" type="text/css" />
<link href="${css }/mallmain.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<script type="text/javascript">
$(function(){
	try  
	{ 
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideToolbar');
		});
	}  
	catch (e)  
	{  }
});
</script>
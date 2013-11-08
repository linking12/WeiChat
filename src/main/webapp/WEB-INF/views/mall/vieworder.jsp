<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>电商</title>
<%@ include file="mall.jsp"%>
	<script type="text/javascript">
	</script>
</head>

<body>
<form:form id="form1" modelAttribute="order" method="post" >
	<div data-role="page" id="page2">
		<%@ include file="mallmenu.jsp"%>
		<div data-role="content">
        <div class="receive">
        	<p>收件人:&nbsp;${order.receiver }<br>
        	<br>	
        	电话:&nbsp;<i style="color: #a30000;font-weight: bold;">${order.phone }</i><br>
        	<br>
        	 地址:&nbsp;${order.address }
           <p>
            
        </div>
        <div data-role="fieldcontain" class="input_div">
               <c:forEach items="${expressList }" var="express"  >
               	<c:if test="${express.id == order.expressType}">${express.expressName}:&nbsp;<i style="color: #a30000;font-weight: bold;">¥${express.price}</i></c:if>    
               </c:forEach>
           
        </div>
        <div data-role="fieldcontain" class="input_div">
        		<c:forEach items="${payTypeList }" var="payType"  >
               		<c:if test="${payType.key == order.payType}">付款方式:&nbsp;<i style="color: #a30000;font-weight: bold;">${payType.value}</i></c:if>    
               	</c:forEach>
        </div>       
        <div class="xqing">
        	<span>商品详情</span>
        	<c:forEach items="${order.productList }" var="product" varStatus="status">      		
        	 <div class="neirong">
        		<img src="${img }/${product.defaultPic}">
        		<em>${product.productName }<br>
        		 数量：${product.stock }<br>
        		 	价格：¥ <i>${product.salePrice } </i>
        		 	</em>
       		 	 </div>
        	</c:forEach>
            <div class="total">
            	订单金额：¥ <i id="totalPrice"> ${order.salePrice }</i>  
            </div>
        </div>
        <div class="tj_bt_div"><a href="${ctx }/order/myorder" data-role="button" data-corners="false" class="tj_bt">返回</a></div>
	</div>
		<%@ include file="mallbottom.jsp"%>
	
	</div>
	</form:form>
</body>

</html>

<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>电商</title>
<%@ include file="mall.jsp"%>
</head>

<body>
	<!-- Home -->
	<div data-role="page" id="page2">
		<%@ include file="mallmenu.jsp"%>
		<div data-role="content">
			     <div class="sm_tx">
<em>我的订单：</em><br></div>
			 <div class="neirong">
			 	<table width="100%">
			 		<tr><td >订单号</td><td>订单状态</td><td>价格</td></tr>
				 	<c:forEach items="${orderList }" var="order">
		  	          <tr>
		  	          	<td><a href="${ctx }/order/view/${order.id}">${order.orderNo}</a></td>
	  	          		<td><c:forEach items="${statusList }" var="status"><c:if test="${status.key== order.status}">${status.value }</c:if></c:forEach></td>
	  	          		<td>${order.salePrice}</td>
	  	          		
  	          		</tr>	
			           </c:forEach>
			 	</table>            	                
           	 </div>
			
		</div>
		<%@ include file="mallbottom.jsp"%>
	</div>
</body>

</html>

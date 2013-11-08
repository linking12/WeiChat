<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>电商</title>
<%@ include file="mall.jsp"%>
	<script type="text/javascript">
	$(function(){
		calcTotalPrice();
	});
	
	function calcTotalPrice(){
		$.post("${ctx}/order/getexpressprice/"+$("#expressType").val(), 
			function(data){
			var totalPrice=data+${order.salePrice};
		 		$("#totalPrice").text(totalPrice);
		});
	}
	
	function goadd(){
		$('#form1').attr('action', '${ctx}/order/add');
		$('#form1').submit();
	}
	</script>
</head>

<body>
<form:form id="form1" modelAttribute="order" method="post" >
	<div data-role="page" id="page2">
		<%@ include file="mallmenu.jsp"%>
		<div data-role="content">
        <div class="receive">
        	收件人：<form:input path="receiver"/>
        	电话:<form:input path="phone"/>
        	地址:<form:input path="address"/>
           <p> </p>
            <!-- <em><a href="#" data-role="button" data-inline="true" data-corners="false" data-mini="true">修改</a></em> -->
        </div>
        <div data-role="fieldcontain" class="input_div">
        <form:select path="expressType" id="expressType" onchange="calcTotalPrice()">
               <c:forEach items="${expressList }" var="express"  >
               <option value="${express.id }">${express.expressName}: ¥${express.price}</option>
               </c:forEach>
            
        </form:select>
           
        </div>
        <div data-role="fieldcontain" class="input_div">
         	<form:select path="payType" id="payType"  items="${payTypeList }" itemValue="key" itemLabel="value"/> 
        </div>       
        <div class="xqing">
        	<span>商品详情</span>
        	<c:forEach items="${order.productList }" var="product" varStatus="status">
        		<form:hidden path="productList[${status.index}].productId"/>
        		<form:hidden path="productList[${status.index}].stock"/>
        	 <div class="neirong">
        		<img src="${img }/${product.defaultPic}">
        		<em>${product.productName }<br>
        		 数量：${product.stock }<br>
        		 	价格：¥ <i>${product.salePrice } </i>
        		 	</em>
       		 	 </div>
        	</c:forEach>
            <div class="total">
            	应付金额：¥ <i id="totalPrice"> ${order.salePrice }</i>  
            </div>
        </div>
        <div class="tj_bt_div"><a href="javascript:goadd()" data-role="button" data-corners="false" class="tj_bt">提交订单</a></div>
	</div>
		<%@ include file="mallbottom.jsp"%>
	
	</div>
	</form:form>
</body>

</html>

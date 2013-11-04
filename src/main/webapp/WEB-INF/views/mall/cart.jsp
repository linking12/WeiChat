<!DOCTYPE html> 
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html> 
<head> 
<title>购物车</title>
<%@ include file="mall.jsp"%>
<script type="text/javascript">

</script>
</head>

<body>
<!-- Home -->
<div data-role="page" id="page2">
	<%@ include file="mallmenu.jsp"%>
    <div data-role="content">
        <div class="cart_div">
        <c:forEach items="${cartList }" var="cartForm">
        	<div class="check_div">
                <label>
                    <input type="checkbox" name="productId " style="display:none" value="${cartForm.productForm.productId}">&nbsp;
                </label>
            </div>          
	            <div class="neirong">
	            	<img src="${img }/${cartForm.productForm.defaultPic}">
	                <em>${cartForm.productForm.productName}<br>
					价格：¥ <i>${cartForm.productForm.salePrice} </i><br>
					运费：<i class="gra">快递¥${cartForm.productForm.expenses}</i>
	                </em>
	                 <div class="shuliang">数量：<input type="text" data-role="none" value="${cartForm.mallCart.count}" class="in_sl">
	                 &nbsp;总计：¥ <i> ${cartForm.productForm.salePrice * cartForm.mallCart.count}</i></div>
	                               
           </c:forEach>
           
           
           
        </div>
        <div class="tj_bt_div">
        	<a href="#" data-role="button" data-corners="false" class="add_bt">在线支付</a>
            <a href="#" data-role="button" data-corners="false" class="buy_bt c_white">货到付款</a>
        </div>
        <div class="js_div">
        	<div class="all_c">
                <label>
                    <input type="checkbox" name="checkbox-0 " style="display:none">全选
                </label>
       		</div>
            <div class="zongjia">总价：¥ <i>108.00</i> </div>
            <div class="zj_bt">
            	<a href="#" data-role="button" data-corners="false" class="buy_bt zj_w">去结算</a>
            </div>
        </div>

    <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

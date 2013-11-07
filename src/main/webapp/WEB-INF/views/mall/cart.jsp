<!DOCTYPE html> 
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html> 
<head> 
<title>购物车</title>
<%@ include file="mall.jsp"%>
<script type="text/javascript">
$(function(){
	  $.mobile.ajaxEnabled = false;
})

function checkAll(){
	var isChecked=$("#checkAllBtn").is(":checked");
	if(isChecked){
		$("input[name='productIds']").prop("checked",true).checkboxradio("refresh");
	}else{
		$("input[name='productIds']").prop("checked",false).checkboxradio("refresh");
	}
	calcTotal();
}

function checkthis(index){
	calcTotal();
}

function preOrder(){
	$("#form1").empty();
	var ischecked=false;
	$("input[name='productIds']").each(function(){
		if(this.checked){
			ischecked=true;
			var clild="<input type='hidden' name='productIds' value='"+this.value+"'/>";
			clild+="<input type='hidden' name='counts' value='"+$("#count"+this.id).val()+"'/>";
			
			$("#form1").append(clild);
		}
	});
	if(ischecked){
		$('#form1').attr('action','${ctx}/order/preadd');
		$('#form1').submit();
	}else {
		alert("请选择至少一个商品!");
		return;
	}
}

function deleteCart(productId){
	$('#form1').attr('action','${ctx}/cart/delete/'+productId);
	$('#form1').submit();
}
function calcTotal(){
	var total=0;
	$("input[name='productIds']").each(function(){
		if(this.checked){
			var saleprice=$("#salePrice"+this.id).val();
			var count=$("#count"+this.id).val();
			total+=saleprice*count;
		}
		
	});
	$("#totalprice").text(total.toFixed(2));
	
}

function calcthis(index){
	var saleprice=$("#salePrice"+index).val();
	var count=$("#count"+index).val();
	$("#total"+index).text((saleprice*count).toFixed(2));
	calcTotal();
}
</script>
</head>

<body>
<!-- Home -->
<form id="form1" method="post" action="" modelAttribute="wxMallUser"></form>
<div data-role="page" id="page2">
	<%@ include file="mallmenu.jsp"%>
    <div data-role="content" >
        <div class="cart_div">
        <c:forEach items="${cartList }" var="cartForm" varStatus="status">
        	<div class="check_div">
                <label>
                    <input type="checkbox" name="productIds" id="${status.index}" style="display:none" value="${cartForm.productForm.productId}" onclick="checkthis(${status.index})">&nbsp;
                	
                </label>
            </div>          
	            <div class="neirong">
	            	<img src="${img }/${cartForm.productForm.defaultPic}">
	                <em>${cartForm.productForm.productName}<br>
					价格：¥ <i>${cartForm.productForm.salePrice} </i><br>
					运费：<i class="gra">快递¥${cartForm.productForm.expenses}</i>
	                </em>
	                 <div class="shuliang">数量：<input type="text" onkeyup="value=this.value.replace(/\D+/g,'')" data-role="none" id="count${status.index}" value="${cartForm.mallCart.count}" class="in_sl" onblur="calcthis(${status.index})">
	                 &nbsp;总计：¥ <i id="total${status.index}"> ${cartForm.productForm.salePrice * cartForm.mallCart.count}</i>
	                 <input type="hidden" id="salePrice${status.index}" value="${cartForm.productForm.salePrice}"/><a href="javascript:deleteCart('${cartForm.productForm.productId}')" >删除</a>          
	                 
	                 </div>
               </div>  
           </c:forEach>
        </div>
       <!-- 
        <div class="tj_bt_div">
        	<a href="javascript:dotest()" data-role="button" data-corners="false" class="add_bt">在线支付</a>
            <a href="javascript:dotest()" data-role="button" data-corners="false" class="buy_bt c_white">货到付款</a>
        </div> 
        -->
        <div class="js_div">
        	<div class="all_c">
                <label>
                    <input type="checkbox" id="checkAllBtn" onclick="checkAll()" style="display:none" value="1">全选
                </label>
       		</div>
            <div class="zongjia">总价：¥ <i id="totalprice">0.00</i> </div>
            <div class="zj_bt">
            	<a href="javascript:void(0);" onclick="preOrder()" data-role="button" data-corners="false" class="buy_bt zj_w">去结算</a>          
            </div>
        </div>

    <%@ include file="mallbottom.jsp"%>
</div>

</body>
</html>

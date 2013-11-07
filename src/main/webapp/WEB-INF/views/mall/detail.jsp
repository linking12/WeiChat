<!DOCTYPE html> 
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html> 
<head> 
<title>商品详情</title>
<%@ include file="mall.jsp"%>
<script type="text/javascript">
$(function(){
	  $.mobile.ajaxEnabled = false;
})
function addToCart(){
	if('${needlogin}'==1){
		$("#form1").attr("action","${ctx}/mall/login?fromUrl="+'${url}');
		$("#form1").submit();
	}else{
		$.post("${ctx}/cart/add/"+$("#productId").val()+"/"+$("#counts").val(), 
				function(data){
			 		if(data==1){
			 			$("#msg").text("添加成功!");
			 			$("#msg").show();
			 		}else {
			 			$("#msg").text("加入购物车失败!请重试!");
			 			$("#msg").show();
			 		}
		});}
}

function add(){
	var quantity = $("#counts").val();
	quantity = parseInt(quantity) + 1;
	$("#counts").val(quantity);
}
function minus(){
	var quantity = $("#counts").val();
	quantity = parseInt(quantity) - 1;
	$("#counts").val(quantity);
	if(quantity < 1 ){		
		$("#shuliang").val(1);
		}
}
</script>
</head>

<body>
<!-- Home -->
<form id="form1" method="post"></form>
<div data-role="page" id="page2">
	<%@ include file="mallmenu.jsp"%>
    <div data-role="content" class="b_con">
    <input type ="hidden" id="productId" value="${form.productId }">
		<div class="dt_banner"><img src="${img }/${form.defaultPic}"></div> 
        <div class="dt_txt">
        ${form.productName }<br>
        现价：<em>¥  ${form.salePrice }</em><br>
        运费：<i>快递¥${form.expenses }</i>
		</div>
        <div class="add">
        	<div class="sl_div">数量：</div>
            <div class="sl_minus" onClick="minus()"><img src="${img }/main/sl_minus.png"></div>
            <div class="txt_box"><input  type="text" data-role="none" id="counts" value="1"></div>
            <div class="sl_add" onClick="add()"><img src="${img }/main/sl_add.png"></div>
        </div>
        <div class="bt_box">
        	<a href="javascript:addToCart()" data-role="button" data-corners="false" class="add_bt" rel="external">加入购物车</a>
            <!-- <a href="confirm.html" data-role="button" data-corners="false" class="buy_bt" rel="external">立即购买</a> -->
            
        </div>
        <div ><font color="red" id="msg" style="display:none">&nbsp;&nbsp;</font></div>
       
        <div class="sm_tx">
<em>商品详情：</em><br>
 ${form.descrpiton }
        </div>
        <div class="arrow"><img src="${img }/main/arrow.png"></div>
    </div>
    <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

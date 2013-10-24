<!DOCTYPE html> 
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html> 
<head> 
<title>商品详情</title>
<%@ include file="mall.jsp"%>
</head>

<body>
<!-- Home -->
<div data-role="page" id="page2">
	<%@ include file="mallmenu.jsp"%>
    <div data-role="content" class="b_con">
		<div class="dt_banner"><img src="${img }/main/cl_banner.jpg"></div>
        <div class="dt_txt">
        【年中促】绿色蛙稻米500 健康大米江南名品青浦薄稻 5kg<br>
        现价：<em>¥ 98.00</em><br>
        运费：<i>快递¥10.00</i>
		</div>
        <div class="add">
        	<div class="sl_div">数量：</div>
            <div class="sl_minus" onClick="minus()"><img src="${img }/main/sl_minus.png"></div>
            <div class="txt_box"><input name="" type="text" data-role="none" id="shuliang" value="0"></div>
            <div class="sl_add" onClick="add()"><img src="${img }/main/sl_add.png"></div>
        </div>
        <div class="bt_box">
        	<a href="cart.html" data-role="button" data-corners="false" class="add_bt" rel="external">加入购物车</a>
            <a href="confirm.html" data-role="button" data-corners="false" class="buy_bt" rel="external">立即购买</a>
        </div>
        <div class="sm_tx">
<em>商品详情：</em><br>
生产许可证编号：310001027787<br>
产品标准号：NY/T 419<br>
厂名：上海恒阳粮油有限公司<br>
厂址：上海市青浦区练塘镇小蒸社区共喜路19号厂家<br>
联系方式：4008608608<br>
储藏方法：请至于阴凉干燥处
        </div>
        <div class="arrow"><img src="${img }/main/arrow.png"></div>
        
        
    </div>
    <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html> 
<head> 
<title>电商</title>
<%@ include file="mall.jsp"%>
</head>

<body>
<!-- Home -->
<div data-role="page" id="page2">
	 <%@ include file="mallmenu.jsp"%>
    <div data-role="content" class="b_con">
		<div class="all_line">
        	<a href="${ctx }/mall/list">
        	<img src="${img }/main/all_img.jpg">
            <div class="jeishao">
            	<span class="l1">限时优惠</span>
                <p>男女装指定摇粒绒茄克</p>
                <em>截至10/17</em><br><br>
				<i>￥149</i><br><br>
				<i><</i>
            </div></a>
        </div>
        <div class="all_line all_bg">
        	<div class="jeishao">
            	<span class="l2">新品上市</span>
                <p class="t_tight">男女装指定摇粒绒茄克</p>
                <em class="t_tight">截至10/17</em><br><br>
				<i class="t_tight">￥149</i><br><br>
				<i class="t_tight">></i>
            </div>
        	<img src="${img }/main/all_img1.jpg"> 
        </div>
        <div class="all_line all_bg1">
        	<img src="${img }/main/all_img2.jpg">
            <div class="jeishao">
            	<span class="l3">限时优惠</span>
                <p class="big">Woman</p>
				<i class="white"><</i>
            </div>
        </div>
    </div>
    
      <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

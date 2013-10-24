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
		<div class="hd_line">
        	<div class="ban_left"><img src="${img}/main/ban1.jpg"></div>
            <div class="ban_cen"><img src="${img}/main/ban2.jpg"></div>
            <div class="ban_right">
            	<div class="arrow_left"></div>
            	五折专场
            </div>
        </div> 
        <div class="hd_line">
        	<div class="ban_left"><img src="${img}/main/ban3.jpg"></div>
            <div class="ban_cen"><img src="${img}/main/ban4.jpg"></div>
            <div class="ban_right">
            	<div class="arrow_left"></div>
            	秋季换装
            </div>
        </div>
        <div class="hd_line">
        	<div class="ban1"><img src="${img}/main/ban5.jpg"></div>
            <div class="ban1"><img src="${img}/main/ban6.jpg"></div>
            <div class="ban1"><img src="${img}/main/ban7.jpg"></div>
            <div class="ban_right">
            	<div class="arrow_left"></div>
            	特别推荐
            </div>
        </div>     
    </div>
    
   <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

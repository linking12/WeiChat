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
		<div class="newup_line">
        	<div class="newup_img"><img src="${img}/main/sj_img1.jpg"></div>
            <div class="ban_right">
            	<div class="arrow_left"></div>
            	摇粒绒夹克
            </div>
        </div> 
        <div class="newup_line new_color">
        	<div class="newup_img"><img src="${img}/main/sj_img2.jpg"></div>
            <div class="ban_right txt_color">
            	<div class="arrow_left1"></div>
            	法兰绒衬衫
            </div>
        </div>
        <div class="newup_line new_color1">
        	<div class="newup_img"><img src="${img}/main/sj_img3.jpg"></div>
            <div class="ban_right txt_color">
            	<div class="arrow_left2"></div>
            	精纺系列
            </div>
        </div>
    </div>
    
    <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

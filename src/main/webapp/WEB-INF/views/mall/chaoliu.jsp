<!DOCTYPE html> 
<html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<head> 
<title>电商</title>
<%@ include file="mall.jsp"%>
</head>

<body>
<!-- Home -->
<div data-role="page" id="page2">
	<%@ include file="mallmenu.jsp"%>
    <div data-role="content">
		<div class="index_banner"><img src="${img }/main/cl_banner.jpg"></div>
        <div class="ban_txt">
        	<div class="sanjiao"></div>
            <div class="ban_name">
            	<span>潮流打底裤 <em>美腿有诀窍</em></span>
            </div>
            <div class="ar_right"></div>
        </div>
        <div class="index_banner"><img src="${img }/main/cl_banner1.jpg"></div>
        <div class="ban_txt">
        	<div class="sanjiao"></div>
            <div class="ban_name">
            	<span>巴黎之旅 <em>休闲裤专场</em></span>
            </div>
            <div class="ar_right"></div>
        </div>   
    </div>
    
   <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

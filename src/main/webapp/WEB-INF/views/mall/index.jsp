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
    <div data-role="content">
		<div class="index_banner"><img src="${img }/main/index_banner.jpg"></div>
        <div class="i_link">
        	<a href="${ctx }/mall/all">全部商品</a>
            <a href="${ctx }/mall/huodong" style="margin:0;">活动专区</a>
            <a href="${ctx }/mall/newup">最新上架</a>
            <a href="${ctx }/mall/chaoliu" style="margin:0;">潮流趋势</a>
        </div>      
    </div>
     <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

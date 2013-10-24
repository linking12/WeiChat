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
		<div class="index_banner"><img src="${img}/main/index_banner.jpg"></div>
        <div class="sp_list">
        	<a href="${ctx }/mall/detail" rel="external">
            	<img src="${img}/main/sp1.jpg">
                <em>条纹彩色波浪打底裤</em>
                <em>黑底白条</em>
                <i>¥39.00 </i>
            </a>
            <a href="${ctx }/mall/detail" rel="external">
            	<img src="${img}/main/sp2.jpg">
                <em>日系原宿风星空打底裤</em>
                <em>星空（紫色）</em>
                <i>¥49.00 </i>
            </a>
            <a href="${ctx }/mall/detail" rel="external">
            	<img src="${img}/main/sp3.jpg">
                <em>甜美蕾丝修身打底裤</em>
                <em>蓝色</em>
                <i>¥48.00</i>
            </a>
        </div> 
        <div class="sp_list">
        	<a href="${ctx }/mall/detail" rel="external">
            	<img src="${img}/main/sp4.jpg">
                <em>条纹彩色波浪打底裤 </em>
                <em>蓝黄条纹</em>
                <i>¥39.00 </i>
            </a>
            <a href="${ctx }/mall/detail" rel="external">
            	<img src="${img}/main/sp5.jpg">
                <em>韩版糖果色针织打底裤 </em>
                <em>果绿色</em>
                <i>¥39.00 </i>
            </a>
            <a href="${ctx }/mall/detail" rel="external">
            	<img src="${img}/main/sp6.jpg">
                <em>韩版糖果色针织打底裤 </em>
                <em>天蓝色</em>
                <i>¥39.00</i>
            </a>
        </div>       
    </div>
    
         <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

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
		<!-- <div class="index_banner"><img src="${img}/main/index_banner.jpg"></div> -->
		<c:forEach items="${formList }" var="form" varStatus="status">
		 	<c:if test="${status.index%3==0 }"><div class="sp_list"></c:if>
			<a href="${ctx }/mall/detail/${form.productId}" rel="external">
            	<img src="${img}/${form.defaultPic}">
                <em>${form.productName}</em>
                <i>¥${form.salePrice}</i>
            </a>
            <c:if test="${(status.index+1)%3==0 }"></div></c:if>
            <c:if test="${formList.size()%3!=0 &&status.count==formList.size() }"></div></c:if>
		</c:forEach>           
    </div>
    <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

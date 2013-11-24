<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head> 
<title>电商</title>
<%@ include file="mall.jsp"%>
<script type="text/javascript">
	window.localStorage.setItem("wxmallaccoutId",'${wxMall.accountId}');
</script>
</head>

<body>
<!-- Home -->
<div data-role="page" id="page2">
 <%@ include file="mallmenu.jsp"%>
    <div data-role="content">
		<div class="index_banner"><img src="${img }/${wxMall.picUrl}"></div>
        <div class="i_link">
        <c:forEach items="${categoryList }" var="category" varStatus="status">
	     	<a href="${ctx }/mall/subcategory/${category.id }" class="ui-link" <c:if test="${status.index%2!=0 }">style="margin:0;"</c:if>>${category.categoryName }</a>
        </c:forEach>
        </div>      
    </div>
     <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

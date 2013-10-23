<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
		<title>信息详情</title>
		<style type="text/css">
			body {
				background-image: url();
			}
		</style>
	</head>
	<body>
		<c:choose>
			<c:when test="${msgType=='text' }">
				<div align="center"><ul>${message.content }</ul></div>
				<br/>
			</c:when>
			<c:otherwise>
			 <c:forEach items="${contents }" var="content">
			 	 <div align="center">
			 	 	<ul>
			 	 	<c:if test="${msgType=='image' }"><img src="${ctx }/${content.picUrl } "/></c:if></ul>
			 	 	<ul>${content.description }</ul>
			 	 	<br/>
		 	 	</div>
		 	 	
			 </c:forEach>
			</c:otherwise>
		</c:choose>
	</body>
</html>
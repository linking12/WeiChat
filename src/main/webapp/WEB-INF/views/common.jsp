
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="js" value="${ctx}/js"/>
<c:set var="css" value="${ctx}/css"/>
<c:set var="images" value="${ctx}/images"/>
<link href="${css }/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/jquery/1.6/jquery.js"></script>
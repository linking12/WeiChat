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
        <c:forEach items="${subCategoryList }" var="subCategory" varStatus="status">
        	<c:choose>
        		<c:when test="${categoryStyle ==1 }">
	        		<c:choose>
	        			<c:when test="${status.index%2==0 }">
	        				<div class="all_line">
	        					<a href="${ctx }/mall/list/${subCategory.id}">
	        						<img src="${img }/${subCategory.picUrl}">
	        							<div class="jeishao">
	        								<span class="l1">${subCategory.subCategoryName}</span>
	        								${subCategory.description }
	        							</div>
       							</a>
       						</div>
	        			</c:when>
	        			<c:otherwise>
		        			<div class="all_line all_bg">
		        				<a href="${ctx }/mall/list/${subCategory.id}">
			        				<div class="jeishao">
			        					<span class="l2">${subCategory.subCategoryName}</span>
			        					${subCategory.description }
			        				</div>
			        				<img src="${img }/${subCategory.picUrl}"> 
		        				</a>
	        				</div>
	        			</c:otherwise>
        			</c:choose>
        		</c:when>
	        	<c:when test="${categoryStyle ==2 }">
		       		<div class='newup_line <c:if test="${status.index%2!=0 }">new_color</c:if>' >
		       			<a href="${ctx }/mall/list/${subCategory.id}">
			        		<div class="newup_img"><img src="${img }/${subCategory.picUrl}"></div>
		           			<div class='ban_right <c:if test="${status.index%2!=0 }">txt_color</c:if>'>
		            			<div class="arrow_left"></div>
		            			${subCategory.subCategoryName}
		            			${subCategory.description }
	            			</div>
            			</a>
       		 		</div> 
	        	</c:when>
	        	<c:when test="${categoryStyle ==3 }">
	        		<a href="${ctx }/mall/list/${subCategory.id}">
		       	 		<div class="index_banner"><img src="${img }/${subCategory.picUrl}"></div>
		       	  		<div class="ban_txt">
	        				<div class="sanjiao"></div>
	            			<div class="ban_name">
	            				<span>${subCategory.subCategoryName}<em>${subCategory.description }</em></span>
	            			</div>
	            			<div class="ar_right"></div>
	        			</div>
        			</a>
	        	</c:when>
	        </c:choose>
        </c:forEach>
    </div>
    
      <%@ include file="mallbottom.jsp"%>
</div>
</body>
</html>

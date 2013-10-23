<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="js" value="${ctx}/js"/>
<c:set var="css" value="${ctx}/css"/>
<c:set var="images" value="${ctx}/images"/>
<link href="${css }/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${js}/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${js}/jquery/weichat.js"></script>
<style type="text/css">
	body {
		background-image: url(${images}/bei.jpg);
	}
</style>
<script type="text/JavaScript">
$(function(){
	$(".nav_bar_c span").mouseover(function(){
		$(this).addClass("bg_col");
		});
	$(".nav_bar_c span").mouseout(function(){
		$(this).removeClass("bg_col");
		})
	})

$(document).ready(function(){
     
    $(window).scroll(function (){ 
        var offsetTop =205 + $(window).scrollTop(); 
		if(offsetTop > 405){
			 $(".nav_bar").css({top: $(window).scrollTop()});
			}
			else{
				$(".nav_bar").css({top: 205+"px"});
				}

    }); 
}); 

</script>

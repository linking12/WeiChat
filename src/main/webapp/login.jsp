<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="org.springframework.security.web.WebAttributes"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="js" value="${ctx}/js" />
<c:set var="css" value="${ctx}/css" />
<c:set var="images" value="${ctx}/images" />
<link href="${css}/main.css" rel="stylesheet" type="text/css" />
<script src="${js}/jquery/jquery-1.9.1.js" type="text/javascript"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>用户登录</title>
<script type="text/javascript">
	$(function(){
		$("#registerbtn").click(function(){
			 $('#form1').attr('action', '${ctx }/register');
             $('#form1').submit();
		})
		
		$("#b_check").click(function(){
			if($("#checkbox").is(':checked')){
				$("#checkbox").prop("checked",false);
				$(this).removeClass("cc_check")
				}else{
					$("#checkbox").prop("checked",true);
					$(this).addClass("cc_check")
					}
			});
		});
</script>
</head>
<body>
<form  id="form1" action="j_spring_security_check" autocomplete="on" method="POST">
<div class="b_con">
	<div class="longin_box">
    	<h2>登录</h2>
        <div class="log_input_box">
        	<dl>
            	<dt><img src="${images}/name.png" /></dt>
                <dd><input id="username" name="username"  required="required" type="text" placeholder="用户名 或者 mymail@mail.com" /></dd>
            </dl>
            <dl>
            	<dt><img src="${images}/pw.png" /></dt>
                <dd><input id="password" name="password"  required="required" type="password" placeholder="eg. X8df!90EO" /></dd>
            </dl>
            <font color="red">${SPRING_SECURITY_LAST_EXCEPTION.message}</font>
            <dl style="margin:20px 0 0 0">
            	<dt><span id="b_check">&nbsp;</span><input name="" type="checkbox" value="" style="display: none" id="checkbox" /></dt>
                <dd><em>记住密码   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</em></dd>
            </dl>
            <div class="log_btbox">
                <input id="registerbtn" type="button" value="马上注册" class="reg_btn" />
                <input id="submitbtn" type="submit" value="登录" class="log_bt" />
            </div>
        </div>
    </div>
    <div class="lodo_div"></div>
</div>
</form>
</body>
</html>
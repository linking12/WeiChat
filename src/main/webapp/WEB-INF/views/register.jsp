<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<link href="${css}/main.css" rel="stylesheet" type="text/css" />
<title>用户注册</title>

</head>
<body>
<form:form id="registerForm" method="post" modelAttribute="registerForm" action="${ctx}/register/submit">
<div class="b_con">
	<div class="reg_box">
    	<h2>用户注册</h2>
        <div class="input_box">
        	<dl>
            	<dt>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</dt>
                <dd><form:input path="phone" class="full" required="required"/></dd>
            </dl>
            <dl>
            	<dt>用 户  名</dt>
                <dd><form:input path="name" class="full" required="required" /></dd>
            </dl>
            <dl>
            	<dt>密      码</dt>
                <dd><form:password path="spassword1" class="full" required="required"/></dd>
            </dl>
            <dl>
            	<dt>确认密码</dt>
                <dd><form:password path="spassword2" class="full" required="required"/></dd>
            </dl>
            <font color="red">${errorMsg}</font>
        </div>
        <div class="longinnow">
        	 <span>已有账号？ <a href="${ctx }/j_spring_security_check" class="d"><em>立即登陆</em></a></span>
        	 <input id="submitbtn" type="submit" value="注册" class="reg_bt" />
        </div>
        
    </div>
    <div class="lodo_div"></div>
    
</div>
</form:form>
</body>

</html>

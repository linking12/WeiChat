<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>电商</title>
<%@ include file="mall.jsp"%>
<script type="text/javascript">
$(function(){
	  $.mobile.ajaxEnabled = false;
})
function doregist() {
	if(validate()){
		$('#form1').attr('action', '${ctx}/mall/doregist');
		$('#form1').submit();
	}
	
}

function validate(){
	var flag=true;
	if($("#userName").val()==''){
		$("#userName").attr("placeholder","用户名不能为空!登录名必须是英文或者数字!");
		flag=false;
	}
	if($("#password").val()==''||$("#password").val().length<4||$("#password").val().length>8){
		$("#password").attr("placeholder","密码在4~8位之间!");
		$("#password").val("");
		flag=false;
	}
	if($("#password1").val()==''||$("#password1").val().length<4||$("#password1").val().length>8){
		$("#password1").attr("placeholder","密码在4~8位之间!");
		$("#password1").val("");
		flag=false;
	}
	if($("#password").val()!=$("#password1").val()){
		$("#password1").val("");
		$("#password1").attr("placeholder","两次输入密码必须相同!");
		flag=false;
	}
	reg=/^1[358]\d{9}$/;
	if($("#phoneNo").val()==""||!reg.test($("#phoneNo").val())){
		$("#phoneNo").val("");
		$("#phoneNo").attr("placeholder","手机号码必须输入!11位数字，前2位是13开头 或15 开头 或18 开头");
		flag=false;
	}
	return flag;
}
</script>
</head>

<body>
	<!-- Home -->
	<form:form id="form1" method="post" modelAttribute="wxMallUser">
	<div data-role="page" id="page2">
		<%@ include file="mallmenu.jsp"%>
	    <div data-role="content">
			<div class="lg_ico"></div>
			<input type="hidden" name="fromUrl" value="${fromUrl }"/>
	        <div class="in_box">
	         	<dl><dt><span style="dispaly:none;color:red" id="errmsg"></span></dt></dl>
	        	<dl>
	            	<dt><font color="red">*</font>用户名：</dt>
	                <dd><form:input id="userName" path="userName" data-role="none" required="required" placeholder="登录名必须是英文或者数字!" /></dd> 
	            </dl>
	            <dl class="top_bor">
	            	<dt><font color="red">*</font>密&nbsp;&nbsp;&nbsp;&nbsp;码：</dt>
	                <dd><form:input id="password" path="password" data-role="none" required="required" placeholder="密码在4~8位之间" /></dd> 
	            </dl>
	            <dl class="top_bor">
	            	<dt><font color="red">*</font>确认密码：</dt>
	                <dd><input type="password" id="password1" data-role="none" required="required" placeholder="密码在4~8位之间" /></dd> 
	            </dl>
	            <dl class="top_bor">
	            	<dt><font color="red">*</font>手&nbsp;&nbsp;&nbsp;&nbsp;机：</dt>
	                <dd><form:input path="phoneNo" data-role="none" required="required" placeholder="11位数字，前2位是13开头 或15 开头 或18 开头" /></dd> 
	            </dl>
	            <dl class="top_bor">
	            	<dt>地&nbsp;&nbsp;&nbsp;&nbsp;址：</dt>
	                <dd><form:input id="address" path="address" data-role="none" required="required" placeholder="上海市徐汇区**路**号" /></dd> 
	            </dl>
	        </div>
	        <div class="lg_bt"><a href="javascript:doregist();" data-role="button" data-corners="false">马上注册</a></div>
    </div>
		<%@ include file="mallbottom.jsp"%>
	</div>
	</form:form>
</body>
</html>

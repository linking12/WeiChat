<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>电商</title>
<%@ include file="mall.jsp"%>
<script type="text/javascript">
	function login() {
		$('#mallUserForm').attr('action', '${ctx}/mall/mall_security_check');
		$('#mallUserForm').submit();
	}
</script>
</head>

<body>
	<!-- Home -->
	<div data-role="page" id="page2">
		<%@ include file="mallmenu.jsp"%>
		<div data-role="content">
			<div class="lg_ico"></div>
			<form:form id="mallUserForm" method="post" modelAttribute="mallUser">
				<div class="in_box">
					<dl>
						<dt>用户名：</dt>
						<dd>
							<form:input path="userName" data-role="none" />
							<input name="fromUrl" type="hidden" data-role="none" value="${fromUrl}">
						</dd>
					</dl>
					<dl class="top_bor">
						<dt>密&nbsp;&nbsp;&nbsp;&nbsp;码：</dt>
						<dd>
							<form:input path="password" data-role="none" />
						</dd>
					</dl>
				</div>
				<div class="lg_bt">
					<a href="javascript:login()" data-role="button">登&nbsp;&nbsp;录</a>
				</div>
				<div class="pw_back">找回密码</div>
				<div class="reg_bt">
					<a href="javascript:login()" data-role="button">登&nbsp;&nbsp;录</a>
				</div>
			</form:form>
		</div>
		<%@ include file="mallbottom.jsp"%>
	</div>
</body>
</html>

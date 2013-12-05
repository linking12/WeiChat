<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>信息内容管理</title>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var options = {
							url : '${ctx}/mallset/savecategory',
							type : "POST",
							success : function(data, statusText, xhr, $form) {
								parent.location.href = "${ctx}/mallset/category?mallId="
										+ data;
								$(window.parent.document).find(".winbj")
										.remove();
								$(window.parent.document).find(".tanChu")
										.remove();

							}
						};
						$('#submitbtn').click(function() {

							if ($("#form2").valid()) {
								$("#form2").ajaxForm(options);
							}
						});

					});
</script>
<style type="text/css">
body {
	background-image: url();
}
</style>
</head>
<body>
	<form:form id="form2" method="post" modelAttribute="category">
		<form:hidden path="id" />
		<form:hidden path="mallId" />
		<table width="96%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="20%" height="40" class="biao">类别名称&nbsp;<font
					color="red">*</font></td>
				<td><form:input path="categoryName" style="width: 300px;"
						class="required" /></td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">样式<font color="red">*</font></td>
				<td><form:radiobuttons items="${styles }" path="style"
						itemValue="key" itemLabel="value" class="required" /></td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">简短描述&nbsp;<font
					color="red">*</font></td>
				<td><form:textarea path="description" cols="45" rows="10"
						class="required" /></td>
			</tr>
			<tr>
				<td height="50"><form:button id="submitbtn" class="btn-primary">提交</form:button></td>
				<td height="50"></td>
			</tr>
		</table>
	</form:form>
</body>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<link href="${css }/jquery-ui-1.10.3.custom.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${js}/jquery/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript"
	src="${js}/jquery/jquery.ui.datepicker-zh-TW.js"></script>
<c:set var="mimg" value="${ctx}/mimg" />
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
	<form:form id="form2" method="post" modelAttribute="subcategory"
		enctype="multipart/form-data">
		<form:hidden path="id" />
		<form:hidden path="categoryId" />
		<table width="96%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="20%" height="40" class="biao">子类别名称&nbsp;<font
					color="red">*</font></td>
				<td><form:input path="subCategoryName" style="width: 300px;"
						class="required" /></td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">所属父类别&nbsp;<font
					color="red">*</font></td>
				<td><select style="width: 100px" disabled>
						<c:forEach items="${categorys}" var="category">
							<c:if test="${subcategory.categoryId== category.id}">
								<option value="${subcategory.categoryId}">${category.categoryName}</option>
							</c:if>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">类别图片<font color="red">*</font></td>
				<td><input name="imageFile" id="imageFile" type="file" size="1"
					accept="image/*" class="required" /></td>
			</tr>
			<tr height="40">
				<td></td>
				<td><img src="${mimg }/${subcategory.picUrl}" height="400"
					width="300"> <form:hidden path="picUrl" /></td>
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
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
	$(document).ready(
			function() {
				$('div.thumb img').hoverpulse().each(function() {
					var $img = $(this);
					var link = $img.attr('data-link');
					$img.attr('title', 'Goto: ' + link);
					$img.click(function() {
						window.open(link);
						return false;
					});
				});
				$("#submitbtn").click(
						function() {
							if (!validate()) {
								return false;
							}
							$('#subcategoryForm').attr('action',
									'${ctx}/mallset/submitSubCategory');
							$('#subcategoryForm').submit();
						});
			});
	function validate() {
		var flag = true;
		if ("" == $("#subCategoryName").val()) {
			$("#esubCategoryName").show();
			flag = false;
		} else {
			$("#esubCategoryName").hide();
		}
		if ("" == $("#imageFile").val()) {
			$("#eimageFile").show();
			flag = false;
		} else {
			$("#eimageFile").hide();
		}
		if ("" == $("#description").val()) {
			$("#edescription").show();
			flag = false;
		} else {
			$("#edescription").hide();
		}
		return flag;
	}
</script>
<style type="text/css">
body {
	background-image: url();
}
</style>
</head>
<body>
	<form:form id="subcategoryForm" method="post"
		modelAttribute="subcategory" enctype="multipart/form-data">
		<form:hidden path="id" />
		<form:hidden path="categoryId" />
		<div class="b_con">
			<div class="by_box">
				<%@include file="../menu.jsp"%>
				<table width="967" border="0" align="center" cellpadding="0"
					cellspacing="0">

					<tr>
						<td height="10">&nbsp;</td>
					</tr>
					<!-- 正文开始 -->
					<tr>
						<td>
							<table width="942" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="53" background="${images}/shan.png">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="2%">&nbsp;</td>
												<td width="4%"><img src="${images}/oic_3.png"
													width="30" height="21" /></td>
												<td width="94%" class="biao">商城子类别</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td height="350" valign="top" background="${images}/he.png">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="120" valign="top">
													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td height="40">
																<div align="center">
																	<a href="${ctx }/mallset/mall" class="biao1">商城设置</a>
																</div>
															</td>
														</tr>
														<tr>
															<td height="40">
																<div align="center">
																	<a href="${ctx }/mallset/category" class="biao1">商品类别设置</a>
																</div>
															</td>
														</tr>
														<tr>
															<td height="40">
																<div align="center">
																	<a href="${ctx }/mallset/subcategory" class="biao1">商品子类别设置</a>
																</div>
															</td>
														</tr>
														<tr>
															<td height="40" bgcolor="#e87352">
																<div align="center">
																	<a href="${ctx }/mallset/product" class="biao1">商品设置</a>
																</div>
															</td>
														</tr>
													</table>
												</td>
												<td width="1" bgcolor="#999999"><img
													src="${images}/su.png" width="1" height="257" /></td>
												<td width="770" valign="top">
													<table width="96%" border="0" align="center"
														cellpadding="0" cellspacing="0">
														<tr>
															<td width="20%" height="40" class="biao">子类别名称&nbsp;<font
																color="red">*</font></td>
															<td><form:input path="subCategoryName"
																	style="width: 300px;" class="required" /> <span
																id="esubCategoryName" style="display: none"
																class="error">请输入子类别名称!</span></td>
														</tr>
														<tr>
															<td width="20%" height="40" class="biao">所属父类别&nbsp;</td>
															<td><select style="width: 100px" disabled>
																	<c:forEach items="${categorys}" var="category">
																		<c:if test="${subcategory.categoryId== category.id}">
																			<option value="${subcategory.categoryId}">${category.categoryName}</option>
																		</c:if>
																	</c:forEach>
															</select></td>
														</tr>
														<tr>
															<td width="20%" height="40" class="biao">类别图片<font
																color="red">*</font></td>
															<td><input name="imageFile" id="imageFile"
																type="file" size="1" accept="image/*" class="required" />
																<span id="eimageFile" style="display: none"
																class="error">请选择图片文件!</span></td>
														</tr>
														<c:if test="${subcategory.picUrl!=null}">
															<tr>
																<td width="20%" height="220" class="biao">原图片&nbsp;<font
																	color="red">*</font>
																<td valign="top"><div class="thumb">
																		<img src="${mimg }${subcategory.picUrl}" height="200"
																			width="200">
																	</div> <form:hidden path="picUrl" /></td>
															</tr>
														</c:if>
														<tr>
															<td width="20%" height="40" class="biao">简短描述&nbsp;<font
																color="red">*</font></td>
															<td valign="bottom"><form:textarea
																	path="description" cols="45" rows="5" class="required" />
																<span id="edescription" style="display: none"
																class="error">请商城类别描述!</span></td>
														</tr>
														<tr>
															<td height="50"><form:button id="submitbtn"
																	class="btn-primary">提交</form:button></td>
															<td height="50"></td>
														</tr>
													</table>

												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><img src="${images}/xia_zu.png" width="942"
										height="14" /></td>
								</tr>

							</table>
						</td>
					</tr>
				</table><%@include file="../bottom.jsp"%>
			</div>
		</div>
	</form:form>
</body>
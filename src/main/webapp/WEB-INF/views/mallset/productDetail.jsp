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
				$("#effectiveDate").datepicker();
				$("#expiryDate").datepicker();
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
</head>
<body>
	<form:form id="productform" method="post" modelAttribute="product">
		<form:hidden path="id" />
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
															<td width="20%" height="40" class="biao">所属商城&nbsp;</td>
															<td><select style="width: 300px">
																	<c:forEach items="${malls}" var="mall">
																		<option value="${mall.id}">${mall.mallName}</option>
																	</c:forEach>
															</select></td>
														</tr>
														<tr>
															<td width="20%" height="40" class="biao">商品名称&nbsp;<font
																color="red">*</font></td>
															<td><form:input path="productName"
																	style="width: 300px;" class="required" /> <span
																id="eproductName" style="display: none" class="error">请输入商品名称!</span></td>
														</tr>
														<tr>
															<td width="20%" height="40" class="biao">商品价格&nbsp;<font
																color="red">*</font></td>
															<td><form:input path="productPrice"
																	style="width: 300px;" class="required" /> <span
																id="eproductPrice" style="display: none" class="error">请输入商品价格!</span></td>
														</tr>
														<tr>
															<td width="20%" height="40" class="biao">商品数量&nbsp;<font
																color="red">*</font></td>
															<td><form:input path="stock" style="width: 300px;"
																	class="required" /> <span id="estock"
																style="display: none" class="error">请输入商品数量!</span></td>
														</tr>
														<tr>
															<td width="20%" height="40" class="biao">商品有效期&nbsp;<font color="red">*</font></td>
															<td><form:input path="effectiveDate"
																	style="width: 135px;" class="required" />&nbsp;到&nbsp;<form:input
																	path="expiryDate" style="width: 140px;"
																	class="required" /></td>
														</tr>
														<tr>
															<td width="20%" height="40" class="biao">简短描述<font
																color="red">*</font></td>
															<td><form:textarea path="descrpiton" cols="65"
																	rows="10" /> &nbsp;<span id="errorDescription"
																class="error" style="display: none;">简短描述不能为空!</span></td>
														</tr>
														<tr>
															<td height="50"><form:button id="submitbtn"
																	class="btn-primary">下一步</form:button></td>
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
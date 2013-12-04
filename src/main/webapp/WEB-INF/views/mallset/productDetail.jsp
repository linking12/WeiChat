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
	$(document).ready(function() {
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
		$("#submitbtn").click(function() {
			if (!validate()) {
				return false;
			}
			$('#productform').attr('action', '${ctx}/mallset/submitProduct');
			$('#productform').submit();
		});
	});
	function validate() {
		var flag = true;
		if ("" == $("#mallId").val()) {
			$("#emallId").show();
			flag = false;
		} else {
			$("#emallId").hide();
		}
		if ("" == $("#productName").val()) {
			$("#eproductName").show();
			flag = false;
		} else {
			$("#eproductName").hide();
		}
		if ("" == $("#productPrice").val()) {
			$("#eproductPrice").show();
			flag = false;
		} else {
			$("#eproductPrice").hide();
		}
		if ("" == $("#stock").val()) {
			$("#estock").show();
			flag = false;
		} else {
			$("#estock").hide();
		}
		if ("" == $("#effectiveDate").val()) {
			$("#eeffectiveDate").show();
			flag = false;
		} else {
			$("#eeffectiveDate").hide();
		}
		if ("" == $("#expiryDate").val()) {
			$("#eexpiryDate").show();
			flag = false;
		} else {
			$("#eexpiryDate").hide();
		}
		if ("" == $("#descrpiton").val()) {
			$("#edescrpiton").show();
			flag = false;
		} else {
			$("#edescrpiton").hide();
		}
		if ("" == $("#imageFile").val()) {
			$("#eimageFile").show();
			flag = false;
		} else {
			$("#eimageFile").hide();
		}
		if ("" == $("#listSubcategory").val()
				|| $("#listSubcategory").val() == null) {
			$("#elistSubcategory").show();
			flag = false;
		} else {
			$("#elistSubcategory").hide();
		}

		return flag;
	}
</script>
</head>
<body>
	<form:form id="productform" method="post" modelAttribute="product"
		enctype="multipart/form-data">
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
															<td><form:select path="mallId" items="${malls}"
																	itemValue="id" itemLabel="mallName"
																	style="width: 300px;" /><span id="emallId"
																style="display: none" class="error">请商品商城!</span></td>
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
															<td width="20%" height="40" class="biao">商品有效期&nbsp;<font
																color="red">*</font></td>
															<td><form:input path="effectiveDate"
																	style="width: 135px;" class="required" />&nbsp;到&nbsp;<form:input
																	path="expiryDate" style="width: 140px;"
																	class="required" /> <span id="eeffectiveDate"
																style="display: none" class="error">请输入有效期!</span> <span
																id="eexpiryDate" style="display: none" class="error">请输入有效期!</span></td>
														</tr>
														<tr>
															<td width="20%" height="40" class="biao">商品图片&nbsp;<font
																color="red">*</font></td>
															<td><input name="imageFile" id="imageFile"
																type="file" size="1" accept="image/*" class="required" /><span
																id="eimageFile" class="error" style="display: none;">商品图片为空!</span></td>
														</tr>
														<tr>
															<td width="20%" height="40" class="biao">商品描述&nbsp;<font
																color="red">*</font></td>
															<td><form:textarea path="descrpiton" cols="65"
																	rows="10" /> &nbsp;<span id="edescrpiton"
																class="error" style="display: none;">简短描述不能为空!</span></td>
														</tr>
														<tr>
															<td width="20%" height="40" class="biao">选择货架<font
																color="red">*</font></td>
															<td style="padding-top: 10px;"><select
																name="listSubcategory" id='listSubcategory'
																multiple='multiple' style="width: 200px; height: 200px;">
																	<c:forEach items="${subcategorys}" var="subcategory">
																		<option value="${subcategory.id }">${subcategory.subCategoryName}</option>
																	</c:forEach>
															</select> &nbsp;<span id="elistSubcategory" class="error"
																style="display: none;">请选择货架，至少得选择一个!</span></td>
														</tr>
														<tr>
															<td height="50"><form:button id="submitbtn"
																	class="btn-primary">设置额外价格</form:button></td>
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
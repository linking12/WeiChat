<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
							$('#priceForm').attr('action',
									'${ctx}/mallset/submitproductExtention');
							$('#priceForm').submit();
						});
			});
</script>
</head>
<body>
	<form:form id="priceForm" method="post" modelAttribute="price"
		enctype="multipart/form-data">
		<form:hidden path="productId" />
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
															<td width="20%" height="40" class="biao">商品价格&nbsp;</td>
															<td><form:input path="salePrice"
																	style="width: 300px;" class="required" /></td>
														</tr>
														<tr>
															<td width="20%" height="40" class="biao">价格有效期&nbsp;</td>
															<td><form:input path="effectiveDate"
																	style="width: 135px;" class="required" />&nbsp;到&nbsp;<form:input
																	path="expiryDate" style="width: 140px;"
																	class="required" /></td>
														</tr>
														<c:if test="${fn:length(pics) gt 0 }">
															<tr>
																<td colspan="2">
																	<table>
																		<tr>
																			<c:forEach items="${pics}" var="pic">
																				<td>
																					<div class="thumb">
																						<img src="${mimg }${pic.picUrl}" height="64"
																							width="64">
																					</div>
																				</td>
																			</c:forEach>
																		</tr>
																		<tr>
																			<c:forEach items="${pics}" var="pic">
																				<td align="center"><input type="radio"
																					name="defaultPic" value="${pic.id}" /></td>
																			</c:forEach>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:if>
														<tr>
															<td width="20%" height="40" class="biao">商品图片(zip文件)&nbsp;<font
																color="red">*</font></td>
															<td><input name="imageFile" id="imageFile"
																type="file" size="1" accept="zip/*" class="required" /></td>
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
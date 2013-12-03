<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="${css }/jquery-ui-1.10.3.custom.min.css" rel="stylesheet"
	type="text/css" />

<c:set var="mimg" value="${ctx}/mimg" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>微信公共平台</title>

<script type="text/javascript">
    $(document).ready(function() {
	    $('div.thumb img').hoverpulse().each(function() {
	    	var $img = $(this);
	    	var link = $img.attr('data-link');
	    	$img.attr('title','Goto: ' + link);
	    	$img.click(function() {
	    		window.open(link);
	    		return false;
	    	});
	    });
	    $("#mallId").change(function(){
	    	$("#form1").attr("action","${ctx}/mallset/product?mallId="+$("#mallId").val());
			$("#form1").submit();
	    });
	    $("#categoryId").change(function(){
	    	$("#form1").attr("action","${ctx}/mallset/product?categoryId="+$("#categoryId").val()+"&mallId="+$("#mallId").val());
			$("#form1").submit();
	    });
	    $("#subcategoryId").change(function(){
	    	$("#form1").attr("action","${ctx}/mallset/product?subcategoryId="+$("#subcategoryId").val()+"&categoryId="+$("#categoryId").val()+"&mallId="+$("#mallId").val());
			$("#form1").submit();
	    });
	    $("#mallId").val(${mallId});
	    $("#categoryId").val(${categoryId});
	    $("#subcategoryId").val(${subcategoryId});
    });

	function doAdd(){
		$("#form1").attr("action",'${ctx}/mallset/productdetail?mallId='+$("#mallId").val());
		$("#form1").submit();	
	}
	function doedit(id){
		$("#form1").attr("action",'${ctx}/mallset/productdetail?mallId='+$("#mallId").val()+'&productId='+id);
		$("#form1").submit();	
	}
	function doDelete(id){
		$("#form1").attr("action",'${ctx}/mallset/deletesubcategory/'+id);
		$("#form1").submit();	
		
	}

	</script>
</head>
<body>
	<form:form id="form1" method="post">
		<div class="b_con">
			<div class="by_box">
				<%@include file="../menu.jsp"%>
				<table width="967" border="0" align="center" cellpadding="0"
					cellspacing="0">

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
												<td width="94%" class="biao">门店设置</td>
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
															<td height="30">
																<table width="98%" border="0" align="center"
																	cellpadding="0" cellspacing="0">
																	<tr height="40">
																		<td colspan="2">
																			<table width="98%" border="0" align="center"
																				cellpadding="0" cellspacing="0">
																				<tr>
																					<td width="30%" class="biao"
																						style="padding-left: 10px">商城名称&nbsp;<font
																						color="red">*</font> <select style="width: 100px;"
																						id="mallId">
																							<c:forEach items="${ malls}" var="m">
																								<option value="${m.id }">${m.mallName }</option>
																							</c:forEach>
																					</select></td>
																					<td width="30%" class="biao"
																						style="padding-left: 10px">商品类别&nbsp;<font
																						color="red">*</font> <select style="width: 100px;"
																						id="categoryId">
																							<c:forEach items="${categorys}" var="category">
																								<option value="${category.id }">${category.categoryName}</option>
																							</c:forEach>
																					</select></td>
																					<td width="30%" class="biao"
																						style="padding-left: 10px">商品货架&nbsp;<font
																						color="red">*</font> <select style="width: 100px;"
																						id="subcategoryId">
																							<c:forEach items="${subcategorys}"
																								var="subcategory">
																								<option value="${subcategory.id }">${subcategory.subCategoryName}</option>
																							</c:forEach>
																					</select></td>
																					<td width="25%" class="biao" align="left"><input
																						type="button" value="新增商品" class="btn-primary"
																						onclick="doAdd()"></td>
																				</tr>
																			</table>
																		</td>

																	</tr>
																	<tr>
																		<td colspan="2"><div align="center">
																				<img src="${images}/xian.jpg" width="800" height="1" />
																			</div></td>
																	</tr>

																	<tr>
																		<td height="30" colspan="2">
																			<table width="98%" border="0" align="center"
																				cellpadding="0" cellspacing="0">
																				<tr height="30" bgcolor="#d3d3d3">
																					<td width="22%" class="biao">产品名称</td>
																					<td width="10%" class="biao">所属货架</td>
																					<td width="15%" class="biao">默认产品图片</td>
																					<td width="25%" class="biao">产品有效期</td>
																					<td width="10%" class="biao">价格</td>
																					<td width="5%" class="biao">数量</td>
																					<td width="40%" class="biao"></td>
																				</tr>
																				<c:forEach items="${productForms.content}"
																					var="productForm">
																					<tr height="70">
																						<td><a
																							title="${productForm.mallProduct.productName}"
																							style="text-decoration: none; color: blue"> <c:choose>
																									<c:when
																										test="${fn:length(productForm.mallProduct.productName) gt 10}">
																					                     ${fn:substring(productForm.mallProduct.productName, 0, 10)}...
																					                </c:when>
																									<c:otherwise>${productForm.mallProduct.productName}</c:otherwise>
																								</c:choose>
																						</a></td>
																						<td><select disabled>
																								<c:forEach items="${subcategorys}"
																									var="subcategory">
																									<c:if
																										test="${subcategory.id==productForm.mallProductCategory.subCategoryId}">
																										<option
																											value="${productForm.mallProductCategory.subCategoryId}">${subcategory.subCategoryName}</option>
																									</c:if>
																								</c:forEach>
																						</select></td>
																						<td>
																							<div class="thumb">
																								<img
																									src="${mimg }${productForm.defaultMallProductPic.picUrl}"
																									height="64" width="64">
																							</div>
																						</td>
																						<td>
																							${fn:substring(productForm.mallProduct.effectiveDate,
																							0, 10)}~
																							${fn:substring(productForm.mallProduct.expiryDate,
																							0, 10)}</td>
																						<td><c:if
																								test="${productForm.mallProductPrice!=null}">
																								${productForm.mallProductPrice.salePrice}																							
																						  </c:if> <c:if
																								test="${productForm.mallProductPrice==null}">
																								${productForm.mallProduct.productPrice}																							
																						  </c:if></td>
																						<td>${productForm.mallProduct.stock}</td>
																						<td><input type="button" value="修改"
																							class="btn-primary" onclick="doedit('${productForm.mallProduct.id}')">
																							&nbsp;<input type="button" value="删除"
																							class="btn-primary" onclick="doDelete('${productForm.mallProduct.id}')"></td>
																					</tr>

																				</c:forEach>
																				<tr>
																					<td align="center" colspan="5"><tags:pagination
																							page="${productForms}" paginationSize="5" /></td>
																				</tr>
																			</table>
																		</td>
																	</tr>

																</table>
															</td>
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

</html>

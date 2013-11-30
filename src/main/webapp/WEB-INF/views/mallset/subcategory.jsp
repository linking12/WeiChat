<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	    	$("#form1").attr("action","${ctx}/mallset/subcategory?mallId="+$("#mallId").val());
			$("#form1").submit();
	    });
	    $("#categoryId").change(function(){
	    	$("#form1").attr("action","${ctx}/mallset/subcategory?categoryId="+$("#categoryId").val()+"&mallId="+$("#mallId").val());
			$("#form1").submit();
	    });
	    $("#mallId").val(${mallId});
	    $("#categoryId").val(${categoryId});
    });

	function doAdd(){
			tanchuceng(600, 500, '类别详细','${ctx }/mallset/subcategorydetail/'+$("#categoryId").val());		
	}
	function doedit(id){
			tanchuceng(600, 500, '类别详细','${ctx }/mallset/subcategorydetail/'+$("#categoryId").val()+'?subcateId='+id);
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
															<td height="40" bgcolor="#e87352">
																<div align="center">
																	<a href="${ctx }/mallset/subcategory" class="biao1">商品子类别设置</a>
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
																						color="red">*</font> <select style="width: 150px;"
																						id="mallId">
																							<c:forEach items="${ malls}" var="m">
																								<option value="${m.id }">${m.mallName }</option>
																							</c:forEach>
																					</select></td>
																					<td width="40%" class="biao"
																						style="padding-left: 10px">父产品类别名称&nbsp;<font
																						color="red">*</font> <select style="width: 150px;"
																						id="categoryId">
																							<c:forEach items="${categorys}" var="category">
																								<option value="${category.id }">${category.categoryName}</option>
																							</c:forEach>
																					</select></td>
																					<td width="25%" class="biao" align="left"><input
																						type="button" value="新增商品子类别" class="btn-primary"
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
																					<td width="20%" class="biao">产品类别名称</td>
																					<td width="20%" class="biao">所属父类别</td>
																					<td width="30%" class="biao">类别图</td>
																					<td width="20%" class="biao">类别描述</td>
																					<td width="40%" class="biao"></td>
																				</tr>
																				<c:forEach items="${subcategorys.content}"
																					var="subcategory">
																					<tr height="70">
																						<td>${subcategory.subCategoryName}</td>
																						<td><select style="width: 100px" disabled>
																								<c:forEach items="${categorys}" var="category">
																									<c:if
																										test="${subcategory.categoryId== category.id}">
																										<option value="${subcategory.categoryId}">${category.categoryName}</option>
																									</c:if>
																								</c:forEach>
																						</select></td>
																						<td><div class="thumb">
																								<img src="${mimg }${subcategory.picUrl}"
																									height="64" width="64">
																							</div></td>
																						<td>${subcategory.description}</td>
																						<td><input type="button" value="修改"
																							class="btn-primary"
																							onclick="doedit(${category.id })"> &nbsp;<input
																							type="button" value="删除" class="btn-primary"
																							onclick="dodelete(${category.id })"></td>
																					</tr>

																				</c:forEach>
																				<tr>
																					<td align="center" colspan="5"><tags:pagination
																							page="${subcategorys}" paginationSize="5" /></td>
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

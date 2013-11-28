<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="${css }/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css" />

<c:set var="mimg" value="${ctx}/mimg"/>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>
	
	<script type="text/javascript">   
	$(function(){});

	function changeAccountId(){
		$("#form1").attr("action","${ctx}/mallset/mall?accountId="+$("#accountId").val());
		$("#form1").submit();
	}
	
	function save(){
		if( $("#form1").valid()){
			$("#form1").attr("action","${ctx}/mallset/savemall");
			$("#form1").submit();
		}
	}
	function doAdd(){
		if('${fn:length(categorys)}'<4)
			tanchuceng(600, 500, '类别详细','${ctx }/mallset/categorydetail/'+$("#mallId").val());
		else alert("最多增加4个类别！");
		
	}
	function doedit(id){
			tanchuceng(600, 500, '类别详细','${ctx }/mallset/categorydetail/'+$("#mallId").val()+'?cateId='+id);
	}

	</script>
</head>
<body>
	<form:form id="form1" method="post" >
	
		<div class="b_con">
	<div class="by_box">
		<%@include file="../menu.jsp"%>
		<table width="967" border="0" align="center" cellpadding="0" cellspacing="0">
			
			<tr>
				<td>
					<table width="942" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="53" background="${images}/shan.png">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2%">&nbsp;</td>
										<td width="4%"><img src="${images}/oic_3.png" width="30"height="21" /></td>
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
											<table width="100%" border="0" cellspacing="0" cellpadding="0">												
												<tr>
													<td height="40" bgcolor="#e87352">
														<div align="center"><a href="${ctx }/mallset/mall" class="biao1">门店设置</a></div>														
													</td>
												</tr>
												<tr>
													<td height="40">
														<div align="center" >
															<a href="javascript:doadd()" class="biao1">商品类别设置</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="1" bgcolor="#999999"><img src="${images}/su.png" width="1" height="257" /></td>
										<td width="770" valign="top">
											<table width="96%" border="0" align="center" cellpadding="0"cellspacing="0">												
												<tr>
													<td height="30">
														<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
															<tr height="40">
																<td width="30%" class="biao">商城名称&nbsp;<font color="red">*</font></td>
																<td>
																<select style="width: 150px;" id="mallId">
																<c:forEach items="${ malls}" var="m">
																 <option value="${m.id }">${m.mallName }</option>
																</c:forEach>											
																</select>																	
																	
																<input
																			type="button" value="新增" class="btn-primary"
																			onclick="doAdd()">
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															
														<tr>
														<td height="30" colspan="2">
															<table width="98%" border="0" align="center"
																cellpadding="0" cellspacing="0">
																<tr height="30" bgcolor="#d3d3d3">
																	<td width="25%" class="biao">类别名称</td>
																	<td width="30%" class="biao">样式</td>
																	<td width="25%" class="biao">描述</td>
																	<td width="20%" class="biao">操作</td>
																</tr>
																<c:forEach items="${categorys}" var="category">
																	<tr height="30">
																		<td>${category.categoryName }</td>
																		<td>
																			<c:forEach items="${styles}" var="s">
																				<c:if test="${s.key==category.style }">${s.value }</c:if>
																			</c:forEach></td>
																		<td><a title="${category.description }"
																			style="text-decoration: none; color: blue"> <c:choose>
																					<c:when test="${fn:length(category.description) gt 20}">
																					  ${fn:substring(category.description, 0, 20)}...
																					</c:when>
																					<c:otherwise>${category.description}</c:otherwise>
																				</c:choose>
																		</a></td>
																		<td><input
																			type="button" value="修改" class="btn-primary"
																			onclick="doedit(${category.id })"> &nbsp;<input
																			type="button" value="删除" class="btn-primary"
																			onclick="dodelete(${category.id })">
																	</tr>

																</c:forEach>
																
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
							<td><img src="${images}/xia_zu.png" width="942" height="14" /></td>
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

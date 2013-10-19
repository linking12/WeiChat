<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="../common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>微信公共平台</title>
<script type="text/javascript">
		function doedit(id){
			$('#form1').attr('action', '${ctx }/account/edit/'+id);
			$('#form1').submit();
		}
		function dodelete(id){
			$('#form1').attr('action', '${ctx }/account/delete/'+id);
			$('#form1').submit();
		}
		function doconfig(id){
			$('#form1').attr('action', '${ctx }/replymsg/init?accountId='+id);
			$('#form1').submit();
		}
		</script>
</head>
<body>
	<form id="form1"></form>
	<div class="b_con">
		<div class="by_box">
			<%@include file="../menu.jsp"%>
			<table width="967" border="0" align="center" cellpadding="0"
				cellspacing="0">
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
											<td width="4%"><img src="${images}/oic_3.png" width="30"
												height="21" /></td>
											<td width="94%" class="biao">我的账号</td>
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
														<td height="40" bgcolor="#e87352"><div align="center"
																class="biao">我的公众账号</div></td>
													</tr>
													<tr>
														<td height="40">
															<div align="center">
																<a href="${ctx }/account/add" class="biao1">添加账号</a>
															</div>
														</td>
													</tr>
												</table>
											</td>
											<td width="1" bgcolor="#999999"><img
												src="${images}/su.png" width="1" height="257" /></td>
											<td width="770" valign="top">
												<table width="96%" border="0" align="center" cellpadding="0"
													cellspacing="0">
													<tr>
														<td height="10"></td>
													</tr>
													<tr>
														<td height="30">
															<table width="98%" border="0" align="center"
																cellpadding="0" cellspacing="0">
																<tr height="30" bgcolor="#d3d3d3">
																	<td width="25%" class="biao">公众帐号</td>
																	<td width="30%" class="biao">URL</td>
																	<td width="25%" class="biao">Token</td>
																	<td width="20%" class="biao">操作</td>
																</tr>
																<c:forEach items="${accounts.content}" var="account">
																	<tr height="30">
																		<td>${account.name }</td>
																		<td><a title="${account.url}"
																			style="text-decoration: none; color: blue"> <c:choose>
																					<c:when test="${fn:length(account.url) gt 20}">
																					  ${fn:substring(account.url, 0, 20)}...
																					</c:when>
																					<c:otherwise>${account.url}</c:otherwise>
																				</c:choose>
																		</a></td>
																		<td><a title="${account.seq }"
																			style="text-decoration: none; color: blue"> <c:choose>
																					<c:when test="${fn:length(account.seq) gt 20}">
																					  ${fn:substring(account.seq, 0, 20)}...
																					</c:when>
																					<c:otherwise>${account.seq}</c:otherwise>
																				</c:choose>
																		</a></td>
																		<td><input type="button" value="配置"
																			class="btn-primary"
																			onclick="doconfig(${account.id })"> &nbsp;<input
																			type="button" value="修改" class="btn-primary"
																			onclick="doedit(${account.id })"> &nbsp;<input
																			type="button" value="删除" class="btn-primary"
																			onclick="dodelete(${account.id })">
																	</tr>

																</c:forEach>
																<tr>
																	<td align="center" colspan="4"><tags:pagination
																			page="${accounts}" paginationSize="5" /></td>
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
			</table>
			<%@include file="../bottom.jsp"%>
		</div>
	</div>
</body>
</html>

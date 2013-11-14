<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>微信公共平台</title>
<script type="text/javascript">
		function doadd(){
			$('#form1').attr('action', '${ctx }/custommenu/add/'+$("#accountId").val());
			$('#form1').submit();
		}
		
		function changeAccount(){
			$('#form1').attr('action', '${ctx }/custommenu/init?accountId='+$("#accountId").val());
			$('#form1').submit();
		}
		
		function dodelete(id){
			$('#form1').attr('action', '${ctx}/custommenu/delete/'+id);
			$('#form1').submit();
		}
		
		function doedit(id){
			$('#form1').attr('action', '${ctx }/custommenu/edit/'+id);
			$('#form1').submit();
		}
		
		$(function(){
			$("#accountId").val("${accountId}");
		})
	</script>
</head>
<body>
	<form id="form1" method="POST">
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
												<td width="94%" class="biao">自定义菜单</td>
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
															<td height="40" bgcolor="#e87352">
																<div align="center" class="biao">菜单列表</div>
															</td>
														</tr>
														<tr>
															<td height="40">
																<div align="center">
																	<a href="javascript:doadd()" class="biao1">添加菜单</a>
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
															<td height="40">
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td width="20%" height="40" class="biao">公众帐号名</td>
																		<td><select id="accountId" style="width: 150px;"
																			onchange="changeAccount()" onchange="changeAccount()">
																				<c:forEach items="${accounts}" var="account">
																					<option value="${account.id }">${account.name}</option>
																				</c:forEach>
																		</select></td>
																	</tr>

																</table>
															</td>
														</tr>
														<tr>
															<td height="30" bgcolor="#d3d3d3">
																<table width="98%" border="0" align="center"
																	cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="20%" class="biao">菜单名称</td>
																		<td width="20%" class="biao">父菜单</td>
																		<td width="20%" class="biao">事件类型</td>
																		<td width="20%" class="biao">事件描述</td>
																		<td width="20%" class="biao">操作</td>
																	</tr>
																</table>
															</td>
														</tr>
														<c:forEach items="${menus}" var="menu" varStatus="status">
															<tr>
																<td height="30">
																	<table width="98%" border="0" align="center"
																		cellpadding="0" cellspacing="0">
																		<tr>
																			<td width="20%" class="biao">${menu.name }</td>
																			<td width="20%" class="biao"><select disabled
																				style="width: 100px">
																					<option>请选择</option>
																					<c:forEach items="${parentMenus}" var="p">
																						<c:if test="${menu.parentId==p.id }">
																							<option selected>${p.name }</option>
																						</c:if>
																					</c:forEach>
																			</select></td>
																			<td width="20%" class="biao"><select disabled
																				style="width: 100px">
																					<c:forEach items="${eventTypes}" var="e">
																						<c:if test="${menu.eventType==e.key }">
																							<option selected>${e.value }</option>
																						</c:if>
																					</c:forEach>
																			</select></td>
																			<td width="20%" class="biao"><c:choose>
																					<c:when test="${menu.eventType=='url' }">
																						<c:if
																							test="${menu.eventDesc!=''&& menu.eventDesc!= null }">
																							<a title="${menu.eventDesc }"
																								style="text-decoration: none; color: blue">查看</a>
																						</c:if>
																					</c:when>
																					<c:otherwise>
																						<select disabled style="width: 100px">
																							<c:forEach items="${msgs}" var="m">
																								<c:if test="${menu.eventDesc==m.id }">
																									<option selected>${m.msgName}</option>
																								</c:if>
																							</c:forEach>
																						</select>
																					</c:otherwise>
																				</c:choose></td>
																			<td width="20%" class="biao"><input
																				type="button" value="修改"
																				onclick="doedit(${menu.id })" class="btn-primary" />&nbsp;
																				<input type="button" value="删除" class="btn-primary"
																				onclick="dodelete('${menu.id }')" /></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:forEach>
														<tr height="30"></tr>
														<tr>
															<td height="30">
																<table width="98%" border="0" align="center"
																	cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="20%" class="biao">菜单预览:</td>
																	</tr>
																	<tr>
																		<td></td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td height="50" align="center"><input type="button" id="submitbtn"
																class="btn-primary" value="生成微信菜单" onclick="dosubmit()" /></td>
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
	</form>
</body>
</html>
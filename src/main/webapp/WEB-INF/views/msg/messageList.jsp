<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>

	<script type="text/javascript">
		$(function(){
			$("#accountId").val('${accountId}');
		});
	
		function changeAccountId() {
			var accountid = $("#accountId").val();
			$('#form1').attr('action', '${ctx}/message/init?accountId=' + accountid);
			$('#form1').submit();
		}
		
		function addMessage() {
			var accountid = $("#accountId").val();
			$('#form1').attr('action', '${ctx }/message/addtext/' + accountid);
			$('#form1').submit();
		}
	</script>
</head>
<body>
	<form id="form1" method="post">
		<table width="967" border="0" align="center" cellpadding="0" cellspacing="0">
		<%@include file="../menu.jsp"%>
		<!-- 正文开始 -->
		<tr>
			<td>
				<table width="942" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td height="53" background="${images}/shan.png">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="2%">&nbsp;</td>
									<td width="4%"><img src="${images}/oic_3.png" width="30" height="21" /></td>
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
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="40" bgcolor="#e87352"><div align="center" class="biao1">我的信息</div></td>
											</tr>
											<tr>
												<td height="40"><div align="center" class="c"><a class="d" href="javascript:addMessage()">添加信息</a></div></td>
											</tr>
										</table>
									</td>
									<td width="1" bgcolor="#999999"><img src="${images}/su.png" width="1" height="257" /></td>
									<td width="770" valign="top">
										<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
											<tr>
												<td height="40">
													<table width="100%" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td>
																<select name="accountId" id="accountId" onchange="changeAccountId()">
																	<c:forEach items="${accounts}" var="account">
																		<option value="${account.id }">${account.name}</option>
																	</c:forEach>
																</select>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td height="30" bgcolor="#d3d3d3">
													<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
														<tr>
															<td width="18%" class="biao">标题</td>
															<td width="36%" class="biao">类型</td>
															<td width="35%" class="biao">创建时间</td>
															<td width="11%" class="biao">操作</td>
														</tr>
													</table>
												</td>
											</tr>
											<c:forEach items="${messages.content}" var="message" varStatus="status">
												<tr>
													<td height="58">
														<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
															<tr>
																<td width="18%">${message.msgName }</td>
																<td width="36%">
																	<c:if test="${message.msgType == 'text'}">文本 </c:if>
																	<c:if test="${message.msgType == 'image'}">图文</c:if>
																	<c:if test="${message.msgType == 'voice'}">音乐</c:if>
																	<c:if test="${message.msgType == 'video' }">视频</c:if>
																</td>
																<td width="31%" class="zeng">${message.createTime }</td>
																<td width="9%" align="center" class="zeng">
																	<c:choose>
																		<c:when test="${message.msgType == 'text'}">
																			<a href="${ctx}/message/edittext/${message.id}">修改</a>
																		</c:when>
																		<c:otherwise>
																			<a href="${ctx}/message/editMultimedia/${message.id}">修改</a>
																		</c:otherwise>
																	</c:choose>
																</td>
																<td width="6%" align="center" class="zeng">
																	<a href="${ctx}/message/delete/${message.id}">删除</a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td><div align="center">
															<img src="${images}/xian.jpg" width="800" height="1" />
														</div></td>
												</tr>
											</c:forEach>
											<tr>
												<td align="center"><tags:pagination page="${messages}" paginationSize="5" /></td>
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
					<%@include file="../bottom.jsp"%>
				</table>
			</td>
		</tr>		
	</table>
	</form>
</body>
</html>
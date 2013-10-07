<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<c:set var="images" value="${ctx}/index/images" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>微信公共平台</title>
<style type="text/css">
body {
	background-image: url("${images}/bei.jpg");
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submitbtn").click(function() {
			$('#messageForm').attr('action', '${ctx}/message/submitMultimedia');
			$('#messageForm').submit();
		});
	});
	function changeMessageType() {
		var msgType = $("#msgType").val();
		if (msgType != '' && msgType != 'text')
			window.location.href = "${ctx}/message/addMultimedia/" + msgType;
		else
			window.location.href = "${ctx}/message/addtext";
	}
</script>
</head>
<body>
	<table width="967" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td><img src="${images}/lo.png" width="955" height="208" /></td>
		</tr>
		<tr>
			<td><%@include file="../menu.jsp"%></td>
		</tr>
		<tr>
			<td height="10">&nbsp;</td>
		</tr>
		<!-- 正文开始 -->
		<tr>
			<td><table width="942" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="53" background="${images}/shan.png"><table
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="2%">&nbsp;</td>
									<td width="4%"><img src="${images}/oic_3.png" width="30"
										height="21" /></td>
									<td width="94%" class="biao">我的信息</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td height="350" valign="top" background="${images}/he.png"><table
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="120" valign="top"><table width="100%"
											border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="40" bgcolor="#e87352">
													<div align="center" class="biao1">
														<a href="${ctx }/message/init" class="d">我的公众信息</a>
													</div>
												</td>
											</tr>
											<tr>
												<td height="40"><div align="center" class="c">
														<a href="${ctx }/message/addtext" class="d">首次关注</a>
													</div></td>
											</tr>
										</table></td>
									<td width="1" bgcolor="#999999"><img
										src="${images}/su.png" width="1" height="257" /></td>
									<td width="770" valign="top">
										<table width="96%" border="0" align="center" cellpadding="0"
											cellspacing="0">
											<form:form id="messageForm" method="post"
												modelAttribute="messageForm">
												<tr>
													<td height="40"><table width="100%" border="0"
															cellspacing="0" cellpadding="0">
															<tr>
																<td width="20%" height="40" class="biao">公众帐号名</td>
																<td><form:select path="message.accountId"
																		id="accountId" style="width: 150px;">
																		<c:forEach items="${accounts}" var="account">
																			<form:option value="${account.id }">${account.name}</form:option>
																		</c:forEach>
																	</form:select></td>
															</tr>
															<tr>
																<td width="20%" height="40" class="biao">自动回复信息类型</td>
																<td><form:select path="message.msgType" onchange="javascript:changeMessageType()"
																		id="msgType" style="width: 150px;">
																		<c:forEach items="${messageTypes}" var="messageType">
																			<form:option value="${messageType.msgType}">${messageType.name}</form:option>
																		</c:forEach>
																	</form:select></td>
															</tr>
															<tr>
																<td width="20%" height="40" class="biao">标题</td>
																<td><form:input path="message.msgName"
																		style="width: 300px;" /> 必须
																	<form:hidden path="message.id"/> </td>
															</tr>
															<tr>
																<td width="20%" height="40" class="biao" valign="middle">自动回复多媒体</td>
																<td>
																	<table width="100%" border="0" align="center"
																		cellpadding="0" cellspacing="0"
																		style="margin-top: 20px">
																		<tr>
																			<td height="30" bgcolor="#d3d3d3">
																				<table width="98%" border="0" align="center"
																					cellpadding="0" cellspacing="0">
																					<tr>
																						<td width="5%" class="biao">选择</td>
																						<td width="10%" class="biao">类型</td>
																						<td width="10%" class="biao">标题</td>
																						<td width="10%" class="biao">描述</td>
																						<td width="20%" class="biao">图片URL</td>
																						<td width="20%" class="biao">引用URL</td>
																						<td width="20%" class="biao">多媒体URL</td>

																					</tr>
																				</table>
																			</td>
																		</tr>
																		<c:forEach items="${wxContents}" var="content"
																			varStatus="status">
																			<tr>
																				<td height="30">
																					<table width="98%" border="0" align="center"
																						cellpadding="0" cellspacing="0">
																						<tr>
																							<td width="5%"><form:checkbox
																									path="selectContents" value="${content.id}" /></td>
																							<td width="10%">多媒体</td>
																							<td width="10%">${content.title}</td>
																							<td width="10%">${content.description}</td>
																							<td width="20%">${content.picUrl}</td>
																							<td width="20%">${content.url}</td>
																							<td width="20%">${content.musicUrl}</td>

																						</tr>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td><div align="center">
																						<img src="${images}/xian.jpg" width="650"
																							height="1" />
																					</div></td>
																			</tr>
																		</c:forEach>
																		<tr>
																			<td height="50" align="right"><input
																				type="button" value="新增" class="btn-primary"
																				onclick="tanchuceng(900, 600, '上传多媒体','${ctx }/content/addContent?isIndex=y&fileName=coverFile');" /></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table></td>
												</tr>
												<tr>
													<td>
														<table width="729" border="0" cellpadding="0"
															cellspacing="0" background="${images}/dd.png">
															<tr>
																<td height="320" valign="top"><table width="100%"
																		border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="40"><table width="100%" border="0"
																					cellspacing="0" cellpadding="0">
																					<tr>
																						<td width="3%">&nbsp;</td>
																						<td width="97%" class="biao">备注</td>
																					</tr>
																				</table></td>
																		</tr>
																		<tr>
																			<td height="260"><table width="90%" border="0"
																					align="center" cellpadding="0" cellspacing="0"
																					bordercolor="#999999" bgcolor="#FFFFFF">
																					<tr>
																						<td height="200" valign="top"><form:textarea
																								path="message.content" cols="90" rows="14" /></td>
																					</tr>
																				</table></td>
																		</tr>
																	</table></td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="50"><input type="image" name="submit"
														id="submitbtn" src="${images }/tijiao.png" width="76"
														height="40" /></td>
													<td height="50"><form:errors path="message.msgType"
															cssClass="error" /> <form:errors path="message.msgName"
															cssClass="error" /> <form:errors path="message.accountId"
															cssClass="error" /> <form:errors path="message.content"
															cssClass="error" /></td>
												</tr>
											</form:form>
										</table>
									</td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td><img src="${images}/xia_zu.png" width="942" height="14" /></td>
					</tr>
				</table></td>
		</tr>
		<!-- 正文结束 -->
		<tr>
			<td><table width="90%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td height="30"><p class="biao1">在线客服</p></td>
					</tr>
					<tr>
						<td><table width="90%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td width="2%"><img src="${images}/qq.png" width="15"
										height="16" /></td>
									<td width="6%" class="biao1">天乐</td>
									<td width="26%" class="biao3">1234677</td>
									<td><img src="${images}/qq.png" width="15" height="16" /></td>
									<td class="biao1">天乐</td>
									<td class="biao3">1234677</td>
									<td><img src="${images}/qq.png" width="15" height="16" /></td>
									<td class="biao1">天乐</td>
									<td class="biao3">1234677</td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="50">&nbsp;</td>
		</tr>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>自动回复配置</title>
<style type="text/css">
.c div {
	color: #ff9001;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {

	});

	function ajaxMsgType() {
		var accountid = $("#accountid").val();
		var str = "";
		if ("" != accountid) {
			$.post("${ctx}/replymsg/msgtype", {
				"accountid" : accountid

			}, function(lst) {
				$(lst).each(function(i, data) {
					str += "<tr><td>" + data.name + "</td>";
					str += "<td></td>";
					str += "<td></td></tr>";

				});
				$("#table1").html(str);
			});

		}

	}
</script>
</head>
<body>
	<form:form id="form1" method="post" modelAttribute="replyMsgForm">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="57" background="${images}/tiao.jpg"><table
						width="960" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td><img src="${images}/logo.png" width="125" height="32" /></td>

							<td>&nbsp;</td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="37" background="${images}/tiao_1.jpg"><%@include
						file="../menu.jsp"%></td>
			</tr>
			<tr>
				<td><table width="960" border="0" align="center"
						cellpadding="0" cellspacing="0">
						<tr>
							<td height="20">&nbsp;</td>
						</tr>

						<tr>
							<td height="555" valign="top">
								<table width="942" border="0" align="center" cellpadding="0"
									cellspacing="0">

									<tr>
										<td height="53" background="${images}/shan.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="2%">&nbsp;</td>

													<td width="4%"><img src="${images}/ic_3.png"
														width="30" height="21" /></td>
													<td width="94%" class="biao">自动回复配置</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td height="350" valign="top" background="${images}/he.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="162" valign="top">
														<table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td height="40" bgcolor="#ff9001">
																	<div align="center" class="biao1"></div>
																</td>
															</tr>
														</table>
													</td>
													<td width="1" bgcolor="#999999"><img
														src="${images}/su.png" width="1" height="257" /></td>
													<td width="770" valign="top"><table width="96%"
															border="0" align="center" cellpadding="0" cellspacing="0">

															<tr>
																<td height="40"><table width="100%" border="0"
																		cellspacing="0" cellpadding="0">
																		<tr>
																			<td><form:select path="accountId"
																					items="${accounts}" itemValue="id" itemLabel="name"></form:select></td>
																		</tr>
																	</table></td>
															</tr>



															<tr>
																<td height="30" bgcolor="#d3d3d3"><table
																		width="98%" border="0" align="center" cellpadding="0"
																		cellspacing="0">
																		<tr>
																			<td width="18%" class="biao">用户发送类型</td>
																			<td width="43%" class="biao">自动回复类型</td>
																			<td width="28%" class="biao">信息内容或处理程序</td>
																		</tr>
																	</table></td>
															</tr>

															<c:forEach items="${msgTypes }" var="msgType"
																varStatus="status">
																<tr>
																	<td height="57"><table width="98%" border="0"
																			align="center" cellpadding="0" cellspacing="0">
																			<tr>
																			<tr>
																				<td width="18%">${msgType.name }</td>
																				<td width="43%"><form:select
																						path="msgTypes[${status.index}].action">
																						<form:option value="program">直接回复</form:option>
																						<form:option value="direct">自定义处理</form:option>
																					</form:select></td>
																				<td width="28%"><c:if
																						test="${msgType.action=='program' }">
																						<form:select
																							path="msgTypes[${status.index}].sourceId"
																							items="${games}" itemValue="id" itemLabel="name"></form:select>
																					</c:if> <c:if test="${msgType.action=='direct' }">
																						<form:select
																							path="msgTypes[${status.index}].sourceId"
																							items="${messages}" itemValue="id"
																							itemLabel="msgName"></form:select>
																					</c:if></td>
																			</tr>
																		</table></td>
																</tr>
																<tr>
																	<td><div align="center">
																			<img src="${images}/xian.jpg" width="727" height="1" />
																		</div></td>
																</tr>
															</c:forEach>
															<tr>
																<td><div align="center">
																		<img src="${images}/xian.jpg" width="727" height="1" />
																	</div></td>
															</tr>

															<tr>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td><div align="center">
																<form:button name="savebtn" id="savebtn">修改配置</form:button>
																	
																	</div></td>
															</tr>
															<tr>
																<td>&nbsp;</td>
															</tr>
														</table></td>
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
					</table></td>
			</tr>
		</table>
	</form:form>
</body>
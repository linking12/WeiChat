<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>信息内容管理</title>
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
			<td><table width="960" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="20">&nbsp;</td>
					</tr>

					<tr>
						<td height="555" valign="top">
							<table width="942" border="0" align="center" cellpadding="0"
								cellspacing="0">

								<tr>
									<td height="53" background="${images}/shan.png">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="2%">&nbsp;</td>

												<td width="4%"><img src="${images}/ic_3.png" width="30"
													height="21" /></td>
												<td width="94%" class="biao">信息内容管理</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td height="350" valign="top" background="${images}/he.png">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="162" valign="top"><table width="100%"
														border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td height="40">
																<div align="center" class="biao1">
																	<a href="${ctx }/message/init">全部信息</a>
																</div>
															</td>
														</tr>
														<tr>
															<td height="40" ><div
																	align="center" class="biao1">
																	<a href="${ctx }/message/addtext">添加文本</a>
																</div></td>
														</tr>
														<tr>
															<td height="40">
																<div align="center" class="biao1">
																	<a href="${ctx }/message/addimg">添加图文</a>
																</div>
															</td>
														</tr>
														<tr>
															<td height="40" bgcolor="#ff9001"><div align="center" class="c">
																	<p>
																		<a href="${ctx }/message/addmusic">添加音乐</a>
																	</p>
																</div></td>
														</tr>
													</table></td>
												<td width="1" bgcolor="#999999"><img
													src="${images}/su.png" width="1" height="257" /></td>
												<td width="770" valign="top"><table width="96%"
														border="0" align="center" cellpadding="0" cellspacing="0">

														<tr>
															<td height="40"><table width="100%" border="0"
																	cellspacing="0" cellpadding="0">
																	<tr>
																		<td><select name="accountid" id="accountid">
																				<c:forEach items="${accounts}" var="account">
																					<option value="${account.id }">${account.name }</option>
																				</c:forEach>
																		</select></td>
																	</tr><tr>
															<td height="40" class="biao">标题</td>
															<td><label for="textfield"></label> <input
																name="title" type="text" id="title" value="" /> 必须</td>
															<td class="biao">音乐地址（URL）</td>
															<td><input name="music1" type="text" id="music1"
																value="" size="40" /></td>
														</tr>
														<tr>
															<td height="40" class="biao">高清音乐地址（URL）</td>
															<td><input name="music2" type="text" id="music2"
																value="" size="45" /></td>
															<td class="biao">必须</td>
															<td>必须</td>
														</tr>
																</table></td>
														</tr>
														
<tr>
																<td><table width="729" border="0" cellpadding="0"
																		cellspacing="0" background="${images}/dd.png">
																		<tr>
																			<td height="320" valign="top"><table
																					width="100%" border="0" cellspacing="0"
																					cellpadding="0">
																					<tr>
																						<td height="40"><table width="100%"
																								border="0" cellspacing="0" cellpadding="0">
																								<tr>
																									<td width="3%">&nbsp;</td>
																									<td width="97%" class="biao">描述</td>
																								</tr>
																							</table></td>
																					</tr>
																					<tr>
																						<td height="260"><table width="90%"
																								border="0" align="center" cellpadding="0"
																								cellspacing="0" bordercolor="#999999"
																								bgcolor="#FFFFFF">
																								<tr>
																									<td height="200" valign="top"><textarea
																											name="description" cols="80" rows="14"></textarea>
																										必须</td>
																								</tr>
																							</table></td>
																					</tr>
																				</table></td>
																		</tr>
																	</table></td>
															</tr>
														<tr>
															<td height="50"><input type="image" name="submit"
																src="${images }/qunfa.png" width="76" height="40"
																id="tijiao" /></td>
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

</body>
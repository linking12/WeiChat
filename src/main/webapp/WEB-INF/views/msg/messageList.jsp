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
															<td height="40" bgcolor="#ff9001">
																<div align="center" class="biao1">
																	<a href="${ctx }/message/init">全部信息</a>
																</div>
															</td>
														</tr>
														<tr>
															<td height="40"><div align="center" class="biao1">
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
															<td height="40"><div align="center" class="c">
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
																	</tr>
																</table></td>
														</tr>
														<tr>
															<td height="20"><div align="right">
																	<a href="#">上一页</a> <a href="#">下一页</a>
																</div></td>
														</tr>


														<tr>
															<td height="30" bgcolor="#d3d3d3">
																<table width="98%" border="0" align="center"
																	cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="18%" class="biao">标题</td>
																		<td width="36%" class="biao">类型</td>
																		<td width="35%" class="biao">创建时间</td>
																		<td width="11%" class="biao">操作</td>
																	</tr>
																</table>
															</td>
														</tr>

														<c:forEach items="${messages }" var="message"
															varStatus="status">
															<tr>
																<td height="58">
																	<table width="98%" border="0" align="center"
																		cellpadding="0" cellspacing="0">
																		<tr>
																			<td width="18%">${message.msgName }</td>
																			<td width="36%">${message.msgType }</td>
																			<td width="31%" class="zeng">${message.createTime }</td>
																			<td width="9%" align="center" class="zeng"><a
																				href="#">删除</a></td>
																			<td width="6%" align="center" class="zeng"><a
																				href="#">修改</a></td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td><div align="center">
																		<img src="images/xian.jpg" width="727" height="1" />
																	</div></td>
															</tr>
														</c:forEach>
														<tr>
															<td>&nbsp;</td>
														</tr>
														<tr>
															<td align="right"><a href="#">上一页</a> <a href="#">下一页</a></td>
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
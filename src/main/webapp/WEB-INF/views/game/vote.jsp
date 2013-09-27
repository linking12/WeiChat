<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>应用管理-投票</title>
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
												<td width="94%" class="biao">应用管理-投票</td>
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
															<td height="40" >
																<div align="center" class="biao1">
																	<a href="${ctx }/game/init">全部应用</a>
																</div>
															</td>
														</tr>
														<tr>
															<td height="40" bgcolor="#ff9001"><div align="center" class="biao1">
																	<a href="${ctx }/game/vote">投票</a>
																</div></td>
														</tr>
														<tr>
															<td height="40">
																<div align="center" class="biao1">
																	<a href="${ctx }/game/robot">聊天机器人</a>
																</div>
															</td>
														</tr>
														<tr>
															<td height="40"><div align="center" class="c">
																	<p>
																		<a href="${ctx }/game/lottery">刮刮乐</a>
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
															<td height="30" colspan="2" bgcolor="#d3d3d3"><table
																	width="98%" border="0" align="center" cellpadding="0"
																	cellspacing="0">
																	<tr>
																		<td width="25%" class="biao">投票名称</td>
																		<td width="29%" class="biao">参与用户数</td>
																		<td width="35%" class="biao">创建时间</td>
																		<td width="11%" class="biao">操作</td>
																	</tr>
																</table></td>
														</tr>
														<tr>
															<td height="58" colspan="2"><table width="98%"
																	border="0" align="center" cellpadding="0"
																	cellspacing="0">
																	<tr>
																		<td width="25%"><a href="autoreply.html">今日股市涨跌</a></td>
																		<td width="29%">3000</td>
																		<td width="31%" class="zeng">2013-05-08 10：09：08</td>
																		<td width="9%" align="center" class="zeng">启用</td>
																		<td width="6%" align="center" class="zeng"><a
																			href="account_add.html">修改</a></td>
																	</tr>
																</table></td>
														</tr>
														<tr>
															<td colspan="2"><div align="center">
																	<img src="images/xian.jpg" width="727" height="1" />
																</div></td>
														</tr>
														<tr>
															<td height="57" colspan="2"><table width="98%"
																	border="0" align="center" cellpadding="0"
																	cellspacing="0">
																	<tr>
																		<td width="25%"><a href="#">谁是最可爱的人</a></td>
																		<td width="30%">50000</td>
																		<td width="30%" class="zeng">?2013-05-08 10：09：08</td>
																		<td width="9%" align="center" class="zeng">停用</td>
																		<td width="6%" align="center" class="zeng"><a
																			href="account_add.html">修改</a></td>
																	</tr>
																</table></td>
														</tr>
														<tr>
															<td height="57" colspan="2"><table width="98%"
																	border="0" align="center" cellpadding="0"
																	cellspacing="0">
																	<tr>
																		<td width="25%"><a href="#">李天一是个流氓吗</a></td>
																		<td width="30%">325654</td>
																		<td width="30%" class="zeng">?2013-05-08 10：09：08</td>
																		<td width="9%" align="center" class="zeng">停用</td>
																		<td width="6%" align="center" class="zeng"><a
																			href="account_add.html">修改</a></td>
																	</tr>
																</table></td>
														</tr>
														<tr>
															<td colspan="2"><div align="center">
																	<img src="images/xian.jpg" width="727" height="1" />
																</div></td>
														</tr>
														<tr>
															<td colspan="2">&nbsp;</td>
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
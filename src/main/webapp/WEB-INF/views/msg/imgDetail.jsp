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
															<td height="40"><div
																	align="center" class="biao1">
																	<a href="${ctx }/message/addtext">添加文本</a>
																</div></td>
														</tr>
														<tr>
															<td height="40">
																<div align="center" class="biao1" bgcolor="#ff9001">
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
															<td width="9%" height="40" class="biao">标题</td>
															<td colspan="2"><label for="textfield5"></label> <input
																name="title" type="text" id="textfield5" value="" /> 必须</td>
															<td width="15%" class="biao">原文链接</td>
															<td width="45%"><input name="url" type="text"
																id="textfield6" value="" size="40" /> 必须</td>
														</tr>

														<tr>
															<td height="153" rowspan="2" valign="top" class="biao">图片</td>
															<td width="12%"><label for="fileField5"> <input
																	name="fileField3" type="file" id="fileupload_input"
																	size="1" />
															</label></td>
															<td width="19%">
																<p id="pp">必须</p>
															</td>
															<td rowspan="2" valign="top" class="biao">简短描述</td>
															<td rowspan="2" valign="top"><label
																for="description"></label> <textarea name="description"
																	id="description" cols="45" rows="10"></textarea> 必须</td>
														</tr>
														<tr>
															<td height="119" colspan="2" align="left" valign="middle"><img
																src="imgupload/" name="imgs" width="150" height="150"
																id="imgs" /></td>
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
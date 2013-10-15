<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>
	<script type="text/javascript">
	function doadd(){
		$('#form1').attr('action', '${ctx }/custommenu/init?accountId='+$("#accountId").val());
		$('#form1').submit();
	}
	</script>
</head>
<body >
	<form id="form1" method="POST">
		<table width="967" border="0" align="center" cellpadding="0" cellspacing="0">
			<%@include file="../menu.jsp"%>
			<tr>
				<td>
					<table width="942" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="53" background="${images}/shan.png">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2%">&nbsp;</td>
										<td width="4%"><img src="${images}/oic_3.png" width="30"height="21" /></td>
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
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td height="40" >
														<div align="center" class="c"><a href="javascript:goback()" class="d">自定义菜单</a></div>
													</td>
												</tr>
												<tr>
													<td height="40" bgcolor="#e87352">
														<div align="center" class="biao1">
															添加菜单
														</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="1" bgcolor="#999999"><img src="${images}/su.png" width="1" height="257" /></td>
										<td width="770" valign="top">
											<table width="96%" border="0" align="center" cellpadding="0"cellspacing="0">
												<tr>
													<td height="40">
														<table width="100%" border="0"cellspacing="0" cellpadding="0">
															<tr>
																<td width="10%" height="40" class="biao">公众帐号名<font color="red">*</font></td>
																<td align="left">
																	<select id="accountId" style="width: 150px;" onchange="changeAccount()" >
																		<c:forEach items="${accounts}" var="account">
																			<option value="${account.id }">${account.name}</option>
																		</c:forEach>
																	</select>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr>
																<td height="40" class="biao">菜单名<font color="red">*</font></td>
																<td>
																	<input type="text">
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													
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
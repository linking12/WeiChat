<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#submitbtn").click(function() {
				$('#accountForm').attr('action', '${ctx}/account/submit');
				$('#accountForm').submit();
			});
		});
	</script>
</head>
<body>
	<table width="967" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td><img src="${images}/lo.png" width="955" height="208" /></td>
		</tr>
		<tr>
			<td><%@include file="../menu.jsp"%></td>
		</tr>
		<!-- 正文开始 -->
		<tr>
			<td>
				<table width="942" border="0" align="center" cellpadding="0" cellspacing="0">
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
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="40" >
													<div align="center" class="biao1">
														<a href="${ctx }/account/list" class="d">我的公众账号</a>
													</div>
												</td>
											</tr>
											<tr>
												<td height="40" bgcolor="#e87352">
													<div align="center" class="biao1">添加账号</div>
												</td>
											</tr>
										</table>
									</td>
									<td width="1" bgcolor="#999999"><img src="${images}/su.png" width="1" height="257" /></td>
									<td width="770" valign="top">
										<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
											<form:form id="accountForm" method="post" modelAttribute="wxAccount">
												<tr>
													<td height="40">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td width="10%" height="40" class="biao">公众帐号名 <font color="red">*</font></td>
																<td width="52%">
																	<form:hidden path="id" /> 
																	<form:input style="width: 300px;" path="name" />&nbsp;
																	<form:errors path="name" cssClass="error" /></td>
															</tr>
															<tr>
																<td width="10%" height="40" class="biao">URL</td>
																<td width="52%">
																	<form:input path="url" style="width: 300px;" readOnly="true" /></td>
															</tr>
															<tr>
																<td width="10%" height="40" class="biao">Token</td>
																<td width="52%"><form:input path="seq" style="width: 300px;" readOnly="true" /></td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="20">&nbsp;</td>
												</tr>
												<tr>
													<td>
														<table width="729" border="0" cellpadding="0" cellspacing="0" background="${images}/dd.png">
															<tr>
																<td height="320" valign="top">
																	<table width="100%" border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="40">
																				<table width="100%" border="0" cellspacing="0" cellpadding="0">
																					<tr>
																						<td width="3%">&nbsp;</td>
																						<td width="97%" class="biao">备注</td>
																					</tr>
																				</table>
																			</td>
																		</tr>
																		<tr>
																			<td height="260">
																				<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#999999" bgcolor="#FFFFFF">
																					<tr>
																						<td height="200" valign="top">
																							<form:textarea path="note" cols="90" rows="14" /></td>
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
													<td height="50">
														<form:button id="submitbtn" class="btn-primary" >提交</form:button>
													</td>
												</tr>
											</form:form>
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
</body>
</html>
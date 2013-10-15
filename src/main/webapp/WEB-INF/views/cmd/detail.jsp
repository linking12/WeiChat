<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>
	<script type="text/javascript">
		$(function(){
			$("#submitbtn").click(function(){
				$('#cmdform').attr('action', '${ctx}/cmd/submit');
				$('#cmdform').submit();
			});
		});
	</script>
</head>
<body>
	<form:form id="cmdform" method="post" modelAttribute="wxCmd">
		<form:hidden path="id" />
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
										<td width="94%" class="biao">智能客服</td>
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
													<td height="40">
														<div align="center" class="c"><a href="${ctx }/cmd/init" class="d">智能客服</a></div>
													</td>
												</tr>
												<tr>
													<td height="40" bgcolor="#e87352">
														<div align="center" class="biao1">添加匹配</div>
													</td>
												</tr>
											</table>
										</td>
										<td width="1" bgcolor="#999999"><img src="${images}/su.png" width="1" height="257" /></td>
										<td width="770" valign="top">
											<table width="96%" border="0" align="center" cellpadding="0"cellspacing="0">												
												<tr>
													<td height="30">
														<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
															<tr>
																<td width="30%" height="40" class="biao">公众帐号名&nbsp;<font color="red">*</font></td>
																<td>
																	<form:select style="width: 150px;" path="accountId" items="${accounts}" itemValue="id" itemLabel="name" />
																</td>
															</tr>
															
														</table>
													</td>
												</tr>
												<tr>
													<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
												</tr>
												<tr>
													<td height="30">
														<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
															<tr>
																<td width="30%" height="40" class="biao">用户发送&nbsp;<font color="red">*</font></td>
																<td>
																	<form:input style="width: 150px;" path="cmd" />&nbsp;
																	<form:errors path="cmd" cssClass="error" />
																	<span id="cmderror" class="error" style="display: none">用户发送信息必须输入!</span>
																</td>
															</tr>															
														</table>
													</td>
												</tr>
												<tr>
													<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
												</tr>
												<tr>
													<td height="30">
														<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
															<tr>
																<td width="30%" height="40" class="biao">匹配类型&nbsp;<font color="red">*</font></td>
																<td>
																	<form:select style="width: 150px;" path="ctype" items="${ctypeList}" itemValue="key" itemLabel="value" />
																</td>
															</tr>
															
														</table>
													</td>
												</tr>
												<tr>
													<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
												</tr>
												<tr>
													<td height="30">
														<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
															<tr>
																<td width="30%" height="40" class="biao">自动回复信息&nbsp;<font color="red">*</font></td>
																<td>
																	<form:select style="width: 150px;" path="messageId" items="${messages}" itemValue="id" itemLabel="msgName" />
																</td>
															</tr>
															
														</table>
													</td>
												</tr>
												<tr>
													<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
												</tr>
												<tr>
													<td height="30">
														<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
															<tr>
																<td height="50" align="">
																	<form:button id="submitbtn" class="btn-primary" >提交</form:button>
																</td>
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
							<td><img src="${images}/xia_zu.png" width="942" height="14" /></td>
						</tr>
						<%@include file="../bottom.jsp"%>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>

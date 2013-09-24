<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>用户登录</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submitbtn").click(function() {
			$('#loginForm').attr('action', '${ctx}/submit');
			$('#loginForm').submit();
		});
	});
</script>
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="57" background="${images}/tiao.jpg" />">
			<table width="960" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td><img src="${images}/logo.png" width="125" height="32" /></td>
					<td><div align="right" class="di">
							<a href="zhuce.jsp" class="b">立即注册</a>
						</div></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><table width="960" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="20">&nbsp;</td>
					</tr>
					<tr>
						<td height="555" background="${images}/di.jpg"><table
								width="382" height="389" border="0" align="center"
								cellpadding="0" cellspacing="0" background="${images}/kun.jpg">
								<tr>
									<td>

										<table width="90%" border="0" align="center" cellpadding="0"
											cellspacing="0">
											<form:form id="loginForm" method="post"
												modelAttribute="loginForm">
												<tr>
													<td width="10%"><img src="${images}/user.png"
														width="39" height="38" /></td>
													<td width="90%"><form:input path="userName"
															class="full" /></td>
												</tr>
											

												<tr>
													<td width="10%"><img src="${images}/password.png"
														width="39" height="38" /></td>
													<td width="90%"><form:password path="password"
															class="full" /></td>
												</tr>


												<tr>
													<td height="20">&nbsp;</td>
													<td>&nbsp;</td>
												</tr>

												<tr>
													<td colspan="2"><table width="100%" border="0"
															cellspacing="0" cellpadding="0">
															<tr>
																<td width="6%"><input type="radio"
																	name="radiobutton" value="radiobutton" /></td>
																<td width="18%" class="zu">记住密码</td>
																<td width="33%"><a href="#" class="zu">忘记密码</a></td>
																<td width="43%">
																<div align="right"><input type="image" id="submitbtn" src="${images }/bt_n.jpg" width="76" height="40" /></div>
																</td>
															</tr>
												
														</table></td>
												</tr>
												
												<tr><td height="30" colspan="2">
												<form:errors path="userName" cssClass="error" />
												<form:errors path="password" class="full" cssClass="error"/>
												</td>
													
													
												</tr>
											</form:form>

										</table>
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
</body>
</html>
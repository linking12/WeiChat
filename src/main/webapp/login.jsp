<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="org.springframework.security.web.WebAttributes" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="js" value="${ctx}/js"/>
<c:set var="css" value="${ctx}/css"/>
<c:set var="images" value="${ctx}/images"/>
<link href="${css }/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/jquery/1.6/jquery.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>用户登录</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submitbtn").click(function() {
			$('#loginForm').attr('action', '${ctx}/login/submit');
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
							<a href="${ctx }/register" class="b">立即注册</a>
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
									     ${SPRING_SECURITY_LAST_EXCEPTION.message}
									    <form action="j_spring_security_check" autocomplete="on" method="POST">
										<table width="90%" border="0" align="center" cellpadding="0"
											cellspacing="0">											
												<tr>
													<td width="10%"><img src="${images}/user.png"
														width="39" height="38" /></td>
													<td width="90%">
								                          <input id="username" name="username" class="full" required="required" type="text" placeholder="用户名 或者 mymail@mail.com" />
												    </td>
												</tr>
											

												<tr>
													<td width="10%"><img src="${images}/password.png"
														width="39" height="38" /></td>
													<td width="90%">
													  <input id="password" name="password" class="full"  required="required" type="password" placeholder="eg. X8df!90EO" />
													</td>
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
												</td>
												</tr>									
										    </table>
										 </form>
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
</body>
</html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>
</head>
<body>
	<table width="967" border="0" align="center" cellpadding="0" cellspacing="0">
		<%@include file="../menu.jsp"%>
		<!-- 正文开始 -->
		<tr>
			<td>
				<table width="942" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td height="53" background="${images}/shan.png">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="2%">&nbsp;</td>
									<td width="4%"><img src="${images}/oic_3.png" width="30" height="21" /></td>
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
												<td height="40" bgcolor="#e87352"><div align="center" class="biao1">我的公众账号</div></td>
											</tr>
											<tr>
												<td height="40">
													<div align="center" class="c">
														<a href="${ctx }/account/add" class="d">添加账号</a>
													</div>
												</td>
											</tr>
										</table>
									</td>
									<td width="1" bgcolor="#999999"><img src="${images}/su.png" width="1" height="257" /></td>
									<td width="770" valign="top">
										<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
											<tr>
												<td height="10"></td>
											</tr>
											<tr>
												<td height="30" bgcolor="#d3d3d3">
													<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
														<tr>
															<td width="18%" class="biao">公众帐号</td>
															<td width="43%" class="biao">URL</td>
															<td width="28%" class="biao">Token</td>
															<td width="11%" class="biao">操作</td>
														</tr>
													</table>
												</td>
											</tr>
											<c:forEach items="${accounts}" var="account">
												<tr>
													<td height="58">
														<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
															<tr>
																<td width="21%">${account.name }</td>
																<td width="40%">http://www.yidia.cn${ctx }${account.url}</td>
																<td width="23%" class="zeng">${account.seq }</td>
																<td width="7%" align="center" class="zeng"><a href="${ctx }/replymsg/init?accountId=${account.id }">配置</a></td>
																<td width="4%" align="center" class="zeng"><a href="${ctx }/account/edit/${account.id }">修改</a></td>
																<td width="5%" align="center" class="zeng"><a href="${ctx }/account/delete/${account.id }">删除</a></td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
												</tr>
											</c:forEach>
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

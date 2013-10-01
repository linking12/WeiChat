<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="js" value="${ctx}/index/js" />
<c:set var="css" value="${ctx}/index/css" />
<c:set var="images" value="${ctx}/index/images" />
<link href="${css }/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/jquery/1.6/jquery.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>微信公共平台</title>
<style type="text/css">
body {
	background-image: url(${images}/bei.jpg);
}
</style>
</head>
<body>
	<table width="967" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td><img src="${images}/lo.png" width="955" height="208" /></td>
		</tr>
		<tr>
			<td><%@include file="menu.jsp"%></td>
		</tr>
		<tr>
			<td height="30">&nbsp;</td>
		</tr>
		<tr>
			<td><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td height="30" class="biao3">fexue2000</td>
					</tr>
					<tr>
						<td height="20" class="biao1">您当前的用户级别：普通用户</td>
					</tr>
					<tr>
						<td height="20" class="biao1">会员期限：永久免费</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="50">&nbsp;</td>
		</tr>
		<tr>
			<td><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td><div align="center">
								<img src="${images}/ic_1.png" width="310" height="310" />
							</div></td>
						<td><div align="center">
						            <a href="${ctx}/message/init">
						               <img src="${images}/ic_2.png" width="310" height="310" />
							        </a>
							</div></td>
						<td><div align="center">
						         <a href="${ctx}/replymsg/init">
								     <img src="${images}/ic_3.png" width="310" height="310" />
								 </a>
							</div></td>
					</tr>
					<tr>
						<td height="20"><div align="center"></div></td>
						<td><div align="center"></div></td>
						<td><div align="center"></div></td>
					</tr>
					<tr>
						<td><div align="center">
						       <a href="${ctx}/message/init">
								<img src="${images}/ic_4.png" width="310" height="310" />
							   </a>
							</div></td>
						<td><div align="center">
								<img src="${images}/ic_5.png" width="310" height="310" />
							</div></td>
						<td><div align="center">
								<img src="${images}/ic_6.png" width="310" height="310" />
							</div></td>
					</tr>
					<tr>
						<td height="20">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><div align="center">
								<img src="${images}/ic_7.png" width="310" height="310" />
							</div></td>
						<td><div align="center">
								<img src="${images}/ic_8.png" width="310" height="310" />
							</div></td>
						<td>&nbsp;</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="50">&nbsp;</td>
		</tr>
		<tr>
			<td><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td><div align="center">
								<img src="${images}/ic_9.png" width="310" height="310" />
							</div></td>
						<td><div align="center">
								<img src="${images}/ic_10.png" width="310" height="310" />
							</div></td>
						<td><div align="center">
								<img src="${images}/ic_11.png" width="310" height="310" />
							</div></td>
					</tr>
					<tr>
						<td height="20"><div align="center"></div></td>
						<td><div align="center"></div></td>
						<td><div align="center"></div></td>
					</tr>
					<tr>
						<td><div align="center">
								<img src="${images}/ic_12.png" width="310" height="310" />
							</div></td>
						<td><div align="center">
								<img src="${images}/ic_13.png" width="310" height="310" />
							</div></td>
						<td><div align="center">
								<img src="${images}/ic_14.png" width="310" height="310" />
							</div></td>
					</tr>

				</table></td>
		</tr>
		<tr>
			<td height="50">&nbsp;</td>
		</tr>
		<tr>
			<td><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td><div align="center">
								<img src="${images}/ic_15.png" width="310" height="310" />
							</div></td>
						<td><div align="center">
								<img src="${images}/ic_16.png" width="310" height="310" />
							</div></td>
						<td><div align="center">
								<img src="${images}/ic_17.png" width="310" height="310" />
							</div></td>
					</tr>
					<tr>
						<td height="20"><div align="center"></div></td>
						<td><div align="center"></div></td>
						<td><div align="center"></div></td>
					</tr>
					<tr>
						<td><div align="center">
								<img src="${images}/ic_18.png" width="310" height="310" />
							</div></td>
						<td><div align="center">
								<img src="${images}/ic_19.png" width="310" height="310" />
							</div></td>
						<td><div align="center">
								<img src="${images}/ic_20.png" width="310" height="310" />
							</div></td>
					</tr>
					<tr>
						<td height="20">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><div align="center">
								<img src="${images}/ic_21.png" width="310" height="310" />
							</div></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="50"><p>&nbsp;</p></td>
		</tr>
		<tr>
			<td><table width="90%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td height="30"><p class="biao1">在线客服</p></td>
					</tr>
					<tr>
						<td><table width="90%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td width="2%"><img src="${images}/qq.png" width="15"
										height="16" /></td>
									<td width="6%" class="biao1">天乐</td>
									<td width="26%" class="biao3">1234677</td>
									<td><img src="${images}/qq.png" width="15" height="16" /></td>
									<td class="biao1">天乐</td>
									<td class="biao3">1234677</td>
									<td><img src="${images}/qq.png" width="15" height="16" /></td>
									<td class="biao1">天乐</td>
									<td class="biao3">1234677</td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="50">&nbsp;</td>
		</tr>
	</table>
</body>
</html>

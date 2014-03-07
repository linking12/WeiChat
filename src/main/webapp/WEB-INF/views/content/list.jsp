<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="../common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>微信公共平台</title>

<script type="text/javascript">
		function doedit(id){
			$('#form1').attr('action', '${ctx}/content/edit/'+id);
			$('#form1').submit();
		}
		
		function changeMsgType(){
			$('#form1').attr('action', '${ctx}/content/init?msgType='+$("#msgType").val());
			$('#form1').submit();
		}
		
		$(function(){
			$("#msgType").val("${msgType}");
		})
	</script>
</head>
<body>
	<form id="form1" method="post"></form>
	<div class="b_con">
		<div class="by_box">
			<%@include file="../menu.jsp"%>
			<table width="967" border="0" align="center" cellpadding="0"
				cellspacing="0">

				<!-- 正文开始 -->
				<tr>
					<td>
						<table width="942" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="53" background="${images}/shan.png">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="2%">&nbsp;</td>
											<td width="4%"><img src="${images}/oic_3.png" width="30"
												height="21" /></td>
											<td width="94%" class="biao">素材管理</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="350" valign="top" background="${images}/he.png">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="120" valign="top">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td height="40" bgcolor="#e87352"><div align="center"
																class="biao">素材列表</div></td>
													</tr>
													<tr>
														<td height="40">
															<div align="center">
																<a href="${ctx }/content/add" class="biao1">添加素材</a>
															</div>
														</td>
													</tr>
												</table>
											</td>
											<td width="1" bgcolor="#999999"><img
												src="${images}/su.png" width="1" height="257" /></td>
											<td width="770" valign="top">
												<table width="96%" border="0" align="center" cellpadding="0"
													cellspacing="0">
													<tr>
														<td height="10"></td>
													</tr>
													<tr>
														<td height="30">
															<table width="98%" border="0" align="center"
																cellpadding="0" cellspacing="0">
																<tr>
																	<td width="10%" class="biao"><select id="msgType"
																		onchange="changeMsgType()" style="width: 100px;">
																			<option value="">全部</option>
																			<c:forEach items="${contentTypes }" var="contentType">
																				<option value="${contentType.key}">${contentType.value}</option>
																			</c:forEach>
																	</select></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td height="10"></td>
													</tr>
													<tr>
														<td>
															<table width="98%" border="0" align="center"
																cellpadding="0" cellspacing="0">
																<tr height="30" bgcolor="#d3d3d3">
																	<td width="20%" class="biao">类型</td>
																	<td width="20%" class="biao">标题</td>
																	<td width="20%" class="biao">URL</td>
																	<td width="20%" class="biao">高清URL</td>
																	<td width="5%" class="biao">操作</td>
																</tr>
																<c:forEach items="${contents.content}" var="content">
																	<tr height="30">
																		<td><select disabled>
																				<c:forEach items="${contentTypes }"
																					var="contentType">
																					<c:if test="${contentType.key==content.msgType }">
																						<option value="${contentType.key}">${contentType.value}</option>
																					</c:if>
																				</c:forEach>
																		</select>
																		<c:if test="${content.msgType=='image' }"><img src="${ctx}/${content.picUrl}" width="30" height="30" /></c:if></td>
																		<td>${content.title}</td>
																		<td><c:choose>
																				<c:when test="${content.msgType=='image' }">
																					<a title="${content.picUrl}"
																						style="text-decoration: none; color: blue"> <c:choose>
																							<c:when test="${fn:length(content.picUrl) gt 20}">${fn:substring(content.picUrl, 0, 20)}...</c:when>
																							<c:otherwise>${content.picUrl}</c:otherwise>
																						</c:choose>
																					</a>
																				</c:when>
																				<c:otherwise>
																					<a title="${content.musicUrl}"
																						style="text-decoration: none; color: blue"> <c:choose>
																							<c:when
																								test="${fn:length(content.musicUrl) gt 20}">${fn:substring(content.musicUrl, 0, 20)}...</c:when>
																							<c:otherwise>${content.musicUrl}</c:otherwise>
																						</c:choose>
																					</a>
																				</c:otherwise>
																			</c:choose></td>
																		<td><a title="${content.hqmusicUrl}"
																			style="text-decoration: none; color: blue"> <c:choose>
																					<c:when
																						test="${fn:length(content.hqmusicUrl) gt 20}">${fn:substring(content.hqmusicUrl, 0, 20)}...</c:when>
																					<c:otherwise>${content.hqmusicUrl}</c:otherwise>
																				</c:choose>
																		</a></td>

																		<td><input type="button" value="修改"
																			class="btn-primary" onclick="doedit(${content.id})"></td>
																	</tr>
																	<tr>
																		<td colspan="6"><div align="center">
																				<img src="${images}/xian.jpg" width="800" height="1" />
																			</div></td>
																	</tr>
																</c:forEach>
															</table>
														</td>
													</tr>
													<tr>
														<td align="center"><tags:pagination
																page="${contents}" paginationSize="5" /></td>
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

						</table>
					</td>
				</tr>
			</table><%@include file="../bottom.jsp"%>
		</div>
	</div>
</body>
</html>

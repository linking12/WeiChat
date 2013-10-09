<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<c:set var="images" value="${ctx}/index/images" />
<head>
	<style type="text/css">
		body {background-image: url("${images}/bei.jpg");}
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#savebtn").click(function() {
			$('#form1').attr('action', '${ctx}/replymsg/save');
			$('#form1').submit();
		});
	});

	function changeAccountId() {
		var accountid = $("#accountId").val();
		$('#form1').attr('action','${ctx}/replymsg/init?accountid=' + accountid);
		$('#form1').submit();
	}

	function changeMsgType(obj) {
		var msgType = obj.value;
		var sourceid = obj.id.replace("msgTypesaction", "msgTypessourceId");

		if ("program" == msgType) {
			$("#" + sourceid).empty();
			$("#programsel option").each(function() {
				$("#" + sourceid).append($(this).clone());
			});
		} else if ("direct" == msgType) {
			$("#" + sourceid).empty();
			$("#directsel option").each(function() {
				$("#" + sourceid).append($(this).clone());

			});
		}
	}
	

	function dodelete(id){
		$('#form1').attr('action', '${ctx}/replymsg/delete/'+id);
		$('#form1').submit();
	}
	
	function doadd(){
		tanchuceng(900, 600, '自动回复配置','${ctx }/replymsg/add/'+$("#accountId").val());
	}
	</script>
</head>
<body >
		<form:form id="form1" method="post" modelAttribute="replyMsgForm">
		<table width="967" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td><img src="${images}/lo.png" width="955" height="208" /></td>
			</tr>
			<tr>
				<td><%@include file="../menu.jsp"%></td>
			</tr>
			<tr>
				<td height="10">&nbsp;</td>
				<td style="display: none">
					<select	id="programsel">
						<c:forEach items="${games}" var="game">
							<option value="${game.id }">${game.name }</option>
						</c:forEach>
					</select>
					<select id="directsel">
						<c:forEach items="${messages}" var="message">
							<option value="${message.id }">${message.msgName }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<table width="942" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="53" background="${images}/shan.png">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2%">&nbsp;</td>
										<td width="4%"><img src="${images}/oic_3.png" width="30"height="21" /></td>
										<td width="94%" class="biao">自动回复配置</td>
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
													<td height="40" bgcolor="#e87352">
														<div align="center" class="biao1">自动回复配置</div>
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
																<td width="20%" height="40" class="biao">公众帐号名</td>
																<td width="20%">
																	<form:select id="accountId" style="width: 150px;" onchange="changeAccountId()" path="accountId" items="${accounts}" itemValue="id" itemLabel="name"/>
																	
																</td>
																<td>
																	<div align="left"><input type="button" name="addbtn" id="addbtn" class="btn-primary" onclick="doadd()" value="新增"/></div>
																</td>
															</tr>
															
														</table>
													</td>
												</tr>
												<tr>
													<td height="30" bgcolor="#d3d3d3">
														<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
															<tr>
																<td width="20%" class="biao">选择</td>
																<td width="20%" class="biao">用户发送类型</td>
																<td width="20%" class="biao">自动回复类型</td>
																<td width="30%" class="biao">信息内容或处理程序</td>		
																<td width="10%" class="biao">操作</td>																
															</tr>
														</table>
													</td>
												</tr>
												<c:forEach items="${msgTypes }" var="msgType" varStatus="status">
													<tr>
														<td height="57">
															<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
																<tr>
																	<td width="20%"><form:checkbox path="msgTypes[${status.index}].istatus" value="1"/></td>
																	<td width="20%">${msgType.name }
																		<form:hidden path="msgTypes[${status.index}].id"/>
																		<form:hidden path="msgTypes[${status.index}].accountId"/>
																		<form:hidden path="msgTypes[${status.index}].msgType"/>																				
																		<form:hidden path="msgTypes[${status.index}].name"/>																		
																	</td>
																	<td width="20%">
																		<form:select path="msgTypes[${status.index}].action" id="msgTypesaction${status.index}"	onchange="changeMsgType(this)" items="${actionTypes}" itemValue="key" itemLabel="value"/>
																	</td>
																	<td width="30%">
																		<c:if test="${msgType.action=='program' }">
																			<form:select path="msgTypes[${status.index}].sourceId" id="msgTypessourceId${status.index}" items="${games}" itemValue="id" itemLabel="name"></form:select>
																		</c:if> 
																		<c:if test="${msgType.action=='direct' }">
																			<form:select path="msgTypes[${status.index}].sourceId" id="msgTypessourceId${status.index}" items="${messages}" itemValue="id" itemLabel="msgName"></form:select>
																		</c:if>
																	</td>
																	<td width="10%">
																			<form:button class="btn-primary" onclick="dodelete(${msgType.id })">删除</form:button>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<div align="center"><img src="${images}/xian.jpg" width="727" height="1" /></div>
														</td>
													</tr>
													
												</c:forEach>
													<tr>
														<td>
															<div align="center"><form:button name="savebtn" id="savebtn" class="btn-primary">修改配置</form:button></div>
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
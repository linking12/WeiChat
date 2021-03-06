<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>" />
<head>

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

	function changeMsgType(id) {
		var msgType = $("#msgTypesaction"+id).val();
		
		removetc();
		if ("program" == msgType) {
			$("#msgTypessourceId" + id).empty();
			$("#btnView"+id).remove();
			$("#programsel option").each(function() {
				$("#msgTypessourceId" + id).append($(this).clone());
			});
		} else if ("direct" == msgType) {
			$("#msgTypessourceId" + id).empty();
			$("#to"+id).prepend("<input type='button' value='查看' onclick='doview("+id+")'  class='btn-primary' name='btnView' id='btnView"+id+"'/>");
			$("#directsel option").each(function() {
				$("#msgTypessourceId" + id).append($(this).clone());
			
			});
		}
	}
	

	function dodelete(id){
		$('#form1').attr('action', '${ctx}/replymsg/delete/'+id);
		$('#form1').submit();
	}
	
	function doadd(){
		$('#form1').attr('action', '${ctx }/replymsg/add/'+$("#accountId").val());
		$('#form1').submit();
	}
	
	function doview(id){
			$("#tc").remove();
			$("#messageId").val($("#msgTypessourceId"+id).val());
			$("#t"+id).append("<table id='tc' width='100%' border='0' align='center' style='margin-top: 20px'></table>");
			ajaxMessage();
	}
	function removetc(){
		$("#tc").remove();
	}
	</script>
</head>
<body >
	<form:form id="form1" method="post" modelAttribute="replyMsgForm">
	<div class="b_con">
	<input type="hidden" id="messageId">
	<div class="by_box">
		<%@include file="../menu.jsp"%>
		<table width="967" border="0" align="center" cellpadding="0" cellspacing="0">
			
			<tr>
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
														<div align="center" class="biao">配置列表</div>
													</td>
												</tr>
												<tr>
													<td height="40">
														<div align="center" ><a href="javascript:doadd()" class="biao1">添加配置</a></div>
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
																<td width="80%">
																	<form:select id="accountId" style="width: 150px;" onchange="changeAccountId()" path="accountId" items="${accounts}" itemValue="id" itemLabel="name"/>
																	
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
																		<form:select path="msgTypes[${status.index}].action" id="msgTypesaction${msgType.id }"	onchange="changeMsgType(${msgType.id },this)" items="${actionTypes}" itemValue="key" itemLabel="value"/>
																	</td>
																	<td width="30%">
																		<c:if test="${msgType.action=='program' }">
																			<form:select path="msgTypes[${status.index}].sourceId" id="msgTypessourceId${msgType.id }" items="${games}" itemValue="id" itemLabel="name" onchange="removetc()"></form:select>
																		</c:if> 
																		<c:if test="${msgType.action=='direct' }">
																			<form:select path="msgTypes[${status.index}].sourceId" id="msgTypessourceId${msgType.id }" items="${messages}" itemValue="id" itemLabel="msgName" onchange="removetc()"></form:select>
																		</c:if>
																	</td>
																	<td width="10%" id="to${msgType.id }">
																	    <c:if test="${msgType.action=='direct' }">
																			<input type="button" value="查看" onclick="doview('${msgType.id }','${msgType.action}','${msgType.sourceId}')"  class="btn-primary" name="btnView" id="btnView${msgType.id }"/>
																			</c:if>
																			&nbsp;<form:button class="btn-primary" onclick="dodelete(${msgType.id })">删除</form:button>
																	</td>
																</tr>
																<tr><td colspan="5" id="t${msgType.id }"></td></tr>
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
						
					</table>
				</td>
			</tr>
		</table><%@include file="../bottom.jsp"%>
	</div>
</div>
	</form:form>
	<%@ include file="../content/content.jsp"%>
</body>
</html>
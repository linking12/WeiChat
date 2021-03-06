<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#submitbtn').click(function() {		
			$('#msgform').attr('action', '${ctx}/replymsg/submit');
			$('#msgform').submit();
		});
		doAjax();
	});
	
	function changeAction(){
		var actionType=$("#actionType").val();
		if ("program" == actionType) {
			$("#sourceId").empty();
			$("#tc").hide();
			$("#programsel option").each(function() {
				$("#sourceId").append($(this).clone());
			});
		} else if ("direct" == actionType) {
			$("#sourceId").empty();
			$("#directsel option").each(function() {
				$("#sourceId").append($(this).clone());

			});
			doAjax();
			$("#tc").show();
		}
	}
	
	function doAjax(){
		if("direct" ==$("#actionType").val()){
			$("#messageId").val($("#sourceId").val());
			ajaxMessage();
		}
	}
	</script>
</head>
<body >
	<form:form id="msgform" method="post" modelAttribute="wxMsgType">
		<form:hidden path="id" />
		<input type="hidden" id="messageId"/>
		<div class="b_con">
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
													<td height="40">
														
														<div align="center" ><a href="${ctx }/replymsg/init" class="biao1">配置列表</a></div>
													</td>
												</tr>
												<tr>
													<td height="40"  bgcolor="#e87352">
														<div align="center" class="biao">添加配置</div>
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
																<td width="30%" height="40" class="biao">公众帐号名</td>
																<td>
																	<form:select path="accountId" items="${accounts}" itemValue="id" itemLabel="name" style="width: 150px;"/>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center" ><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr>
																<td width="30%" height="40" class="biao">用户发送类型</td>
																<td>
																	<form:select path="msgType" items="${sendTypes}" itemValue="key" itemLabel="value" style="width: 150px;"/>
																	
																	<span id="cmderror" class="error" style="display: none">用户发送信息必须输入!</span>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center" ><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr>
																<td width="30%" height="40" class="biao">自动回复类型</td>
																<td>
																	<form:select path="action" id="actionType" items="${actionTypes}" itemValue="key" itemLabel="value" onchange="changeAction()" style="width: 150px;"/>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center" ><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr>
																<td width="30%" height="40" class="biao">信息内容或处理程序</td>
																<td>
																	<form:select path="sourceId" id="sourceId" items="${games}" itemValue="id" itemLabel="name" style="width: 150px;" onchange="doAjax()"/>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center" ><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr><td colspan="2"><table id="tc" width="100%" border="0" align="center" style="margin-top: 20px"></table></td></tr>
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
						
					</table>
				</td>
			</tr>
		</table><%@include file="../bottom.jsp"%>
	</div>
</div>
	</form:form>
</body>
<%@ include file="../content/content.jsp"%>
</html>
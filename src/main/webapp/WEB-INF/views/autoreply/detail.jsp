<%@ page language="java" contentType="text/html;charset=utf8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<c:set var="images" value="${ctx}/index/images" />
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>信息内容管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			var options = {
				url : '${ctx}/replymsg/submit',
				type : "POST",
				success : function(data, statusText, xhr, $form) {
									if("0"!=data){
										parent.location.href = "${ctx}/replymsg/init?accountId="+data;
										$(window.parent.document).find(".winbj").remove();
										$(window.parent.document).find(".tanChu").remove();
									}
						}
				};
				$('#submitbtn').click(function() {
					$("#msgform").ajaxForm(options);		
				});		
		});
		
		function changeAction(){
				var actionType=$("#actionType").val();
				if ("program" == actionType) {
					$("#sourceId").empty();
					$("#programsel option").each(function() {
						$("#sourceId").append($(this).clone());
					});
				} else if ("direct" == actionType) {
					$("#sourceId").empty();
					$("#directsel option").each(function() {
						$("#sourceId").append($(this).clone());

					});
				}
			
		}
	</script>
</head>
<body>
	<form:form id="msgform" method="post" modelAttribute="wxMsgType">
		<form:hidden path="id" />
		<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="30%" height="40" class="biao">公众帐号名</td>
				<td>
					<form:select path="accountId" items="${accounts}" itemValue="id" itemLabel="name" />
				</td>
			</tr>
			<tr>
				<td width="30%" height="40" class="biao">用户发送类型</td>
				<td>
					<form:select path="msgType" items="${sendTypes}" itemValue="key" itemLabel="value" />
					
					<span id="cmderror" class="error" style="display: none">用户发送信息必须输入!</span>
				</td>
			</tr>
			<tr>
				<td width="30%" height="40" class="biao">自动回复类型</td>
				<td>
					<form:select path="action" id="actionType" items="${actionTypes}" itemValue="key" itemLabel="value" onchange="changeAction()"/>
				</td>
			</tr>
			<tr>
				<td width="30%" height="40" class="biao">信息内容或处理程序</td>
				<td>
					<form:select path="sourceId" id="sourceId" items="${games}" itemValue="id" itemLabel="name" />
				</td>
			</tr>
			<tr>
				<td height="50" align="center">
					<form:button id="submitbtn" class="btn-primary" >提交</form:button>
				</td>
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
		</table>
	</form:form>
</body>
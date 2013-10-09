<%@ page language="java" contentType="text/html;charset=utf8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<c:set var="images" value="${ctx}/index/images" />
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>信息内容管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			var options = {
				url : '${ctx}/cmd/submit',
				type : "POST",
				success : function(data, statusText, xhr, $form) {
									if("1"==data){
										parent.location.href = "${ctx}/cmd/init";
										$(window.parent.document).find(".winbj").remove();
										$(window.parent.document).find(".tanChu").remove();
									}
						}
				};
				$('#submitbtn').click(function() {
					if($("#cmd").val()==""){
						$("#cmderror").show();
						return false;
					}
					$("#cmderror").hide();
					$("#cmdform").ajaxForm(options);
			
				});		
		});
	</script>
</head>
<body>
	<form:form id="cmdform" method="post" modelAttribute="wxCmd">
		<form:hidden path="id" />
		<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="30%" height="40" class="biao">公众帐号名</td>
				<td>
					<form:select path="accountId" items="${accounts}" itemValue="id" itemLabel="name" />
				</td>
			</tr>
			<tr>
				<td width="30%" height="40" class="biao">用户发送</td>
				<td>
					<form:input path="cmd" />&nbsp;
					<form:errors path="cmd" cssClass="error" />
					<span id="cmderror" class="error" style="display: none">用户发送信息必须输入!</span>
				</td>
			</tr>
			<tr>
				<td width="30%" height="40" class="biao">匹配类型</td>
				<td>
					<form:select path="ctype" items="${ctypeList}" itemValue="key" itemLabel="value" />
				</td>
			</tr>
			<tr>
				<td width="30%" height="40" class="biao">自动回复信息</td>
				<td>
					<form:select path="messageId" items="${messages}" itemValue="id" itemLabel="msgName" />
				</td>
			</tr>
			<tr>
				<td height="50" align="center">
					<form:button id="submitbtn" class="btn-primary" >提交</form:button>
				</td>
				<td height=""></td>
			</tr>
		</table>
	</form:form>
</body>
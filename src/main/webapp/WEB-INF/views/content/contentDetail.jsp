<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<c:set var="images" value="${ctx}/index/images" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>信息内容管理</title>
<style type="text/css">
.c div {
	color: #ff9001;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var options = {
			url : '${ctx}/content/saveContent',
			type : "POST",
			success : function(data, statusText, xhr, $form) {
				parent.location.href = "${ctx}/message/addMultimedia/image";
				$(window.parent.document).find(".winbj").remove();
				$(window.parent.document).find(".tanChu").remove();
				
			}
		};
		$('#submitbtn').click(function() {
			$("#contentForm").ajaxForm(options);
		});

	});
</script>
</head>
<body>
	<form:form id="contentForm" action="${ctx}/content/saveContent"
		method="post" modelAttribute="contentForm"
		enctype="multipart/form-data">
		<table width="96%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="20%" height="40" class="biao">标题</td>
				<td><form:input path="title" style="width: 300px;" />必须</td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">原文链接</td>
				<td><form:input path="url" style="width: 300px;" />必须</td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">自动回复信息类型</td>
				<td><form:select path="msgType" id="msgType"
						style="width: 300px;">
						<c:forEach items="${messageTypes}" var="messageType">
							<form:option value="${messageType.msgType}">${messageType.name}</form:option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">图片</td>
				<td><input name="imageFile" type="file" id="fileupload_input"
					size="1" />如果是图文必须</td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">音乐</td>
				<td><input name="musicFile" type="file" id="fileupload_input"
					size="1" />如果是音乐必须</td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">高清音乐</td>
				<td><input name="hqMusicFile" type="file" id="fileupload_input"
					size="1" /></td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">视频</td>
				<td><input name="vidioFile" type="file" id="fileupload_input"
					size="1" />如果是视频必须</td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">高清视频</td>
				<td><input name="hqVidioFile" type="file" id="fileupload_input"
					size="1" /></td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">简短描述</td>
				<td><form:textarea path="description" cols="45" rows="10" />必须</td>
			</tr>
			<tr>
				<td height="50"><input type="image" name="submit"
					id="submitbtn" src="${images }/tijiao.png" width="76" height="40" /></td>
				<td height="50"></td>
			</tr>
		</table>
	</form:form>
</body>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${js}/jquery/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${js}/jquery/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${js}/jquery/jquery.fileupload.js"></script>
<title>信息内容管理</title>
<style type="text/css">
.c div {
	color: #ff9001;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submitbtn").click(function() {
			$('#form1').attr('action', '${ctx}/message/sumitimg');
			$('#form1').submit();
		});

		$("#fileupload_input").fileupload({
			type : "POST",
			url : "${ctx}/message/upload",
			formData : {
				'accountId' : $("#accountId").val()
			},
			maxFileSize : 1000000,
			dataType : 'text',
			done : function(e, data) {

				$("#imgs").attr("src", "${ctx}/upload/" + data.result);
				$("#imgs").attr("width", 150);
				$("#imgs").attr("height", 150);
				$("#pp").text("上传完成！");
				$("#picUrl").val(data.result);
			},
			fail : function(e, data) {
				$("#pp").text("上传文件失败!");

			},
			progressall : function(e, data) {
				//进度条；
				var progress = parseInt(data.loaded / data.total * 100, 10);
				$("#pp").text(progress + '%');
			}

		});
	});
</script>
</head>
<body>
	<form:form id="contentForm" method="post" modelAttribute="contentForm"
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
				<td><input name="fileField3" type="file" id="fileupload_input"
					size="1" />如果是图文必须</td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">音乐</td>
				<td><input name="fileField3" type="file" id="fileupload_input"
					size="1" />如果是音乐必须</td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">高清音乐</td>
				<td><input name="fileField3" type="file" id="fileupload_input"
					size="1" /></td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">视频</td>
				<td><input name="fileField3" type="file" id="fileupload_input"
					size="1" />如果是视频必须</td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">高清视频</td>
				<td><input name="fileField3" type="file" id="fileupload_input"
					size="1" /></td>
			</tr>
			<tr>
				<td width="20%" height="40" class="biao">简短描述</td>
				<td><form:textarea path="description" cols="45" rows="10" />必须</td>
			</tr>
			<tr>
				<td height="50"><input type="image" id="submitbtn"
					src="${images }/qunfa.png" width="76" height="40" id="tijiao" /></td>
			</tr>
		</table>
	</form:form>
</body>
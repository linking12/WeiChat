<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>信息内容管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			var options = {
				url : '${ctx}/content/saveContent',
				type : "POST",
				success : function(data, statusText, xhr, $form) {
					
					parent.location.href = "${ctx}/message/addMultimedia/"+data;
					$(window.parent.document).find(".winbj").remove();
					$(window.parent.document).find(".tanChu").remove();
					
				}
			};
			$('#submitbtn').click(function() {
				if(!validate()){return false;}
				$("#contentForm").ajaxForm(options);
			});
	
		});
		
		function validate(){
			var flag=true;
			if(""==$("#title").val()){
				$("#errorTitle").show();
				flag=false;
			}else {
				$("#errorTitle").hide();
			}						
			
			if(""==$("#description").val()){
				$("#errorDescription").show();
				flag=false;
			}else {
				$("#errorDescription").hide();
			}
			if("image"==$("#msgType").val()){
				if(""==$("#imageFile").val()){
					$("#errorimage").show();
					flag=false;
				}else{
					$("#errorimage").hide();
				}
			}else if("voice"==$("#msgType").val()){
				if(""==$("#musicFile").val()){
					$("#errormusic").show();
					flag=false;
				}else{
					$("#errormusic").hide();
				}
			}else if("video"==$("#msgType").val()){
				if(""==$("#videoFile").val()){
					$("#errorvideo").show();
					flag=false;
				}else{
					$("#errorvideo").hide();
				}
			}
			return flag;
		}
		
	</script>
	<style type="text/css">
	body {
		background-image: url();
	}
</style>
</head>
<body>
	<form:form id="contentForm" action="${ctx}/content/saveContent" method="post" modelAttribute="wxContent" enctype="multipart/form-data">
		<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td width="20%" height="40" class="biao">标题&nbsp;<font color="red">*</font></td>
				<td>
					<form:input path="title" style="width: 300px;" />
					&nbsp;<span id="errorTitle" class="error" style="display:none;">标题不能为空!</span>
				</td>
			</tr>
			
			<tr>
				<td width="20%" height="40" class="biao">自动回复信息类型&nbsp;<font color="red">*</font></td>
				<td><form:select path="msgType" id="msgType" items="${contentTypes}" itemValue="key" itemLabel="value" style="width: 300px;" onchange="changeMsgType()"/></td>
			</tr>
			<c:choose>
				<c:when test="${msgType=='image' }">
					<tr>
						<td width="20%" height="40" class="biao">图片&nbsp;<font color="red">*</font></td>
						<td>
							<input name="imageFile" id="imageFile" type="file" size="1" accept="image/*" />
							&nbsp;<span id="errorimage" class="error" style="display:none">上传图片不能为空!</span>
						</td>
					</tr>
				</c:when>
				<c:when test="${msgType=='voice' }">	
					<tr>
						<td width="20%" height="40" class="biao">音乐&nbsp;<font color="red">*</font></td>
						<td>
							<input name="musicFile" id="musicFile" type="file" size="1" accept="audio/*"/>
							&nbsp;<span id="errormusic" class="error" style="display:none">上传音乐不能为空!</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="40" class="biao">高清音乐</td>
						<td><input name="hqMusicFile" type="file" size="1" accept="audio/*"/></td>
					</tr>
				</c:when>
				<c:when test="${msgType=='video' }">
					<tr>
						<td width="20%" height="40" class="biao">视频&nbsp;<font color="red">*</font></td>
						<td>
							<input name="videoFile" id="videoFile" type="file" size="1" accept="video/*"/>
							&nbsp;<span id="errorvideo" class="error" style="display:none">上传视频不能为空!</span>
						</td>
					</tr>
					<tr>
						<td width="20%" height="40" class="biao">高清视频</td>
						<td><input name="hqVideoFile" type="file" size="1" accept="video/*"/></td>
					</tr>
				</c:when>
			</c:choose>
			<tr>
				<td width="20%" height="40" class="biao">简短描述&nbsp;<font color="red">*</font></td>
				<td>
					<form:textarea path="description" cols="45" rows="10" />
					&nbsp;<span id="errorDescription"  class="error" style="display:none;">简短描述不能为空!</span>
				</td>
			</tr>
			<tr>
				<td height="50"><form:button id="submitbtn" class="btn-primary" >提交</form:button></td>
				<td height="50"></td>
			</tr>
		</table>
	</form:form>
</body>
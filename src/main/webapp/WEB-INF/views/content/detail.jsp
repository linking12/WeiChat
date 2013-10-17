<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>
	<script type="text/javascript">
	$(document).ready(function() {
		$('#submitbtn').click(function() {
			if(!validate()){
				return false;
			}
			$('#form1').attr('action', '${ctx}/content/submit');
			$('#form1').submit();
		});
		changeMsgType();
	});
	
	function validate(){
		var flag=true;
		if(""==$("#title").val()){
			$("#errorTitle").show();
			flag=false;
		}else {
			$("#errorTitle").hide();
		}
		
		if(""==$("#url").val()){
			$("#errorUrl").show();
			flag=false;
		}else {
			$("#errorUrl").hide();
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
	function changeMsgType(){
		var msgType=$("#msgType").val();
		if("image"==msgType){
			$("#imageTable").show();
			$("#musicTable").hide();
			$("#videoTable").hide();
		}else if("voice"==msgType){
			$("#imageTable").hide();
			$("#musicTable").show();
			$("#videoTable").hide();
		}else if("video"==msgType){
			$("#imageTable").hide();
			$("#musicTable").hide();
			$("#videoTable").show();
		}
	}
	</script>
</head>
<body >
	<form:form id="form1" method="post" modelAttribute="wxContent" enctype="multipart/form-data">
		<form:hidden path="id" />
		<div class="b_con">
	<div class="by_box">
		<%@include file="../menu.jsp"%>
		<table width="967" border="0" align="center" cellpadding="0" cellspacing="0">
			
			<tr>
				<td>
					<table width="942" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="53" background="${images}/shan.png">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="2%">&nbsp;</td>
										<td width="4%"><img src="${images}/oic_3.png" width="30"height="21" /></td>
										<td width="94%" class="biao">添加素材</td>
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
														<div align="center">
															<a href="${ctx }/content/init" class="biao1">素材列表</a>
														</div>
													</td>
												</tr>
												<tr>
													<td height="40" bgcolor="#e87352">
														<div align="center" class="biao">添加素材</div>
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
																<td width="20%" height="40" class="biao">标题 <font color="red">*</font></td>
																<td>
																	<form:input path="title" style="width: 300px;" />&nbsp;
																	<span id="errorTitle" class="error" style="display:none;">标题不能为空!</span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
												</tr>
												<tr>
													<td height="30">
														<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
															<tr>
																<td width="20%" height="40" class="biao">原文链接 <font color="red">*</font></td>
																<td>
																	<form:input path="url" style="width: 300px;" />
																	&nbsp;<span id="errorUrl" class="error" style="display:none">原文链接不能为空!</span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
												</tr>
												<tr>
													<td height="30">
														<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
															<tr>
																<td width="20%" height="40" class="biao">自动回复信息类型</td>
																<td>
																	<form:select path="msgType" id="msgType" items="${contentTypes}" itemValue="key" itemLabel="value" style="width: 300px;" onchange="changeMsgType()"/>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
												</tr>
												<tbody id="imageTable">
													<tr>
														<td height="30">
															<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
																<tr>
																	<td width="20%" height="40" class="biao">图片<font color="red">*</font></td>
																	<td>
																		<input name="imageFile" id="imageFile" type="file" size="1" accept="image/*" />
																		&nbsp;<span id="errorimage" class="error" style="display:none">上传图片不能为空!</span>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
													</tr>
												</tbody>
												<tbody id="musicTable">
													<tr>
														<td height="30">
															<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
																<tr>
																	<td width="20%" height="40" class="biao">音乐<font color="red">*</font></td>
																	<td>
																		<input name="musicFile" id="musicFile" type="file" size="1" accept="audio/*"/>
																		&nbsp;<span id="errormusic" class="error" style="display:none">上传音乐不能为空!</span>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
													</tr>
													<tr>
														<td height="30">
															<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
																<tr>
																	<td width="20%" height="40" class="biao">高清音乐</td>
																	<td>
																		<input name="hqMusicFile" type="file" size="1" accept="audio/*"/></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
													</tr>
												</tbody>
												<tbody id="videoTable">		
													<tr>
														<td height="30">
															<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
																<tr>
																	<td width="20%" height="40" class="biao">视频<font color="red">*</font></td>
																	<td>
																		<input name="videoFile" id="videoFile" type="file" size="1" accept="video/*"/>
																		&nbsp;<span id="errorvideo" class="error" style="display:none">上传视频不能为空!</span>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
													</tr>
													<tr>
														<td height="30">
															<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
																<tr>
																	<td width="20%" height="40" class="biao">高清视频</td>
																	<td><input name="hqVideoFile" type="file" size="1" accept="video/*"/></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
													</tr>
												</tbody>
												<tr>
													<td height="30">
														<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
															<tr>
																<td width="20%" height="40" class="biao">简短描述<font color="red">*</font></td>
																<td>
																	<form:textarea path="description" cols="45" rows="10" />
																	&nbsp;<span id="errorDescription"  class="error" style="display:none;">简短描述不能为空!</span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
													<td><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
												</tr>
												<tr>
													<td height="30">
														<table width="98%" border="0" align="center"cellpadding="0" cellspacing="0">
															<tr>
																<td height="50" align="center">
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
</html>
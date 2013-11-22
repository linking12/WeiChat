<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<link href="${css }/jquery-ui-1.10.3.custom.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/jquery/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="${js}/jquery/jquery.ui.datepicker-zh-TW.js"></script>
<c:set var="mimg" value="${ctx}/mimg"/>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>
	
	<script type="text/javascript">   
	$(function(){
		$( "#effectiveDate" ).datepicker();
		$( "#expiryDate" ).datepicker();
	});

	function changeAccountId(){
		$("#form1").attr("action","${ctx}/mallset/init?accountId="+$("#accountId").val());
		$("#form1").submit();
	}
	
	function save(){
		if(validate()){
			$("#form1").attr("action","${ctx}/mallset/savemall");
			$("#form1").submit();
		}
	}
	
	function validate(){
		var flag=true;
		if($("#mallName").val()==""){
			$("#mname").show();
			flag=false;
		}else {
			$("#mname").hide();
		}
		if($("#effectiveDate").val()==""){
			$("#me").show();
			flag=false;
		}else {
			$("#me").hide();
		}
		
		if($("#expiryDate").val()==""){
			$("#mp").show();
			flag=false;
		}else {
			$("#mp").hide();
		}
		
		if($("#description").val()==""){
			$("#md").show();
			flag=false;
		}else {
			$("#md").hide();
		}
		if($("#picUrl").val()==""){
		if($("#imageFile").val()==""){
			$("#mm").show();
			flag=false;
		}else {
			$("#mm").hide();
		}}
		return flag;
	}
	</script>
</head>
<body>
	<form:form id="form1" method="post" modelAttribute="wxmall" enctype="multipart/form-data">
	<form:hidden path="id"/>
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
										<td width="94%" class="biao">门店设置</td>
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
														<div align="center" class="biao">门店设置</div>
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
															<tr height="40">
																<td width="30%" class="biao">公众帐号名&nbsp;<font color="red">*</font></td>
																<td>																	
																	<form:select style="width: 150px;" path="accountId" items="${accounts}" itemValue="id" itemLabel="name" onchange="changeAccountId();"/>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr height="40">
																<td >商城名称&nbsp;<font color="red">*</font></td>
																<td><form:input path="mallName" />&nbsp;<span id="mname" class="error" style="display:none">商城名称不能为空!</span>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															
															<tr height="40">
																<td >商城有效期&nbsp;<font color="red">*</font></td>
																<td><form:input path="effectiveDate" style="width: 75px;"/>&nbsp;到&nbsp;<form:input path="expiryDate" style="width: 75px;" />
																&nbsp;<span id="me" class="error" style="display:none">商城有效期起不能为空!</span>
																&nbsp;<span id="mp" class="error" style="display:none">商城有效期止不能为空!</span>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr height="40">
																<td >描述&nbsp;<font color="red">*</font></td>
																<td><form:textarea path="description" rows="5" cols="70" />&nbsp;<span id="md" class="error" style="display:none">描述不能为空!</span>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr height="40">
																<td >商城首页图片&nbsp;<font color="red">*</font></td>
																<td><input name="imageFile" id="imageFile" type="file" size="1" accept="image/*"/>&nbsp;<span id="mm" class="error" style="display:none">请选择商城图片!</span>
																</td>
															</tr>
															<tr height="40">
																<td ></td>
																<td><img src="${mimg }/${wxmall.picUrl}"  height="400" width="300">
																<form:hidden path="picUrl"/>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr>
																<td height="50"  colspan="2">
																    <input type="button" class="btn-primary" onclick="save()" value="保存" />															
																															
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

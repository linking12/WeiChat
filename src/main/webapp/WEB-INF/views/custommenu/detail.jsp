<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>
	<script type="text/javascript">
	function goback(){
		$('#form1').attr('action', '${ctx }/custommenu/init?accountId='+$("#accountId").val());
		$('#form1').submit();
	}
	function changeAccount(){
		$('#form1').attr('action', '${ctx }/custommenu/add/'+$("#accountId").val());
		$('#form1').submit();
	}
	function changeEventType(){
		var eventType=$("#eventType").val();
		if("url"==eventType){
			$("#turl").show();
			$("#tmsg").hide();
		}else{
			$("#turl").hide();
			$("#tmsg").show();
		}
	}
	
	$(function(){
		changeEventType();
	});
	
	function chenckparent(){
		var flag=true;
		var parentId=$("#parentId").val();
		if(""!=parentId){
			if(parentId==$("#id").val()){
				$("#eparentId").text("不可以选择当前修改菜单为父菜单！");
				$("#eparentId").show();
				flag=false;
			}else{
				
			}
		}
	}
	
	function dosubmit(){
		var eventType=$("#eventType").val();
		if("url"==eventType){			
			$("#tmsg").remove();
		}else{
			$("#turl").remove();
		}
		$('#form1').attr('action', '${ctx }/custommenu/submit');
		$('#form1').submit();
	}
	</script>
</head>
<body >
	<form:form id="form1" method="POST" modelAttribute="wxCustomMenu">
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
										<td width="94%" class="biao">自定义菜单</td>
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
													<td height="40" >
														<div align="center"><a href="javascript:goback()" class="biao1">菜单列表</a></div>
													</td>
												</tr>
												<tr>
													<td height="40" bgcolor="#e87352">
														<div align="center" class="biao">
															添加菜单
														</div>
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
																<td  height="40" class="biao">公众帐号名<font color="red">*</font></td>
																<td align="left">
																	<form:select style="width: 150px;" path="accountId" items="${accounts}" itemValue="id" itemLabel="name" onchange="changeAccount()"/>
																</td>																
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr>
																<td height="40" class="biao">菜单名<font color="red">*</font></td>
																<td>
																	<form:input path="name"/>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr>
																<td height="40" class="biao">父菜单</td>
																<td>
																	<form:select style="width: 150px;" path="parentId" onchange="chenckparent()">
																		<form:option value="">请选择</form:option>
																		<form:options items="${parentMenus}" itemValue="id" itemLabel="name"/>
																	</form:select>&nbsp;<span id="eparentId" style="display:none"></span>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr>
																<td height="40" class="biao">事件类型</td>
																<td>
																	<form:select style="width: 150px;" path="eventType" items="${eventTypes}" itemValue="key" itemLabel="value" onchange="changeEventType()"/>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tbody id="tmsg">
															<tr>
																<td height="40" class="biao">回复信息</td>
																<td>
																	<form:select style="width: 150px;" path="eventDesc" items="${msgs}" itemValue="id" itemLabel="msgName"/>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															</tbody>														
															
															<tbody id="turl">
															<tr>
																<td height="40" class="biao">超链接URL</td>
																<td>
																	<form:input path="eventDesc"/>
																</td>
															</tr>
															
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															</tbody>
															<tr>
																<td height="50" >
																	<input type="button" id="submitbtn" class="btn-primary" value="提交" onclick="dosubmit()"/>
																</td>
															</tr>
															
														</table>
													</td>
												</tr>
												<tr>
													
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
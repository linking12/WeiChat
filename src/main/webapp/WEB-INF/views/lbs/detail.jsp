<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
	<title>微信公共平台</title>
	<script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>  
	<script type="text/javascript">   
		function loadScript() {  
		  var script = document.createElement("script");  
		  script.src = "http://api.map.baidu.com/api?v=1.4&callback=initbaidu";//此为v1.5版本的引用方式  
		  document.body.appendChild(script);  
		} 
		
		$(function(){
			loadScript();
		});
		
		function changeAccountId(){
			$("#form1").attr("action","${ctx}/lbs/init?accountId="+$("#accountId").val());
			$("#form1").submit();
		}
		function doSave(){
			if(validate()){
			$("#form1").attr("action","${ctx}/lbs/save");
			$("#form1").submit();
			}
		}
		
		function validate(){
			var flag=true;
			if(""==$("#companyName").val()){
				flag=false;
				$("#cmsg").show();
			}else{
				$("#cmsg").hide();
			}
			
			if(""==$("#provinceCode").val()){
				flag=false;
				$("#pmsg").show();
			}else{
				$("#pmsg").hide();
			}
			
			if(""==$("#cityCode").val()){
				flag=false;
				$("#citymsg").show();
			}else{
				$("#citymsg").hide();
			}
			
			if(""==$("#address").val()){
				flag=false;
				$("#addressmsg").show();
			}else{
				$("#addressmsg").hide();
			}
			
			if(""==$("#xPoint").val()||""==$("#xPoint").val()){
				flag=false;
				$("#lmsg").show();
			}else{
				$("#lmsg").hide();
			}
			return flag;
		}
		
		function changeProvince(){
			$("#xPoint").val("");$("#xPoint").val("");
			$.post("${ctx}/lbs/getcity/"+$("#provinceCode").val(), function(lst) {
				$("#cityCode").empty();
				$.each(lst, function(i,city){
					var html="<option ";
					if("${wxlbs.cityCode}"==city.cityCode){
						html+="selected";
					}
					html+="value='"+city.cityCode+"'>"+city.cityName+"</option>"
					$("#cityCode").append(html);
				  });
				changeCity();
			});
		}
		
		function changeCity(){
			$("#xPoint").val("");$("#xPoint").val("");
			$.post("${ctx}/lbs/getarea/"+$("#cityCode").val(), function(lst) {
				$("#areaCode").empty();
				$("#areaCode").append("<option>请选择</option>");
				$.each(lst, function(i,area){      
					$("#areaCode").append("<option value='"+area.areaCode+"'>"+area.areaName+"</option>");
				  });  
			});
		}
		function changeArea(){
			$("#xPoint").val("");$("#xPoint").val("");
		}
		function initbaidu(){
			 var mp = new BMap.Map('map');
			 mp.addControl(new BMap.NavigationControl());
			 mp.enableScrollWheelZoom(); 
			 mp.enableContinuousZoom();
			 mp.addEventListener("click", function(e){    
				  if(confirm("确认选择这个位置？")){
						destPoint = e.point;
						mp.clearOverlays();
						var marker1 = new BMap.Marker(destPoint);  // 创建标注
						mp.addOverlay(marker1);												
						$("#xPoint").val(destPoint.lng);
						$("#yPoint").val(destPoint.lat);
					}
				 });
			 if(""!=$("#xPoint").val()&&""!=$("#xPoint").val()){
				 mp.centerAndZoom(new BMap.Point($("#xPoint").val(), $("#yPoint").val()), 15); 
				 var marker1 = new BMap.Marker(new BMap.Point($("#xPoint").val(), $("#yPoint").val())); // 创建标注				
				 mp.addOverlay(marker1);              // 将标注添加到地图中
				 
			 }else {
				 var location= $("#provinceCode").find("option:selected").text();
				  location+= $("#cityCode").find("option:selected").text();
				 mp.centerAndZoom(location, 15); 
				 if($("#address").val()!=""){
				 	var local = new BMap.LocalSearch(mp, {  renderOptions: { map: mp}});    
					 if(""!=$("#areaCode").val())
					 location+=$("#areaCode").find("option:selected").text();
					 local.search(location+$("#address").val()); }
				 }
		}
	</script>
</head>
<body>
	<form:form id="form1" method="post" modelAttribute="wxlbs">
	<form:hidden path="id"/>
	<form:hidden path="xPoint" id="xPoint"/>
	<form:hidden path="yPoint" id="yPoint"/>
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
										<td width="94%" class="biao">LBS设置</td>
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
														<div align="center" class="biao">LBS设置</div>
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
																<td >商家名称&nbsp;<font color="red">*</font></td>
																<td><form:input path="companyName"/><span id="cmsg" style="display:none" class="error">商家名称不能为空!</span>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr height="40">
																<td >商家所在地区</td>
																<td >
																<form:select style="width: 150px;" path="provinceCode" items="${provinceList}" itemValue="provinceCode" itemLabel="provinceName" onchange="changeProvince();"/>
																&nbsp;<form:select style="width: 150px;" path="cityCode" id="cityCode" items="${cityList}" itemValue="cityCode" itemLabel="cityName" onchange="changeCity();"/>
																
																&nbsp;<form:select style="width: 150px;" path="areaCode" id="areaCode" onchange="changeArea();">
																<form:option value="">请选择</form:option>
																<form:options items="${areaList}" itemValue="areaCode" itemLabel="areaName" />
																</form:select>
																
																<span id="pmsg" style="display:none" class="error">请选择省份!</span>
																<span id="citymsg" style="display:none" class="error">请选择城市!</span>
																
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr height="40">
																<td >地址&nbsp;<font color="red">*</font></td>
																<td><form:input path="address" style="width: 300px;"/>&nbsp;&nbsp;<input type="button" class="btn-primary" value="定位" onclick="changeArea();initbaidu()">
																	<span id="addressmsg" style="display:none" class="error">请输入地址!</span>
																</td>
															</tr>
															<tr  height="40">
																<td colspan="2"><span style="font-size:10">输入地址后，点击“定位”按钮可以在地图上定位。（如果输入地址后无法定位，请在地图上直接点击选择地点）</span><br><span id="lmsg" style="display:none" class="error">请在地图上选择地址!</span>
																</td>
															</tr>
															<tr>															
																<td colspan="2"> <div id="map" style="width:500px;height:320px"></div> </span>
																</td>
															</tr>
															<tr>
																<td colspan="2"><div align="center"><img src="${images}/xian.jpg" width="800" height="1" /></div></td>
															</tr>
															<tr>
																<td height="50"  colspan="2">
																	<input type="button" id="submitbtn" class="btn-primary" onclick="doSave()" value="保存">
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

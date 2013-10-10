<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="js" value="${ctx}/index/js" />
<c:set var="css" value="${ctx}/index/css" />
<c:set var="images" value="${ctx}/index/images" />

<script type="text/JavaScript">
	function MM_swapImgRestore() { //v3.0
		var i, x, a = document.MM_sr;
		for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
			x.src = x.oSrc;
	}

	function MM_preloadImages() { //v3.0
		var d = document;
		if (d.images) {
			if (!d.MM_p)
				d.MM_p = new Array();
			var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
			for (i = 0; i < a.length; i++)
				if (a[i].indexOf("#") != 0) {
					d.MM_p[j] = new Image;
					d.MM_p[j++].src = a[i];
				}
		}
	}

	function MM_findObj(n, d) { //v4.01
		var p, i, x;
		if (!d)
			d = document;
		if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
			d = parent.frames[n.substring(p + 1)].document;
			n = n.substring(0, p);
		}
		if (!(x = d[n]) && d.all)
			x = d.all[n];
		for (i = 0; !x && i < d.forms.length; i++)
			x = d.forms[i][n];
		for (i = 0; !x && d.layers && i < d.layers.length; i++)
			x = MM_findObj(n, d.layers[i].document);
		if (!x && d.getElementById)
			x = d.getElementById(n);
		return x;
	}

	function MM_swapImage() { //v3.0
		var i, j = 0, x, a = MM_swapImage.arguments;
		document.MM_sr = new Array;
		for (i = 0; i < (a.length - 2); i += 3)
			if ((x = MM_findObj(a[i])) != null) {
				document.MM_sr[j++] = x;
				if (!x.oSrc)
					x.oSrc = x.src;
				x.src = a[i + 2];
			}
	}
</script>
<body
	onload="MM_preloadImages('${images}/an_1_1.png','${images}/an_2_1.png','${images}/an_3_1.png','${images}/an_4_1.png','${images}/an_5_1.png')">
<table width="967" border="0" align="center" cellpadding="0"
		cellspacing="0">
	<tr>
		<td><a href="${ctx }/index" onmouseout="MM_swapImgRestore()"
			onmouseover="MM_swapImage('Image2','','${images}/an_1_1.png',1)"><img
				src="${images}/an_1.png" name="Image2" width="194" height="84"
				border="0" id="Image2" /></a></td>
		<td><a href="${ctx }/account/list" onmouseout="MM_swapImgRestore()"
			onmouseover="MM_swapImage('Image3','','${images}/an_2_1.png',1)"><img
				src="${images}/an_2.png" name="Image3" width="194" height="84"
				border="0" id="Image3" /></a></td>
		<td><a href="${ctx }/account/list" onmouseout="MM_swapImgRestore()"
			onmouseover="MM_swapImage('Image4','','${images}/an_3_1.png',1)"><img
				src="${images}/an_3.png" name="Image4" width="194" height="84"
				border="0" id="Image4" /></a></td>
		<td><a href="${ctx }/account/list" onmouseout="MM_swapImgRestore()"
			onmouseover="MM_swapImage('Image5','','${images}/an_4_1.png',1)"><img
				src="${images}/an_4.png" name="Image5" width="194" height="84"
				border="0" id="Image5" /></a></td>
		<td><a href="${ctx }/j_spring_security_logout" onmouseout="MM_swapImgRestore()"
			onmouseover="MM_swapImage('Image6','','${images}/an_5_1.png',1)"><img
				src="${images}/an_5.png" name="Image6" width="194" height="84"
				border="0" id="Image6" /></a></td>
	</tr>
	<tr><td height="10"></td></tr>
	<tr>
		<td colspan="5">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="30" class="biao3" >fexue2000</td>
					</tr>
					<tr>
						<td height="20" class="biao1" >您当前的用户级别：普通用户</td>
					</tr>
					<tr>
						<td height="20" class="biao1" >会员期限：永久免费</td>
					</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10">&nbsp;</td>
	</tr>
</table>
</body>

<!-- 
<table width="960" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="118" height="37"><div align="center">
				<a href="${ctx }/account/list" class="c">公众帐号管理</a>
			</div></td>
		<td width="97"><div align="center">
				<a href="${ctx }/replymsg/init" class="c">自动回复配置</a>
			</div></td>
		<td width="89"><div align="center">
				<a href="${ctx }/message/init" class="c">信息内容管理</a>
			</div></td>
		<td width="127"><div align="center">
				<a href="${ctx }/game/init" class="c">应用管理</a>
			</div></td>
		<td width="79"><div align="center">
				<a href="${ctx }/user/init" class="c">注册用户管理</a>
			</div></td>
		<td width="112"><div align="center"></div></td>
		<td width="124">&nbsp;</td>
		<td width="214">&nbsp;</td>
	</tr>
</table> -->
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="w_logo">&nbsp;</div>
<div class="nav_bar">
	<div class="nav_bar_l">&nbsp;</div>
    <div class="nav_bar_c">
    	<a href="${ctx }/index"><span class="ico1">&nbsp;</span></a>
        <a href="${ctx }/account/list"><span class="ico2">&nbsp;</span></a>
        <a href="${ctx }/account/list"><span class="ico3">&nbsp;</span></a>
        <a href="${ctx }/account/list"><span class="ico4">&nbsp;</span></a>
        <a href="${ctx }/j_spring_security_logout"><span class="ico5" style="border:0;">&nbsp;</span></a>
    </div>
    <div class="nav_bar_r">&nbsp;</div>
</div>
<div class="user_box">
   	<em><%=net.chat.utils.AppContext.getUsername()%></em><br />
	您当前的用户级别：普通用户<br />   
	会员期限：永久免费 
</div>
        <!-- 
<tr>
    <td><img src="${images}/lo.png" width="955" height="208" id="logo_div" /></td>
</tr>
<tr>
    <td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" id="nav_tb">
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
		</table>
	</td>
</tr>
<tr><td height="30"></td></tr>
	<tr>
		<td colspan="5">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="30" class="biao3" ><%=net.chat.utils.AppContext.getUsername()%></td>
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
		<td height="30">&nbsp;</td>
	</tr> -->

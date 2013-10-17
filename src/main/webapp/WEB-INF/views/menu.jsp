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

<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<div data-role="header">
        <div data-role="navbar" class="topnav_bg">
            <ul>
                <li><a href="javascript:if(window.location.href.indexOf('/mall/index')==-1 )history.go(-1);" class="back" rel="external">&nbsp;</a></li>
                <li><a href="${ctx }/mall/index/<%=session.getAttribute("accountId") %>" class="w_logo" rel="external">&nbsp;</a></li>
                <li><a href="${ctx }/order/myorder" class="home" rel="external">&nbsp;</a></li>
                <li><a href="${ctx }/cart/show" class="cart" rel="external">&nbsp;</a></li>   
            </ul>
        </div>
</div>
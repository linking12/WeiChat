<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<div data-role="header">
        <div data-role="navbar" class="topnav_bg">
            <ul>
                <li><a href="${ctx }/mall/index/<%=session.getAttribute("accountId") %>" class="back" rel="external">&nbsp;</a></li>
                <li><a href="#" class="w_logo" rel="external">&nbsp;</a></li>
                <li><a href="${ctx }/mall/myaccount" class="home" rel="external">&nbsp;</a></li>
                <li><a href="${ctx }/mall/cart" class="cart" rel="external">&nbsp;</a></li>   
            </ul>
        </div>
</div>
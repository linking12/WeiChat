<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<div data-theme="c" data-role="footer" data-position="fixed" id="fot">
   <div class="bot">
   	<a href="${ctx }/mall/index/<%=session.getAttribute("accountId") %>" rel="external">商户首页 </a>
       <a href="#">会员中心 </a>
       <a href="#" style="border:0;">品牌介绍 </a>
   </div>
</div>
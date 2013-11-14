
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>亿点</title>
</head>
<body>
<div class="b_con">
	<div class="by_box">
    	<%@include file="menu.jsp"%>
        <div class="link_box">
        	<span>
            	<em>新手任务</em>
                <i></i>
            </span>
            <a href="${ctx}/message/init"><span>
            	<em>首次关注</em>
                <i class="ico2"></i>
            </span></a>
            <a href="${ctx}/cmd/init">
            <span>
            	<em>智能客服</em>
                <i class="ico3"></i>
            </span>
            </a>
             <a href="${ctx}/content/init">
            <span style="margin:0;">
            	<em>素材管理</em>
                <i class="ico4"></i>
            </span></a>
             <a href="${ctx}/replymsg/init">
            <span>
            	<em>关键字回复</em>
                <i class="ico5"></i>
            </span>
            </a>
             <a href="${ctx}/custommenu/init">
            <span>
            	<em>自定义菜单</em>
                <i class="ico6"></i>
            </span>
            </a>
            <span>
            	<em>内容编辑</em>
                <i class="ico7"></i>
            </span>
            <span style="margin:0;">
            	<em>LBS设置</em>
                <i class="ico8"></i>
            </span>
        </div>
        <div class="link_box1">
        	<span>
            	<em>优惠券</em>
                <i></i>
            </span>
            <a href="${ctx}/ggl/init">
            <span>
            	<em>刮刮卡</em>
                <i class="ico2"></i>
            </span></a>
            <span>
            	<em>幸运大转盘</em>
                <i class="ico3"></i>
            </span>
            <span style="margin:0;">
            	<em>一站到底</em>
                <i class="ico4"></i>
            </span>
            <span>
            	<em>幸运机</em>
                <i class="ico5"></i>
            </span>
            <span>
            	<em>微吧</em>
                <i class="ico6"></i>
            </span>
        </div>
        <div class="link_box2">
        	<span>
            	<em>微官网</em>
                <i></i>
            </span>
            <span>
            	<em>微会员卡</em>
                <i class="ico2"></i>
            </span>
            <span>
            	<em>微团购</em>
                <i class="ico3"></i>
            </span>
            <span style="margin:0;">
            	<em>微调研</em>
                <i class="ico4"></i>
            </span>
            <span>
            	<em>微投票</em>
                <i class="ico5"></i>
            </span>
            <span>
            	<em>门店设置</em>
                <i class="ico6"></i>
            </span>
            <span>
            	<em>在线订单管理</em>
                <i class="ico7"></i>
            </span>
        </div>
       	<%@include file="bottom.jsp"%>
    </div>
</div>
</body>
</html>

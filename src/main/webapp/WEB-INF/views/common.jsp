<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="js" value="${ctx}/js"/>
<c:set var="css" value="${ctx}/css"/>
<c:set var="images" value="${ctx}/images"/>
<link href="${css }/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${js}/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${js}/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${js}/jquery/weichat.js"></script>
<style type="text/css">
	body {
		background-image: url(${images}/bei.jpg);
	}
</style>
<script type="text/JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->

$(document).ready(function(){
	MM_preloadImages('${images}/an_1_1.png','${images}/an_2_1.png','${images}/an_3_1.png','${images}/an_4_1.png','${images}/an_5_1.png');
    var menuYloc = $("#nav_tb").offset().top; 
    $(window).scroll(function (){ 
        var offsetTop =208 + $(window).scrollTop(); 
		if(offsetTop > 408){
			 $("#nav_tb").css({top: $(window).scrollTop()});
			}
			else{
				$("#nav_tb").css({top: 208+"px"});
				}

    }); 
}); 

</script>

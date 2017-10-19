<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.sys.pojo.TsEnterprise"%>
<%@page import="com.hontek.sys.pojo.TsUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


String ischarge = "2";//是否收费用户，1是；2否
Object obj = session.getAttribute("enterprse");
Object objUser = session.getAttribute("tsUser");

if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}else{
	TsEnterprise enterprise = (TsEnterprise)obj;
	if(enterprise!=null){
		ischarge = enterprise.getIscharge()==null?"2":enterprise.getIscharge();
	}
}

TsUser tsUser = null;
if(objUser!=null){
	tsUser = (TsUser)objUser;
}


EntStyle companyStyle = session.getAttribute("companyStyle")==null?null:(EntStyle)session.getAttribute("companyStyle");
String styleFile = "blue";
String logo = basePath + "company/images/logo.png";
String bottomInfo = "版权所有 广州薪火网络科技有限公司";
if(companyStyle!=null){
	if(companyStyle.getLogoImage()!=null && !"".equals(companyStyle.getLogoImage())){
		logo = "/nytsyFiles/entstyle/"+companyStyle.getLogoImage();
	}
	if(companyStyle.getBottomInfo()!=null && !"".equals(companyStyle.getBottomInfo())){
		bottomInfo = companyStyle.getBottomInfo();
	}
	if(companyStyle.getScCss()!=null && !"".equals(companyStyle.getScCss())){
		styleFile = companyStyle.getScCss();
	}
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><%=session.getAttribute("systemName") %></title>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<style type="text/css">
html, 
body, 
#box { 
height:100%; 
} 
body { 
text-align:center; 
} 
#box { 
text-align:left; 
display:table; 
width:100%; 
margin:0 auto; 
position:relative; 
} 
#box > div { 
display:table-row; 
} 
 
#main #wrap { 
display:table-cell; 
vertical-align:middle; 
} 
#left{ position:absolute; top:88px; left:0; width:187px; height:100%;overflow: hidden;background: #f0f9fd none repeat scroll 0 0;border-right: 1px solid #b7d5df;}
#right{ margin-left:187px; height:100%;overflow: hidden}
</style>

<script type="text/javascript">
$(function(){
	
	var height = document.documentElement.clientHeight;
	$('#rightFrame').attr('height',height-118);
	$('#left').css('height',height-118);

	$('.menuson li').bind('click',function(){
		$('.menuson li').removeClass('active');
		$(this).addClass('active');
		layer.msg('页面加载中', {icon: 16,time: 20000});
	});
	
});
window.onresize = function(){
	var height = document.documentElement.clientHeight;
	$('#rightFrame').attr('height',height-118);
	$('#left').css('height',height-118);
}

</script>
</head>

<body>
<div id="box"> 
	<jsp:include page="top.jsp"></jsp:include>
	<div id="main"> 
		<div id="left">
			<div style="width: 187px;">
		<div style="background:#f0f9fd;">
		
		    <div class="lefttop"><span></span>企业信息</div>
    
		    <dl class="leftmenu">
		    <dd>
		    	<ul class="menuson">
		        <li class="active"><cite></cite><a href="qyxx.jsp" target="rightFrame">基本信息</a><i></i></li>
		        <li><cite></cite><a href="zzxx.jsp" target="rightFrame">资质证书</a><i></i></li>
		      <!--	<li><cite></cite><a href="qykh.jsp" target="rightFrame">企业考核</a><i></i></li> --> 
		        <%if(ischarge.equals("1")){ %>
		        <li><cite></cite><a href="companyStyle.jsp" target="rightFrame">风格设置</a><i></i></li>
		        <%} %>
		        </ul>    
		    </dd>
		        
		    
		    </dl>
		</div>
	</div>
		</div>
		<div id="right">
		<iframe src="qyxx.jsp" name="rightFrame" width="100%" height="100%" id="rightFrame" title="rightFrame"></iframe>
		</div>
	</div> 
	<div id="footer" >
		<div class="footer">
	    <span><%=bottomInfo %></span>
	    </div>
	</div> 
</div> 
</body>
</html>
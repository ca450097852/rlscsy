<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String entCode = request.getParameter("entCode");
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
	<link rel="stylesheet" href="<%= basePath%>static/css/mobile/web5.css" >
	<link rel="stylesheet" href="<%= basePath%>static/css/mobile/main14.css" >
	<script src="<%= basePath%>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script src="<%= basePath%>static/js/hontek/mobile/mobile.js"　type="text/javascript"></script>

	<script src="<%= basePath%>static/js/hontek/mobile/index.js"></script>
	
	<link rel="stylesheet" href="<%= basePath%>static/js/royalsider/css/reset.css" >
	<link rel="stylesheet" href="<%= basePath%>static/js/royalsider/css/royalslider.css" >
	<link rel="stylesheet" href="<%= basePath%>static/js/royalsider/css/skin-white.css" >
	
	<script src="<%= basePath%>static/js/royalsider/js/jquery.royalslider.min.js"　type="text/javascript"></script>

	<style type="text/css">
		.comInfo td {
			padding:0 10px;line-height: 30px;
		}
		
		.news_list{margin:10px;background:#fff;border:1px solid #ccc;border-radius:5px;padding:10px;}
		.news_list ul{margin:0;padding:0;}
		.news_list ul li{list-style:inside;line-height:25px;border-bottom:1px dashed #ccc;padding:10px;height:30px;vertical-align: middle;}
		.news_list ul li a{color: black;}
	</style>
</head>

<body>
<header class="top"> 
  	<div class="t-logo"> <img src="<%= basePath%>static/image/mobile/logo.png"> </div>
</header>
<section class="center" id="mainCenter">


</section>
<DIV style="height: 50px;"></DIV>
<footer>
   <nav class="footerNav" style="margin:0 auto;">
       <a id="a1" class="active" href="javascript:void();"><img src="<%= basePath%>static/image/mobile/t-icon.png">企业信息</a>
       <a id="a2" href="javascript:void();"><img src="<%= basePath%>static/image/mobile/r-icon.png">资质信息</a>
       <a id="a3" href="javascript:void();"><img src="<%= basePath%>static/image/mobile/n-icon.png">生产信息</a>
       <a id="a4" href="javascript:void();"><img src="<%= basePath%>static/image/mobile/s-icon.png">监管信息</a>
   </nav>
</footer>
<input type="hidden" id="entCode" value="<%=entCode %>">
<!-- 企业信息 -->
<div id="div_a1" style="display:none">
<table class="comInfo" width="97%" border="0" cellspacing="1" style="margin:5px;">
	<tr>
		<td width="90" style="padding:0 10px;">企业名称：</td>
		<td id="name"></td>
	</tr>
	<tr>
		<td>企业简称：</td>
		<td id="simpleName"></td>
	</tr>
	<tr>
		<td>企业法人：</td>
		<td id="legalPerson"></td>
	</tr>
	<tr>
		<td>注册地址：</td>
		<td id="regAddr"></td>
	</tr>
	<tr>
		<td>经营地址：</td>
		<td id="manageAddr"></td>
	</tr>
	<tr>
		<td>联系电话：</td>
		<td id="tel"></td>
	</tr>
	<tr>
		<td>邮政编码：</td>
		<td id="postCode"></td>
	</tr>
	<tr>
		<td>企业网址：</td>
		<td id="domName"></td>
	</tr>
	<tr>
		<td>电子邮箱：</td>
		<td id="email"></td>
	</tr>
	<tr>
		<td  valign="top">企业介绍：</td>
		<td id="intro"></td>
	</tr>
</table>
</div>


<div id="div_a2" style="display:none">
	<DIV id=header>
		<DIV id=header-inner>
			<!-- <div id="btn_list" class="btn_bar"><a href="javascript:;" id="typeName">资质文件</a></div> -->
			<div style="position: relative; width: 100%; margin: 0 auto;">
				<div id="allgirl">
					<!-- 
				    <div class="contentbox">
				    	<ul id="ul_type">
				        <li id="nav1"><span>1</span>资质文件</li>
				        <li id="nav2"><span>2</span>营业执照</li>
				
				        <li id="nav3"><span>3</span>企业荣誉</li>
				        <li id="nav4"><span>4</span>其他证书</li>
				               </ul>
				        <script type="text/javascript">
				        document.getElementById('nav1').className = 'on';
				        </script>
				        <div class="allbtn">
				        <a href="javascript:;" id="btn_no">取消</a>
				        <a href="javascript:zzInfo();" id="btn_yes">确定</a>
				        </div>
				    </div>
				     -->
				</div>
			</div>
			<DIV class=clear></DIV>
		</DIV>
	</DIV>
	<DIV class="listmain">
		<ul id="container">

		</ul>
	</DIV>
</div>

<div id="div_a3" style="display:none">
	
</div>

<div id="div_a4" style="display:none">
	
</div>

<input type="hidden" id="h_appType" value="2">
<input type="hidden" id="total" >
<input type="hidden" id="pages" >

</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String elementDir = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/element/";
String productionDir = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/production/";
String proimgDir = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/proimg/";
String zizhiDir = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/zizhi/";

String dimenno = request.getParameter("code");

%>


<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title><%=session.getAttribute("systemName") %></title>

	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<meta name="Keywords" content="广州市肉类蔬菜流通追溯管理平台">
	<meta name="description" content="广州市肉类蔬菜流通追溯管理平台">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>weixin/tabs/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>weixin/tabs/css/tabs.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>weixin/tabs/css/tabstyles.css" />
	
	<link href="<%=basePath%>weixin/tabs/css/trace.css" rel="stylesheet" type="text/css">
	
    <script src="<%=basePath%>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
    <script src="<%=basePath%>static/js/layer-v3.0.1/layer.js" type="text/javascript"></script>  
    
    
    <link rel="stylesheet" href="<%=basePath%>weixin/css/weui.css">
	<link rel="stylesheet" href="<%=basePath%>weixin/css/weui.min.css">
	<link rel="stylesheet" href="<%=basePath%>weixin/css/jquery-weui.css">
	<link rel="stylesheet" href="<%=basePath%>weixin/css/demos.css">
	
	<script src="<%=basePath%>weixin/js/traceCom_1.js" type="text/javascript"></script> 
	
	<style type="text/css">
		.btn-sure
		{  margin-top:32px; height:40px;
		    text-decoration:none;  
		    background:#5bb75b;  
		    color:#f2f2f2;  
		      
		    padding: 7px 27px 10px 27px;  
		    font-size:16px;  
		    font-family: 微软雅黑,宋体,Arial,Helvetica,Verdana,sans-serif;  
		    font-weight:bold;  
		    border-radius:3px;  
		      
		    -webkit-transition:all linear 0.30s;  
		    -moz-transition:all linear 0.30s;  
		    transition:all linear 0.30s;  
		      
		 }  
		.subnav{
			margin:10px 5px 0 5px;
			padding:2px 15px;
			border:1px solid #ccc;
			float:left;
			text-align:center;
			border-radius:8px;
			font-size:14px;
		}
		.subnav:hover{
			background:#ccc;
		}
		.p_zizhi span {float: none !important;display: block !important;margin-left: 10px;} 
	</style>
	 
  </head>
  
<body>

<input type="hidden" id="elementDir" value="<%=elementDir%>">
<input type="hidden" id="productionDir" value="<%=productionDir%>">
<input type="hidden" id="proimgDir" value="<%=proimgDir%>">
<input type="hidden" id="zizhiDir" value="<%=zizhiDir%>">
<input type="hidden" id="basePath" value="<%=basePath%>">
<input type="hidden" id="dimenno" value="<%=dimenno%>">


<div class="weui-tab">
      <div class="weui-navbar" id="head_html">
      	<a class="weui-navbar__item weui-bar__item--on" href="#section-circlefill-1">企业信息</a>
      	<a class="weui-navbar__item" href="#section-circlefill-2">资质信息</a>
      	<a class="weui-navbar__item" href="#section-circlefill-3">档案信息</a>
      </div>
      <div class="weui-tab__bd" id="section_html">
      	<div id="section-circlefill-1" class="weui-tab__bd-item weui-tab__bd-item--active"></div>
      	<div id="section-circlefill-2" class="weui-tab__bd-item"></div>
      	<div id="section-circlefill-3" class="weui-tab__bd-item"><div style="border-bottom:#ccc 1px solid; padding:0 15px 10px 15px" id="recordDiv"></div></div>
      </div>
    </div>



</body>
</html>

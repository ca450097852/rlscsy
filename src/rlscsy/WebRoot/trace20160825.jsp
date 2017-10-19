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

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta charset="utf-8">
	<title><%=session.getAttribute("systemName") %></title>
	<meta content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0" name="viewport">
	
	
	<meta name="MobileOptimized" content="320">   
	<link href="<%=basePath%>weixin/css/trace.css" rel="stylesheet" type="text/css">
	
    <script src="<%=basePath%>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
    <script src="<%=basePath%>static/js/layer/layer.js" type="text/javascript"></script>  
    <script src="<%=basePath%>weixin/js/traceCom.js" type="text/javascript"></script>  
  </head>
  
<body>

<input type="hidden" id="elementDir" value="<%=elementDir%>">
<input type="hidden" id="productionDir" value="<%=productionDir%>">
<input type="hidden" id="proimgDir" value="<%=proimgDir%>">
<input type="hidden" id="zizhiDir" value="<%=zizhiDir%>">
<input type="hidden" id="basePath" value="<%=basePath%>">
<input type="hidden" id="dimenno" value="<%=dimenno%>">

<div class="wrap-container">
   <div id="company_info">
   <!--  
    <h3 class="htit htit-fregray">生产企业信息</h3>
    <ul class="info-list m-btm" id="enterpriseUl">
        
    </ul>
    
    	
	<div class="cutline"></div>
    <h3 class="htit htit-fregray">资质荣誉信息</h3>
    <ul class="info-list m-btm" id="rongyuUl">

    </ul>
    -->
    </div>

</div>

</body>
</html>

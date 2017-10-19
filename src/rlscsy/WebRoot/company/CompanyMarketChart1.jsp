<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.comm.util.DateUtil"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/";

Object obj = session.getAttribute("loginUser");
String isAdmin = "";
String entIdStr = "";
if(obj!=null){
	isAdmin  = ((LoginUser)obj).getAdmin();
	entIdStr = ((LoginUser)obj).getEntId()+"";
}

EntStyle companyStyle = session.getAttribute("companyStyle")==null?null:(EntStyle)session.getAttribute("companyStyle");
String styleFile = "blue";
if(companyStyle!=null){
	if(companyStyle.getScCss()!=null && !"".equals(companyStyle.getScCss())){
		styleFile = companyStyle.getScCss();
	}
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>企业管理 - 企业经营者信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
<script src="<%=basePath %>static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
    
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />


<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>

<script type="text/javascript" src="<%=basePath %>static/js/xheditor-1.2.1/xheditor-1.2.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/xheditor-1.2.1/xheditor_lang/zh-cn.js"></script>

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />		
	<script src="<%=basePath %>company/js/CompanyMarketChart.js" type="text/javascript"></script>
	 <link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	
  </head>
  <body>
  <div id="chimgDiv" style="display:none;">
  	
  </div>

			  <div>
  				&nbsp;&nbsp;图表类型: <select class="easyui-combobox" style="width:120px" id="chartType1">
  										<option value="0" selected="selected">柱形图</option>
									  	<option value="1">线形图</option>
									  	<option value="2">饼图</option> 										
  									 </select>
				&nbsp;&nbsp;时间从: <input class="easyui-datebox" style="width:120px" id="startDate1">
				&nbsp;&nbsp;到: <input class="easyui-datebox" style="width:120px" id="endDate1">
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find1(0)">统计</a>
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find1(1);">统计本月</a>
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find1(2);">统计本年</a>
				
		</div>
				<div style="padding: 20px" id="companyTable1">
			
		
		</div> 
		
  		 <table id="marketChart1"></table>  
		
			
	 
	   
	
  

  </body>
 
</html>

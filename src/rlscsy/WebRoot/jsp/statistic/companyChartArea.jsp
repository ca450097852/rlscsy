<%@page import="com.hontek.sys.pojo.TsEnterprise"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
TsEnterprise enterprise = null;
Object o = session.getAttribute("enterprse");
if(o!=null){
	enterprise = (TsEnterprise)o;
}else{
	out.print("您是超级管理员，无法使用该菜单!");
	return;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>统计管理  - 企业统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="${basePath}<%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>	
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	 <script src="${basePath}static/js/hontek/statistic/companyChart.Areajs" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	    
    
  </head>
  <body style="padding: 0px" >
  	<div id="p" class="easyui-panel" title="统计企业数量" data-options="iconCls:'icon-chart_curve',fit:true"> 
  			<div>
  				&nbsp;&nbsp;图表类型: <select class="easyui-combobox" style="width:120px" id="chartType">
  										<option value="0" selected="selected">柱形图</option>
									  	<option value="1">线形图</option>
									  	<option value="2">饼图</option> 										
  									 </select>
				&nbsp;&nbsp;时间从: <input class="easyui-datebox" style="width:120px" id="startDate">
				&nbsp;&nbsp;到: <input class="easyui-datebox" style="width:120px" id="endDate">
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find(0)">统计</a>
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find(1);">统计本月</a>
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find(2);">统计本年</a>
				
		</div>
		
		<div style="padding: 20px" id="companyTable">
			
		
		</div>
		
  		 <table id="companyChart"></table>  
  	
  	</div>
	   		   
  </body>
</html>

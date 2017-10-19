<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String rsts = request.getParameter("rsts");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>档案审核</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script type="text/javascript" src="static/js/jquery.cookie.js"></script>
	<link href="static/js/easyui-1.3.4/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>	
	<script src="static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	 <script src="static/js/hontek/company/recordAudit.js" type="text/javascript"></script>
	<script src="static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
    
    
  </head>
  <body style="padding: 0px" >
  
	 <table id="zizhiAuditRecordDataGrid"></table>  
	
	<div id="showRecord" class="easyui-window" title="查看档案信息" data-options="iconCls:'icon-table',modal:true,closed:true" style="width:1050px;height:450;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<iframe id="recordframe" style="width:100%;height:100%;" frameborder="0"></iframe>
			</div>
			<div data-options="region:'south',border:false" style="text-align:right;padding:9px;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:updateAuditState(1)" >审核通过</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="javascript:updateAuditState(2)" >审核不通过</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#showRecord').window('close')" >关闭</a>
			</div>
		</div>
		
	</div>	
	
	
  </body>
</html>

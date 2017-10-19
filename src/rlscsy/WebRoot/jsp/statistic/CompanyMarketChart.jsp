<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.comm.util.DateUtil"%>
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
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js" type="text/javascript"></script>		
	<script src="${basePath}static/js/easyui-1.3.4/datagrid-detailview.js" type="text/javascript"></script>	
	<script src="${basePath}static/js/hontek/comm/hontek.js" type="text/javascript"></script>	
	<script src="${basePath}static/js/hontek/statistic/CompanyMarketChart.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
	<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
	
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/browser.js"></script>
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	
	
	<style type="text/css">
		#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		
	</style>
	
  </head>
  <body>
  <div id="chimgDiv" style="display:none;">
  	
  </div>
  <input type="hidden" id="sessionId" value="<%=session.getId() %>"> 	 
  <input type="hidden" id="h_path" value="<%=path %>">
  <input type="hidden" id="filePath" value="<%=filePath %>">
  <input type="hidden" id="entIdStr" value="<%=entIdStr %>">
  
      <div class="easyui-layout" data-options="fit:true">   
   		<div data-options="region:'west',title:'企业名称',split:true" style="width:250px;">
    		<ul id="tt"></ul>  

    	</div>   
    	<input type="text" id="comId">
	    <div   id ="tab" data-options="region:'center',title:'统计企业管理'">
		  <!-- 列表 -->
		   <table id="list_companyNode"></table>  
		   
		    <!-- list_enttype 工具栏 -->
		    <div id="tb" style="height:auto" title="统计企业销售量">
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
		
  		 <table id="marketChart"></table>  
			</div>
			
	   
			
	    </div>   
	   
	</div>  
  

  </body>
 
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>统计管理  - 溯源统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	
	<script type="text/javascript">
		$(function() {
			var datas = {};	
			$.ajax({
			   	url:'scanCount_findScanCount.action',
			   	data: datas,
			   	type:'post',
			   	dataType:'text',
			   	success : function(result) {
					$("#productChart").html(result);
				}
			});
		});
	</script>
	    
    
  </head>
  <body style="padding: 0px" >
  	<div id="p" class="easyui-panel" title="溯源统计" data-options="iconCls:'icon-chart_curve',fit:true"> 
		
  		 <table id="productChart"></talbe>  
  	
  	</div>
	   		   
  </body>
</html>

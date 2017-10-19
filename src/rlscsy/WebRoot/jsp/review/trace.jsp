<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>溯源信息列表</title>
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
	<script src="${basePath}static/js/hontek/review/trace.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	       
  </head>
  <body style="padding: 0px" >
  
	 <table id="traceDataGrid"></table>  
	 <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendTrace()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateTrace()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeTrace()">删除</a>
	 </div>
	 <div id="h_value">
	 		<input type="hidden" name="trace.tid"/>
	 </div>
	 
   	<div id="traceDlg" class="easyui-window" data-options="iconCls:'icon-save',closed:true"  style="width:530px;top: 10px">
		<div style="width: 100%">
	   		<form id="traceForm" method="post">
	   			<div id="in_value"></div>
	   			<input type="hidden" name="trace.sysCode"/>
	   			<table style="width: 480px;padding: 0px;" align="center">
	   				<tr>
	   					<td>产品:</td>   											  	   					
	   					<td> <input name="trace.proId" id="proId" style="width:400px;"></td>
	   				</tr>
   				
	   				<tr>
	   					<td>标题:</td>
	   					<td><input name="trace.title" class="easyui-validatebox" data-options="required:true" style="width: 400px"></td>   										
	   				</tr>
	   				<tr>
	   					<td>手机扫描链接地址:</td>
	   					<td><input name="trace.purl" class="easyui-validatebox" type="text" data-options="required:true" style="width: 400px"></td>   										
	   				</tr>
	   				<tr>
	   					<td>链接地址:</td>
	   					<td><input name="trace.url" class="easyui-validatebox" type="text" data-options="required:true" style="width: 400px"></td>   										
	   				</tr>
	   				<tr>
	   					<td>内容:</td>
	   					<td>
	   						<textarea rows="4" cols="" id="ct" name="trace.content" style="width: 400px"></textarea>
	   					</td>   										
	   				</tr>
	   					   				
	   				
	   				<tr>
	   					<td>说明:</td>
	   					<td>
	   					<textarea rows="4" cols="" id="re" name="trace.remark" style="width: 400px"></textarea>
	   					</td>   										
	   				</tr>
	   					  
	   				<tr>
	   					<td colspan="2" align="center" style="padding: 5">
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitForm()">提交</a>
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="$('#traceDlg').window('close')">取消</a>
	   					</td>
	   				</tr>
	   			</table>
	   		</form>
	   	</div>
	</div>  
  </body>
</html>

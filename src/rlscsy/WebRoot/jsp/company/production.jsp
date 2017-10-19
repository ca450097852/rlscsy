<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>企业管理  - 生产信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>	
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	 <script src="${basePath}static/js/hontek/company/production.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
	<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
    <link rel="stylesheet" href="uploadify/uploadify.css" type="text/css"></link>
    
    
  </head>
  <body style="padding: 0px" >
  
	 <table id="productionDataGrid"></table>  
	 
	  <input type="hidden" id="jsessionid" value="<%=session.getId() %>"> 	 
	  <input type="hidden" id="h_path" value="<%=path %>">
	  
	 <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendProduction()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateProduction()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeProduction()">删除</a>
	 </div>
	 
	 <div id="h_value">
	 	<input type="hidden" name="production.proId">
	 	<input type="hidden" name="production.entId">
	 	<input type="hidden" name="production.userId">	 	
	 	<input type="hidden" name="production.crttime"> 	
	 </div>
	 
   	<div id="productionDlg" class="easyui-window" title="添加生产信息" data-options="iconCls:'icon-save',closed:true"  style="width:520px;height:320px;top: 10px">
		<div style="width: 100%">
	   		<form id="productionForm" method="post">
	   			<div id="in_value"></div>
	   			<table style="width: 480px;padding: 0px;" align="center">
	   				<tr>
	   					<td>产品情况:</td>
	   					<td><textarea class="easyui-validatebox" name="production.productinfo" data-options="required:true" rows="10" style="width: 400"></textarea></td>
	   				</tr>
   				
	   				<tr>
	   					<td>生产许可证:</td>
	   					<td>
	   					<input name="production.license" class="easyui-validatebox"  style="width: 400px">
   					  </td>   										
	   				</tr>
	   					   				
	   				<tr>
	   					<td colspan="2" align="center" style="padding: 15">
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitForm()">提交</a>
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="$('#productionDlg').window('close')">取消</a>
	   					</td>
	   				</tr>
	   			</table>
	   		</form>
	   	</div>
	</div>
   
   
   <div id="attachWin" class="easyui-window" title="附件管理" data-options="iconCls:'icon-attach',closed:true" style="width: 500px; height: 420px; padding: 10px;top:20px">
		<table>
			<tr>
				<td>
					产品附件:
				</td>
				<td>
					<div id="l_uploadify">
						<input type="file" name="uploadify" id="uploadify" />
					</div>
				</td>
			</tr>
				
				<tr>
					<td colspan="2">
						<div id="fileQueue" style="width: 400px;height: 200;OVERFLOW-Y: auto; OVERFLOW-X:hidden"></div>
					</td>
				</tr>		
				
				
				<tr>
				<td>
					许可证附件:
				</td>
				<td>
					<input type="file" name="uploadify2" id="uploadify2" />
				</td>
			</tr>
				
				<tr>
					<td colspan="2">
						<div id="fileQueue2" style="width: 400px"></div>
					</td>
				</tr>					
		</table>
	</div>
  </body>
</html>

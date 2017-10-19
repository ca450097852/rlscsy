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
    <title>系统管理  - 企业资质证书列表</title>
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
	 <script src="static/js/hontek/company/zizhiAudit.js" type="text/javascript"></script>
	<script src="static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
    
    
  </head>
  <body style="padding: 0px" >
  
	 <table id="zizhiAuditRecordDataGrid"></table>  
	 
	  <input type="hidden" id="jsessionid" value="<%=session.getId() %>"> 	 
	  <input type="hidden" id="h_path" value="<%=path %>">
	  
	 <div id="tb" style="height:auto">
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendZizhi()">添加</a> -->
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="batAudit()">批量通过</a>
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeZizhi()">删除</a> -->
	 </div>
	 
   	<div id="zizhiAuditRecordDlg" class="easyui-window" title="审核资质信息" data-options="iconCls:'icon-save',closed:true"  style="width:500px;height:320px;top: 10px">
		<div style="width: 100%">
	   		<form id="zizhiAuditRecordForm" method="post">
	   			<input type="hidden" name="zizhiAuditRecord.auditId">
	 			<input type="hidden" name="zizhiAuditRecord.entId">
	 			<input type="hidden" name="zizhiAuditRecord.zid">
	 			
	   			<table style="width: 450px;padding: 10px;" align="center">
	   				<tr height="30px">
	   					<td>企业名称:</td>
	   					<td>
	   							<input name="zizhiAuditRecord.entName" class="easyui-validatebox" readonly="readonly" style="width: 300px">
   					  </td>   										
	   				</tr>
	   				<tr height="30px">
	   					<td>证书名称:</td>
	   					<td>
	   							<input name="zizhiAuditRecord.zname" class="easyui-validatebox" readonly="readonly" style="width: 300px">
   					  </td>   										
	   				</tr>
	   				   				
					<tr height="30px">
	   					<td>申请时间:</td>
	   					<td>
	   							 <input name="zizhiAuditRecord.applyTime" class="easyui-validatebox"  readonly="readonly" style="width: 300px">	   						
	   					</td>
	   				</tr>
	   				
	   				<tr height="30px">
	   					<td>申请原因:</td>
	   					<td>
	   					<input name="zizhiAuditRecord.applyCause" class="easyui-validatebox" readonly="readonly" style="width: 300px">
   					  </td>   										
	   				</tr>
	   				
	   					 <tr height="30px">
	   					<td>审核状态:</td>
	   					<td>
	   						<select id="auditState" class="easyui-combobox" name="zizhiAuditRecord.auditState" style="width:300px;">   
							    <option value="1" selected="selected">通过</option>   
							   <!--  <option value="2">暂停</option>    -->
							    <option value="3">不通过</option>   
							</select>
   					  </td>   										
	   				</tr>
	   				
	   				<tr height="30px">
	   					<td>审核意见:</td>
	   					<td>
	   						<input name="zizhiAuditRecord.opinion" class="easyui-validatebox" data-options="required:true"  style="width: 300px">
   					  </td>   										
	   				</tr>

	   				<tr height="30px">
	   					<td colspan="2" align="center" style="padding: 15">
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitForm()">提交</a>
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="$('#zizhiAuditRecordDlg').window('close')">取消</a>
	   					</td>
	   				</tr>
	   			</table>
	   		</form>
	   	</div>
	</div>

	</body>
</html>

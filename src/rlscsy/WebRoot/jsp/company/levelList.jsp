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
    <title>企业管理  - 级别列表</title>
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
	 <script src="${basePath}static/js/hontek/company/level.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
    
    
  </head>
  <body style="padding: 0px" >
  
	 <table id="levelDataGrid"></table>  
	   		
	 <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendTongbao()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateTongbao()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeTongbao()">删除</a>
	
	 </div>
	 
   	<div id="levelDlb" class="easyui-window" title="添加通报企业信息" data-options="iconCls:'icon-save',closed:true"  style="width:500px;top: 100px">
		<div style="width: 100%">
	   		<form id="levelForm" method="post">
	   			<input type="hidden" name="level.levelId" value="0"> 	   		
	   			<table style="width: 480px;padding: 0px;" align="center">
	   			
	   				<tr>
	   					<td>级别名称:</td>
	   					<td><input name="level.levelTitle" class="easyui-validatebox"  data-options="required:true,validType:'length[0,50]'"  style="width: 400px"></td>   										
	   				</tr>
	   				<tr>
	   					<td>排序:</td>
	   					<td><input name="level.seq" id="seq" class="easyui-numberbox" value="5" style="width: 400px"></td>   										
	   				</tr>	   			
	   				<tr>
	   					<td>类型:</td>
	   					<td>
							<select id="typeClass" class="easyui-combobox" name="level.typeClass" style="width:400px;">   
							    <option value="1">级别</option>   
							    <option value="2">认证</option>   
							</select>
   					  </td>   										
	   				</tr>
	   				<tr>
	   					<td>备注:</td>
	   					<td><textarea class="easyui-validatebox" id="remarks" name="level.remarks" data-options="validType:'length[0,500]'" rows="2" style="width: 400"></textarea></td>
	   				</tr>	   				
	   				<tr>
	   					<td colspan="2" align="center" style="padding: 15">
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitForm()">提交</a>
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="$('#levelDlb').window('close')">取消</a>
	   					</td>
	   				</tr>
	   			</table>
	   		</form>
	   	</div>
	</div>
   
  </body>
</html>

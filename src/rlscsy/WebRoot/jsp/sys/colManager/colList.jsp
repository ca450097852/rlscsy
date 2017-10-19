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
    <title>系统管理  - 栏目列表</title>
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
	<script src="${basePath}static/js/hontek/sys/colManager/sys_col.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>	
  </head>
  <body>
    
     <table id="mydatagrid">  
    </table>
      
    <!-- mydatagrid 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
	</div>
		
	<div id="add" class="easyui-window"  style="width:500px;top:10px">
		<div class="easyui-panel" style="width:480px">
			<div align="center">
			<form id="add_form" method="post">
				<input type="hidden" name="col.id" />	
				<table class="formtable" cellspacing="1" cellpadding="0">
					<tr>
						<td class="form_label">选择父栏目：</td>
					    <td class="form_value">
						    <input type="text" id="parentid" name="col._parentId" style="width:300px" required="true"/>						
						</td>
					</tr>
					<tr>
						<td class="form_label">栏目名称：</td>
						<td class="form_value"><input type="text" name="col.colName" class="easyui-validatebox" style="width:300px" required="true"/></td>
					</tr>
					<tr>
						<td class="form_label">栏目说明：</td>
						<td class="form_value">
							<input type="text" name="col.remarks" class="easyui-validatebox" style="width:300px"/>
						</td>
					</tr>
					<tr>
						<td class="form_label">连接路径：</td>
						<td class="form_value"><input type="text" class="easyui-validatebox"  style="width:300px" name="col.colUrl"/></td>
					</tr>
					<tr>
						<td class="form_label">显示顺序：</td>
						<td class="form_value"><input type="text" name="col.seq" id="col_seq" class="easyui-numberbox" required="true" value="5" style="width:300px"/></td>
					</tr>
					<tr>
						<td class="form_label">图标路径：</td>
						<td class="form_value"><input type="text" class="easyui-validatebox" name="col.iconUrl" style="width:300px" /></td>
					</tr>
				</table>
			</form>
			</div>
			<div style="text-align:center;padding:5px">
		    	<a href="javascript:void(0)" id="userlist_sub" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitForm()">提交</a>
		    	<a href="javascript:void(0)" id="userlist_reset" class="easyui-linkbutton" iconCls="icon-no" onclick="$('#add').window('close')">关闭</a>
		    </div>
	    </div>
	</div>
  </body>
</html>
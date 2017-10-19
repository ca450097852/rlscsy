<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理 - 区域列表</title>
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
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/sys/entManage/entArea.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>	
  </head>
  <body>
  <!-- 组织机构列表 -->
   <table id="list_enterprise">
   </table>  
    <!-- list_enttype 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<div>
				&nbsp;&nbsp;区域名称: <input class="easyui-validatebox" style="width:200px" id="name" name="name">
				&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="submit_search" onclick="find()">搜 索</a>
		</div>
	</div>
	
	<!-- 添加区域 -->
	<div id="add" class="easyui-dialog" data-options="iconCls:'icon-save',closed:true" style="width:500px;height:250px;padding:10px;top:10px">
		 <div class="easyui-panel" style="width:460px">
		    <form id="add_form" method="post">
		     	<input type="hidden" id="entId" name="enterprise.entId"/>
				<input type="hidden" name="enterprise.flag" value="1"/>				
				<table class="formtable" cellspacing="1" cellpadding="0">
				    <tr height="30px">
					    <td class="form_label">上级区域：</td>
						<td class="form_value">
						   <input name="enterprise.parentId" id="parentId" style="width:300px;">
					    </td>
					    </tr>	
					<tr height="30px">
					    <td class="form_label">区域名称：</td>
						<td class="form_value">
					    	<input class="easyui-validatebox" type="text" name="enterprise.name" data-options="required:true" style="width:300px;">
					    </td>
				    </tr>				    
				    <tr height="30px">
					    <td class="form_label">区域编码：</td>
						<td class="form_value">
					      <input class="easyui-validatebox" name="enterprise.entCode" data-options="required:true" style="width:300px;"/>
					      
					    </td>
				    </tr>				    
			    </table>
		    </form>
		    
		 <div style="text-align:center;padding:15px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-ok" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp;
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="$('#add').dialog('close')">关 闭</a>
	  	 </div>
       </div>  
	</div>
  </body>
</html>

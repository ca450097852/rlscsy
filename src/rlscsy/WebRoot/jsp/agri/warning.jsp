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
    <title>系统管理  - 预警信息</title>
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
	<script src="${basePath}static/js/hontek/agri/warning.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
  </head>
  <body>
  
    <table id="list_warning_ent">
   </table> 
  
  	<div id="showWaring" class="easyui-window" title="预警信息" data-options="iconCls:'icon-save',closed:true">
  <!-- 资讯类别列表 -->
   <table id="list_warning">
   </table>  
    <!-- list_warning 工具栏 -->
    <div id="tb" style="height:auto">
		
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-flag_green',plain:true" onclick="readWaring(2)">已读</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="readWaring(3)">待处理</a>		 
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<div>
				&nbsp;&nbsp;预警分类: <select class="easyui-combobox" id="s_wtype" style="width:150px;">
						   		<option value="-1" selected="selected">请选择分类</option>
						   		<option value="1">安全间隔期预警</option>
						   		<option value="2">信息上报预警</option>
						   </select>
				&nbsp;&nbsp;状态: <select class="easyui-combobox" id="s_state" style="width:150px">
						   		<option value="-1" selected="selected">请选择状态</option>
						   		<option value="1">未读</option>
						   		<option value="2">已读</option>
						   		<option value="3">待处理</option>
						   </select>
				&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="submit_search" onclick="find()">搜 索</a>
		</div>
	</div>
	
	<!-- 预警信息 -->
	<div id="add" class="easyui-window" title="添加预警信息" data-options="iconCls:'icon-save',closed:true" style="width:500px;height:320px;padding:10px;top:150px">
		 <div class="easyui-panel" style="width:460px">
         <div align="center">
		    <form id="add_form" method="post">
				<table class="formtable" cellspacing="1" cellpadding="0">
				    	
				    <tr height="40px">
					    <td class="form_label">预警类型：</td>
						<td class="form_value">
					      <select class="easyui-combobox" id="wtype" name="warning.wtype" style="width:380px;">
						   		<option value="1">禁用农业投入品预警</option>
						   		<option value="2">信息上报预警</option>
						   </select> 
						   
					    </td>
				    </tr>
				    <tr height="40px">
					    <td class="form_label">预警时间：</td>
						<td class="form_value">
					      <input class="easyui-datetimebox" type="text" id="warningTime" name="warning.warningTime" data-options="required:true" style="width:380px;" />
					    </td>
				    </tr>
				    <tr height="40px">
					    <td class="form_label">预警内容：</td>
						<td class="form_value">
					      <textarea class="easyui-validatebox" rows="3" cols="45" id="contents" name="warning.contents" data-options="validType:'length[0,150]'" style="width:98%"></textarea>
					    </td>
				    </tr>
				    
				    <input type="hidden" id="wid" name="warning.wid"/>
				    <input type="hidden" id="crttime" name="warning.crttime"/>
				    <input type="hidden" id="state" name="warning.state"/><!-- 1未读，2已阅 -->
				    <input type="hidden" id="oprerate"/>
			    </table>
		    </form>
       </div>
	   <div style="text-align:center;padding:5px">
		    <!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp; -->
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="clearForm()">关 闭</a>
	   </div>
    </div>
	</div>
  </div>
  
  
   
  </body>
</html>

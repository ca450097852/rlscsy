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
    <title>系统管理 - 对象档案参照管理</title>
    <META http-equiv="X-UA-Compatible" content="IE=8" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/record/elementTemplet2.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
  </head>
  <body>
  <!--要素参照配置  -->
	<div align="center" id="elementTemplet_win" class="easyui-window" title="要素参照配置" data-options="modal:true,closed:false,iconCls:'icon-save',maximized:true" style="width:500px;top:50;height:450px">
	<div id="cc" class="easyui-layout" align="center" style="width:500px;top:50;height:450px;vertical-align: middle;">
			<div data-options="region:'west',split:true" title="选择类型" style="width:200px;">
				<ul id="element_type">
				</ul>
			</div>
			<div data-options="region:'center',title:'操作'" align="center" id="option_col" style="width:100px;vertical-align: middle;">
				<br/><br/>
				<a href="javascript:void(0)" id="userlist_sub" class="easyui-linkbutton" onclick="selectAll()">全选</a><br/><br/>
		    	<a href="javascript:void(0)" id="userlist_update" class="easyui-linkbutton" onclick="RevSelect()">反选</a><br/><br/>
		    	<a href="javascript:void(0)" id="userlist_reset" class="easyui-linkbutton" iconCls="icon-ok" onclick="passPower()">保存</a><br/>
			</div>
			<div data-options="region:'east',split:true" title="选择要素" style="width:200px;">
				<ul id="element_tree">
				
				</ul>
			</div>
		</div>
	</div>
  
  </body>
</html>
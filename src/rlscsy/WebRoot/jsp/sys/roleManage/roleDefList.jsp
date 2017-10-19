<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理  - 默认角色设置</title>
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
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/sys/roleManager/sys_roleDef.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	<style type="text/css">
		#option_col a{
			margin-top: 15px;
		}
	</style>
	
  </head>
  <body>
    
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-group'" onclick="empower(0)">默认管理员角色授权</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-group'" onclick="empower(1)">默认普通用户角色授权</a>
    
	
	<!-- 栏目授权 -->
	<div id="rolePower" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;top:10;height:450px">
	<div id="cc" class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west',split:true" title="栏目菜单" style="width:350px;">
				<ul id="rol_col"></ul>
			</div>
			<div data-options="region:'center',title:'操作'" align="center" id="option_col">
				<a href="javascript:void(0)" id="userlist_sub" class="easyui-linkbutton" onclick="selectAll()">全选</a><br/>
		    	<a href="javascript:void(0)" id="userlist_update" class="easyui-linkbutton" onclick="RevSelect()">反选</a><br/>
		    	<a href="javascript:void(0)" id="userlist_reset" class="easyui-linkbutton" iconCls="icon-ok" onclick="passPower()">授权</a><br/>
		    	<a href="javascript:void(0)" id="userlist_reset" class="easyui-linkbutton" iconCls="icon-no" onclick="$('#rolePower').window('close')">关闭</a>
			</div>
		</div>
	</div>
  </body>
</html>

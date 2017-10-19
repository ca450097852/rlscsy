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
    <title>系统管理  - 禁用投入品管理</title>
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
	<script src="${basePath}static/js/hontek/agri/agriDis.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
  </head>
  <body>
   <!-- 禁用列表 -->
   <table id="list_agriDis">
   </table>  
    <!-- list_agriDis 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<div>
				&nbsp;&nbsp;禁用投入品名称: <input class="easyui-validatebox" style="width:200px" id="s_agName">
				&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="submit_search" onclick="find()">搜 索</a>
		</div>
	</div>
	
	<!-- 信息供应商 -->
	<div id="add" class="easyui-window" title="添加禁用投入品" data-options="iconCls:'icon-save',resizable:false" style="width:500px;height:320px;padding:10px;top:150px">
		 <div class="easyui-panel" style="width:460px">
         <div align="center">
		    <form id="add_form" method="post">
				<table class="formtable" cellspacing="1" cellpadding="0">
				    
				    <tr height="40px">
					    <td class="form_label">投入品名称：</td>
						<td class="form_value">
					      <input class="easyui-validatebox" type="text" id="agName" name="agriDis.agName" data-options="required:true,validType:'length[0,50]'" style="width:98%" />
					    </td>
				    </tr>
				    <tr height="40px">
					    <td class="form_label">禁用时间：</td>
						<td class="form_value">
					      <input class="easyui-datebox" type="text" id="agTime" name="agriDis.agTime" data-options="required:true" style="width:98%" />
					    </td>
				    </tr>
				    <tr height="40px">
					    <td class="form_label">登记证号：</td>
						<td class="form_value">
					      <input class="easyui-validatebox" type="text" id="agNum" name="agriDis.agNum" data-options="required:true,validType:'length[0,25]'" style="width:98%" />
					    </td>
				    </tr>
				    <tr height="40px">
					    <td class="form_label">禁用原因：</td>
						<td class="form_value">
					      <textarea class="easyui-validatebox" rows="3" cols="45" id="agReason" name="agriDis.agReason" data-options="validType:'length[0,150]'" style="width:98%"></textarea>
					    </td>
				    </tr>
				    
				    <input type="hidden" id="agId" name="agriDis.agId"/>
				    <input type="hidden" id="crttime" name="agriDis.crttime"/>
				    <input type="hidden" id="oprerate"/>
				    
			    </table>
		    </form>
       </div>
	   <div style="text-align:center;padding:5px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp;
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="clearForm()">关 闭</a>
	   </div>
    </div>
	</div>
  </body>
</html>

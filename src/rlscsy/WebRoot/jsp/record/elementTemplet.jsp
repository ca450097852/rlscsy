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
	<script src="${basePath}static/js/hontek/record/elementTemplet.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
  </head>
  <body>
  <!-- 信息列表 -->
   <table id="list_elementTemplet">
   </table>  
    <!-- list_enttype 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<div>
				&nbsp;&nbsp;档案类型: <select id="s_archivesType" class="easyui-combobox"  style="width:200px;">   
						    <option value="-1">--请选择类型--</option>   
						    <option value="1">普通</option>   
						    <option value="2">三品一标</option>   
						    <option value="3">种植类</option>   
						    <option value="4">畜牧类</option>   
						</select> 
				&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="submit_search" onclick="find()">搜 索</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
		</div>
	</div>
	<!-- 添加-->
	<div id="add" class="easyui-window" title="添加对象档案参照" data-options="iconCls:'icon-save',resizable:false,shadow:false" style="width:580px;height:auto;padding:10px;top:10px">
		 <div class="easyui-panel" style="width:550px">
         <div align="center">
		    <form id="add_form" method="post" enctype="multipart/form-data">
		    		<input type="hidden" id="elementId" name="elementTemplet.tempId"/>
				    <input type="hidden" id="oprerate"/>
				<table class="formtable" cellspacing="1" cellpadding="0">
			    	<tr height="30px">
				        <td class="form_label">档案类型:</td>
					    <td class="form_value">
						  <select id="archivesType" class="easyui-combobox" name="elementTemplet.archivesType" style="width:200px;">   
						    <option value="1">普通</option>   
						    <option value="2">三品一标</option>   
						    <option value="3">种植类</option>   
						    <option value="4">畜牧类</option>   
						</select>  
					    </td>
				    </tr>
				   <tr height="30px">
				        <td class="form_label">选择要素:</td>
					    <td class="form_value">
					     <input class="easyui-combobox" 
							name="elementTemplet.elementId" 
							style="width:200px;" 
							id="elementidss" 
							data-options=" 
									url:'element_getElementCombobox.action',
									method:'get',
									valueField:'id',
									textField:'text',
									required:'true',
									editable: false
						  ">
					    </td>
				    </tr>
		    	
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
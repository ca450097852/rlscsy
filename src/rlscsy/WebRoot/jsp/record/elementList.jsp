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
    <title>系统管理 - 档案要素管理</title>
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
	<script src="${basePath}static/js/hontek/record/element.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
	 <link rel="stylesheet" href="${basePath}uploadify/uploadify.css" type="text/css"></link>
	<script type="text/javascript" src="${basePath}uploadify/jquery.uploadify.js"></script>
	
  </head>
  <body>
  <input type="hidden" id="jsessionid" value="<%=session.getId() %>"> 	 
  <input type="hidden" id="h_path" value="<%=path %>">
  <input type="hidden" id="b_path" value="<%=basePath %>">
  <!-- 信息列表 -->
   <table id="list_element">
   </table>  
    <!-- list_enttype 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<div>
				&nbsp;&nbsp;要素名称: <input class="easyui-validatebox" style="width:200px" id="elementName" name="elementName">
				&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="submit_search" onclick="find()">搜 索</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
		</div>
	</div>
	<!-- 添加-->
	<div id="add" class="easyui-window" title="添加档案要素" data-options="iconCls:'icon-save',resizable:false,shadow:false" style="width:650px;height:auto;padding:10px;top:10px">
		 <div class="easyui-panel" style="width:620px">
         <div align="center">
		    <form id="add_form" method="post" enctype="multipart/form-data">
		    	<input type="hidden" id="elementId" name="element.elementId"/>
				<input type="hidden" id="oprerate"/>
				    
				<table class="formtable" cellspacing="1" cellpadding="0">
			    	<tr height="30px">
				        <td class="form_label">要素名称:</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" name="element.elementName" data-options="required:true,validType:'length[0,100]'" style="width:100%;" />
					    </td>
				    </tr>
				    <tr height="30px">
				        <td class="form_label">要素表名:</td>
					    <td class="form_value">
					      <input name="element.tableName" id="tableName" style="width:100%;" />
					    </td>
					 </tr>
				     <tr height="30px">
					    <td class="form_label">排序号:</td>
					    <td class="form_value">
						   <input class="easyui-numberbox" type="text" name="element.seq" id="seq" style="width:100%;" value="5" />
					    </td>
				    </tr>
				     <tr height="30px">
				    	<td class="form_label">要素图标:</td>
				    	<td class="form_value">
				    		<input type="file" name="fileQueue" id="uploadifyLogo" />
				    		<input type="hidden" name="element.elementIcon" id="elementIcon" />
				    	
				    		<div id="fileQueueLogo" style="width:300px;"></div>
				   		</td>
					</tr>
					<tr height="30px">
				        <td class="form_label">链接地址:</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" name="element.elementUrl" data-options="validType:'length[0,50]'" style="width:100%" />
					    </td>
				    </tr>	
				    <tr height="30px">
				        <td class="form_label">备注:</td>
					    <td class="form_value">
					      <textarea class="easyui-validatebox" rows="1" style="width:100%" id="remark" name="element.remark" data-options="resizable:false,validType:'length[0,250]'"></textarea>
					    </td>
				    </tr>
		    	
			    	</table>
		    </form>
       </div>
	   <div style="text-align:center;padding:5px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp;
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="clearForm()">关 闭</a>
	   </div>
  </body>
</html>
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
    <title>企业管理  - 通报企业信息列表</title>
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
	 <script src="${basePath}static/js/hontek/company/tongbao.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
	<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
    <link rel="stylesheet" href="uploadify/uploadify.css" type="text/css"></link>
    
    
  </head>
  <body style="padding: 0px" >
  
	 <table id="tongbaoDataGrid"></table>  
	   		
	  <input type="hidden" id="jsessionid" value="<%=session.getId() %>"> 
	  <input type="hidden" id="h_path" value="<%=path %>">
	  
	 <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendTongbao()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateTongbao()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeTongbao()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="publishTongbao()">发布</a>
	
	 </div>
	 
	 <div id="h_value">
	 	<input type="hidden" name="tongbao.tid">
	 	<input type="hidden" name="tongbao.crttime"> 		 		 	
	 </div>
	 
   	<div id="tongbaoDlg" class="easyui-window" title="添加通报企业信息" data-options="iconCls:'icon-save',closed:true"  style="width:550px;height:450px;top: 10px">
		<div style="width: 100%">
	   		<form id="tongbaoForm" method="post">
	   			<input type="hidden" name="tongbao.sysCode" value="0"> 	   		
	   			<div id="in_value"></div>
				<table class="formtable" cellspacing="1" cellpadding="0">
	   			
	   				<tr>
	   					<td class="form_label">企业名称:</td>
	   					<td class="form_value"><input name="tongbao.entName" class="easyui-validatebox"  data-options="required:true,validType:'length[0,50]'"  style="width: 400px"></td>   										
	   				</tr>
	   				<tr>
	   					<td class="form_label">通报主题:</td>
	   					<td class="form_value"><input name="tongbao.title" class="easyui-validatebox" data-options="required:true,validType:'length[0,50]'" style="width: 400px"></td>   										
	   				</tr>	   			
	   				<tr>
	   					<td class="form_label">通报内容:</td>
	   					<td class="form_value"><textarea class="easyui-validatebox" name="tongbao.content" rows="5" data-options="required:true,validType:'length[0,2500]'" style="width: 400"></textarea></td>
	   				</tr>
   					<tr>
	   					<td class="form_label">通报附件:</td>
	   					<td class="form_value">
	   					<input type="file" name="uploadify" id="uploadify" />
	   					<div id="fileQueue"></div>
	   					<input name="tongbao.appdUrl" id="appdUrl" type="hidden">
	   					<input name="tongbao.appdName" id="appdName" type="hidden">	   					
   					  </td>   										
	   				</tr>
	   				<tr>
	   					<td class="form_label">状态:</td>
	   					<td class="form_value">
							<select id="sts" class="easyui-combobox" name="tongbao.sts" style="width:400px;">   
							    <option value="1">发布</option>   
							    <option value="0">待审</option>   							    
							</select>
   					  </td>   										
	   				</tr>
	   				<tr>
	   					<td class="form_label">发布人:</td>
	   					<td class="form_value"><input name="tongbao.userName" class="easyui-validatebox"  style="width: 400px"></td>   										
	   				</tr> 	 		   				
	   				<tr>
	   					<td class="form_label">通报说明:</td>
	   					<td class="form_value"><textarea class="easyui-validatebox" name="tongbao.remark" data-options="validType:'length[0,500]'" rows="2" style="width: 400"></textarea></td>
	   				</tr>	   				
	   				<tr>
	   					<td class="form_value" colspan="2" align="center" style="padding: 15">
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitForm()">提交</a>
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="$('#tongbaoDlg').window('close')">取消</a>
	   					</td>
	   				</tr>
	   			</table>
	   		</form>
	   	</div>
	</div>
   
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String rsts = request.getParameter("rsts");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理  - 企业资质证书列表</title>
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
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>	
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	 <script src="${basePath}static/js/hontek/company/zizhizhengshu.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
	<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
    <link rel="stylesheet" href="uploadify/uploadify.css" type="text/css"></link>
    
    
  </head>
  <body style="padding: 0px" >
  
	 <table id="zizhiDataGrid"></table>  
	 
	  <input type="hidden" id="jsessionid" value="<%=session.getId() %>"> 	 
	  <input type="hidden" id="h_path" value="<%=path %>">
	  
	 <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendZizhi()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateZizhi()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeZizhi()">删除</a>
	 </div>
	 
	 <div id="h_value">
	 	<input type="hidden" name="zizhi.id">
	 	<input type="hidden" name="zizhi.entId">
	 </div>
	 
   	<div id="zizhiDlg" class="easyui-window" title="添加资质信息" data-options="iconCls:'icon-save',closed:true"  style="width:500px;height:300px;top: 10px">
		<div style="width: 100%">
	   		<form id="zizhiForm" method="post">
	   			<div id="in_value"></div>
	   			<table style="width: 450px;padding: 0px;" align="center">
	   				<tr>
	   					<td>证书名称:</td>
	   					<td>
	   					<input name="zizhi.zizhiName" class="easyui-validatebox"  data-options="required:true" style="width: 300px">
   					  </td>   										
	   				</tr>
	   				   				
					<tr>
	   					<td>证书类型：</td>
	   					<td>
	   						<select id="appType" class="easyui-combobox" name="zizhi.appType" style="width:300px;">   
							    <option value="1">资质文件</option>   
							    <option value="2">营业执照</option>   
							    <option value="3">企业荣誉</option>   
							    <option value="4">其他证书</option>   
							</select>
	   					</td>
	   				</tr>
	   				
	   				<tr>
	   					<td>授予单位:</td>
	   					<td>
	   					<input name="zizhi.grantUnit" class="easyui-validatebox"  style="width: 300px">
   					  </td>   										
	   				</tr>
	   				
	   				<tr>
	   					<td>颁发单位:</td>
	   					<td>
	   					<input name="zizhi.awardUnit" class="easyui-validatebox"  style="width: 300px">
   					  </td>   										
	   				</tr>
	   				
	   				<tr>
	   					<td>颁发时间:</td>
	   					<td>
	   					<input name="zizhi.awardTime" id="zizhi_awardTime" class="easyui-datebox"  data-options="required:true" style="width: 300px">
   					  </td>   										
	   				</tr>

	   				<tr>
	   					<td>备注:</td>
	   					<td><textarea class="easyui-validatebox" name="zizhi.remark" style="width: 300"></textarea></td>
	   				</tr>
	   				<tr>
	   					<td colspan="2" align="center" style="padding: 15">
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitForm()">提交</a>
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="$('#zizhiDlg').window('close')">取消</a>
	   					</td>
	   				</tr>
	   			</table>
	   		</form>
	   	</div>
	</div>

		<div id="attachWin" class="easyui-window" title="附件管理" data-options="iconCls:'icon-attach',closed:true" style="width: 500px; height: 400px; padding: 10px">
			<table>
				<tr>
					<td>
						附件:
					</td>
					<td>
						<div id="l_uploadify">
							<input type="file" name="uploadify" id="uploadify" />
						</div>
					</td>
				</tr>
					
					<tr>
						<td colspan="2">
							<div id="fileQueue" style="width: 400px"></div>
						</td>
					</tr>					
			</table>
		</div>
	</body>
</html>

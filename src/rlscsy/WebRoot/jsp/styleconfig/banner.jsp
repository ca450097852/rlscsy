<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理</title>
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
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
	<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
	<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
	
	
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/browser.js"></script>
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	
	
	<script src="${basePath}static/js/hontek/styleconfig/banner.js" type="text/javascript"></script>
	       
  </head>
  <body style="padding: 0px" >
  	<input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
  	
  	
	 <table id="tabdataGrid"></table>  
	 	  
	 <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendData()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateInterAccount()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeInterAccount()">删除</a>
	 </div>
	 
	 
   	<div id="interAccountDlg" class="easyui-window" title="添加接入账号" data-options="iconCls:'icon-save',closed:true,modal:true"  style="width:520px;top: 10px">
		<div style="width: 100%">
	   		<form id="interAccountForm" method="post">
	   			<div id="in_value"></div>
	   			<table style="width: 480px;padding: 0px;" align="center">
	   				<tr>
	   					<td width="66px">标题:</td>   											  	   					
	   					<td colspan="2"> <input name="banner.imgTitle" style="width:400px;" class="easyui-validatebox" required="true"></td>
	   				</tr>
	   				
	   				<tr>
	   					<td>图片:</td>   											  	   					
	   					<td colspan="2"> 
	   						<input type="file" id="imgUrl"/>
	   						<input name="banner.imgUrl" type="hidden"/>
	   					</td>
	   				</tr>
	   				<tr>
	   					<td></td>   											  	   					
	   					<td colspan="2"><font color="red">最佳规格为1920x500像素，大小不能超过1M</font></td>
	   				</tr>
	   				
	   				<tr>
	   					<td>状态:</td>
	   					<td colspan="2">
	   						<select class="easyui-combobox" name="banner.sts" id="_state" style="width:400px;">
	   							<option value="1">启用</option>
	   							<option value="2">停用</option>
	   						</select>
	   					</td>
	   				</tr>
	   					   				
	   				<tr>
	   					<td colspan="3" align="center" style="padding: 20">
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitForm()">提交</a>
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="$('#interAccountDlg').window('close')">取消</a>
	   					</td>
	   				</tr>
	   			</table>
	   		</form>
	   	</div>
	</div>  
  </body>
</html>

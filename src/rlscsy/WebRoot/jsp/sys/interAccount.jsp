<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理  - 接入系统账号列表</title>
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
	<script src="${basePath}static/js/hontek/sys/interAccount.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	       
  </head>
  <body style="padding: 0px" >
  
	 <table id="interAccountDataGrid"></table>  
	 	  
	 <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendInterAccount()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateInterAccount()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeInterAccount()">删除</a>
	 </div>
	 
	 <div id="h_value">
	 	<input type="hidden" name="interAccount.sysCode">
	 	<input type="hidden" name="interAccount.userId">	 	
	 	<input type="hidden" name="interAccount.crttime"> 	
	 </div>
	 
   	<div id="interAccountDlg" class="easyui-window" title="添加接入账号" data-options="iconCls:'icon-save',closed:true"  style="width:520px;height:360px;top: 10px">
		<div style="width: 100%">
	   		<form id="interAccountForm" method="post">
	   			<div id="in_value"></div>
	   			<table style="width: 480px;padding: 0px;" align="center">
	   				<tr>
	   					<td>所属机构:</td>   											  	   					
	   					<td> <input name="interAccount.entId" id="parentId" style="width:400px;"></td>
	   				</tr>
	   				<tr>
	   					<td>区域:</td>   											  	   					
	   					<td> <input name="interAccount.areaId" id="areaId" style="width:400px;"></td>
	   				</tr>
   				
	   				<tr>
	   					<td>系统名称:</td>
	   					<td><input name="interAccount.sysName" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'" style="width: 400px"></td>   										
	   				</tr>
	   				
	   				<tr>
	   					<td>系统账号:</td>
	   					<td><input id="interAccount_account" name="interAccount.account" class="easyui-validatebox" data-options="required:true,validType:'length[0,30]'" style="width: 400px"></td>   										
	   				</tr>
	   					   				
	   				<tr>
	   					<td>系统密码:</td>
	   					<td><input name="interAccount.pass" class="easyui-validatebox" type="password" data-options="required:true,validType:'length[0,30]'" style="width: 400px"></td>   										
	   				</tr>
	   				
	   				<tr>
	   					<td>IP地址:</td>
	   					<td><input name="interAccount.ip" class="easyui-validatebox" data-options="required:true,validType:'length[0,25]'" style="width: 400px"></td>   										
	   				</tr>
	   					  
	   				<tr>
	   					<td>使用状态:</td>
	   					<td>
	   					<select id="sts" class="easyui-combobox" name="interAccount.sts" style="width:400px;">   
						    <option value="0">使用</option>   
						    <option value="1">停用</option>   						    
						</select>  
	   				</tr> 		
	   				<tr>
	   					<td>系统说明:</td>
	   					<td><textarea class="easyui-validatebox" name="interAccount.sysDesc" data-options="validType:'length[0,500]'" rows="3" style="width: 400"></textarea></td>
	   				</tr>		
	   				<tr>
	   					<td colspan="2" align="center" style="padding: 20">
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

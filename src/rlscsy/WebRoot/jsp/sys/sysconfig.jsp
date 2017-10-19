<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理  - 配置信息设置</title>
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
	
	<script src="${basePath}static/js/hontek/sys/sysconfig.js" type="text/javascript"></script>
	       
  </head>
  <body style="padding: 0px" >
  
	 <div class="easyui-panel" align="center" style="vertical-align: middle;text-align: center" >
		   	
		   	 <div align="center" style="vertical-align: middle;text-align: center;">
				
				<form id="sysconfigForm" method="post">
	   			<div id="in_value"></div>
	   			<input type="hidden" id="configId" name="sysconfig.configId"/>
	   			<table style="width: 500px;padding: 0px;" align="center" class="formtable" cellspacing="1" cellpadding="0">
	   				<tr>
	   					<td class="form_label">区县审核:</td>
	   					<td class="form_value">
						 <input type="radio" name="sysconfig.needareqaudit" value="n" >：不需要&nbsp;
						 <input type="radio" name="sysconfig.needareqaudit" value="y">：需要&nbsp;
						 </td>
	   				</tr> 
   				
	   				<tr>
	   					<td class="form_label">地市审核:</td>
	   					<td class="form_value">
						<input type="radio" name="sysconfig.needcityaudit" value="n" >：不需要&nbsp;
						 <input type="radio" name="sysconfig.needcityaudit" value="y" >：需要&nbsp;
						 </td>
	   				</tr> 
	   				
	   				<tr>
	   					<td class="form_label">诚信评价:</td>
	   					<td class="form_value">
						<input type="radio" name="sysconfig.showpingjia" value="y" >：显示&nbsp;
						<input type="radio" name="sysconfig.showpingjia" value="n">：不显示&nbsp;
						</td>
						 
	   				</tr> 
	   					   				
	   				<tr>
	   					<td class="form_label">监管信息:</td>
	   					<td class="form_value">
						 <input type="radio" name="sysconfig.showjianguan" value="y" >：显示&nbsp;
						<input type="radio" name="sysconfig.showjianguan" value="n">：不显示&nbsp;
						</td>
	   				</tr> 
	   				
	   				<tr>
	   					<td class="form_label">自检信息:</td>
	   					<td class="form_value">
						 <input type="radio" name="sysconfig.showjijian" value="y" >：显示&nbsp;
						<input type="radio" name="sysconfig.showjijian" value="n">：不显示&nbsp;
						</td>
	   				</tr>
	   				<tr>
	   					<td class="form_value" colspan="2" align="center" style="padding: 20">
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitForm()">提交</a>
	   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'"  onclick="updateSysconfig()">取消</a>
	   					</td>
	   				</tr>
	   			</table>
	   		</form>
		    </div>
	</div>
	
	
	
	
  </body>
</html>

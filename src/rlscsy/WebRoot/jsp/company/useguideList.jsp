<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.comm.util.DateUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>企业管理 - 指引信息管理</title>
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
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>	
	<script src="${basePath}static/js/hontek/company/useguideList.js"　type="text/javascript"></script>	
	    
    <script type="text/javascript" src="${basePath}static/js/xheditor-1.2.1/xheditor-1.2.1.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/xheditor-1.2.1/xheditor_lang/zh-cn.js"></script>
	
	<script type="text/javascript">
		$(function(){
			//初始化xhEditor编辑器插件
			$('#xh_editor').xheditor({
				tools:'full',
				skin:'default',
				upMultiple:true,
				upImgUrl: "<%=basePath%>/UploadFileServlet",
				upImgExt: "jpg,jpeg,gif,bmp,png",
				onUpload:insertUpload,
				html5Upload:false
			});
			//xbhEditor编辑器图片上传回调函数
			function insertUpload(msg) {
				var _msg = msg.toString();
				var _picture_name = _msg.substring(_msg.lastIndexOf("/")+1);
				var _picture_path = Substring(_msg);
				var _str = "<input type='checkbox' name='_pictures' value='"+_picture_path+"' checked='checked' onclick='return false'/><label>"+_picture_name+"</label><br/>";
				$("#xh_editor").append(_msg);				
				$("#uploadList").append(_str);
			}
			//处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.
			function Substring(s){
				return s.substring(s.substring(0,s.lastIndexOf("/")).lastIndexOf("/"),s.length);
			}
		});
	
	</script>
    
  </head>
  <body>
  
	 <table id="useguideDataGrid"></table>  	   		
	 <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendUseguide()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateUseguide()">修改</a>
	 </div>
	
	<div id="useguideWin" class="easyui-window" style="width:800px;height:400px" data-options="iconCls:'icon-save',closed:true">
		<form action="" id="useguideForm" method="post">
			<input type="hidden" id="useguide_ugId" name="useguide.ugId" />
			<table class="formtable" cellspacing="1" cellpadding="0">
				<tr>
					<td class="form_label">指引标题:</td>
					<td class="form_value"><input type="text" class="easyui-validatebox" data-options="required:true" id="useguide_title" name="useguide.title" style="width: 300px"></td>
					<td class="form_label">指引编号:</td>
					<td class="form_value">
						<input type="text" class="easyui-validatebox" data-options="required:true" id="useguide_ugNo" name="useguide.ugNo" >（创建后不可修改）
					</td>
				</tr>
				<tr>
					<td class="form_label">指引内容:</td>
					<td class="form_value" colspan="3"><textarea id="xh_editor" name="useguide.contents" style="width: 99%;height: 250px"></textarea> 
					
						<div id="uploadList"></div>
					</td>
				</tr>
					
				<tr>
					<td class="form_value" colspan="4" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitUseguideForm()" data-options="iconCls:'icon-ok'">提交</a>
						
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#useguideWin').window('close')" data-options="iconCls:'icon-no'">关闭</a>
					
					</td>
				</tr>				
			</table>
		</form>
	</div>			
	
  </body>
</html>

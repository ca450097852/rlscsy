<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.comm.util.DateUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
LoginUser obj = (LoginUser)session.getAttribute("loginUser");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>企业管理 - 监管信息发布</title>
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
	<script src="${basePath}static/js/hontek/company/superviseList.js"　type="text/javascript"></script>	
	
	<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
    <link rel="stylesheet" href="uploadify/uploadify.css" type="text/css"></link>
    
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
  
  	<div id="tb" style="height:auto;margin-bottom: 5px;">
	</div>
	
	<div>
		<form action="" id="superviseForm" method="post">
			<input type="hidden" name="ids" />
			<table class="formtable" cellspacing="1" cellpadding="0">
				<tr>
					<td class="form_label">信息标题:</td>
					<td class="form_value"><input type="text" class="easyui-validatebox" data-options="required:true" id="supervise_title" name="supervise.title" style="width: 400px"></td>
					<td class="form_label">发布时间:</td>
					<td class="form_value">
						<input type="text" class="easyui-datebox" data-options="required:true" id="supervise_supTime" name="supervise.supTime" value="<%=DateUtil.formatDate() %>">
					</td>
				</tr>
				<tr>
				<td class="form_label">信息状态:</td>
					<td class="form_value">
							<select id="state" class="easyui-combobox" name="supervise.state" style="width:200px;">   
							    <option value="1">待审</option>   
							    <option value="2">对内通告</option>   
							    <option value="3">对外发布</option>   
							</select>  
					</td>
					<td class="form_label">监管对象:</td>
					<td class="form_value">
						<select id="objList" style="width: 150px;" onchange="selectOne();">
							<option>已选择0个企业</option>
						</select>
								<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="setObj()">设置监管对象</a>
						
					</td>
				</tr>
				<tr>
					<td class="form_label">信息内容:</td>
					<td class="form_value" colspan="3"><textarea id="xh_editor" name="supervise.contents" style="width: 99%;height: 250px"></textarea> 
					
						<div id="uploadList"></div>
					</td>
				</tr>
					
				<tr>
					<td class="form_value" colspan="4" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitSuperviseForm()" data-options="iconCls:'icon-ok'" id="btnSupervise">提交</a>
					</td>
				</tr>				
			</table>
		</form>
	</div>
	
	<div id="entTab" class="easyui-window" title="设置监管对象"
	 data-options="iconCls:'icon-table',modal:true,closed:true" style="width:750px;height:450;">
		 	<table id="list_enterprise">
   			</table> 
   			<div id="tb1" style="height:auto;padding: 5px 0;">
				企业名称: <input class="easyui-validatebox" style="width:150px" id="nameSearch">&nbsp;&nbsp;
				地区: <select class="easyui-combobox" style="width:200px;" id="areaId_id">
					      </select>&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find()">搜 索</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
			</div>
	</div>	
	
	
	
  </body>
</html>

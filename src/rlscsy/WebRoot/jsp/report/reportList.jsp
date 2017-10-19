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
    <title>新供销 - 举报信息列表</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<!-- 引入的文件的语句必须在引入jquery语句的后面 -->
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
  	<script src="${basePath}static/js/hontek/report/reportList.js" type="text/javascript"></script>
  	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
  	<style type="text/css">
  		.opter_a{
  			margin-left: 50px;
  		}
  	</style>
  </head>
  <body>
  <div style="display: none;">  <OBJECT ID="BarcodeUSBPrinter" CLASSID="CLSID:5D7CB30D-8BA0-4749-97D9-929DE8E47185" codebase="" ></OBJECT>
  </div>
     <table id="conveydatagrid">  
    </table>  
	<!-- conveydatagrid 工具栏 -->
    <div id="tb" style="height:auto">
    	<div>
			<table>
				<tr>
					<td>举报人:</td>
					<td><input id="creater" class="easyui-validatebox" style="width:100px"></td>
					<td>联系电话:</td>
					<td><input id="phome" class="easyui-validatebox" style="width:100px"></td>
					<td>投诉时间:</td>
					<td>
					<input type="text" class="easyui-datebox" id="startDate">-<input type="text" class="easyui-datebox" id="endDate">
					</td>				
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="findReport()">查询</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetSearch()">重置</a>						
					</td>											
				</tr>
			</table>	
		</div>
	</div>

	<!-- 显示物流信息详细信息 -->
	<div id="showDesc" class="easyui-window" title="投诉回复" style="width:580px;top:10;height: 500">
		<div style="height:410px;padding:10px 0 10px 10px">
		<div style="height: 300px;">
			<div>
			<p id="question">
			</p>
			</div>
			<div>
				<ul id="answer">
				</ul> 
			</div>
		</div>
			<form id="reportanswer" method="post">
			    <textarea rows="5" style="width:550px" id="t_answer" name="reportAnswer.answer" ></textarea>
			    <input type="hidden" id="repId" name="reportAnswer.repId"/>
			    <input type="hidden" id="anId" name="anId"/>
		 	 </form>
	 	 </div>
		<div id="dlg-buttons" align="center">
			<a id="bt1" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="submitForm();">提交</a>
			<a id="bt2" href="javascript:void(0)" style="display: none" class="easyui-linkbutton" iconCls="icon-save" onclick="submitForm1();">修改</a>
			<a id="bt3" href="javascript:void(0)" style="display: none" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cencelUpdate();">取消修改</a>
		</div>
	</div>
	
  </body>
</html>

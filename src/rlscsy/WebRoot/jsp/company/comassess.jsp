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
    <title>企业考核信息管理</title>
  <style>
			
			.fl-lt{float: left;}
			.fl-rt{
				float: right;
			}
			li{
				list-style-type: none;
			}
			.table-c{
				width: 100%;
				border-collapse: collapse;
				font-size: 13px;
				
			}
			th{
				border: 1px solid #ccc;
				max-width: 500px;
				text-align: center;
				line-height: 35px;
			}
			th,td{
				border: 1px solid #ccc;
				max-width: 500px;
			}
			
			
			.table-box{
				margin: 0 auto;
			}
			.table-info{
				width: 100%;
				height: 20px;
				margin:15px 0;
			}
			.table-info ul li{
				float: left;
				margin-right: 30px;
				font-size: 13px;
				font-weight: 700;
			}
			.table-info ul li:last-child{
				margin-right: 0;
			}
			.table-info ul li span{
				font-weight: normal;
				margin-left: 5px;
			}
		</style>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
	<!-- 引入的文件的语句必须在引入jquery语句的后面 -->
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js" type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
  	<script src="${basePath}static/js/hontek/company/comassess.js" type="text/javascript"></script>
  	<script src="${basePath}static/js/hontek/comm/hontek.js" type="text/javascript"></script>
  </head>
  <body>
  <input type="hidden" id="cadId"/>
     <table id="comAssessdatagrid">  
    </table>  
    <!-- purchasedatagrid 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
	</div>
	
	<div id="showWin" class="easyui-window" data-options="iconCls:'icon-house',closed:true" style="width:900px;height:400px;">
		<form id='tForm'>
		<div class="table-box">
		 <div style="font-size:20px;text-align:center">
<span style="font-size:20px" id='nodeType'></span>追溯子系统考核评估测评表</div>
		<div class="table-info">
			<ul>                                                  
				<li>企业名称：<span id='caName'></span></li>
				<li>企业地址：<span id='addr'></span></li>
				<li>企业联系人及电话：<span id='linkUser'></span><span id='phone'></span></li>
			</ul>
		</div>    
    </div>
    		<table class="table-c" cellspacing="0">
				<thead>
					<th>考核指标</th>
					<th>指标描述</th>
					<th>自测 </th>
					<th>核查 </th>
				</thead>
				<tbody id="khtbody">
				</tbody>
				<tfoot>
				<tr>
				<td colspan="4" align="center">
				<div class="table-info">
				<ul class="fl-rt">                                                  
					<li>自测填表员：<span id='inUser'></span></li>
					<li>填表日期：<span id='inDate'></span></li>
				</ul>
			</div>
			</td>
			</tr>
			<tr>
					<td class="form_value" colspan="4" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" data-options="iconCls:'icon-ok'" id="btnSupervise">提交</a>
					</td>
				</tr>
				</tfoot>
			</table>
			</form>
			
		</div>
		
		<div id="showAllWin" class="easyui-window" data-options="iconCls:'icon-house',closed:true" style="width:900px;height:400px;">
		<div class="table-box">
		 <div style="font-size:20px;text-align:center">
<span style="font-size:20px" id='nodeType2'></span>追溯子系统考核评估测评表</div>
		<div class="table-info">
			<ul>                                                  
				<li>企业名称：<span id='caName2'></span></li>
				<li>企业地址：<span id='addr2'></span></li>
				<li>企业联系人及电话：<span id='linkUser2'></span><span id='phone2'></span></li>
			</ul>
		</div>    
    </div>
    		<table class="table-c" cellspacing="0">
				<thead>
					<th>考核指标</th>
					<th>指标描述</th>
					<th>自测 </th>
					<th>核查 </th>
				</thead>
				<tbody id="khtbody2">
				</tbody>
				<tfoot>
				<tr>
				<td colspan="4" align="center">
				<div class="table-info">
				<ul class="fl-rt">                                                  
					<li>自测填表员：<span id='inUser2'></span></li>
					<li>填表日期：<span id='inDate2'></span></li>
				</ul>
			</div>
			</td>
			</tr>
			<tr>
					<td class="form_value" colspan="4" align="center">
						 <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="closeWin('showAllWin')">关闭页面</a>
					</td>
				</tr>
				</tfoot>
			</table>
		</div>
		
	       
  </body>
</html>

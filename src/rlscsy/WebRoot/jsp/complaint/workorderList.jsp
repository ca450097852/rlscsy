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
    <title>系统管理 - 举报工单列表</title>
    <META http-equiv="X-UA-Compatible" content="IE=8" >
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
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/complaint/workOrder.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
  </head>
  <body>
  <!-- 工单列表 -->
   <table id="workOrderList">
   </table>  
    <!-- list_enttype 工具栏 -->
    <div id="tb" style="height:auto">
		<!--  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>  -->
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<div>
				&nbsp;&nbsp;标题: <input class="easyui-validatebox" style="width:150px" id="title" name="title">
				&nbsp;&nbsp;状态: <select id="rsts" style="width:100px;">
							      	<option value="-1">--请选择--</option>
							      	<option value="0">未处理</option>
							      	<!--<option value="1">处理中</option>-->
							      	<option value="2">已处理</option>
							      </select>
				&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="submit_search" onclick="Search()">搜 索</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
		</div>
	</div>
	<!-- 显示详细信息 -->
	<div id="workOrder_detail" class="easyui-window" title="工单详细" style="width:700px;height:500px;padding:10px;top:10px">
		<div style="padding:10px 0 10px 10px">
				<table width="100%" >
				    <tr height="30px">
				        <td style="width:75px;">标题:</td>
					    <td colspan="3">
					      <span id="d_title"></span>
					    </td>
				    </tr>
				    <tr height="30px">
				        <td style="width:75px;">举报产品:</td>
					    <td colspan="3">
					      <span id="d_proName"></span>
					    </td>
				    </tr>
				    <tr height="30px">
				        <td style="width:75px;">举报企业:</td>
					    <td colspan="3">
					      <span id="d_companyName"></span>
					    </td>
				    </tr>
				    <tr height="30px">
					    <td style="width:75px;">举报内容:</td>
					    <td colspan="3">
					       <textarea id="d_content" class="easyui-validatebox" rows="3" style="width:100%" readonly="readonly"></textarea>
					    </td>
				    </tr>
				    <tr height="30px">
					    <td style="width:75px;">处理意见:</td>
					    <td colspan="3">
					       <textarea id="d_opinion" class="easyui-validatebox" rows="2" style="width:100%" readonly="readonly"></textarea>
					    </td>
				    </tr>
				    <tr height="40px">
				        <td style="width:75px;">处理结果:</td>
					    <td colspan="3">
					      <textarea id="d_finalResult" class="easyui-validatebox" rows="3" style="width:100%" readonly="readonly"></textarea>
					    </td>
				    </tr>
				    <tr height="40px">
				        <td style="width:75px;">备注:</td>
					    <td colspan="3">
					      <textarea id="d_remark" class="easyui-validatebox" rows="1" style="width:100%" readonly="readonly"></textarea>
					    </td>
				    </tr>
				   <tr height="30px">
				        <td style="width:75px;">处理人:</td>
					    <td>
					      <span id="d_douser">
					    </td>
					    <td class="enterprise_parentId_tr"  style="width:100px">状态:</td>
					    <td class="enterprise_parentId_tr">
					    	<span id="d_sts">
					    </td>
				    </tr>
			  </table>
	 	 </div>
		<div id="dlg-buttons1" align="center" style="text-align:center;padding-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#workOrder_detail').dialog('close')">关闭</a>
		</div>
	</div>
	<!-- 添加工单-->
	<div id="add" class="easyui-window" title="添加工单" data-options="iconCls:'icon-save',resizable:false" style="width:700px;height:500px;padding:10px;top:10px">
		 <div class="easyui-panel" style="width:650px" fit="true">
         <div align="center">
		    <form id="add_form" method="post" enctype="multipart/form-data">
			    <table width="100%">
				    <tr height="30px">
				        <td style="width:90px;">标题:</td>
					    <td colspan="3">
					    	<span id="up_title"></span>
					    </td>
				    </tr>
				    <tr height="30px">
				        <td style="width:90px;">举报产品:</td>
					    <td colspan="3">
					    	<span id="up_proName"></span>
					    </td>
				    </tr>
				    <tr height="30px">
				        <td style="width:90px;">举报企业:</td>
					    <td colspan="3">
					    	<span id="up_companyName"></span>
					    </td>
				    </tr>
				    <tr height="30px">
					    <td style="width:90px;">举报内容:<br/><font style="color: red">(只读)</font></td>
					    <td colspan="3">
					    <textarea id="up_content" class="easyui-validatebox" rows="3" style="width:100%" readonly="readonly"></textarea>
					    </td>
				    </tr>
				   
				    <tr height="30px">
				        <td style="width:90px;">处理意见:</td>
					   <td colspan="3">
					       <textarea  class="easyui-validatebox" rows="2" style="width:100%" name="workOrder.opinion" data-options="validType:'length[0,250]'"></textarea>
					    </td>
				    </tr>
				    <tr height="30px">
				        <td style="width:90px;">处理结果:</td>
					   <td colspan="3">
					       <textarea  class="easyui-validatebox" required="true" rows="3" style="width:100%" name="workOrder.finalResult" data-options="validType:'length[0,250]'"></textarea>
					    </td>
				    </tr>
				    
				    <tr height="30px">
				        <td style="width:90px;">备注:</td>
					    <td colspan="3">
					      <textarea class="easyui-validatebox" rows="1" style="width:100%" id="remark" name="workOrder.remark" data-options="resizable:false,validType:'length[0,250]'"></textarea>
					    </td>
				    </tr>
			    	
			    	<tr height="30px">
				        <td style="width:90px;">处理人:</td>
					    <td colspan="3">
					      <input  class="easyui-validatebox" name="workOrder.douser" style="width:150px" data-options="validType:'length[0,25]'">
					    </td>
				    </tr>
				    <input type="hidden" name="workOrder.sts"/>
				    <input type="hidden" name="workOrder.woId"/>
				    <input type="hidden" name="workOrder.cid"/>
   				    <input type="hidden" name="workOrder.dotime"/>
   				    
				    <input type="hidden" id="oprerate"/>
			    </table>
			    
		    </form>
       </div>
	   <div style="text-align:center;padding-bottom:5px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp;
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="clearForm()">关 闭</a>
	   </div>
 	</div>
</div>
  </body>
</html>
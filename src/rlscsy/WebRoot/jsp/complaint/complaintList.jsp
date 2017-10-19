<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.hontek.comm.base.LoginUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
LoginUser loginUser = (LoginUser)session.getAttribute("loginUser");
int mainentid = loginUser.getEntId();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理 - 投诉举报列表</title>
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
	<script src="${basePath}static/js/hontek/complaint/complaint.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>

  </head>
  <body>
  <input type="hidden" id="mainentid" value="<%=mainentid %>">
  <!-- 信息列表 -->
   <table id="complaintList">
   </table>  
    <!-- list_enttype 工具栏 -->
    <div id="tb" style="height:auto">
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="changeCheck(0)">处理</a>	
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="changeCheck(1)">待处理</a>
		 -->
		 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<div>
				&nbsp;&nbsp;标题: <input class="easyui-validatebox" style="width:100px" id="s_title" name="s_title">
				&nbsp;&nbsp;区域: <input name="s_entId" id="s_entId" style="width:150px;">
				&nbsp;&nbsp;状态: <select id="s_sts" style="width:100px;">
							      	<option value='-1'>--请选择--</option>
							      	<option value='0'>未处理</option>
							      	<option value='1'>处理中</option>
							      	<option value='2'>已处理</option>
							      </select>
				&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="submit_search" onclick="find()">搜 索</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
		</div>
	</div>
	<!-- 显示详细信息 -->
	<div id="complaint_detail" class="easyui-window" title="举报详细" style="width:700px;height:auto;padding:10px;top:10px">
		<div>
				<table class="formtable" cellspacing="1" cellpadding="0">
				    <tr height="30px">
				        <td class="form_label" style="width:15%">标题:</td>
					    <td class="form_value" style="width:35%">
					      <span id="d_title"></span>
					    </td>
					    <td class="form_label" style="width:15%">区域:</td>
					    <td class="form_value" style="width:35%">
						   <span id="d_entName"></span>
					    </td>
				    </tr>
				 	<tr height="30px">
				 		<td class="form_label">举报类型:</td>
					    <td class="form_value">
					      <span id="d_typeNo"></span>
					    </td>
					    
				        <td class="form_label">举报人:</td>
					    <td class="form_value">
					      <span id="d_userName"></span>
					    </td>
					    
				    </tr>
				    <tr height="30px">
				        
					    <td class="form_label"  style="width:80px">举报人电话:</td>
					    <td class="form_value">
						  <span id="d_phone"></span>
					    </td>
					    <td class="form_label">通讯地址:</td>
					    <td class="form_value">
					      <span id="d_userAddr"></span>
					    </td>
				    </tr>

				    <tr height="30px">
				        <td class="form_label">举报产品:</td>
					    <td class="form_value">
					      <span id="d_proName"></span>
					    </td>
					    <td class="form_label"  style="width:80px">举报企业:</td>
					    <td class="form_value">
						  <span id="d_companyName"></span>
					    </td>
				    </tr>
				    <tr height="30px">
					    <td class="form_label">举报内容:</td>
					    <td class="form_value" colspan="3">
					       <textarea id="d_content" rows="3" style="width:100%" readonly="readonly"></textarea>
					    </td>
				    </tr>
				    <tr height="30px">
					    <td class="form_label">举报附件:</td>
					    <td class="form_value" colspan="3">
					       <span id="d_appName"></span>
					    </td>
				    </tr>
				    <tr height="40px">
				        <td class="form_label">处理结果:</td>
					    <td class="form_value" colspan="3">
					      <textarea id="d_finalResult" rows="3" style="width:100%" readonly="readonly"></textarea>
					    </td>
				    </tr>
				   <tr>
		    			<td class="form_label">状态:</td>
		    			<td class="form_value" colspan="3">
		    				<span id="d_sts"></span>
		    			</td>
			    	</tr>
			  </table>
	 	 </div>
		<div id="dlg-buttons1" align="center" style="text-align:center;padding-bottom:5px;padding-top: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#complaint_detail').dialog('close')">关闭</a>
		</div>
	</div>
	
	<!-- 添加信息-->
	<div id="add" class="easyui-window" title="添加信息" data-options="iconCls:'icon-save',resizable:false" style="width:700px;height:auto;padding:10px;top:10px;">
		 <div class="easyui-panel" style="width:650px" fit="true">
         <div align="center">
		    <form id="add_form" method="post" enctype="multipart/form-data">
		    		<input type="hidden" name="complaint.cid"/>
   				    <input type="hidden" name="complaint.crttime"/>
   				    <input type="hidden" name="complaint.sysCode"/>
   				    <input type="hidden" name="complaint.appName"/>
   				    <input type="hidden" name="complaint.title"/>
   				    <input type="hidden" name="complaint.proName"/>
   				    <input type="hidden" name="complaint.companyName"/>
   				    <input type="hidden" name="complaint.entId"/>
   				    <input type="hidden" name="complaint.userName"/>
   				    <input type="hidden" name="complaint.phone"/>
   				    <input type="hidden" name="complaint.content"/>
   				    <input type="hidden" name="complaint.sts"/><!-- 处理时赋值为1：处理中 -->
   				    <input type="hidden" name="complaint.mainentid"/>
				    <input type="hidden" id="oprerate"/>
					<table class="formtable" cellspacing="1" cellpadding="0">
				    <tr height="30px">
				        <td class="form_label" style="width:15%">标题:</td>
					    <td class="form_value" style="width:35%">
					      <span id="up_title"></span>
					    </td>
					    <td class="form_label" style="width:15%">区域:</td>
					    <td class="form_value" style="width:35%">
						   <span id="up_entName"></span>
					    </td>
				    </tr>
				    
				    <tr height="30px">
				       	<td class="form_label">举报类型:</td>
					    <td class="form_value">
					      <span id="up_typeNo">
					    </td>
					    
					    <td class="form_label">举报人:</td>
					    <td class="form_value">
					      <span id="up_userName">
					    </td>			   
				    </tr>	
				    
				    <tr height="30px">
				        
					    <td class="form_label">举报人电话:</td>
					    <td class="form_value">
						   <span id="up_phone">
					    </td>
					     <td class="form_label">通讯地址:</td>
					    <td class="form_value">
					      <span id="up_userAddr">
					    </td>
				    </tr>
				    			    
				    <tr height="30px">
				        <td class="form_label">举报产品:</td>
					    <td class="form_value">
					      <span id="up_proName">
					    </td>
					    <td class="form_label">举报企业:</td>
					    <td class="form_value">
						   <span id="up_companyName">
					    </td>
				    </tr>
				    <tr height="30px">
					    <td class="form_label">举报内容:<br/><font style="color: red">(只读)</font></td>
					    <td class="form_value" colspan="3">
					       <textarea id="up_content" class="easyui-validatebox" rows="3" readonly="readonly" style="width:100%"></textarea>
					    </td>
				    </tr>
				    <tr height="40px">
				        <td class="form_label">处理结果:</td>
					    <td class="form_value" colspan="3">
					      <textarea class="easyui-validatebox" rows="3" style="width:100%" id="remark" name="complaint.remark" data-options="validType:'length[0,250]'"></textarea>
					    </td>
				    </tr>
				   <%--<tr height="30px" id="gdshow">
			    		<td class="form_label" style="width:15%">创建工单:</td>
					    <td colspan="3">
					    	是：<input name="complaint.isCreatGd" type="radio" value="1">&nbsp;&nbsp;
					    	否：<input name="complaint.isCreatGd" type="radio" value="0" checked="checked">
					    </td>
					</tr>
			  --%>
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
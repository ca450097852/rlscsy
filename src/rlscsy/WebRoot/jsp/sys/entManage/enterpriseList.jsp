<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("loginUser");
String isAdmin = "";
if(obj!=null){
	isAdmin  = ((LoginUser)obj).getAdmin();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理 - 机构列表</title>
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
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
	<script src="${basePath}static/js/hontek/sys/entManage/enterprise.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
	<script type="text/javascript" src="uploadify/jquery.uploadify.js"></script>
    <link rel="stylesheet" href="uploadify/uploadify.css" type="text/css"></link>
	
  </head>
  <body>
  <!-- 组织机构列表 -->
   <table id="list_enterprise">
   </table>  
    <!-- list_enttype 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="removeitBtn" onclick="removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="changeCheck(0)">使用</a>	
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lock',plain:true" onclick="changeCheck(1)">禁用</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="changeCheck(3)">注销</a>	
		<div>
				&nbsp;&nbsp;组织帐号: <input class="easyui-validatebox" style="width:100px" id="account" name="account">
				&nbsp;&nbsp;组织名称: <input class="easyui-validatebox" style="width:100px" id="name" name="name">
				&nbsp;&nbsp;使用状态: <select id="sts" style="width:100px;">
					      	<option value='-1'>--请选择--</option>
					      	<option value='0'>使用</option>
					      	<option value='1'>禁用</option>
					      	<option value='2'>待定</option>
					      	<option value='3'>注销</option>   
					      </select>
				&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="submit_search" onclick="find()">搜 索</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
		</div>
	</div>
	
	<!-- 添加组织机构 -->
	<div id="add" class="easyui-window" data-options="iconCls:'icon-save',resizable:false,closed:true" style="width:650px;height:450px;padding:10px;top:10px">
		 <div class="easyui-panel" style="width:600px">
         <div align="center">
		    <form id="add_form" method="post" enctype="multipart/form-data">
		    	<input type="hidden" name="enterprise.flag" value="2">
			    <input type="hidden" name="enterprise.integrityRecord" value=""> 
			    <input type="hidden" name="enterprise.isReported" value="0">
			   	<input type="hidden" name="enterprise.sysCode" value="0"> 			     		    	 
		    	<input class="easyui-validatebox" type="hidden" id="entId" name="enterprise.entId"/>
				<input class="easyui-validatebox" type="hidden" id="oprerate"/>				
				<table class="formtable" cellspacing="1" cellpadding="0">
				    <tr>
					    
					    <td class="form_label">机构名称：</td>
					    <td class="form_value">
					    	<input class="easyui-validatebox" type="text" name="enterprise.name" data-options="required:true" style="width:200px">
					    </td>
					    <td class="form_label">组织帐号：</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" name="enterprise.account" data-options="required:true" style="width:200px" validType="uniqueName['ent_findAccountIsUnique','enterprise.account','该帐号已经存在']">
					    </td>
				    </tr>				    
				    <tr>
				  		<td class="form_label">上级组织：</td>
					    <td class="form_value">
						   <input name="enterprise.parentId" id="parentId" style="width:200px;">
					    </td>
					    
					    <td class="form_label">所属区域：</td>
					    <td class="form_value">
					      <input type="hidden" name="enterprise.sts" id="sts_id" value="0">
					      <input id="areaId" name="enterprise.areaId" style="width:200px;">					      	   
					    </td>				    	
				    </tr>
				    <tr>
				  		<td class="form_label">机构类型：</td>
					    <td class="form_value">
					    <!-- multiple:true, 多选属性 -->
					      <input class="easyui-combobox" 
							name="enterprise.entType"
							style="width:200px;"
							id="entType_id"
							data-options="
									url:'enttype_getEntTypeToSelect.action',
									method:'get',
									valueField:'id',
									textField:'text',
									required:'true',
									editable: false
						  ">
					    </td>
				        <td class="form_label">简称：</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" name="enterprise.simpleName" style="width:200px">
					    </td>
				    </tr>				    
				     <tr>
				        <td class="form_label">注册地址：</td>
					    <td class="form_value">
							<input class="easyui-validatebox" type="text" name="enterprise.regAddr" style="width:200px">					      
					    </td>
					    <td class="form_label">经营地址：</td>
					    <td class="form_value">
							<input class="easyui-validatebox" type="text" name="enterprise.manageAddr" style="width:200px">					      
					    </td>	
				    </tr>
				    <tr>
				  		<td class="form_label">电话：</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" name="enterprise.tel" data-options="validType:'phone'" style="width:200px">
					    </td>
				        <td class="form_label">邮编：</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" name="enterprise.postCode" data-options="validType:'ZIP'" style="width:200px">
					    </td>
				    </tr>
				    <tr>
				  		<td class="form_label">网址：</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" name="enterprise.domName" style="width:200px">
					    </td>
				        <td class="form_label">签名：</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" name="enterprise.sign" style="width:200px">
					    </td>
				    </tr>
				    <tr>
				    	<td class="form_label">电子邮箱：</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" name="enterprise.email" style="width:200px"  data-options="validType:'email'">
					    </td>
				  		<td class="form_label">排序号：</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" name="enterprise.seq" value="3" style="width:200px">
					    </td>
				    </tr>
					<tr>
				    	<td class="form_label">简介：</td>
					    <td class="form_value" colspan="3">
				    	<textarea name="enterprise.intro"  style="width:100%"></textarea>
				   		</td>
				    </tr>				    
			    </table>
		    </form>
       </div>
	   <div style="text-align:center;padding:15px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp;
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="clearForm(1)">关 闭</a>
	   </div>
	</div>
</div>
  </body>
</html>

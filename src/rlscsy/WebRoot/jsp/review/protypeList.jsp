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
    <title>产品分类列表</title>
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
  	<script src="${basePath}static/js/hontek/review/protype.js" type="text/javascript"></script>
  	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
  </head>
  <body>
     <table id="protypedatagrid">  
    </table>  
    <!-- purchasedatagrid 工具栏 -->
    <div id="tb" style="height:auto">
   		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendProType()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateProType()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="updateProTypeState(1)">停用</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="updateProTypeState(0)">启用</a>
	</div>
	
	<div id="hiden_value">
    	<input type="hidden" name="proType.entId"> 
    	<input type="hidden" name="proType.typeId"> 
	</div>
	
	<div id="addProType" class="easyui-window" title="添加分类信息" style="width:450px;top:10">
		<div style="padding:1px 10 1px 1px">
		    <form id="proType_form" method="post">
		    	<input type="hidden" id="method"> 
		    	<div id="in_value" style="display: none"></div>
				<table class="formtable" cellspacing="1" cellpadding="0">
			    	<tr height="30px">
			    	    <td class="form_label">选择父类：</td>
						<td class="form_value">
					      <input id="upcateId" name="proType.upcateId" style="width:250px">
					    </td>				   
				    </tr>		    
				    <tr height="30px">
				        <td class="form_label">分类编码：</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" name="proType.typeNo" id="proType_typeNo" readonly="readonly"  style="width:250px">(自动)
					    </td>				   
				    </tr>			
				    <tr height="30px">
					    <td class="form_label">分类性质：</td>
					    <td class="form_value">
							<select name="proType.typeClass" id="_typeClass" style="width:250px" class="easyui-combobox" data-options="required:true">
								<option value="1">种植类</option>
								<option value="2">养植类</option>
								<option value="3">加工类</option>
							</select>
				    	</td>
				    </tr>	    
				    <tr height="30px">
					    <td class="form_label">分类名称：</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="proType.typeName" data-options="required:true"  style="width:250px"/>
				    	</td>
				    </tr>
				    <tr height="30px">
					    <td class="form_label">状态：</td>
					    <td class="form_value">
							<select id="pro_state" name="proType.state"  class="easyui-combobox" data-options="panelHeight: 'auto'" style="width:250" data-options="required:true" >
								<option value="0">启用</option>
								<option value="1">停用</option>
							</select>
				    	</td>
				    </tr>	
				    <tr height="30px">
					    <td class="form_label">备注：</td>
					    <td class="form_value">
							<textarea rows="3" cols="" id="P_remark" name="proType.remark" style="width:250"></textarea>
				    	</td>
				    </tr>	
			    </table>
		    </form>
	 	 </div>
		<div id="dlg-buttons" align="center">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="submitProTypeForm()";>提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addProType').dialog('close')">关闭</a>
		</div>
	</div>
	
  </body>
</html>

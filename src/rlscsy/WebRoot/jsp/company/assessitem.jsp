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
    <title>考核项目列表</title>
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
  	<script src="${basePath}static/js/hontek/company/assessItem.js" type="text/javascript"></script>
  	<script src="${basePath}static/js/hontek/comm/hontek.js" type="text/javascript"></script>
  </head>
  <body>
     <table id="assessItemdatagrid">  
    </table>  
    <!-- purchasedatagrid 工具栏 -->
    <div id="tb" style="height:auto">
   		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendAssessItem()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateAssessItem()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
	</div>

	
	<div id="addAssessItem" class="easyui-window" title="添加考核项目" style="width:450px;top:10">
		<div style="padding:1px 10 1px 1px">
		    <form id="assessItem_form" method="post">
		    	<input type="hidden" id="itemId" name="assessItem.itemId"> 
		    	<input type="hidden" id="method"> 
		    	<div id="in_value" style="display: none"></div>
				<table class="formtable" cellspacing="1" cellpadding="0">
				    <tr height="30px">
					    <td class="form_label">考核指标：</td>
					    <td class="form_value">
							<input class="easyui-validatebox" name="assessItem.itemName" data-options="required:true"  style="width:250px"/>
				    	</td>
				    </tr>
				    <tr height="30px">
					    <td class="form_label">排序号：</td>
					    <td class="form_value">
							<input class="easyui-numberbox" name="assessItem.seq" id="seq" style="width:250px"/>
				    	</td>
				    </tr>
				    <tr height="30px">
					    <td class="form_label">节点类型：</td>
					    <td class="form_value">
							<select id="item_type" name="assessItem.nodeType"  class="easyui-combobox" data-options="panelHeight: 'auto'" style="width:250"  >
								<option value="1">屠宰企业</option>
								<option value="2">批发企业</option>
								<option value="3">菜市场</option>
								<option value="4">超市</option>
								<option value="5">团体消费单位</option>
								<option value="6">其他</option>
							</select>
				    	</td>
				    </tr>	
				    <tr height="30px">
					    <td class="form_label">指标描述：</td>
					    <td class="form_value">
							<textarea rows="3" cols="" id="item_desc" name="assessItem.itemDesc" style="width:250"></textarea>
				    	</td>
				    </tr>	
			    </table>
		    </form>
	 	 </div>
		<div id="dlg-buttons" align="center">
			<a href="javascript:void(0)" class="easyui-linkbutton" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true"  onclick="submitAssessItemForm()">提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="javascript:$('#addAssessItem').dialog('close')">关闭</a>
		</div>
	</div>
  </body>
</html>

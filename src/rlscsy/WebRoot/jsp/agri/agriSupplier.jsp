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
    <title>系统管理  - 投入品供应商目录</title>
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
	<script src="${basePath}static/js/hontek/agri/agriSupplier.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
  </head>
  <body>
   <!-- 资讯类别列表 -->
   <table id="list_agriSupplier">
   </table>  
    <!-- list_agriSupplier 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<div>
				&nbsp;&nbsp;供应商名称: <input class="easyui-validatebox" style="width:200px" id="s_asName">
				&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="submit_search" onclick="find()">搜 索</a>
		</div>
	</div>
	
	<!-- 信息供应商 -->
	<div id="add" class="easyui-window" title="添加投入品供应商" data-options="iconCls:'icon-save',resizable:false" style="width:500px;height:350px;padding:10px;top:150px">
		 <div class="easyui-panel" style="width:460px">
         <div align="center">
		    <form id="add_form" method="post">
				<table class="formtable" cellspacing="1" cellpadding="0">
				    <tr height="40px">
					    <td class="form_label">供应商名称：</td>
						<td class="form_value">
					      <input class="easyui-validatebox" type="text" id="asName" name="agriSupplier.asName" data-options="required:true,validType:'length[0,50]'" style="width:100%" />
					    </td>
				    </tr>
				    <tr height="40px">
					    <td class="form_label">供应商地址：</td>
						<td class="form_value">
					      <input class="easyui-validatebox" type="text" id="asAddr" name="agriSupplier.asAddr" data-options="required:true,validType:'length[0,50]'" style="width:100%" />
					    </td>
				    </tr>
				    <tr height="40px">
					    <td class="form_label">供应商电话：</td>
						<td class="form_value">
					      <input class="easyui-validatebox" type="text" id="asPhone" name="agriSupplier.asPhone" data-options="required:true,validType:'length[0,25]'" style="width:100%" />
					    </td>
				    </tr>
				    <tr height="40px">
					    <td class="form_label">主营产品：</td>
						<td class="form_value">
					      <input class="easyui-validatebox" type="text" id="asGoods" name="agriSupplier.asGoods" data-options="required:true,validType:'length[0,50]'" style="width:100%" />
					    </td>
				    </tr>
				    <tr height="40px">
					    <td class="form_label">联系人：</td>
						<td class="form_value">
					      <input class="easyui-validatebox" type="text" id="asMan" name="agriSupplier.asMan" data-options="required:true,validType:'length[0,50]'" style="width:100%" />
					    </td>
				    </tr>
				    <input type="hidden" id="asId" name="agriSupplier.asId"/>
				    <input type="hidden" id="crttime" name="agriSupplier.crttime"/>
				    <input type="hidden" id="oprerate"/>
			    </table>
		    </form>
       </div>
	   <div style="text-align:center;padding:5px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp;
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="clearForm()">关 闭</a>
	   </div>
    </div>
	</div>
  </body>
</html>

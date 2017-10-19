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
    <title>系统管理 - 对象档案参照管理</title>
    <META http-equiv="X-UA-Compatible" content="IE=8" >
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="<%=basePath %><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/record/elementTemplet3.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
  </head>
  <body>
	
	
<!-- 对象档案参照-->
		 <div class="easyui-panel" align="center" style="vertical-align: middle;text-align: center" >
		   	
		   	 <div align="center" style="vertical-align: middle;text-align: center;">
				
				<table align="center" class="formtable" cellspacing="1" cellpadding="0" style="text-align:center;width: 80%;top: 100px;">
			    	<tr height="60px">
				        <td class="form_label" align="center" style="text-align:center;"><strong>普通:</strong></td>
					    <td align="center" class="form_label" style="width: 50px;">
					    <input type="checkbox" onclick="selectAll(1,this);"> 全选
					    </td>
					    <td class="form_value" id="putong_element">
					    
					    </td>
				    </tr>
				   <tr height="60px">
				        <td class="form_label" align="center" style="text-align:center;"><strong>加工类:</strong></td>
					    <td align="center" class="form_label" style="width: 50px;">
					    <input type="checkbox" onclick="selectAll(2,this);"> 全选
					    </td>
					    <td class="form_value"  id="sanping_element">
					    
					    </td>
				    </tr>
				    <tr height="60px">
				        <td class="form_label" align="center" style="text-align:center;"><strong>种植类:</strong></td>
					    <td align="center" class="form_label" style="width: 50px;">
					    <input type="checkbox" onclick="selectAll(3,this);"> 全选
					    </td>
					    <td class="form_value"  id="zhongzhi_element">
					    
					    </td>
				    </tr>
				    <tr height="60px">
				        <td class="form_label" align="center" style="text-align:center;"><strong>畜牧类:</strong></td>
					    <td align="center" class="form_label" style="width: 50px;">
					    <input type="checkbox" onclick="selectAll(4,this);"> 全选
					    </td>
					    <td class="form_value"  id="xumu_element">
					    
					    </td>
				    </tr>
		    	
			    </table>
		    </div>
	   <div style="text-align:center;padding:5px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp;
	   </div>
	</div>
  
  </body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理 - 开通管理员账号</title>
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
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/sys/entManage/createEntAndUser.js" type="text/javascript"></script>
	
		<%--<style type="text/css">
		td{
			border-color: #CCCCCC;
			border-style: dotted;
		    border-width: 0 1px 1px 0;
		    margin: 0;
		    padding: 0;
		}
	</style>
  --%></head>
  <body style="padding: 0">
   <div style="width: 100%;" align="center">	
     <div class="easyui-panel" title="开通机构账号" data-options="fit:true,border:false,iconCls:'icon-group'">
	    <form id="add_form" method="post" enctype="multipart/form-data">
	    	<input type="hidden" name="enterprise.flag" value="2">
		    <input type="hidden" name="enterprise.integrityRecord" value=""> 
		    <input type="hidden" name="enterprise.isReported" value="0">
		   	<input type="hidden" name="enterprise.sysCode" value="086020">
		   	<%-- 			     		    	 
	    	<input class="easyui-validatebox" type="hidden" id="entId" name="enterprise.entId"/>--%>
			<table class="formtable" cellspacing="1" cellpadding="0">
			    <tr height="30px">
			    	<td class="form_label">机构名称：</td>
				    <td class="form_value">
				    	<input class="easyui-validatebox" type="text" name="enterprise.name" data-options="required:true" style="width:250px">
				    </td>
				    
				    <td class="form_label">机构帐号：</td>
				    <td class="form_value">
				      <input class="easyui-validatebox" type="text" name="enterprise.account" data-options="required:true" style="width:250px" validType="uniqueName['ent_findAccountIsUnique','enterprise.account','该帐号已经存在']">
				    </td>			   
			    </tr>				    
			    <tr height="30px">
			  		<td class="form_label">上级机构：</td>
				    <td class="form_value">
					   <input name="enterprise.parentId" id="parentId" style="width:250px;">
				    </td>			    
				    <td class="form_label">所属区域：</td>
				    <td class="form_value">
				      <input type="hidden" name="enterprise.sts" id="sts_id" value="0">
				      <input id="areaId" name="enterprise.areaId" style="width:200px;">					      	   
				    </td>
			    	
			    </tr>
			    <tr height="30px">
			  		<td class="form_label">机构类型：</td>
				    <td class="form_value">
				      <input class="easyui-combobox" name="enterprise.entType" style="width:250px;" id="entType_id">
				    </td>
				    
				    <td class="form_label">简称：</td>
				    <td class="form_value">
				      <input class="easyui-validatebox" type="text" name="enterprise.simpleName" style="width:250px">
				      <input type="hidden" name="enterprise.sts" id="sts_id" value="0" style="width:250px;">
				    </td>
			    </tr>				    
			     <tr height="30px">
			        <td class="form_label">联系地址：</td>
				    <td class="form_value">
						<input class="easyui-validatebox" type="text" name="enterprise.regAddr" style="width:250px">	
						<input type="hidden" name="enterprise.manageAddr">					      										      
				    </td>
				    <td class="form_label">机构网址：</td>
				    <td class="form_value">
				      <input class="easyui-validatebox" type="text" name="enterprise.domName" style="width:250px">
				    </td>
			    </tr>
			    <tr height="30px">
			  		<td class="form_label">联系电话：</td>
				    <td class="form_value">
				      <input class="easyui-validatebox" type="text" name="enterprise.tel" data-options="validType:'phone'" style="width:250px">
				    </td>
			        <td class="form_label">邮政编码：</td>
				    <td class="form_value">
				      <input class="easyui-validatebox" type="text" name="enterprise.postCode" data-options="validType:'ZIP'" style="width:250px">
				    </td>
			    </tr>
			    <tr height="30px">
			    	<td class="form_label">电子邮箱：</td>
				    <td class="form_value">
				      <input class="easyui-validatebox" type="text" name="enterprise.email" style="width:250px"  data-options="validType:'email'">
				    </td>
			  		<td class="form_label">排序号：</td>
				    <td class="form_value">
				      <input class="easyui-validatebox" type="text" name="enterprise.seq" value="3" style="width:250px">
				    </td>
			    </tr>
			    <tr height="30px">			  		
			        <td class="form_label">签名：</td>
				    <td class="form_value" colspan="3">
				      <input class="easyui-validatebox" type="text" name="enterprise.sign" style="width:100%">
				    </td>
			    </tr>
				<tr height="30px">
			    	<td class="form_label">机构简介：</td>
				    <td class="form_value" colspan="3">
			    	<textarea name="enterprise.intro"  style="width:100%"></textarea>
			   		</td>
			    </tr>				    
			     <tr height="30px">
					    <td class="form_value" colspan="4" >
					    	<fieldset>
					    		<legend>选择创建默认角色用户</legend>					    		
					    		<input type="radio" name="enterprise.addUser" checked="checked" value="0" />创建管理员角色用户&nbsp;&nbsp;
				    			<input type="radio" name="enterprise.addUser" value="1" />创建普通角色用户
					    	</fieldset>			    		
				   		</td>
				    </tr>		
			    
			    <tr height="30px">
				    <td class="form_value" colspan="4">
						<div style="text-align:center;padding:15px">
						    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp;
						    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="closeWin()">关 闭</a>
					    </div>    
			   		</td>
			    </tr>					     
		    </table>
		</form>
  	</div>   
  </div>
  </body>
</html>

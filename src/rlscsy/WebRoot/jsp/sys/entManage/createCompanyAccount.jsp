<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理 - 开通企业账号</title>
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
	<script src="${basePath}static/js/hontek/sys/entManage/createCompanyAccount.js" type="text/javascript"></script>
	
		<style type="text/css">
		td{
			border-color: #CCCCCC;
			border-style: dotted;
		    border-width: 0 1px 1px 0;
		    margin: 0;
		    padding: 0;
		}
	</style>
  </head>
  <body style="padding: 0">
   <div style="width: 100%;" align="center">	
     <div class="easyui-panel" title="开通企业账号" data-options="fit:true,border:false,iconCls:'icon-group'">
	    <form id="add_form" method="post" enctype="multipart/form-data">
	    	<input type="hidden" name="enterprise.flag" value="3">
		    <input type="hidden" name="enterprise.integrityRecord" value=""> 
		    <input type="hidden" name="enterprise.isReported" value="0">
		   	<input type="hidden" name="enterprise.sysCode" value="086020">
		   	<input type="hidden" name="enterprise.townRsts" id="townRsts" value="">
		   	<input type="hidden" name="enterprise.areaRsts" id="areaRsts" value="">
		   	<input type="hidden" name="enterprise.cityRsts" id="cityRsts" value="">
		   	<input type="hidden" name="enterprise.provinceRsts" id="provinceRsts" value="">
		   	<input type="hidden" name="enterprise.companyRsts" id="companyRsts" value="0">
		   	
		    <table>
			    <tr height="30px">
			    	<td>所属区域:</td>
				    <td>
					   <input name="enterprise.areaId" id="areaId" style="width:300px;">
				    </td>
				    <td>所属行政机构:</td>
				    <td>
					   <input name="enterprise.parentId" id="parentId" style="width:300px;">
				    </td>				   
			    </tr>				    
			    <tr height="30px">
			  		<td>企业名称:</td>
				    <td colspan="3">
				    	<input class="easyui-validatebox" type="text" name="enterprise.name" data-options="required:true" style="width:100%">
				    </td>
			    	
			    </tr>
			    <tr height="30px">
			    	<td>企业简称:</td>
				    <td>
				      <input class="easyui-validatebox" type="text" name="enterprise.simpleName" style="width:300px;">
				    </td>
			  		<td>企业帐号:</td>
				    <td>
				      <input class="easyui-validatebox" type="text" name="enterprise.account" data-options="required:true" style="width:300px;" validType="uniqueName['ent_findAccountIsUnique','enterprise.account','该帐号已经存在']">
				    </td>
				    
			    </tr>
			
			    <tr height="30px">
			  		<td>企业法人:</td>
				    <td>
				    	<input class="easyui-validatebox" type="text" name="enterprise.legalPerson" data-options="required:true" style="width:300px;">
				    </td>
			    	<td>组织机构代码:</td>
				    <td>
				      <input class="easyui-validatebox" type="text" name="enterprise.orgCode" data-options="required:true" style="width:300px;">
				    </td>
			    </tr>
			    <tr height="30px">
			  		<td>企业类型:</td>
				    <td>
				      <input class="easyui-combobox" name="enterprise.entType" style="width:300px;" id="entType_id">
				    </td>
			        <td>使用状态:</td>
				    <td>
				      <select class="easyui-combobox" name="enterprise.sts" id="sts_id" data-options="required:true,editable:false" style="width:300px;">
				      	<option value='0'>使用</option>
				      	<option value='1'>禁用</option>
				      	<option value='2'>待定</option>
				      	<option value='3'>注销</option>   
				      </select>
				    </td>
			    </tr>				    
			     <tr height="30px">
			        <td>注册地址:</td>
				    <td>
						<input class="easyui-validatebox" type="text" name="enterprise.regAddr"  style="width:300px;">					      
				    </td>
				    <td>经营地址:</td>
				    <td>
						<input class="easyui-validatebox" type="text" name="enterprise.manageAddr" style="width:300px;">					      
				    </td>	
			    </tr>
			    <tr height="30px">
			  		<td>联系电话:</td>
				    <td>
				      <input class="easyui-validatebox" type="text" name="enterprise.tel" data-options="validType:'phone'" style="width:300px;">
				    </td>
			        <td>手机号码:</td>
				    <td>
				      <input class="easyui-validatebox" type="text" name="enterprise.mobile" style="width:300px;">			    
				      <input type="hidden" name="enterprise.sign" style="width:300px;">
				    </td>
			    </tr>
			    <tr height="30px">
			  		<td>企业网址:</td>
				    <td>
				      <input class="easyui-validatebox" type="text" name="enterprise.domName" style="width:300px;">
				    </td>
				    <td>邮政编码:</td>
				    <td>
				      <input class="easyui-validatebox" type="text" name="enterprise.postCode" data-options="validType:'ZIP'" style="width:300px;">
				    </td>

			    </tr>
			    <tr height="30px">
			    	<td>电子邮箱:</td>
				    <td>
				      <input class="easyui-validatebox" type="text" name="enterprise.email" style="width:300px;"  data-options="validType:'email'">
				    </td>
			  		<td>排序号:</td>
				    <td>
				      <input class="easyui-validatebox" type="text" name="enterprise.seq" value="3" style="width:300px;">
				    </td>
			    </tr>
				<tr height="30px">
			    	<td>企业简介:</td>
				    <td colspan="3">
			    		<textarea name="enterprise.intro"  style="width:100%"></textarea>
			   		</td>
			    </tr>		
			    <tr height="30px">
			    	<td>指定审核机构:</td>
				    <td colspan="3" id="auditEnt" >
			    		<%--<input id="ck1" name="enterprise.auditEnt" type="checkbox" value="1">乡镇级
			    		<input id="ck2" name="enterprise.auditEnt" type="checkbox" value="2" >区县级
			    		<input id="ck3" name="enterprise.auditEnt" type="checkbox" value="3">市级
			    		<input id="ck4" name="enterprise.auditEnt" type="checkbox" value="4">省级
			   		
					    <input name="enterprise.parentId" id="auditEnt"  class="easyui-combotree" style="width:400px;">
			   			--%>
			   		</td>
			    </tr>				    					    
			    <tr height="30px">
				    <td colspan="4">
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

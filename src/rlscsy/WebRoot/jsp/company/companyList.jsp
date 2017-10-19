<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.comm.util.DateUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/";

Object obj = session.getAttribute("loginUser");
String isAdmin = "";
String entIdStr = "";
if(obj!=null){
	isAdmin  = ((LoginUser)obj).getAdmin();
	entIdStr = ((LoginUser)obj).getEntId()+"";
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>企业管理 - 企业经营者信息列表</title>
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
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
	<script src="${basePath}static/js/easyui-1.3.4/datagrid-detailview.js"　type="text/javascript"></script>		
	
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>	
	<script src="${basePath}static/js/hontek/company/companyList.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
	<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
	
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/browser.js"></script>
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	
	
	<style type="text/css">
		#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		
	</style>
	
  </head>
  <body>
  <div id="chimgDiv" style="display:none;">
  	
  </div>
  <input type="hidden" id="sessionId" value="<%=session.getId() %>"> 	 
  <input type="hidden" id="h_path" value="<%=path %>">
  <input type="hidden" id="filePath" value="<%=filePath %>">
  <input type="hidden" id="entIdStr" value="<%=entIdStr %>">
  
      <div class="easyui-layout" data-options="fit:true">   
   		<div data-options="region:'west',title:'流通节点',split:true" style="width:250px;">
    		<ul id="tt"></ul>  

    	</div>   
	    <div data-options="region:'center',title:'企业信息'">
		  <!-- 列表 -->
		   <table id="list_companyNode"></table>  
		   
		    <!-- list_enttype 工具栏 -->
		    <div id="tb" style="height:auto">
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="setState(1)">设为使用</a>
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="setState(2)">设为停用</a>
				<div>
						&nbsp;&nbsp;
						企业名称: <input class="easyui-validatebox" style="width:150px" id="s_name">&nbsp;&nbsp;
						经营类型: <select class="easyui-combobox" style="width:100px;" id="s_comType">&nbsp;&nbsp;
							      	<option value=''>--请选择--</option>
							      	<option value='1'>生猪批发商</option>
							      	<option value='2'>肉菜批发商</option>
							      	<option value='3'>肉菜零售商</option>
							      	<option value='4'>配送企业</option>
							      	<option value='5'>其他</option>
							      </select>&nbsp;&nbsp;
						状态: <select class="easyui-combobox" style="width:100px;" id="s_state">
							      	<option value=''>--请选择--</option>
							      	<option value='1'>使用</option>
							      	<option value='2'>停用</option>
							      </select>&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find()">搜 索</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
				</div>
			</div>
	    
	    </div>   
	</div>  
  
  

	
	<!--企业基本信息-->
	<div id="add" class="easyui-window" data-options="iconCls:'icon-house',closed:true" style="width:900px;height:450px;">
		 <div align="center" title="经营企业基本信息">
			    <form id="add_form" method="post" enctype="multipart/form-data">
			    	<input type="hidden" name="company.comId"><!-- Id -->
			    	<input type="hidden" name="company.nodeCode" ><!-- 节点编号 -->
			    	<input type="hidden" name="company.comCode" ><!-- 经营者编码 -->
			    	<input type="hidden" name="company.regTime" ><!-- 注册时间 -->
			    	<input type="hidden" name="company.entId" ><!-- 默认监管Id为当前操作用户的entId -->
				    
				    <table class="formtable" cellspacing="1" cellpadding="0">
				     	<tr height="20px">
					  		<td class="form_label">名称：</td>
						    <td class="form_value">
						    	<input class="easyui-validatebox" type="text" name="company.name" data-options="required:true" style="width:300px">
						    </td>
						    <td  class="form_label">经营者类型：</td>
						    <td class="form_value">
						      <select class="easyui-combobox"   name="company.comType" id="comType" style="width:300px;">
						      	<option value=''>--请选择--</option>
						      	<option value='1'>生猪批发商</option>
						      	<option value='2'>肉菜批发商</option>
						      	<option value='3'>肉菜零售商</option>
						      	<option value='4'>配送企业</option>
						      	<option value='5'>其他</option>
						      </select>
						    </td>
					    </tr>
					    
					    <tr height="20px" class="nodeAnCom">
					  		<td class="form_label">账户名称：</td>
						    <td class="form_value">
								<input class="easyui-validatebox" type="text" name="company.account" validType="uniqueAccountName['companyUser_findComUserIsUnique','account','该账户名已经存在']" data-options="required:true" style="width: 300px;">
							</td>
								
					        <td class="form_label">账户密码：</td>
						    <td class="form_value">
						      <input class="easyui-validatebox" type="password" name="company.password" style="width:300px">
						    </td>
					    </tr>
					    
					    <tr height="20px">
						    <td class="form_label">所属区域：</td>
						    <td class="form_value">
							   <input name="company.area" id="area" data-options="required:true" style="width:300px;">
						    </td>
							<td class="form_label">所属节点：</td>
						    <td class="form_value">
						    	<input  name="company.parentId" id="parentId" data-options="required:true" style="width:300px">
						    </td>
					    </tr>
					    
					    
					    <tr height="20px">
					  		<td class="form_label">企业性质：</td>
						    <td class="form_value">
						    	<select class="easyui-combobox"   name="company.nature" id="nature" style="width:300px;">
							      	<option value=''>--请选择--</option>
							      	<option value='1'>企业</option>
							      	<option value='2'>个体户</option>
						      </select>
						    </td>
						    <td class="form_label">地址：</td>
						    <td class="form_value">
						    	<input class="easyui-validatebox" type="text" name="company.addr"  style="width:300px">
						    </td>
					    </tr>
					    
					    		    
					    <tr height="20px">
					  		<td class="form_label">联系人：</td>
						    <td class="form_value">
								<input class="easyui-validatebox"  name="company.linkMan" style="width: 300px;">
							</td>
								
					        <td class="form_label">手机号码：</td>
						    <td class="form_value">
						      <input class="easyui-validatebox" type="text" name="company.phone" style="width:300px">
						    </td>
					    </tr>				    
					    
					    <tr height="20px">
					    	<td class="form_label">电子邮箱：</td>
						    <td class="form_value">
						      <input class="easyui-validatebox" type="text" name="company.email" style="width:300px" >
						    </td>
						   <td class="form_label">状态：</td>
						    <td class="form_value">						    
						      <select class="easyui-combobox" name="company.state" id="state" data-options="required:true" style="width:300px;"  >
						      	<option value='1' selected="selected">使用</option>
						      	<option value='2'>停用</option>
						      </select>
						    </td>
					    </tr>
					    
					    <tr height="20px">
					  		<td class="form_label">法人：</td>
						    <td class="form_value">
								<input class="easyui-validatebox"  name="company.corporate" style="width: 300px;">
							</td>
					        <td class="form_label">注册号或身份证号：</td>
						    <td class="form_value">
						      	<input class="easyui-validatebox" type="text" name="company.regCode" style="width:300px">
						    </td>
					    </tr>	
					    
					    <tr height="20px">
					  		<td class="form_label">营业执照或注册号：</td>
						    <td class="form_value">
								<input class="easyui-validatebox"  name="company.licenseNum" style="width: 300px;">
							</td>
					        <td class="form_label">备案日期：</td>
						    <td class="form_value">
						      	<input class="easyui-datebox" type="text" name="company.recordDate" id="recordDate" style="width:300px">
						    </td>
					    </tr>	
					   
					    <tr height="20px">
					    	<td class="form_label">营业执照扫描件：</td>
					    	<td class="form_value">
					    		<input type="file" name="fileQueue" id="uploadify" />
					    		<input type="hidden" name="company.licenseImg" id="licenseImg" />
							</td>
						    <td class="form_value" colspan="2">
					    		<div id="fileQueue" style="width: 300px"></div>
					   		</td>					   						
						</tr>							
						
						<tr height="20px">
					    	<td class="form_label">简介：</td>
						    <td  class="form_value" colspan="3">
					    	<textarea class="easyui-validatebox" name="company.introduction" id="introduction" rows="2"  style="width:100%"></textarea>
					   		</td>
					    </tr>	
					    
					    <tr height="20px">
					    	<td class="form_value" colspan="4">
					    		<div style="text-align:center;padding:5px">
					    			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" data-options="iconCls:'icon-ok'">提交</a>
								    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="clearForm(1)">关闭页面</a>
							   </div>					    		
					    	</td>
					    </tr>			    
				    </table>
			    </form>
	       </div>
	       
	</div>

	
	
	<!--企业基本信息-->
	<div id="showWin" class="easyui-window" data-options="iconCls:'icon-house',closed:true" style="width:900px;height:400px;">
		 <div class="easyui-tabs" id="companyTab" data-options="fit:true">		 
		 	<div align="center" title="节点基本信息">					
				    <table class="formtable" cellspacing="1" cellpadding="0">
				     	<tr height="20px">
					  		<td class="form_label">名称：</td>
						    <td class="form_value" style="width:300px" id="d_name">
						    </td>
						  	<td  class="form_label">经营者类型：</td>
						    <td class="form_value" style="width:300px" id="d_comType">
						    </td>
					    </tr>
					    <tr height="20px">
						    <td class="form_label">所属区域：</td>
						    <td class="form_value" id="d_areaName">
						    </td>
							<td class="form_label">所属节点：</td>
						    <td class="form_value" id="d_nodeName">
						    </td>
					    </tr>	
					    
					    <tr height="20px">
					  		<td class="form_label">企业性质：</td>
						    <td class="form_value" id="d_nature">
						    </td>
						    <td  class="form_label">节点类型：</td>
						    <td class="form_value" id="d_flag">
						    </td>
					    </tr>
					    <tr height="20px">
					  		<td class="form_label">节点编码：</td>
						    <td class="form_value" id="d_nodeCode">
						    </td>
						    <td  class="form_label">经营者编码：</td>
						    <td class="form_value" id="d_comCode">
						    </td>
					    </tr>
					    <!-- <tr height="20px">
						    <td class="form_label">所属区域：</td>
						    <td class="form_value" id="d_areaName">
						    </td>
						    
							<td class="form_label">地址：</td>
						    <td class="form_value" id="d_addr">
						    </td>
					    </tr>	 -->			    
					    <tr height="20px">
					  		<td class="form_label">联系人：</td>
						    <td class="form_value" id="d_linkMan">
							</td>
								
					        <td class="form_label">手机号码：</td>
						    <td class="form_value" id="d_phone">
						    </td>
					    </tr>				    
					    
					    <tr height="20px">
					    	<td class="form_label">电子邮箱：</td>
						    <td class="form_value" id="d_email">
						    </td>
						   <td class="form_label">状态：</td>
						   <td class="form_value" id="d_state">						    
						    </td>
					    </tr>
					    
					    <tr height="20px">
					  		<td class="form_label">法人：</td>
						    <td class="form_value" id="d_corporate">
							</td>
					        <td class="form_label">注册号或身份证号：</td>
						    <td class="form_value" id="d_regCode">
						    </td>
					    </tr>	
					    
					    <tr height="20px">
					  		<td class="form_label">营业执照或注册号：</td>
						    <td class="form_value" id="d_licenseNum">
							</td>
					        <td class="form_label">备案日期：</td>
						    <td class="form_value" id="d_recordDate">
						    </td>
					    </tr>	
					   
					    <tr height="20px">
					    	<td class="form_label">营业执照扫描件：</td>
					    	<td class="form_value" >
					    		<div id="d_fileQueue"></div>
							</td>
							<td class="form_label">地址：</td>
						    <td class="form_value" id="d_addr">
						    </td>
						</tr>
					    
						<tr height="20px">
					    	<td class="form_label">简介：</td>
						    <td  class="form_value" colspan="3" id="d_introduction">
					   		</td>
					    </tr>	
					    
					    <tr height="10px">
					    	<td class="form_value" colspan="4">
					    		<div style="text-align:center;padding:5px">
								    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="closeWin('showWin')">关闭页面</a>
							   </div>					    		
					    	</td>
					    </tr>			    
				    </table>
	       </div>
	       
	       <div align="center" title="资质信息">
		       
				<table id="zizhiDataGrid"></table>  
	 
				<input type="hidden" id="zizhiEntId" name="zizhi.entId">				   			
				  		
				<div id="attachWin" class="easyui-window" title="附件管理" data-options="iconCls:'icon-attach',closed:true" style="width: 550px; height: 400px; padding: 10px">
					<div id="zizhiFile">
						
					</div>
				</div>       
	      </div>
	      </div>
	</div>

	<div id="recordWin" class="easyui-window" title="档案管理" data-options="iconCls:'icon-attach',closed:true">
		<div id="cc" class="easyui-layout"  data-options="fit:true" >   
			    <div data-options="region:'north',title:'批次列表',split:true" style="height:300px;">
			    	<table id="recordDataGrid"></table>  
			    </div>   
			    
			    <div data-options="region:'center',title:'详细档案'" >
			    		<div id="recordData"></div>  			 
			    </div>   
		</div>  
	</div>     




  </body>
 
</html>

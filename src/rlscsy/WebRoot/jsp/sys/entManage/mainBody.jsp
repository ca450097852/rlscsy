<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理  - 接入系统账号列表</title>
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
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>	
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
	<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
	<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
	
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/browser.js"></script>
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/css/imgselect.css" media="screen" />	
	
	<script src="<%=basePath %>static/js/hontek/sys/entManage/mainbody.js" type="text/javascript"></script>
	       
  </head>
  <body style="padding: 0px" >
  	<input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
  	<input type="hidden" id="basePath" value="<%=basePath %>"/>
  	
  	
	 <table id="tabdataGrid"></table>  
	 	  
	 <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendData()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateInterAccount()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cog',plain:true" onclick="styleConfig()">风格设置</a>
		
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cog',plain:true" onclick="tagStyleDlg()">二维码样式</a>
		
		
		<div>
			主体名称: <input class="easyui-validatebox" style="width:150px" id="s_name">&nbsp;&nbsp;
			是否为收费用户: <select class="easyui-combobox" style="width:100px;" id="s_ischarge">
				      	<option value=''>--请选择--</option>
				      	<option value='1'>是</option>
				      	<option value='2'>否</option>
				      </select>&nbsp;&nbsp;
			主体类型: <select class="easyui-combobox" style="width:100px;" id="s_mbType">
				      	<option value=''>--请选择--</option>
				      	<option value='1'>农业局</option>
				      	<option value='2'>协会</option>
				      	<option value='3'>企业</option>
				      </select>&nbsp;&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find()">搜 索</a>&nbsp;&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
		</div>
	 </div>
	 
	 
   	<div id="interAccountDlg" class="easyui-window" data-options="iconCls:'icon-save',closed:true,modal:true"  style="width:750px;top: 10px">
		<div style="width: 100%">
	   		<form id="add_form" method="post" enctype="multipart/form-data">
	    	<input type="hidden" name="enterprise.flag" value="2">
		    <input type="hidden" name="enterprise.integrityRecord" value=""> 
		    <input type="hidden" name="enterprise.isReported" value="0">
		   	<input type="hidden" name="enterprise.sysCode" value="086020">
		   	<div id="in_value" style="display: none;"></div>
		   	<%-- 			     		    	 
	    	<input class="easyui-validatebox" type="hidden" id="entId" name="enterprise.entId"/>--%>
			<table class="formtable" cellspacing="1" cellpadding="0">
			    <tr height="30px">
			    	<td class="form_label">主体名称：</td>
				    <td class="form_value">
				    	<input class="easyui-validatebox" type="text" name="enterprise.name" data-options="required:true" style="width:250px">
				    </td>
				    
				    <td class="form_label">机构帐号：</td>
				    <td class="form_value">
				      <input class="easyui-validatebox" type="text" name="enterprise.account" id="_account" data-options="required:true" style="width:250px" oldValue="" validType="uniqueNameNew['ent_findAccountIsUnique','enterprise.account','该帐号已经存在','_account']">
				    </td>			   
			    </tr>		
			    <tr height="30px">
			    	<td class="form_label">系统名称：</td>
				    <td class="form_value" colspan="3">
				    	<input class="easyui-validatebox" type="text" name="enterprise.mbName" data-options="required:true" style="width:250px">
				    </td>
			    </tr>		    
			    <tr height="30px">
			  		<td class="form_label">上级机构：</td>
				    <td class="form_value">
					   <input name="enterprise.parentId" id="parentId" style="width:250px;">
				    </td>			    
				    <td class="form_label">所属区域：</td>
				    <td class="form_value">
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
			    <!-- 主体信息 -->
			    <tr height="30px">
			  		<td class="form_label">是否为收费用户：</td>
				    <td class="form_value">
				    	<input type="radio" name="enterprise.ischarge" checked="checked" value="1" />是&nbsp;&nbsp;
				     	<input type="radio" name="enterprise.ischarge" value="2" />否
				    </td>
				    
				    <td class="form_label">费用到期时间：</td>
				    <td class="form_value">
				      <input class="easyui-datebox" type="text" name="enterprise.expired" id="_expired" data-options="required:true" style="width:250px">
				    </td>
			    </tr>
			    <tr height="30px" id="ontrial" style="display:none;">
			  		<td class="form_label">试用开始时间：</td>
				    <td class="form_value">
				     	<input class="easyui-datebox" type="text" name="enterprise.ontrialStart" id="_ontrialStart" data-options="required:true" style="width:250px">
				    </td>
				    
				    <td class="form_label">试用结束时间：</td>
				    <td class="form_value">
				      <input class="easyui-datebox" type="text" name="enterprise.ontrialEnd" id="_ontrialEnd" data-options="required:true" style="width:250px">
				    </td>
			    </tr>
			    
			    <tr height="30px">
			  		<td class="form_label">主体类型：</td>
				    <td class="form_value">
				     	<select class="easyui-combobox" name="mbType" id="_mb_type" style="width:250px;">
				     		<option value="1">农业局</option>
				     		<option value="2">协会</option>
				     		<option value="3">企业</option>
				     	</select>
				    </td>
				    
				    <td class="form_label">域名：</td>
				    <td class="form_value">
				      <input class="easyui-validatebox" type="text" name="enterprise.mbDomain" style="width:250px" onblur="autoComplete(this);" data-options="required:true">
				    </td>
			    </tr>
			    
			    
			    <tr height="30px">
			    	<td class="form_label">是否有验证二维码：</td>
				    <td class="form_value">
				      	<input type="radio" name="enterprise.validCode" value="1" />是&nbsp;&nbsp;
				     	<input type="radio" name="enterprise.validCode" checked="checked" value="2" />否
				    </td>
			  		<td class="form_label">是否显示标识管理：</td>
				    <td class="form_value">
				     	<input type="radio" name="enterprise.showCode" checked="checked" value="1" />是&nbsp;&nbsp;
				     	<input type="radio" name="enterprise.showCode" value="2" />否
				    </td>
			    </tr>
			    
			    <tr height="30px">
			    	<td class="form_label">是否需要审核品种：</td>
				    <td class="form_value">
				      	<input type="radio" name="enterprise.auditPro" value="1" />是&nbsp;&nbsp;
				     	<input type="radio" name="enterprise.auditPro" checked="checked" value="2" />否
				    </td>
			  		<td class="form_label">是否需要审核批次：</td>
				    <td class="form_value">
				     	<input type="radio" name="enterprise.auditBatch" checked="checked" value="1" />是&nbsp;&nbsp;
				     	<input type="radio" name="enterprise.auditBatch" value="2" />否
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
			    <!-- <tr height="30px">			  		
			        <td class="form_label">签名：</td>
				    <td class="form_value" colspan="3">
				      <input class="easyui-validatebox" type="text" name="enterprise.sign" style="width:100%">
				    </td>
			    </tr> -->
				<tr height="30px">
			    	<td class="form_label">机构简介：</td>
				    <td class="form_value" colspan="3">
			    	<textarea name="enterprise.intro"  style="width:100%"></textarea>
			   		</td>
			    </tr>				    
			     <tr height="30px" id="userTr">
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
						    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="$('#interAccountDlg').window('close')">关 闭</a>
					    </div>    
			   		</td>
			    </tr>					     
		    </table>
		</form>
	   	</div>
	</div>  
	
		
	<div id="tagStyleDlg" class="easyui-window" title="二维码风格设置" data-options="iconCls:'icon-save',closed:true,modal:true,fit:true" >
					<div id="p" class="easyui-panel"   style="width:100%;height:auto;padding:10px;background:#fafafa;"   data-options="collapsible:false,minimizable:true,maximizable:true,fit:true">   
			    <div style="width: 100%">
	    				
	    				<div style="width: 100%" id="aixuexi">
				   			<table style="padding: 0px;width: 100%;" align="left">
				   				<tr>
				   					<td align="right" width="150px">选择风格:</td>   											  	   					
				   					<td align="left"> 
				   						<ul class="imglist" >
										    <li class="selected">
										    <span><img src="<%=basePath %>static/images/tagstyle1.png" width="130px" height="127px" title=""/></span>
										    <h2>二维码样式1</h2>
										    <p>
										    <input type="checkbox" class="scImg_checkbox" name="tsType" id="tsType1" value="1" checked="checked"/>
										    </p>
										    </li>
										    
										    <li class="selected">
										    <span><img src="<%=basePath %>static/images/tagstyle2.png" width="170px" height="127px" title=""/></span>
										    <h2>二维码样式2</h2>
										    <p>
										    <input type="checkbox" class="scImg_checkbox" name="tsType" id="tsType2" value="2" />
										    </p>
										    </li>
										    
										    <li class="selected">
										    <span><img src="<%=basePath %>static/images/tagstyle3.png" width="170px" height="127px" title=""/></span>
										    <h2>二维码样式3</h2>
										    <p>
										    <input type="checkbox" class="scImg_checkbox" name="tsType"  id="tsType3" value="3" />
										    </p>
										    </li>
										    
										    <li class="selected">
										    <span><img src="<%=basePath %>static/images/tagstyle4.png" width="130px" height="127px" title=""/></span>
										    <h2>二维码样式4</h2>
										    <p>
										    <input type="checkbox" class="scImg_checkbox" name="tsType"  id="tsType4" value="4" />
										    </p>
										    </li>
										    
									    </ul>
				   					</td>
				   				</tr>
				   			</table>
				   		</div>

				   		

				   		<div style="width: 100%">	
				   		<form id="tagStyleForm" method="post">
				   			<input type="hidden" name="tagStyle.tsId" >
				   			<input type="hidden" name="tagStyle.tsType" >
				   			<input type="hidden" name="tagStyle.entId" >
				   			<input type="hidden" name="tagStyle.toplogo" >
				   			<input type="hidden" name="tagStyle.codelogo">
				   			<input type="hidden" name="tagStyle.userId" >
				   			<input type="hidden" name="tagStyle.createTime" >
				   							   			
				   			<table style="width: 100%;padding: 0px;" align="left">
				   			
				   				<tr>
				   					<td align="right">标签尺寸:</td>
				   					<td colspan="2" align="left">
				   						<input type="radio" value="1" name="tagStyle.tagSize" />小(400*200px)  
				   						<input checked="checked" type="radio" value="2" name="tagStyle.tagSize" />中(480*240px)  
				   					</td>
				   				</tr>		
				   			
				   				<tr style="border-bottom: 1px;border-bottom-color: blue;">
				   					<td align="right" width="150px">头部LOGO:</td>   											  	   					
				   					<td align="left" width="150px"> 
				   						<input type="file" id="toplogoFile"/>
				   					</td>
				   					<td><div id="toplogofileQueue"></div></td>
				   				</tr>
				   				
				   				<tr>
							        <td></td>
							        <td colspan="2" align="left"><font color="red">logo规格为：中480*74px  小400*61px</font></td>
							    </tr>
				   				
				   				<tr style="border-bottom: 1px;border-bottom-color: blue;">
				   					<td align="right" width="150px">二维码LOGO:</td>   											  	   					
				   					<td align="left" width="150px"> 
				   						<input type="file" id="codelogoFile"/>
				   					</td>
				   					<td><div id="codelogofileQueue"></div></td>
				   				</tr>
				   				
				   				<tr>
							        <td></td>
							        <td colspan="2" align="left"><font color="red">logo规格为：200*200px</font></td>
							    </tr>
							    			
				   				<tr>
				   					<td align="right">备注:</td>
				   					<td colspan="2" align="left"><textarea class="easyui-validatebox" name="tagStyle.remark" data-options="validType:'length[0,500]'" rows="3" style="width: 550px"></textarea></td>
				   				</tr>		
				   				
				   			</table>
				   			
				   		</form>
				   		</div>
				   		
				   		<div style="width: 100%;">
				   				<div style="padding-left: 200px;padding-top: 50px">
				   						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="submitTagStyleForm()">保存</a>
				   				</div>
				   		</div>
				   				
				   	</div>  
			</div> 

	</div>
	
	<div id="styleConfigDlg" class="easyui-window" title="风格设置" data-options="iconCls:'icon-save',closed:true,modal:true,fit:true" >
		<iframe frameborder="0" scrolling="no" width="100%" height="100%" id="ifStyleConfig"></iframe>
	</div>
  </body>
</html>

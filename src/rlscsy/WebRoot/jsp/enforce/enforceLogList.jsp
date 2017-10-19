<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.comm.util.DateUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/";


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>执法管理 - 执法日志信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="${basePath}<%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js" type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js" type="text/javascript"></script>		
	<script src="${basePath}static/js/easyui-1.3.4/datagrid-detailview.js" type="text/javascript"></script>		
	
	<script src="${basePath}static/js/hontek/comm/hontek.js" type="text/javascript"></script>	
	<script src="${basePath}static/js/hontek/enforce/enforceLogList.js" type="text/javascript"></script>
	
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
  <input type="hidden" id="jsessionid" value="<%=session.getId() %>"> 	 
  <input type="hidden" id="h_path" value="<%=path %>">
  <input type="hidden" id="b_path" value="<%=basePath %>">
 <table id="list_enforceNode"></table>  
   
    <!-- list_enttype 工具栏 -->
    <div id="tb" >
		&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addEnforce()">添加</a>
		&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateLawUser()">修改</a>
		&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteLawUser()">删除</a>
						
		<div>
				&nbsp;&nbsp;
				执法人员名称: <input class="easyui-validatebox" style="width:150px" id="e_name">&nbsp;&nbsp;
				&nbsp;&nbsp;时间从: <input class="easyui-datebox" style="width:120px" id="startDate">
				&nbsp;&nbsp;到: <input class="easyui-datebox" style="width:120px" id="endDate">
						被执法企业:	<select id="selqq" class="easyui-combobox" style="width:200px;" name="lawLog.comId">   
									
						    	</select>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find()">搜 索</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
		</div>
	</div>
	
   
   
   
    <!-- 组织机构列表 -->
   <div id="add" class="easyui-window" title="添加执法日志" data-options="iconCls:'icon-house',closed:true" style="width:500px;height:300px;">
		 <div align="center" >
			    <form id="add_form" method="post" name="myform" >
				    <table class="formtable" cellspacing="1" cellpadding="0">
				    <tr>
					    	<td  align="right" class="form_label" style="width:45%;">执法人员:</td>
						    <td class="form_value">
						    	<select id="sel" class="easyui-combobox"  style="width:200px;" name="lawLog.userId">   
									<option >---请选择---</option>
						    	</select>
						    </td>
						   
					    </tr>
				     	<tr height="20px">
					  		<td  align="right" class="form_label">被执法企业:</td>
						    <td class="form_value">
						    	<select id="selq" class="easyui-combobox" style="width:200px;" name="lawLog.comId">   
									<option>---请选择---</option>
						    	</select>
						    </td>
					    </tr>
					      <tr height="20px">
						  <td align="right" class="form_label">执法日志编号：</td>
						<td class="form_value"><input class="easyui-validatebox"  type="text"
							name="lawLog.logNo"  data-options="required:true" style="width:200px;">
							</td>
						  
					</tr>
					  
					    <tr  height="20px">
					     <td  align="right" class="form_label">执法时间:</td>
						    <td class="form_value">
						    	<input id="time" class="easyui-datebox" type="text" name="lawLog.lawTime" data-options="required:true" style="width:200px;" >
						    </td>
					    </tr>
					     <tr  height="20px">
					     <td  align="right" class="form_label">处理结果:</td>
						    <td class="form_value">
						    	<input class="easyui-validatebox" type="text" name="lawLog.lawResult" data-options="required:true" style="width:200px;" >
						    </td>
					    </tr>
					    <tr  height="20px">
					     <td  align="right" class="form_label">执法地点:</td>
						    <td class="form_value">
						    	<input class="easyui-validatebox" type="text" name="lawLog.addr" data-options="required:true" style="width:200px;" >
						    </td>
					    </tr>
					    	<tr height="20px">
					  		
						    <td  align="right" class="form_label">执法单位:</td>
						    <td class="form_value">
						    	<input class="easyui-validatebox" type="text" name="lawLog.entName" data-options="required:true" style="width:200px;" >
						    	<input class="easyui-validatebox" type="hidden" name="lawLog.logId" >
						    	
						    </td>
						    
					    </tr>
					    <tr height="20px">
					     <td  align="right" class="form_label">附件:</td>
						    <td class="form_value" >
						    <input type="file" name="uploadify" id="uploadify"  />
						   <input name="lawLog.logApp" id="logApp" readonly="readonly" style="width:200px;" /><a href="javascript:void(0)" onclick="delLogApp();">清空</a>
						    <div id="divId"></div>
						    <input type ="hidden" id="uploadifyFileName" name="uploadifyFileName"/>
						    </td>
						   <td>
						   
						   <input type="hidden" name="lawLog.createTime" />
					     
					       </td> 
					     
					    </tr>
					   
					    <tr height="20px">
					    	<td class="form_value" colspan="4">
					    		<div style="text-align:center;padding:5px">
					    			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" data-options="iconCls:'icon-ok'">提交</a>
								    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-no" onclick="clearForm()">关闭页面</a>
								
							   </div>					    		
					    	</td>
					    </tr>			    
				    </table>
			    </form>
	       </div>
	</div>
	<div id="img" class="easyui-window" title="查看附件" data-options="iconCls:'icon-house',closed:true" style="width:500px;height:300px;">
	 <img id ="imgi" src="" width='480px' height='250px'/>
	</div>
  </body>
 
</html>

<%@page import="com.hontek.company.pojo.Company"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("loginCompany");
Company enterprise = (Company)obj;
if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}
String recId = request.getParameter("recId");
//批次名称
String batchName = request.getParameter("batchName");
batchName = batchName==null ? "" : batchName;
batchName = java.net.URLDecoder.decode(batchName , "UTF-8");

EntStyle companyStyle = session.getAttribute("companyStyle")==null?null:(EntStyle)session.getAttribute("companyStyle");
String styleFile = "blue";
if(companyStyle!=null){
	if(companyStyle.getScCss()!=null && !"".equals(companyStyle.getScCss())){
		styleFile = companyStyle.getScCss();
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>检验报告</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/checkinfo.js"></script>
<script type="text/javascript" src="js/json2.js"></script>

<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/default/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

<style type="text/css">
.weitianxie table td{line-height:25px;text-align: center;}

.combo .combo-text {
    border: 0 none;
    font-size: 12px;
    margin: 0;
    padding: 0 2px;
    vertical-align: top;
}

#proImgInfo td{line-height:20px;text-align: left;}

#proImgList table td input {
    border: 1px solid #ccc;
    height: 25px;
    width: 300px;
}

.uploadify-queue-item .uploadcancel a {
    background: rgba(0, 0, 0, 0) url("../uploadify/uploadify-cancel.png") no-repeat scroll 0 0;
    float: right;
    height: 16px;
    text-indent: -9999px;
    width: 20px;
}
</style>


</head>


<body>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="proTypeBatch.jsp" target="rightFrame" level="1"><%=batchName %></a></li>
	    <li>检验报告</li>
	    </ul>
	</div>
	
	<input type="hidden" id="h_recId" value="<%=recId %>"/>
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click" onclick="addRecord();"><span><img src="images/t01.png" /></span>添加</li>
        </ul>
        
    </div>
    
    <div id='proDiv'>
	    <table id="proTab" class="tablelist">
	    	<thead>
	    	<tr>
	        <th width="200px">报告名称</th>
	        <th width="80px">报告编号</th>
	        <th width="100px">检验单位</th>
	        <th width="80px">检验时间</th>
	        <th width="90px">检验结果</th>
	        <th width="90px">报告类型</th>
	        <th width="90px">操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        </tbody>
	    </table>
    
   
	    <div class="pagin">
	    	<div class="message">共<i class="blue" id="total">0</i>条记录，当前显示第&nbsp;<i class="blue" id="page">0</i>&nbsp;页</div>
	        <ul class="paginList">
	        </ul>
	    </div>
	    
    </div>
    
    <div class="center_content3" style="display: none;">
	    <div class="formbody">
	    
	    <div class="formtitle"><span>添加检验报告</span></div>
	    
	    <div style="width: 100%" >
	    		<table style="width: 100%">
	    		<tr>
	    		<td style="width: 65%;">
	    			<form id="recordForm" method="post">
				    	<input type="hidden" name="checkinfo.checkId" value="0"/>
				    	<input type="hidden" name="checkinfo.recId" value="<%=recId %>"/>
				    	<input type="hidden" id="jsonApp" name="jsonApp"/>
				    	<input type="hidden" id="ids" name="ids"/>
				    	<table>
				    		<tr>
				    			<td class="form_label" style="width:150px;">报告名称</td>
				    			<td class="form_value"><input name="checkinfo.checkName" type="text" class="easyui-validatebox" maxlength="50" data-options="required:true" /></td>
				    		</tr>
				    		<tr>
				    			<td class="form_label">报告编号</td>
				    			<td class="form_value"><input name="checkinfo.checkNum" type="text" class="easyui-validatebox" data-options="required:true"/></td>
				    		</tr>
				    		<tr>
				    			<td class="form_label">检验单位</td>
				    			<td class="form_value"><input name="checkinfo.checkUnit" type="text" class="easyui-validatebox" data-options="required:true" /></td>
				    		</tr>
				    		<tr>
				    			<td class="form_label">检验时间</td>
				    			<td class="form_value"><input name="checkinfo.checkTime" id="checkTime" type="text" class="easyui-datebox" style="width:300px;" data-options="required:true" /></td>
				    		</tr>
				    		<tr>
				    			<td class="form_label">检验结果</td>
				    			<td class="form_value"><input name="checkinfo.checkResult" type="text" class="easyui-validatebox" data-options="required:true" /></td>
				    		</tr>
				    		<tr>
				    			<td class="form_label">上传附件</td>
				    			<td class="form_value">
				    				<table width="100%;" style="margin:0;">
					    			<tr>
					    				<td  width="120"><input type="file" id="uploadify"/></td>
				    				</tr>
				    				<tr>
					    				<td><div id="fileQueue"></div></td>
					    			</tr>
					    		</table>
				    			</td>
				    		</tr>
				    		 
				    		<tr>
				    			<td class="form_label">报告类型</td>
				    			<td class="form_value">
				    				<select name="checkinfo.checkType" id="checkType_id" class="easyui-combobox" style="width:300px;">
							    		<option value="1">第三方检测报告</option>
							    		<option value="2">企业自检报告</option>
							    	</select>
				    			</td>
				    		</tr>
				    		
				    	</table>
				    	
				    	
					    
				    </form>
	    		</td>
	    		<td style="width: 35%;">
	    		<!-- 提示信息 -->
			        <div style="width: 350px;height: auto;float: right;">
				   		<div class="point" id="useguide">
				        </div>
			    	</div>
	
	    		</td>
	    		</tr>
	    	</table>
				                
	    </div>
	    <ul class="forminfo">
	    	<!-- 
		    <li><label>报告名称</label><input name="checkinfo.checkName" type="text" class="dfinput easyui-validatebox" maxlength="50" data-options="required:true" /></li>
		    <li><label>报告编号</label><input name="checkinfo.checkNum" type="text" class="dfinput easyui-validatebox" data-options="required:true"/></li>
		    <li><label>检验单位</label><input name="checkinfo.checkUnit" type="text" class="dfinput easyui-validatebox" data-options="required:true" /></li>
		    
		    <li><label>检验时间</label><input name="checkinfo.checkTime" id="checkTime" type="text" class="dfinput easyui-datebox" data-options="required:true" /></li>
		    <li><label>检验结果</label><input name="checkinfo.checkResult" type="text" class="dfinput easyui-validatebox" data-options="required:true" /></li>
		    <li><label>报告类型</label>
		    	<select name="checkinfo.checkType" class="dfinput">
		    		<option value="1">生产档案检测报告</option>
		    		<option value="2">企业自检报告</option>
		    	</select>
		    </li>
		     -->
		    <li><input id="subBut" name="" type="button" class="btn" onclick="submitForm();" value="确认保存"/>
		    	<label>&nbsp;</label><input name="" type="button" class="btn" onclick="exitContent();" value="取消"/>
		    </li>
	    </ul>
	    
	    
	    </div>
    </div>
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<input type="hidden" id="h_entId" value="<%=enterprise.getComId()%>"/>
    <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

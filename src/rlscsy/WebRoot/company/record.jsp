<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("enterprse");

if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}

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
<title>档案管理</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/record.js"></script>

<style type="text/css">
.weitianxie table td{line-height:25px;text-align: center;}
/*
.combo{
background-color: #fff;
border-color: #cccccc;
}
*/
#proImgInfo td{line-height:20px;text-align: left;}

#proImgList table td input {
    border: 1px solid #ccc;
    height: 25px;
    width: 300px;
}

.iconlist {
	border-top: 1px solid #3eafe0;
    border-bottom: 1px solid #3eafe0;
    height: 0px;
    /*cursor: pointer;*/
}


.iconlist li {
    float: left;
    margin-bottom: 15px;
    margin-right: 25px;
    margin-top: 25px;
    text-align: center;
    padding: 10px 10px 10px 0;
    width: 100px;
    cursor: pointer;
}

.iconlist li:HOVER{

	background: #fff none repeat scroll 0 0;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 2px 3px 5px #fafafa;
    cursor: pointer;
    float: left;
    margin-bottom: 15px;
    margin-right: 25px;
    margin-top: 25px;
   	padding: 10px 10px 10px 0;
   	width: 100px;
    text-align: center;
}

.iconlist:HOVER {
    background-color: #d2f4fe;
}
</style>


</head>


<body style="overflow: hidden">

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">档案管理</a></li>
    </ul>
    </div>
    
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
	        <th width="250px">档案名称</th>
	        <th width="200px">创建时间</th>
	        <th width="100px">排序</th>
	        <th width="100px">类型</th>
	        <th width="100px">档案状态</th>
	        <th width="150px">操作</th>
	        </tr>
	        </thead>
	        <tbody>
	       		 <tr>
	        		<td colspan="6" align="center">
	        			数据加载中...
	        		</td>
	        	</tr>
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
	    
	    <div class="formtitle"><span>添加档案</span></div>
	    
	    <form id="recordForm" method="post">
	    	<input type="hidden" name="record.recId" value="0"/>
	    	
	    	<table class="formtable">
	    		<tr>
	    			<td class="form_label">档案名称</td>
	    			<td class="form_value"><input name="record.recName" type="text" class="easyui-validatebox" maxlength="50" data-options="required:true" /></td>
	    		</tr>
	    		<tr id="recordType">
	    			<td class="form_label" sytle="width:150px;">档案分类</td>
	    			<td class="form_value">
	    				<select style="width: 300px;" class="easyui-combobox" id="h_recordType" name="recordType" onchange="recordTypeChange(this.value);">
				    		<%--<option value=1>普通</option>
				    		<option value=2>三品一标</option>
				    		<option value=3>种植类</option>
				    		<option value=4>畜牧类</option>
				    	--%></select>
	    			</td>
	    		</tr>
	    		<tr id="proType" style="display:none;">
	    			<td class="form_label" >产品分类</td>
	    			<td class="form_value"><input type="text" id="typeId" class="dfinput" name="typeId" style="width:300px;"/></td>
	    		</tr>

	    		<tr>
	    			<td class="form_label">排序</td>
	    			<td class="form_value"><input type="text" id="rSeq" class="easyui-numberbox" name="record.seq" value="5" data-options="required:true"/></td>
	    		</tr>
	    		<tr>
	    			<td class="form_label">档案状态</td>
	    			<td class="form_value">
	    				<select style="width: 300px;" name="record.recSts" id="recSts" class="easyui-combobox" >
				    		<option value=1>启用</option>
				    		<option value=2>停用</option>
				    	</select>
	    			</td>
	    		</tr>
	    	</table>
	    	
	    	
		    <ul class="forminfo">
			    <li><input id="subBut" name="" type="button" class="btn" onclick="submitForm();" value="确认保存"/>
			    	<label>&nbsp;</label><input name="" type="button" class="btn" onclick="exitContent();" value="取消"/>
			    </li>
		    </ul>
	    </form>
	    
	    </div>
    </div>
    
    </div>
    
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
    <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

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

String recId = request.getParameter("recId");

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
<title>三品一标</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/threea.js"></script>

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/default/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	

<style type="text/css">
.weitianxie table td{line-height:25px;text-align: center;}
.combo{
background-color: #fff;
border-color: #07aa94;
}
#proImgInfo td{line-height:20px;text-align: left;}

#proImgList table td input {
    border: 1px solid #ccc;
    height: 25px;
    width: 300px;
}

.forminfo li label {
    width: 120px;
}

.textinput{height: 100px;}
</style>


</head>


<body>

		<div class="place">
		    <span>位置：</span>
		    <ul class="placeul">
		    <li>生产档案</li>
		    <li>三品一标</li>
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
	        <th style="width:200px;overflow: hidden;">生产质量控制措施</th>
	        <th style="width:200px;overflow: hidden;">生产操作规程</th>
	        <th style="width:200px;overflow: hidden;">产地环境检验</th>
	        <th style="width:80px;overflow: hidden;">操作</th>
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
	    
	    <div class="formtitle"><span></span></div>
	    
	    <form id="recordForm" method="post">
	    	<input type="hidden" name="threea.thId" value="0"/>
	    	<input type="hidden" name="threea.recId" value="<%=recId %>"/>
	    	
	    	
	    	<table class="formtable">
	    		<tr>
	    			<td class="form_label">生产质量控制措施</td>
	    			<td class="form_value">
	    				<textarea name="threea.cuoshi" cols="" rows="" name="" class="textinput easyui-validatebox" data-options="required:true"></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="form_label">生产操作规程</td>
	    			<td class="form_value">
	    				<textarea name="threea.guicheng" cols="" rows="" name="" class="textinput easyui-validatebox" data-options="required:true"></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="form_label">产地环境检验</td>
	    			<td class="form_value">
	    				<textarea name="threea.jianyan" cols="" rows="" name="" class="textinput easyui-validatebox" data-options="required:true"></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="form_label">产品执行标准</td>
	    			<td class="form_value">
	    				<textarea name="threea.biaozhun" cols="" rows="" name="" class="textinput easyui-validatebox" data-options="required:true"></textarea>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="form_label">保证执行标准声明</td>
	    			<td class="form_value">
	    				<textarea name="threea.shengming" cols="" rows="" name="" class="textinput easyui-validatebox" data-options="required:true"></textarea>
	    			</td>
	    		</tr>
	    	</table>
	    	
		    <ul class="forminfo">
		   	 	<!-- 
			    <li><label>生产质量控制措施</label><textarea name="threea.cuoshi" cols="" rows="" name="" class="textinput easyui-validatebox" data-options="required:true"></textarea></li>
			    <li><label>生产操作规程</label><textarea name="threea.guicheng" cols="" rows="" name="" class="textinput easyui-validatebox" data-options="required:true"></textarea></li>
			    <li><label>产地环境检验</label><textarea name="threea.jianyan" cols="" rows="" name="" class="textinput easyui-validatebox" data-options="required:true"></textarea></li>
			    <li><label>产品执行标准</label><textarea name="threea.biaozhun" cols="" rows="" name="" class="textinput easyui-validatebox" data-options="required:true"></textarea></li>
			    <li><label>保证执行标准声明</label><textarea name="threea.shengming" cols="" rows="" name="" class="textinput easyui-validatebox" data-options="required:true"></textarea></li>
			     -->
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

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
<title>农业投入品购买</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/agriInput.js"></script>


<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />


<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>

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

</style>


</head>


<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li>生产档案</li>
	    <li>投入品购买记录</li>
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
	    	<th>投入品分类</th>	    	
	        <th>投入品名称</th>
	        <th>生产厂家</th>
	        <th>购买人</th>
	        <th>销售商</th>
	        <th>购买日期</th>
	        <th>购买数量</th>
	        <th>操作</th>
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
	    
	    <div class="formtitle"><span>添加档案</span></div>
			<div style="width: 100%" >
	    		<table style="width: 100%">
	    		<tr>
	    		<td style="width: 65%;">
	    			<form id="recordForm" method="post">
			    	<input type="hidden" name="agriInput.crttime"/>
			    	<input type="hidden" name="agriInput.agriImg"/>
			    	<input type="hidden" name="agriInput.agriId" value="0"/>
			    	<input type="hidden" name="agriInput.recId" value="<%=recId %>"/>
			    	
			    	<table class="formtable">
			    		<tr>
			    			<td class="form_label">投入品分类</td>
			    			<td class="form_value"><input name="agriInput.typeId" id="typeId" type="text" style="width:300px;" class="easyui-combobox" data-options="required:true,panelHeight:'auto'"/></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">投入品名称</td>
			    			<td class="form_value"><input name="agriInput.agriName"  id="agriInput_agriName" onblur="checkName()" type="text" class="easyui-validatebox" maxlength="50" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">生产厂家</td>
			    			<td class="form_value"><input name="agriInput.agriCompany" id="agriCompany" type="text"  class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">购买人</td>
			    			<td class="form_value"><input name="agriInput.buyUser" id="buyUser" type="text"  class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">销售商</td>
			    			<td class="form_value"><input name="agriInput.buyAddr" id="buyAddr" type="text" style="width:300px;" class="easyui-validatebox" data-options="required:true"/></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">规格型号</td>
			    			<td class="form_value"><input name="agriInput.agspc" id="agspc" type="text" style="width:300px;" class="easyui-validatebox" data-options="required:true"/></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">登记证号</td>
			    			<td class="form_value"><input name="agriInput.agnum" id="agnum" type="text" style="width:300px;" class="easyui-validatebox" data-options="required:true"/></td>
			    		</tr>			    		
			    		<tr>
			    			<td class="form_label">购买日期</td>
			    			<td class="form_value"><input name="agriInput.buyDate" id="buyDate" type="text" style="width:300px;" class="easyui-datebox" data-options="required:true"/></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">购买数量</td>
			    			<td class="form_value">
			    			<input name="agriInput.buyNum" id="buyNum" type="text" style="width: 240px;" class="easyui-validatebox" data-options="required:true" />
			    			<select name="agriInput.buyUnit" id="buyUnit" class="easyui-combobox" data-options="panelHeight:'auto'" style="width:60px;height: 27px;">
			    				<option value="千克">千克</option>
			    				<option value="升">升</option>
			    			</select>
			    			</td>
			    		</tr>
			    		
			    		
			    		<tr>
			    			<td class="form_label">外包装或说明书图片</td>
			    			<td class="form_value">
			    				<table style="float:none;margin:0;width: 345px;">
						    		<tr>
						    			<td style="width: 106px;"><input type="file" id="agriImg"/></td>
						    			<td style="vertical-align: top">
						    				<div id="prev" class="uploadify-button disabled" style="height: 20px; line-height: 20px; width: 100px;">
						    					<span class="uploadify-button-text"><a id="a_pre" style="color: #808080">预览</a></span>
						    				</div>
						    			</td>
						    		</tr>
						    		<tr >
					    				<td id="fileQueue" colspan="2"></td>
						    		</tr>
						    	</table>
			    			</td>
			    		</tr>
			    	</table>
			    </form>
	    		</td>
	    		<td style="width: 35%;">
	    		<!-- 提示信息 -->
			        <div style="width: 350px;height: 300px;float: right;">
				   		<div class="point" id="useguide">
				        </div>
			    	</div>
	
	    		</td>
	    		</tr>
	    	</table>
				                
	    </div>
	    
	     <ul class="forminfo">
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
	<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
    <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

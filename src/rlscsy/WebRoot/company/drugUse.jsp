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
<title>投入药品使用记录</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/drugUse.js"></script>

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>

<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	

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
	    <li><a href="proTypeBatch.jsp" target="rightFrame" level="1"><%=batchName %></a></li>
	    <li>药品使用记录</li>
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
	        <th>药品名称</th>
	        <th>防治对象</th>
	        <th>使用方法</th>  
	        <th>使用剂量</th>
	        <th>使用量</th>	   	        
	        <th>使用人</th>
	        <th>使用日期</th>	        	
	        <th>安全隔离天数</th>   
	        <th>安全采收期</th>
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
			    	<input type="hidden" name="drugUse.crttime"/>
			    	<input type="hidden" name="drugUse.drugId"/>
			    	<input type="hidden" name="drugUse.recId" value="<%=recId %>"/>
			    	
			    	<table class="formtable">
		                  <tr>
		                    <td class="form_label" style="width: 150px">药品名称</td>
		                    <td class="form_value"><input name="drugUse.drugName" type="text" id="drugName" class="easyui-combobox" style="width:300px;" maxlength="50" data-options="required:true" /></td>
		                  </tr>		                  
		                  <tr>
		                    <td class="form_label">防治对象</td>
		                    <td class="form_value"><input name="drugUse.useObject" id="useObject" type="text" class="easyui-validatebox" maxlength="50" data-options="required:true" /></td>
		                  </tr>
		                  <tr>
		                    <td class="form_label">施用方法</td>
		                    <td class="form_value">
			                    <select id="useWay" class="easyui-combobox" name="drugUse.useWay" style="width:300px;">   
								    <option value="喂食">喂食</option>   
								    <option value="注射">注射</option>   								    
								</select>  		             		                    
		                  </tr>
		                  		                  <tr>
		                    <td class="form_label">使用剂量</td>
		                    <td class="form_value"><input name="drugUse.useDosage" type="text" class="easyui-validatebox" maxlength="50" data-options="required:true" /></td>
		                  </tr>
		                  		                  
		                  <tr>
		                    <td class="form_label">施用量</td>
		                    <td class="form_value"><input name="drugUse.useTotal" id="useTotal" type="text" class="easyui-validatebox" style="width:300px;" data-options="required:true"/></td>
		                  </tr>
		                  		                  <tr>
		                    <td class="form_label">施用人</td>
		                    <td class="form_value"><input name="drugUse.useMan" id="useMan" type="text" class="easyui-validatebox" style="width:300px;"/></td>
		                  </tr>
		                  <tr>
		                    <td class="form_label">使用日期</td>
		                    <td class="form_value"><input name="drugUse.useDate" id="useDate" type="text" class="easyui-datebox" style="width:300px;" data-options="required:true"/></td>
		                  </tr>

		                  	<tr>
		                    <td class="form_label">安全隔离天数</td>
		                    <td class="form_value"><input name="drugUse.safeDay" id="safeDay" type="text" class="easyui-numberbox" style="width:300px;"/></td>
		                  </tr>
		                  <tr>
		                    <td class="form_label">安全采收期</td>
		                    <td class="form_value"><input name="drugUse.safeDate" id="safeDate" type="text" class="easyui-datebox" style="width:300px;"/></td>
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

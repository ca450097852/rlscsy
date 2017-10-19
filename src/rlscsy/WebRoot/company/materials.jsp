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
<title>原料信息</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/materials.js"></script>

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>

<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<link href="<%=basePath %>static/js/easyui-1.3.4/themes/default/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/browser.js"></script>
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

</style>


</head>


<body>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li>生产档案</li>
	    <li>原料信息</li>
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
	        <th width="15%">原料名称</th>
	        <th width="25%">购买日期</th>
	        <th width="15%">数量</th>	        
	        <th width="25%">原料产地</th>
	        <th width="20%">操作</th>
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
				    	<input type="hidden" name="materials.maId"/>
				    	<input type="hidden" name="materials.crttime"/>
				    	<input type="hidden" name="materials.userId"/>
				    	<input type="hidden" name="materials.recId" value="<%=recId %>"/>
				    	<input type="hidden" name="materials.maImg"/>
				    	<table class="formtable">
		                  	<tr>
				    			<td class="form_label">原料图片</td>
				    			<td class="form_value">
				    				<table style="float:none;margin:0;width: 345px;">
							    		<tr>
							    			<td style="width: 106px;"><input type="file" id="maImg"/></td>
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
				    		
				    		<tr>
			                    <td class="form_label" style="width: 150px">原料名称</td>
			                    <td class="form_value"><input name="materials.maName" type="text" class="easyui-validatebox" style="width:300px;" maxlength="50" data-options="required:true" /></td>
			                  </tr>
			                  
			                  <tr>
			                    <td class="form_label" style="width: 150px">购买日期</td>
			                    <td class="form_value"><input name="materials.buyTime" id="buyTime" type="text" class="easyui-datebox" style="width:300px;" maxlength="50" data-options="required:true" /></td>
			                  </tr>
			                  <tr>
			                    <td class="form_label" style="width: 150px">数量</td>
			                    <td class="form_value"><input name="materials.totalNum" type="text" class="easyui-validatebox" style="width:300px;" maxlength="50" data-options="required:true" /></td>
			                  </tr>
			                  
			                  <tr>
			                    <td class="form_label" style="width: 150px">原料产地</td>
			                    <td class="form_value"><input name="materials.addr" type="text" class="easyui-validatebox" style="width:300px;" maxlength="50" data-options="required:true" /></td>
			                  </tr>
			                 
			                 <tr>
			                    <td class="form_label" style="width: 150px">供应商名称</td>
			                    <td class="form_value"><input name="materials.supplyName" type="text" class="easyui-validatebox" style="width:300px;" maxlength="50" /></td>
			                  </tr>
			                  
			                  <tr>
			                    <td class="form_label" style="width: 150px">供应商地址</td>
			                    <td class="form_value"><input name="materials.supplyAddr" type="text" class="easyui-validatebox" style="width:300px;" maxlength="50" /></td>
			                  </tr>
			                  <tr>
			                    <td class="form_label" style="width: 150px">供应商联系电话</td>
			                    <td class="form_value"><input name="materials.supplyPhone" type="text" class="easyui-validatebox" style="width:300px;" maxlength="50"  /></td>
			                  </tr>
			                  
			                  
			                  
			                  <tr>
			                    <td class="form_label">备注</td>
			                    <td class="form_value"><input name="materials.remark"  type="text" class="easyui-validatebox" style="width:300px;"/></td>
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
	<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
    <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

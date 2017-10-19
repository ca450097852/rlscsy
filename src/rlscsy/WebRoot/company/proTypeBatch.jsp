<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@page import="com.hontek.company.pojo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("loginCompany");

if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}

Company enterprise = (Company)obj;


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
<title>批次信息</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>	
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/proTypeBatch.js"></script>
<script type="text/javascript" src="js/geo.js"></script>

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>

<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

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

</style>
<style type="text/css">
.point2{border:1px solid #d0dee5;padding:5px;background:#fff;}
.point2 font{line-height:30px;color:#F90;font-size:16px;background:url("images/point.png") no-repeat;padding-left:30px;height:30px;display:block;}
.point2 p{line-height:30px;margin:0 0 0 30px;}
</style>

</head>


<body>
<input type="hidden" id="jsessionid" value="<%=session.getId() %>"/> 
<input type="hidden" id="basePath" value="<%=basePath %>"/> 
<input type="hidden" id="comType" value="<%=enterprise.getComType() %>"/> 
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
			        <th width="100px">批次名称</th>
			        <th width="100px">生产时间</th>        
			        <th width="100px">批次二维码</th>
			        <th width="200px">档案信息</th>
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
	    
	    <div class="formtitle"><span>添加</span></div>
	    
	    <div style="width: 100%" >
	    		<table style="width: 100%">
	    		<tr>
	    		<td style="width: 65%;">
	    			<form id="recordForm" method="post">
	    			<input type="hidden" name="proTypeBatch.entId" id="entId" value="<%=enterprise.getComId()%>"/>
				    	<table class="formtable">
				    		<tr>
			                  <td class="form_label">产品种类</td>
			                  <td class="form_value">
			                  	<select class="easyui-combobox" id="_ptqId" name="proTypeBatch.ptqId" data-options="required:true" style="width:400px;"></select>
			                  </td>
			                </tr>
				    		<tr>
			                    <td class="form_label">批次名称</td>
			                    <td class="form_value"><input name="proTypeBatch.batchName" type="text" class="easyui-validatebox" style="width:400px;" maxlength="50" data-options="required:true" /></td>
			                  </tr>
        					<tr>
			                    <td class="form_label" style="width: 150px">生产日期</td>
			                    <td class="form_value"><input name="proTypeBatch.batchTime" type="text" class="easyui-datebox" style="width:400px;" data-options="required:true" /></td>
			                </tr>
			                
        		  </table>        	

				    	
				    </form>
	    		</td>
	    		<td style="width: 35%;">
	    			<!-- 提示信息 
			        <div style="width: 350px;height: auto;float: right;">
				   		<div class="point" id="useguide">
				        </div>
			    	</div>
					-->
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
    
    <div id="batchWin" class="easyui-window" title="批次二维码" style="width:500px;height:320px" data-options="iconCls:'icon-save',closed:true">   
    		<img id="codeImg" src="" alt=""  />      		 		
    		<a onclick="printBatchImg()" data-options="iconCls:'icon-print',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" >打印追溯标识图</a>   		
	</div>  
	
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
    <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

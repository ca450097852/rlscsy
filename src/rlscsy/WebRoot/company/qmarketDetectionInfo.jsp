<%@page import="com.hontek.company.pojo.Company"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("loginCompany");
Object nodeObject = session.getAttribute("node");

if(obj==null||nodeObject==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}
//批次ID
String ptbId = request.getParameter("ptbId");

//批次名称
String batchName = request.getParameter("batchName");
batchName = batchName==null ? "" : batchName;
batchName = java.net.URLDecoder.decode(batchName , "UTF-8");

Company company = (Company)obj;
int comId = company.getComId();
Company companyNode = (Company)nodeObject;

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
<title>批发市场肉类蔬菜检测信息</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/qmarketDetectionInfo.js"></script>

<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

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
	<input type="hidden" id="basePath" value="<%=basePath %>"/>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="proTypeBatch.jsp" target="rightFrame" level="1"><%=batchName %></a></li>
	    <li>批发检验管理</li>
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
	    	<th>批发市场编码</th>	    	
	        <th>批发市场名称</th>
	        <th>批发商编码</th>
	        <th>批发商名称</th>
	        <th>交易凭证号</th>
	        <th>动物产品检疫合格证号</th>
	        <th>肉品品质检验证号</th>
	        <th>检测日期</th>
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
	    
	   <input type="hidden" id="h_ptbId" value="<%=ptbId %>"/>
	    
	    <div class="formbody">
	    
	    <div class="formtitle"><span>添加档案</span></div>
			<div style="width: 100%" >
	    		<table style="width: 100%">
	    		<tr>
	    		<td style="width: 65%;" valign="top">
	    			<form id="recordForm" method="post">
			    	<input type="hidden" name="qmarketDetectionInfo.qdiId" />
			    	<input type="hidden" name="qmarketDetectionInfo.comId" value="<%=comId %>"/>
			    	<input type="hidden" name="qmarketDetectionInfo.ptbId" value="<%=ptbId %>"/>
			    	<input type="hidden" name="qmarketDetectionInfo.createTime" />
			    	
			    	<table class="formtable">
			    		<tr>
			    			<td class="form_label">批发市场编码</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.marketId"  type="text" value="<%=companyNode.getNodeCode()%>" class="easyui-validatebox" data-options="required:true"/></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">批发市场名称</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.marketName"  type="text" value="<%=companyNode.getName()%>" class="easyui-validatebox" data-options="required:true"/></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">批发商编码</td>
			    			<td class="form_value">
			    			<input name="qmarketDetectionInfo.wholesalerId" type="text" value="<%=company.getComCode()%>" class="easyui-validatebox" data-options="required:true" />
			    			</td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">批发商名称</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.wholesalerName" type="text" value="<%=company.getName()%>" class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">交易凭证号</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.tranId" type="text" class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">动物产品检疫合格证号</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.quarantineAnimalProductsId" type="text" class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">肉品品质检验合格证号</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.inspectionMeatId" type="text" class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">进货批次号</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.batchId" type="text" class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		
			    		
			    		
			    		<tr>
			    			<td class="form_label">商品编码</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.goodsCode" type="text" class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">商品名称</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.goodsName" type="text" class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">样品编号</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.sampleId" type="text" class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">检测员</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.detector" type="text" class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">检测日期</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.detectionDate" id="_detectionDate" type="text" class="easyui-datebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">检测结果</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.detectionResult" type="text" class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<tr>
			    			<td class="form_label">检测结果说明</td>
			    			<td class="form_value"><input name="qmarketDetectionInfo.resultExpl" type="text" class="easyui-validatebox" data-options="required:true" /></td>
			    		</tr>
			    		<!-- <tr>
			    			<td class="form_label">备注</td>
			    			<td class="form_value">
			    				<textarea name="qmarketDetectionInfo.remark" id="_remark" class="easyui-validatebox validatebox-text" style="width: 300px;height: 80px;"></textarea>
			    			</td>
			    		</tr> -->
			    		
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

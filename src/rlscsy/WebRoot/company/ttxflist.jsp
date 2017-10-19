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
<title>团体消费进货验收管理</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>	
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/ttxflist.js"></script>

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
    <div class="rightinfo">
    <div class="tools">    
    	<ul class="toolbar">
        	<li class="click" onclick="addTeamBuyInfo();"><span><img src="images/t01.png" /></span>添加</li>
        </ul>      
    </div>
     
    <div id='ttDiv'>
	    <table id="ttTab" class="tablelist">
	    	<thead>
		    	<tr>
			        <th width="100px">团体消费单位名称</th>
			        <th width="100px">进场日期</th>        
			        <th width="100px">商品编码</th>
			        <th width="100px">操作</th>
		        </tr>
	        </thead>
	        <tbody id="tbody">
	        </tbody>
	    </table>
	   <div style="margin:20px 0;"></div>
    </div>
    <div class="center_content3" style="display: none;">
	    <div class="formbody">
	    
	    <div class="formtitle"><span>添加</span></div>
	    
	    <div style="width: 100%" >
	    		<table style="width: 100%">
	    		<tr>
	    		<td style="width: 65%;">
	    			<form id="ttForm" method="post">
	    			<input type="hidden" id="comId" name="teamBuyAcceptanceInfo.comId" value="<%=enterprise.getComId() %>"/>
	    			<input type="hidden" id="tbaiId" name="teamBuyAcceptanceInfo.tbaiId" value=""/>
										            	<table class="formtable" border="0" cellspacing="0" cellpadding="0">
										            	<tr>
										                    <td class="form_label" width="150">团体消费单位编码：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.teamConsumeId" id="tt_teamConsumeId" class="easyui-validatebox" value="<%=enterprise.getComCode() %>"  data-options="required:true,validType:'length[0,100]'" /></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">团体消费单位名称：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.teamConsumeName" id="tt_teamConsumeName" class="easyui-validatebox" value="<%=enterprise.getName() %>" data-options="required:true,validType:'length[0,100]'" /></td>
										                  </tr>
										            	 <tr>
										                    <td class="form_label">进场日期：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.inDate" id="tt_inDate" class="easyui-datebox"  data-options="required:true" style="width: 300px;"/></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">供应商编码：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.supplierId" id="tt_supplierId" class="easyui-validatebox"  data-options="validType:'length[0,100]'" /></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">供应商名称：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.supplierName" id="tt_supplierName" class="easyui-validatebox"  data-options="validType:'length[0,100]'" /></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">交易凭证号：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.tranId" id="tt_tranId" class="easyui-validatebox"  data-options="validType:'length[0,100]'" /></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">商品编码：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.goodsCode" id="tt_goodsCode" class="easyui-validatebox"  data-options="validType:'length[0,100]'" /></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">商品名称：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.goodsName" id="tt_goodsName" class="easyui-validatebox"  data-options="validType:'length[0,100]'" /></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">重量：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.weight" id="tt_weight" class="easyui-validatebox"  data-options="validType:'length[0,100]'" /></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">单价：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.price" id="tt_price" class="easyui-validatebox"  data-options="validType:'length[0,100]'" /></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">供货市场或屠宰厂编码：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.wsSupplierId" id="tt_wsSupplierId" class="easyui-validatebox"  data-options="validType:'length[0,100]'" /></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">供货市场或屠宰厂名称：</td>
										                    <td class="form_value"><input name="teamBuyAcceptanceInfo.wsSupplierName" id="tt_wsSupplierName" class="easyui-validatebox"  data-options="validType:'length[0,100]'" /></td>
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
     <div class="easyui-panel">
        <div class="easyui-pagination"  id="epage"></div>
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
    <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

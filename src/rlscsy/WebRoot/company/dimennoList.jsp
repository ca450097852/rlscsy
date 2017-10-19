<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@page import="com.hontek.sys.pojo.TsEnterprise"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("enterprse");
TsEnterprise enterprise = (TsEnterprise)obj;
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

String auditPro = "1";
Object scObj = session.getAttribute("auditPro");
if(scObj!=null && !"".equals(scObj))
	auditPro = (String)scObj;

String auditBatch = "1";
scObj = session.getAttribute("auditBatch");
if(scObj!=null && !"".equals(scObj))
	auditBatch = (String)scObj;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>二维码下载</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script language="JavaScript" src="js/dimennoList.js"></script>
<script language="JavaScript" src="js/jquery.jqprint-0.3.js"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>

	<!-- 图片显示 -->
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.point{margin:30px 30px;border:1px solid #d0dee5;padding:10px;background:#fff;}
.point font{line-height:30px;color:#F90;font-size:16px;background:url("images/point.png") no-repeat;padding-left:30px;height:30px;display:block;}
.point p{line-height:30px;margin:0 0 0 30px;}
</style>

<script type="text/javascript">
	$(function(){
		$.ajax( {
			url : 'useguide_findUseguideList.action',
			data : {'useguide.ugNo':'zsbz'},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result&&result.length==1){
					var useguide = result[0];					
					var html = '<font>'+useguide.title+'</font>'+useguide.contents;
					$('#useguide').html(html);
				}
				
			}
		});

	});	
</script>

<style type="css/text">

</style>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>标识管理</li>
    </ul>
    </div>
    
    <input type="hidden" id="auditPro" value="<%=auditPro %>"/>
    <input type="hidden" id="auditBatch" value="<%=auditBatch %>"/>
    <input type="hidden" id="isBatch" value="<%=enterprise.getIsbatch() %>"/>
    
    <div class="rightinfo">
    
    <div class="center_content3" style="width: 650px;float: left">
	    <div class="formbody">
	    <div class="formtitle" ><span>企业追溯标识</span></div>
	    
	    <div id="entDiv"></div>
	    
	    <div class="formtitle" style="clear:both;"><span>产品追溯标识</span></div>
	    
	    <div id="typeDiv"></div>
	    
	    </div>
    </div>
    
   	<div style="width: 450px;height: 500px;float: left;">
   		<div class="point" id="useguide">
           	
            
        </div>

    </div>
    
    </div>
    <div id="printDiv" style="display: none;">
		<table>
			<tr>
				<td>
				
				</td>
			</tr>
			<tr>
				<td>
				<img src="" alt="" />
				</td>
			</tr>
		</table>    
    </div>
    
    <div id="batchWin" class="easyui-window" title="批次二维码" style="width:500px;height:320px" data-options="iconCls:'icon-save',closed:true">   
    		<img id="codeImg" src="" alt=""  />   
    		 		
    		<a onclick="printBatchImg()" data-options="iconCls:'icon-print',plain:true" class="easyui-linkbutton l-btn l-btn-plain" style="background-color: silver " href="javascript:void(0)" >打印追溯标识图</a>   		
	</div>  
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
</body>

</html>

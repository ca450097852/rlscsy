<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@page import="com.hontek.sys.pojo.TsEnterprise"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("enterprse");

if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}

TsEnterprise enterprise = (TsEnterprise)obj;


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
<script type="text/javascript" src="js/auditRecord.js"></script>
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
<input type="hidden" id="isbatch" value="<%=enterprise.getIsbatch() %>"/>
<input type="hidden" name="proTypeBatch.entId" id="entId" value="<%=enterprise.getEntId() %>"/>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li>产品种类</li>
	    <li>档案审核</li>
	    </ul>
	</div>
	
    <div class="rightinfo">
     
    <div id='proDiv'>
	    <table id="proTab" class="tablelist">
	    	<thead>
		    	<tr>
			        <th width="100px">名称</th>
			        <th width="100px">分类</th>
			        <th width="150px">生产时间</th>        
			        <th width="100px">二维码</th>
			        <th width="100px">状态</th>
			        <th width="100px">类型</th>
			        <th width="150px">提交时间</th>
			        <th width="150px">审核时间</th>
			        <th width="100px">操作</th>
		        </tr>
	        </thead>
	        <tbody>
	        </tbody>
	    </table>
    </div>
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
    <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

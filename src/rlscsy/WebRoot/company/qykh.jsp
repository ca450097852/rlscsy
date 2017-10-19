<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.company.pojo.Company"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@page import="com.hontek.company.pojo.CompanyUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object obj = session.getAttribute("loginCompany");
Object objUser = session.getAttribute("loginCompanyUser");

if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}
CompanyUser tsUser = null;
if(objUser!=null){
	tsUser = (CompanyUser)objUser;
}

Company loginCompany = (Company)obj;
String nodeType=(String)loginCompany.getComType();
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
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/easyuiExpand.js" type="text/javascript"></script>		
<script src="<%=basePath %>static/js/hontek/comm/hontek.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
    
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=basePath %>company/js/qykh.js"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>

<script type="text/javascript" src="<%=basePath %>static/js/xheditor-1.2.1/xheditor-1.2.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/xheditor-1.2.1/xheditor_lang/zh-cn.js"></script>

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<title></title>
		<style>
			*{
				margin: 0;
				border: 0;
				padding: 0;
			}
			.fl-lt{float: left;}
			.fl-rt{
				float: right;
			}
			.td-left{
			text-align: left;
			}
			li{
				list-style-type: none;
			}
			table{
				width: 100%;
				border-collapse: collapse;
				font-size: 13px;
				
			}
			th{
				border: 1px solid #ccc;
				max-width: 500px;
				text-align: center;
				line-height: 35px;
			}
			td{
				border: 1px solid #ccc;
				max-width: 500px;
			}
			table div{
				padding:5px 5px;
			}
			.table-info{
				width: 100%;
				height: 20px;
				margin:15px 0;
			}
			.table-info ul li{
				float: left;
				margin-right: 30px;
				font-size: 13px;
				font-weight: 700;
			}
			.table-info ul li:last-child{
				margin-right: 0;
			}
			.table-info ul li span{
				font-weight: normal;
				margin-left: 5px;
			}
		</style>
</head>
<body>
<input type="hidden" id="jsessionid" value="<%=session.getId() %>"/>
<input type="hidden" id="nodeType" value="<%=loginCompany.getComType() %>"/>
<input type="hidden" id="basePath" value="<%=basePath %>"/>
    <c:set var="nodeType" value="<%=nodeType %>"/>  
    <div style="font-size:20px;text-align:center">
    <c:if test="${nodeType=='1'}">屠宰企业</c:if>
<c:if test="${nodeType=='2'}">批发市场</c:if>
<c:if test="${nodeType=='3'}">菜市场</c:if>
<c:if test="${nodeType=='4'}">超市</c:if>
<c:if test="${nodeType=='5'}">团体消费单位</c:if>
追溯子系统考核评估测评表</div>
<form id='tForm'>
<div class="table-box">
<input type="hidden" name="comAssess.comId"  value="<%=loginCompany.getComId() %>"/>
<input type="hidden" id="itemIds" name="itemIds"/>
<input type="hidden" id="checkSelf" name="checkSelf"/>
			<div class="table-info">
			<ul>                                                  
					<li>企业名称：<span><input type="text" id='cName' name='comAssess.name' value="<%=loginCompany.getName() %>"/></span></li>
					<li>企业地址：<span><input type="text" id='cAddr' name='comAssess.addr' value="<%=loginCompany.getAddr() %>"/></span></li>
					<li>企业联系人及电话：<span><input type="text" id='cLinkUser' name='comAssess.linkUser' value="<%=loginCompany.getLinkMan() %>"/><input type="text" name='comAssess.phone' value="<%=loginCompany.getPhone() %>"/></span></li>
				</ul>
			</div>    

    </div>
    
    <table cellspacing="0">
				<thead>
					<th>考核指标</th>
					<th>指标描述</th>
					<th>自测 </th>
				</thead>
				<tbody id="khtbody">
				</tbody>
				
			</table>
			
			<div class="table-info">
				<ul class="fl-rt">                                                  
					<li>自测填表员：<span><input type='text' id='cInUser' name='comAssess.inUser' value="<%=tsUser.getRealName() %>"/></span></li>
					<li>填表日期：<span><input type='text' id='cInDate' name='comAssess.inDate' value="<%=(new SimpleDateFormat("yyyy-MM-dd")).format(new Date()) %>"/></span></li>
				</ul>
			</div>
		 <div class="btn_area" style="width:100px;margin:0 auto;display:block;">
		                <input   id="updateBtn" type="button" class="btn" onclick='submitForm();' value="提交"/>
		 </div>
</form>
</body>
</html>


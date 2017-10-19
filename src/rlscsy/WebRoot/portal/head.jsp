<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String navckeckId = request.getParameter("navckeckId");
	navckeckId = navckeckId==null ? "head":navckeckId;
	
	String css = "css/index.css";
	String logoImage = "images/logo.png";
	Object obj = session.getAttribute("entStyle_QT");
	if(obj!=null){
		EntStyle entStyle = (EntStyle)obj;
		if(entStyle.getScCss()!=null && !"".equals(entStyle.getScCss())){
			css = entStyle.getScCss();
		}
		if(entStyle.getLogoImage()!=null && !"".equals(entStyle.getLogoImage())){
			logoImage = "/nytsyFiles/entstyle/"+entStyle.getLogoImage();
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 如果使用IE浏览器，优先使用IE高版本 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" ></meta>
<!-- 如果使用双核浏览器，优先使用极速模式 -->
<meta name="renderer" content="webkit"></meta>
<title><%=session.getAttribute("systemName") %></title>
<link type="text/css" rel="stylesheet" href="<%=css %>"/>
<script type="text/javascript" src="js/head.js"></script>

<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
</head>

<body>
<input id="myBasePath" type="hidden" value="<%=basePath %>"/>
<div class="body">
	<!--header-->
            <div class="TOP">
            <div class="top_bg w mar0">
                <span class="fl">欢迎使用<%=session.getAttribute("systemName") %></span>
                <span class="fr"> <a href="<%=basePath%>jsp/login/login.jsp">监管用户登录</a> | <a href="<%=basePath%>company/login.jsp">企业用户登录</a> | <a href="<%=basePath%>portal/register.jsp">企业用户注册</a></span>
                <div class="clear"></div>
            </div>
        </div>
        <div class="padding_tb">
        	<div class="w mar0">
            	<div class="fl"><img src="<%=logoImage %>"></div>
                <div class="fr"><span class="search_area"><input id="proNamess" value="请输入需要搜索的企业名称"  onfocus="if (value =='请输入需要搜索的企业名称'){value =''}" onblur="if (value ==''){value='请输入需要搜索的内容'}"/><a class="search_icon" onclick="myTopSreach();"></a></span></div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="Navigation">
        	<div class="w mar0">
        		<ul>
            		<li <%if("head".equals(navckeckId)){ %>class="nav_click"<%} %>><a  href="<%=basePath%>portal/index.jsp">首页</a></li>
                	<li <%if("compt".equals(navckeckId)){ %>class="nav_click"<%} %>><a  href="<%=basePath%>portal/complaint.jsp">投诉举报</a></li>
                	<li <%if("sycx".equals(navckeckId)){ %>class="nav_click"<%} %>><a  href="<%=basePath%>portal/suyuan.jsp">溯源查询</a></li>
                	<li <%if("jmqy".equals(navckeckId)){ %>class="nav_click"<%} %>><a href="<%=basePath%>portal/company.jsp">企业名录</a></li>
            	    <li <%if("news".equals(navckeckId)){ %>class="nav_click"<%} %>><a href="<%=basePath%>portal/news.jsp">应急管理</a></li>
            	
            	</ul>
            </div>
        </div>
	<!--header=end-->

	
</div>
</body>
</html> 

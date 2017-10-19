<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String navckeckId = request.getParameter("navckeckId");
	navckeckId = navckeckId==null ? "head":navckeckId;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>    
    <title>头部</title>
	<link href="<%=basePath%>static/css/portalweb/head.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath %>static/js/hontek/portalweb/head.js"　type="text/javascript"></script>
  </head>
  <body>
 	 <input id="myBasePath" type="hidden" value="<%=basePath %>"/>
		<DIV class="TOP">
        <div class="logocenter">
			<div class="logo"><img src="<%=basePath%>static/image/portalweb/logo.png" /></div>
			<div class="top_search">
            	<p><strong>溯源企业</strong></p>
                <div class="search_div">
                <span><input id="proNamess"/></span>
                <span class="top_search_btn"><a href="javascript:void(0);" onclick="mySreach();">搜索</a></span></div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="navigation">
        	<ul>
        		<li <%if("head".equals(navckeckId)){ %>class="check"<%} %>><a href="<%=basePath%>index.jsp">首页</a></li>
        		<li <%if("tzgg".equals(navckeckId)){ %>class="check"<%} %>><a href="<%=basePath%>portalweb/allNews_list.jsp?typecode=tzgg">通知公告</a></li>
                <li <%if("news".equals(navckeckId)){ %>class="check"<%} %>><a href="<%=basePath%>portalweb/news_list.jsp">新闻发布</a></li>
                <li <%if("zcxc".equals(navckeckId)){ %>class="check"<%} %>><a href="<%=basePath%>portalweb/allNews_list.jsp?typecode=zcxc">政策宣传</a></li>
                <li <%if("compt".equals(navckeckId)){ %>class="check"<%} %>><a href="<%=basePath%>portalweb/complaint.jsp">投诉举报</a></li>
                <li <%if("sycx".equals(navckeckId)){ %>class="check"<%} %>><a href="<%=basePath%>portalweb/suyuan.jsp">溯源查询</a></li>
                <li <%if("jmqy".equals(navckeckId)){ %>class="check"<%} %>><a href="<%=basePath%>portalweb/company_list.jsp">企业名录</a></li>
            </ul>
        </div>
    </DIV>
  </body>
</html>

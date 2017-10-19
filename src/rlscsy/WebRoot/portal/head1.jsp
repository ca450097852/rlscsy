<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/protype/";
	String navckeckId = request.getParameter("navckeckId");
	navckeckId = navckeckId==null ? "head":navckeckId;
	
	String uri = request.getRequestURI();
	
	String mainEntId = "";
	
	String css = "css/dgreen.css";
	String logoImage = "images/logo_01.png";
	String entPhone = "020-8765 9788";
	if(session.getAttribute("entPhone")!=null && !"".equals(session.getAttribute("entPhone"))){
		entPhone = (String)session.getAttribute("entPhone");
	}
	Object obj = session.getAttribute("entStyle_QT");
	if(obj!=null){
		EntStyle entStyle = (EntStyle)obj;
		
		mainEntId = entStyle.getEntId()+"";
		
		if(entStyle.getScCss()!=null && !"".equals(entStyle.getScCss())){
			css = basePath+entStyle.getScCss();
		}
		if(entStyle.getLogoImage()!=null && !"".equals(entStyle.getLogoImage())){
			logoImage = "/nytsyFiles/entstyle/"+entStyle.getLogoImage();
		}
	}
%>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link href="css/product-slide.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/lubotu.js"></script>
		<script language="javascript" type="text/javascript" src="js/product-slide.js"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<!--<link rel="stylesheet" type="text/css" href="<%=css %>" />-->
<script type="text/javascript" src="js/head.js"></script>

<input id="basePath" type="hidden" value="<%=basePath %>"/>

<input id="mainEntId" type="hidden" value="<%=mainEntId %>"/> 
 
			<div class="header">
				<div class="top">
					<div class="top-container">
						<p class="user">
						</p>
						<div class="search">
							<input type="text" placeholder="输入您要搜索的企业名称" id="proNamess">
							<button onclick="myTopSreach();"></button>
						</div>
					</div>
				</div>
				<div class="header-content">
					<div class="logo">
						<img src="<%=logoImage %>">
					</div>
					<div class="tel">
						<img src="images/tel.png">
						<p><%=entPhone %></p>
					</div>
					<div class="nav">
						<ul>
							<li>
							<a href="<%=basePath%>portal/index.jsp" <%="index".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>首页</a>
							</li>
							<li>
							<a href="<%=basePath %>portal/plant.jsp" <%="product".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>溯源产品</a>
							</li>
							<li>
      						<a href="<%=basePath %>portal/news.jsp" <%="news".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>应急管理</a>
							</li>
							<li>
      						<a href="<%=basePath%>portal/complaint.jsp" <%="complaint".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>投诉举报</a>
							</li>
							<li>
							<a href="<%=basePath%>portal/suyuan.jsp" <%="suyuan".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>溯源查询</a>
							</li>
							<li>
							<a href="<%=basePath%>portal/company.jsp" <%="company".equals(navckeckId)?"class=\"nav_li_sel\"":"" %>>企业名录</a>
							</li>
						</ul>
						<div class="login">
							<li class="login01"><a href="<%=basePath %>portal/register.jsp" class="login_03">企业用户注册</a></liclass="login01">
							<li class="login02"><a href="<%=basePath %>company/login.jsp" class="login_02"> 企业用户登录</a></liclass="login02">
							<li class="login03"><a href="<%=basePath %>jsp/login/login.jsp" class="login_01">监管用户登录</a></li class="login03">
						</div>
					</div>
					
					<div class="header-decoration"></div>
</div>
			</div>
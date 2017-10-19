<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String infoId = request.getParameter("infoId");

%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 如果使用IE浏览器，优先使用IE高版本 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" ></meta>
<!-- 如果使用双核浏览器，优先使用极速模式 -->
<meta name="renderer" content="webkit"></meta>
<title><%=session.getAttribute("systemName") %></title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/newsdetail.js"></script>

</head>

<body>
<div class="container"> 
  <!--head start-->
  <jsp:include page="head1.jsp" ><jsp:param value="news" name="navckeckId"/></jsp:include>
  
  <input type="hidden" id="infoId" value="<%=infoId %>"/>
  
  <!--news_detail-->
  
  <div class="main clearfix">
				<div class="main-content">
					<div class="left-box clearfix">
						<h3 class="page-title"><span>应急管理</span></h3>
						<div class="news-detail">
							<h4 id="info_title"></h4>
							<div class="date02"><span id="tit"></span></div>
						</div>
							<div class="news_detail_article" id="infoContent">
    </div>
					</div>
					<div class="right-box clearfix">
					<div class="tips">	
					    <h4>热门资讯</h4>
					    <ul class="right-box-list" id="infotitle">
					    	
					    </ul>
					</div>
				</div>
				</div>
			</div>
  <!--footer-->
  <jsp:include page="bottom1.jsp"></jsp:include>
</div>
</body>
</html>

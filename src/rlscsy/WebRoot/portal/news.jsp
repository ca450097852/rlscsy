<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script type="text/javascript" src="js/news.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
</head>

<body>
<div class="container"> 
  <input type="hidden" id="pageNum"/>
  <!--head start-->
  <jsp:include page="head1.jsp" ><jsp:param value="news" name="navckeckId"/></jsp:include>

  <!--news-->
			<div class="main clearfix">
				<div class="main-content">
				<div class="left-box clearfix">
					<h3 class="page-title"><span>应急管理</span></h3>
					<div class="left-box-content">
						<div class="news-page">
							<ul id="news_list">
           					 </ul>
							
						</div>
					</div>
					<div class="page pt20 mb10">
				<input name="total" type="hidden" id="total" />
				<input name="pageNum" value="1" type="hidden" id="pageNum" />
				<span id="pageTools"> </span>
			</div>
				</div>
				<div class="right-box clearfix">
					<div class="tips">	
					    <h4>应急管理</h4>
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

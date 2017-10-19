<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/nytsyFiles/company/";

	String entName = request.getParameter("entName");
	entName = entName == null ? "" : entName;
	entName = java.net.URLDecoder.decode(entName, "UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=session.getAttribute("systemName") %></title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="css/product-slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/company.js"></script>
<style type="text/css">
.compage{
		padding: 3px;
	margin: 10px 0;
	text-align: center
		}
	.compage a {
	border: 1px solid #1f9f7d;
	padding: 3px 8px;
	margin: 2px;
	color: #1f9f7d;
}

.compage a:hover {
	border: 1px solid #007153;
	color: #007153;

}

.compage a:active {
	border: 1px solid #007153;
	color: #000;
	
}

.compage span.current {
	border: 1px solid #007153;
	padding: 3px 8px;
	font-weight: bold;
	margin: 2px;
	color: #fff;
	background: #007153;
}

.compage span.disabled {
	padding: 3px 8px;
	border: #eee 1px solid;
	margin: 2px;
	color: #ddd;
}
</style>
</head>

<body>
	<div class="container ">
		<input type="hidden" id="entName" value="<%=entName%>" /> <input
			type="hidden" id="filePath" value="<%=filePath%>" /> <input
			type="hidden" id="basePath" value="<%=basePath%>" />
		<!-- head -->
  		<jsp:include page="head1.jsp" ><jsp:param value="index" name="navckeckId"/></jsp:include>
  		<!-- head end -->
		<div class="main clearfix">
			<div class="main-content">
				<div class="left-box clearfix">
					<h3 class="page-title">
						<span>企业名录</span>
					</h3>

					<div class="left-box-content">
						<div class="company-catalog">
							<ul id="qiye_list">
							</ul>
						</div>
					</div>
				</div>
				
				<div class="right-box clearfix">
				 <div class="tips">
					<h4>应急管理</h4>
					<ul class="right-box-list" id="news_list">
					
					</ul>
				</div>

				</div>
			
			</div>

			
		</div>
		
		 <!-- 分页 -->
	<div class="compage">
		<input name="total" type="hidden" id="total" />
		<input name="pageNum" value="1" type="hidden" id="pageNum" />
		<span id="pageTools"> </span>
	</div>

		<!-- footer -->
			<jsp:include page="bottom1.jsp" />
			<!-- footer end-->
	
		
	</div>
</body>
</html>

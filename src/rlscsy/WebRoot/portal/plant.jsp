<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/nytsyFiles/protype/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>广州肉菜溯源|溯源产品</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="css/product-slide.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/plant.js"></script>
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
	<input name="total" type="hidden" id="total" />
	<input name="pageNum" value="1" type="hidden" id="pageNum" />
	<div class="container ">



			<jsp:include page="head1.jsp"><jsp:param value="news"
					name="navckeckId" /></jsp:include>
			<div class="main clearfix">
				<div class="main-content">
					<div class="left-box clearfix">
						<h3 class="page-title">
							<span>溯源产品</span>
						</h3>
						<input type="hidden" id="filePath" value="<%=filePath%>" />
						<div class="product-page">
							<ul id="qiye_list"></ul>
						</div>
							
						<div></div>

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
		
		<span id="pageTools"> </span>
	
			<jsp:include page="bottom1.jsp"><jsp:param value="news"
					name="navckeckId" /></jsp:include>
		</div>
</body>
</html>

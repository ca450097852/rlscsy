<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String basePath2 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页-产品展示</title>
<link href="<%=basePath%>static/css/portalweb/center.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath%>static/js/hontek/portalweb/index_product.js"　type="text/javascript"></script>

<style type="text/css">
table.altrowstable {
	font-family: Simhei;
	font-size:1em;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.altrowstable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #2CEF87;
}
table.altrowstable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
.headcolor{
	background-color:rgba(0, 0, 0, 0);
	background:url("<%=basePath%>static/image/portalweb/title_bg.png") repeat-x;
}
.oddrowcolor{
	background-color:#00000;
}
.evenrowcolor{
	background-color:#fdfbf4;/*d4e3e5*/
}

</style>
</head>
  
 
<body>
		<input id="basePath" type="hidden" value="<%=basePath%>" />
		<input id="basePath2" type="hidden" value="<%=basePath2%>" />
<div class="CENTER_content_left">

			<!-- 1、畜牧类溯源信息 start -->
			<div class="product_title">
				<img src="<%=basePath%>static/image/portalweb/icon.png" />
				<font>最新畜牧类溯源信息</font>
			</div>
			
			<div class="product_list">
				<div class="index_product_text">
			
					<table id="animal_product_list" width="100%" border="0" cellpadding="0" cellspacing="0">
						
					
					</table>
					
					<div class="clear"></div>
		            <div class="page">
						<input name="total1" type="hidden" id="total1" />
						<input name="pageNum1" value="1" type="hidden" id="pageNum1" />
						<span id="pageTools1"> </span>
					</div>
					
	            </div>

			
			</div>
			<!-- 1、畜牧类溯源信息 end -->
			
			
			<!-- 2、种植类溯源信息 start -->
			<div class="product_title">
				<img src="<%=basePath%>static/image/portalweb/icon.png" />
				<font>最新种植类溯源信息</font>
			</div>
			
			<div class="product_list">
				<div class="index_product_text">
			
					<table id="index_company_list" width="100%" border="0" cellpadding="0" cellspacing="0">
						
					
					</table>
					
					<div class="clear"></div>
		            <div class="page">
						<input name="total2" type="hidden" id="total2" />
						<input name="pageNum2" value="1" type="hidden" id="pageNum2" />
						<span id="pageTools2"> </span>
					</div>
					
	            </div>

			
			</div>
			<!-- 2、种植类溯源信息 end -->
			
			<!-- 4、分类溯源信息 start 
			<div class="product_title">
				<img src="<%=basePath%>static/image/portalweb/icon.png" />

				<font>分类溯源信息</font>
			</div>
			
			<div class="product_list">
				<div class="index_product_text">
			
					<table id="proType_company_list" width="100%" border="0" cellpadding="0" cellspacing="0">
						
					
					</table>
					
					<div class="clear"></div>
		            <div class="page">
						<input name="total4" type="hidden" id="total4" />
						<input name="pageNum4" value="1" type="hidden" id="pageNum4" />
						<span id="pageTools4"> </span>
					</div>
					
	            </div>

			
			</div>
			
			-->
			<!-- 4、种植类溯源信息 end -->
			
			<!-- 3、溯源产品信息 start -->
			<!--
			<div class="product_title">
				<img src="<%=basePath%>static/image/portalweb/icon.png" />
				<font>溯源产品</font>
			</div>
			<div class="product_list" id="product_list">
			</div>
			<div class="page">
				<input name="total" type="hidden" id="total" />
				<input name="pageNum" value="1" type="hidden" id="pageNum" />
				
				<span id="pageTools"> </span>
			</div>
			-->
			<!-- 3、溯源产品信息 end -->
		</div>
</body>
</html>

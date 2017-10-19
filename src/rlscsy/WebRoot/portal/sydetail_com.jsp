<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String filePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/nytsyFiles/";

	String dimenno = request.getParameter("dimenno");
	dimenno = dimenno == null ? "440200000079" : dimenno;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 如果使用IE浏览器，优先使用IE高版本 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge"></meta>
<!-- 如果使用双核浏览器，优先使用极速模式 -->
<meta name="renderer" content="webkit"></meta>
<title>广州肉菜溯源|溯源查询</title>

<link rel="stylesheet" type="text/css" href="css/style.css" />
<link href="css/product-slide.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="js/product-slide.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/sydetail.js"></script>
</head>

<body>
	<div class="container ">
 <input type="hidden" id="dimenno" value="<%=dimenno %>"/>
	<input type="hidden" id="code" value="<%=dimenno%>"/>
    <input type="hidden" id="filePath" value="<%=filePath%>"/>
    <input type="hidden" id="basePath" value="<%=basePath%>"/>
			<div class="main-content">
				<div class="big-box clearfix">
					
					<div class="big-box-content">
						<div class="detail">
						<div class="detail_nav clearfix" id="ss"> 
                	
                    
							 <div class="detail_block div_content">
						</div>
					</div>
				</div>


			</div>
		</div>
		
		</div>
		
</body>
</html>


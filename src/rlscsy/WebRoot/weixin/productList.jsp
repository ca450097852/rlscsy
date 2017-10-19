<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广东省农产品质量安全监管追溯平台-产品展示</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>		
<script src="<%=basePath %>weixin/js/productList.js" type="text/javascript"></script>		
<link type="text/css" rel="stylesheet" href="<%=basePath %>weixin/css/weixin.css"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no" /> 
		
</head>
  
<body>
<div id="INDEX">

	    <div class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="images/icon.png" /> <font>产品列表</font></div>
                <div class="search">
                	<div class="search_area"><input type="text" id="searchText" placeholder="输入产品名称"/><span onclick="searchText()"></span></div>
                </div>
            	
                <div class="product_list" id="product_list">


                </div>
				<div class="page">
	                <input name="total" type="hidden" id="total"/>
	                <input name="pageNum" value="1" type="hidden" id="pageNum"/>
	                <input id="basePath" type="hidden" value="<%=basePath %>"/>
	                <input id="basePath2" type="hidden" value="<%=basePath2 %>"/>
	               	<span id="pageTools">
	               	<!-- 分页工具 -->
	                </span>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
	
    <div class="BOTTOM">
    	广东省农业厅版权所有       技术支持:广州薪火网络科技有限公司 
    </div>         	
 </div>
</body>
</html>

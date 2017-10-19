<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";

String proNmaes = request.getParameter("proNmaes");
proNmaes = proNmaes==null ? "" : proNmaes;
proNmaes = java.net.URLDecoder.decode(proNmaes , "UTF-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-新闻列表</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/product.css"/>
<script src="<%=basePath %>static/js/hontek/portalweb/product_list.js"　type="text/javascript"></script>
</head>
  
<body>
<DIV>
<jsp:include page="head.jsp"><jsp:param value="cpzs" name="navckeckId"/></jsp:include>
 
 <DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>溯源产品</font></div>
                <div class="product_list" id="product_list">
                	
                    <!-- 产品展示 -->
                    
                </div>
                <div class="page">
                <input name="total" type="hidden" id="total"/>
                <input name="pageNum" value="1" type="hidden" id="pageNum"/>
                <input id="basePath" type="hidden" value="<%=basePath %>"/>
                <input id="basePath2" type="hidden" value="<%=basePath2 %>"/>
                <input id="proNmaes" type="hidden" value="<%=proNmaes %>"/>
               	<span id="pageTools">
               	<!-- 分页工具 -->
                </span>
                </div>
            </div>
            
            <div class="CENTER_content_right">
            	
            	<!-- 两个信息列表  -->
                <jsp:include page="news_info.jsp"></jsp:include>
                
            	<!-- 企业列表  -->
                <jsp:include page="company_info.jsp"></jsp:include>
                
            </div>
        </div>
        <div class="clear"></div>
    </DIV>
    
 <jsp:include page="bottom.jsp"></jsp:include>
 </DIV>
</body>
</html>

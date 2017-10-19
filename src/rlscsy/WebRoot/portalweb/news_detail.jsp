<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String infoId = request.getParameter("infoId");
infoId = infoId==null ? "" : infoId;

String typecode = request.getParameter("typecode");
typecode = typecode==null ? "news" : typecode;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-新闻列表</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/news_detail.css"/>
<script src="<%=basePath %>static/js/hontek/portalweb/news_detail.js"　type="text/javascript"></script>
</head>
  
<body>
<DIV>
<input id="infoId" type="hidden" value="<%=infoId %>"/>
<jsp:include page="head.jsp"><jsp:param value="<%=typecode %>" name="navckeckId"/></jsp:include>
 <DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font id="weizhi">新闻详情 </font></div>
                <div class="news_detail">
                	<h3 id="info_title"></h3>
                    <div class="article_source" id="tit"></div>
                    <p id="info_content">
                    </p>
                </div>
            </div>
            
            <div class="CENTER_content_right">
            	<!-- 两个信息列表  -->
                <jsp:include page="news_info.jsp"></jsp:include>
                
                
                <!--<div class="info">
                	<div class="info_title"><span>更多>></span><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>加盟企业</font></div>
                    <div class="firm">
                    	<h4>贺州市贺街业旺蔬菜合作社</h4>
                        <div class="firm_content">
                        	<div class="firm_logo"><img src="<%=basePath %>static/image/portalweb/20130227150958855.jpg" /></div>
                            <div class="firm_text">以供港澳蔬菜生产标准来保障蔬菜的质量；用一流的品牌服务消费大众。</div>
                            <div class="clear"></div>
                        </div>
                    </div>
                    <div class="firm">
                    	<h4>贺州市贺街业旺蔬菜合作社</h4>
                        <div class="firm_content">
                        	<div class="firm_logo"><img src="<%=basePath %>static/image/portalweb/20130227150958855.jpg" /></div>
                            <div class="firm_text">以供港澳蔬菜生产标准来保障蔬菜的质量；用一流的品牌服务消费大众。</div>
                            <div class="clear"></div>
                        </div>
                    </div>
                    <div class="firm">
                    	<h4>贺州市贺街业旺蔬菜合作社</h4>
                        <div class="firm_content">
                        	<div class="firm_logo"><img src="<%=basePath %>static/image/portalweb/20130227150958855.jpg" /></div>
                            <div class="firm_text">以供港澳蔬菜生产标准来保障蔬菜的质量；用一流的品牌服务消费大众。</div>
                            <div class="clear"></div>
                        </div>
                    </div>
                </div>-->
            </div>
        </div>
        <div class="clear"></div>
    </DIV>
 <jsp:include page="bottom.jsp"></jsp:include>
 </DIV>
</body>
</html>

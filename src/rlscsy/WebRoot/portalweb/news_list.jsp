<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-新闻列表</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/news_list.css"/>
<script src="<%=basePath %>static/js/hontek/portalweb/news_list.js"　type="text/javascript"></script>
</head>
  
<body>
<DIV>
<jsp:include page="head.jsp"><jsp:param value="news" name="navckeckId"/></jsp:include>
 <DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>新闻发布</font></div>
                <div class="news_list" >
                <div  class="newsdiv_btn">
                    	<span><a id="newsType1" class="curre" onclick="changeInfoType(1)">农产品安全事件公布</a></span>
                        <span><a id="newsType2" onclick="changeInfoType(2)">违规企业公布</a></span>
                        <span><a id="newsType3" onclick="changeInfoType(3)">农产品追回公布</a></span>
                        <div class="clear"></div>
                </div>
                	<ul id="news_list">
                    	<!-- 信息列表部份 -->
                    </ul>
                </div>
                <div class="page">
                <input name="total" type="hidden" id="total"/>
                <input name="pageNum" value="1" type="hidden" id="pageNum"/>
                <input id="basePath" type="hidden" value="<%=basePath %>"/>
                <span id="pageTools">第1页/共37页  <a href="javascript:void(0)" onclick="goPage();">首页</a>  <a>上一页</a>  <a>下一页</a>  <a>尾页</a>  跳转至:<select name="" onchange="goPage(this.value)">
                  <option value="" selected="selected">1</option>
                </select>
                </span>
                </div>
            </div>
            
            <div class="CENTER_content_right">
            	
				<!-- 两个信息列表  -->
                <jsp:include page="index_info.jsp"></jsp:include>

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

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
<script src="<%=basePath %>static/js/hontek/portalweb/news_info.js"　type="text/javascript"></script>
</head>
  
<body>
<input id="InfoPath" type="hidden" value="<%=basePath %>"/>
<div class="info">
    <div class="info_title"><span><a href="<%=basePath %>portalweb/allNews_list.jsp?typecode=tzgg">更多</a>>></span><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font id="tzgg_title">通知公告</font></div>
         <div class="info_content">
              	<ul id="tzgg">
                  	 
              </ul>
        </div>
</div>
<div class="info">
    <div class="info_title"><span><a href="<%=basePath %>portalweb/allNews_list.jsp?typecode=zcxc">更多</a>>></span><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font id="zcxc_title">政策宣传</font></div>
         <div class="info_content">
              	<ul id="zcxc">
                  	 
              </ul>
        </div>
</div>
</body>
</html>

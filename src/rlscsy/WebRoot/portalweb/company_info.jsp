<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String infofilePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/company/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-新闻列表</title>
<script src="<%=basePath %>static/js/hontek/portalweb/company_info.js"　type="text/javascript"></script>
<style type="">
.info_content{padding:0 5px;}
.info_content ul{margin:0;padding:0;}
.info_content ul li{border-bottom:1px dashed #ccc;height:30px;line-height:30px;list-style:inside;}
</style>
</head>
  
<body>
<input id="BPath" type="hidden" value="<%=basePath %>"/>
<input id="infofilePath" type="hidden" value="<%=infofilePath %>"/>
 <div class="info">
 <div class="info_title" ><span><a href="<%=basePath %>portalweb/company_list.jsp">更多</a>>></span><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>最新地市溯源企业</font></div>
    <div class="info_content">
       <ul id="info_title">
           
        </ul>
    </div>       
</div>
</body>
</html>

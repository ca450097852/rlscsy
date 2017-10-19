<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-举报投诉</title>
<link href="${basePath}static/js/easyui-1.3.4/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}static/js/easyui-1.3.4/themes/default/combo.css" rel="stylesheet" type="text/css"/>

<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/complaint.css"/>
	<script src="${pageContext.request.contextPath}/static/js/xheditor-1.2.1/jquery/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/hontek/complaint/jquery.validate.js" type="text/javascript"></script>

</head>
  
<body>
<DIV>
<jsp:include page="head.jsp"><jsp:param value="compt" name="navckeckId"/></jsp:include>
<DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>投诉举报提示</font></div>
	                <div class="complaint_content" align="center">
	                	<img src="<%=basePath %>static/image/portalweb/tishi.jpg" />
                	</div>
            </div>
            
            <div class="CENTER_content_right">
            <!-- 右边列表 -->
            <jsp:include page="complaint_info.jsp"></jsp:include>
            
            </div>
        </div>
        <div class="clear"></div>
    </DIV>
 <jsp:include page="bottom.jsp"></jsp:include>
 </DIV>
</body>
</html>

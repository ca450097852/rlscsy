<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>    
    <title>底部</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>static/css/portalweb/bottom.css" rel="stylesheet" type="text/css" />
  </head>
<body>
		<DIV class="BOTTOM">
    	版权所有  广东省农业厅 技术支持 广州薪火网络科技有限公司 &nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=basePath%>jsp/login/login.jsp">后台管理</a>
    	 &nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=basePath%>company/login.jsp">企业登陆</a>
    	</DIV>
</body>
</html>

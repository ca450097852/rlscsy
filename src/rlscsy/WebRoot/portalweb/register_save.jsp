<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String result = request.getParameter("result");
result = result==null ? "" : result;
result = java.net.URLDecoder.decode(result , "UTF-8");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-企业注册</title>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/register_save.css"/>
<script src="<%=basePath %>static/js/hontek/portalweb/jquery-1.8.3.min.js"　type="text/javascript"></script>
</head>
  
<body>
<DIV>

<DIV class="TOP">
        <div class="logocenter">
			<div class="logo" align="center"><img src="<%=basePath%>static/image/portalweb/logo.png" align="center"/></div>
            <div class="clear"></div>
        </div>

</DIV>

<DIV class="CENTER">
        <div class="CENTER_content">
	                <div align="center">
	                	 <img src="<%=basePath %>static/image/portalweb/register-ok.png" style="width: 600px;"/>
                	</div>
                	<div  align="center" style="font-size: 18px;font-style: normal;height: 50px;">
	                	<%=result %>
	                	<br/>
	                	<div class="clear" align="center" style="height: 50px;"></div>
	                	<a href="<%=basePath%>company/login.jsp">
	                	<font color="blue">
	                	点击这里，登录企业管理后台
	                	</font>
	                	</a>&nbsp;&nbsp;&nbsp;
	                	<a href="<%=basePath%>index.jsp">返回首页</a>
	                	<br/>
	                	<br/>
	                	<br/>
                	</div>
                	<div  align="center" style="height:100px;">
                	</div>
        </div>
        <div class="clear"></div>
    </DIV>

<DIV class="BOTTOM">
    	版权所有  广东省农业厅 技术支持 广州薪火网络科技有限公司 &nbsp;&nbsp;
</DIV>
    	
    	
 </DIV>
</body>
</html>

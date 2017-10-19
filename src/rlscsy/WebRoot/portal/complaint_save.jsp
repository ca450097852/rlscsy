<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 如果使用IE浏览器，优先使用IE高版本 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" ></meta>
<!-- 如果使用双核浏览器，优先使用极速模式 -->
<meta name="renderer" content="webkit"></meta>
<title><%=session.getAttribute("systemName") %></title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="css/public.css"/>
</head>
  
<body>

<div class="container">
<jsp:include page="head1.jsp" ><jsp:param value="complaint" name="navckeckId"/></jsp:include>

    <!--mainbox-->
	<div class="mainbox clear clearfix">

        
        
       <!--投诉举报-->

        <div class="width mt50 hidden" style="margin: 20px auto 0;width: 850px;">
        	<div class="content_left width">
            	<div class="complaint_know">
                	<div class="complaint_success_content">
                    	<h3 class="green">您的举报提交成功！</h3>
                        <div class="success_txt">
                            <p class="font18">感谢您的提交，我们会及时处理您的举报！</p>
                            <div><button class="submit" onclick="window.location.href='index.jsp'">返回首页</button></div>
                    	</div>
                    </div>
                </div>
            </div>
       </div>
       
       <!--投诉举报=end-->
       
    </div>
    <!--mainbox=end-->

	<!--footer-->
	
	 <jsp:include page="bottom1.jsp"></jsp:include>
	
	<!--footer=end-->
</div>

</body>
</html>

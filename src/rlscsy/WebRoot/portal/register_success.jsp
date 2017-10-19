<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String  jsonType = (String)request.getParameter("jsonType");//用来保存，是否注册成功；
jsonType = jsonType==null?"":jsonType;
String  entCode = (String)request.getParameter("entCode");//保存提示信息
entCode = entCode==null?"":entCode;
entCode = java.net.URLDecoder.decode(entCode , "UTF-8");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 如果使用IE浏览器，优先使用IE高版本 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" ></meta>
<!-- 如果使用双核浏览器，优先使用极速模式 -->
<meta name="renderer" content="webkit"></meta>
<title>注册提示</title>
<link type="text/css" rel="stylesheet" href="<%=basePath %>portal/css/public.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript">
function toLogin(){
	document.location.href="../company/login.jsp";
}
</script>
<style type="text/css">

.clearfix::after {
    clear: both;
    content: ".";
    display: block;
    height: 0;
    visibility: hidden;
}
.mainbox {
    width: 100%;
}
.clearfix {
    display: block;
}

.width {
    clear: both;
    margin-left: auto;
    margin-right: auto;
    width: 1036px;
}
.mt50 {
    margin-top: 50px;
}
.hidden {
    overflow: hidden;
}
.content_left {
    float: left;
}

.complaint_know {
    border: 2px solid #e8e8e8;
    padding: 2px;
}
.error_content {
    border: 2px solid #f5f5f5;
    padding: 20px 25px;
}

.error_content h3 {
    background: rgba(0, 0, 0, 0) url("images/error2.png") no-repeat scroll 0 0;
    font-size: 40px;
    height: 128px;
    line-height: 133px;
    margin-left: 282px;
    padding-left: 132px;
}

.error_txt {
    border-top: 1px solid #f2f2f2;
    margin-top: 30px;
    padding-top: 30px;
    text-align: center;
}

.error_txt p {
    padding: 20px 0;
}
.font18 {
    font-size: 18px;
}

</style>
</head>

<body>
<div class="container">
	<!--header-->
    <jsp:include page="head1.jsp"></jsp:include>
	<!--header=end-->


    <!--mainbox-->
	<div class="mainbox clear clearfix">
        
        <!--注册成功-->
        <div class="width mt50 hidden">
        	<div class="content_left width">
            	<div class="complaint_know">
            		<%if(jsonType.equals("true")){ %>
                	<div class="success_content">
                    	<h3 class="green">恭喜注册成功！</h3>
                        <div class="success_txt">
                         	<p class="font18"><%=entCode %></p>
                            <p class="font18">请登录企业管理后台继续完善资料！</p>
                            <div  style="margin:0 auto; width:125px; "><button class="submit" style="cursor: pointer" onclick="toLogin()">立即登录</button></div>
                            <p class="font20"><a href="index.jsp">返回首页</a></p>
                    	</div>
                    </div>
                    <%}else{ %>
                    <div class="error_content">
                    	<h3 class="grey">注册失败！</h3>
                        <div class="error_txt">
                         	<p class="font18"><%=entCode %></p>
                            <p class="font20"><a href="register.jsp">返回注册页面</a></p>
                            <p class="font20"><a href="index.jsp">返回首页</a></p>
                    	</div>
                    </div>
                    <%} %>
                </div>
            </div>
       </div>
       
       
       
       <!--注册成功=end-->
       
       <!--line-->
       
       
        
    </div>
    <!--mainbox=end-->

	<!--footer-->
	<jsp:include page="bottom1.jsp"></jsp:include>
	<!--footer=end-->
</div>
</body>
</html>  



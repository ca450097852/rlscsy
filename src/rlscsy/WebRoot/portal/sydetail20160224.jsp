<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/";

String dimenno = request.getParameter("dimenno");
dimenno = dimenno==null ? "440200000079" : dimenno;
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
<script type="text/javascript" src="js/sydetail20160224.js"></script>

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />


</head>
  
<body>
	<input type="hidden" id="dimenno" value="<%=dimenno %>"/>
	<input type="hidden" id="code" value="<%=dimenno%>"/>
    <input type="hidden" id="filePath" value="<%=filePath%>"/>
    <input type="hidden" id="basePath" value="<%=basePath%>"/>
<div class="container">
	<!--header-->
	
		<jsp:include page="/portal/head.jsp"><jsp:param value="sycx" name="navckeckId"/></jsp:include>
		
	<!--header=end-->
	<div class="w mar0">
       
    	<div class=""><img src="images/banner02.jpg"></div>

	</div>  

    <!--mainbox-->
	<div class="w mar0">         
      	<!--溯源查询结果-->
        <div class="w mt50 mar0">
           <div class="title_area">
           		<div class="title_area_Bg">
                	<div class="results_nav">
                    	<a class="taptitle nav_check" herf="#firstAnchor">企业信息</a>
                        <!-- 
                        <a class="taptitle">主体档案</a>
                        <a class="taptitle">生产档案</a>
                         -->
                    </div>
                </div>
           </div>
       </div>  

	   
	  <div class="w mar0">
           <div class="div_content ml40 mr20">
           		<div class="entdetail">
           		<div class="tc font24 light-grey" id="firstAnchor">——企业信息——</div>
           		<div class="line_bb"><span class="font18">基本信息</span></div>
                <div class="mt20 hidden ml40">
                	<div class="fleft wid400" id="qyjbxx">
                    	<!-- js填充 -->
                    </div>
                    <div class="fleft wid400" id="qylxfs">
                    	<!-- js填充 -->
                    </div>
                </div>
                <div class="line_bb"><span class="font18">企业简介</span></div>
                <div class="mt20 hidden ml40">
                	<div class="div_txt" id="qyjj">
                		<!-- js填充 -->
                	</div>
                </div>
                
                
                </div>
                
   
           </div>
           
           
       </div>
       
       <!--溯源查询结果=end-->
       
       <!--line-->


    </div>
    <!--mainbox=end-->

	<!--footer-->
	
	 <jsp:include page="/portal/bottom.jsp"></jsp:include>
	
	<!--footer=end-->
</div>

</body>
</html>

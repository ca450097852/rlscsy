<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/protype/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 如果使用IE浏览器，优先使用IE高版本 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" ></meta>
<!-- 如果使用双核浏览器，优先使用极速模式 -->
<meta name="renderer" content="webkit"></meta>
<title>肇庆市农产品质量安全追溯平台</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/animal.js"></script>
</head>
  
<body>

<input id="filePath" type="hidden" value="<%=filePath %>"/>  

<div class="container">
	<!--header-->
	
		<jsp:include page="/portal/head.jsp"></jsp:include>
		
	<!--header=end-->


    <!--mainbox-->
	<div class="mainbox clear clearfix">
        
        <!--banner-->
        <div class="inner-banner">
			<div style="height:200px;width:100%;background:url(images/inner-banner01.jpg) center 0 no-repeat;"></div>
        	<%--<img src="images/inner-banner.jpg" width="1876" height="200" alt="xxxxxxx" />--%>
        </div>      
        <!--/.banner-->        
        
       
       <!--最新畜牧类溯源信息-->
       <div class="width mt80 hidden">
          <div class="common-tt">
              <div class="fleft"><h3>最新畜牧类溯源信息</h3><span class="en-txt">NEW&nbsp;INFORMATION</span></div>
              
          </div>
          
          <ul class="information-cont mt30" id="animalul">
              

          </ul>
	   </div>       
       
      <div class="width hidden">
			<!-- 分页 -->
			<div class="page pt20 mb10">
				<input name="total" type="hidden" id="total" />
				<input name="pageNum" value="1" type="hidden" id="pageNum" />
				<span id="pageTools"> </span>
			</div>

			<!--page
	        <div class="page pt20 mb10"><span class="num">1/10</span><span class="pre"><a href="#">上一页</a></span><span><a href="#">1</a></span><span><a href="#">2</a></span><span><a href="#">3</a></span><span><a href="#" class="hover">4</a></span><span class="next"><a href="#">下一页</a></span><span><input type="text" class="page_input" /></span><input type="button" class="page_btn"  value="确定"/></div>
	        <!--/.page-->                      
       </div> 
       
       
    </div>
    <!--mainbox=end-->

	<!--footer-->
	
	 <jsp:include page="/portal/bottom.jsp"></jsp:include>
	
	<!--footer=end-->
</div>

</body>
</html>

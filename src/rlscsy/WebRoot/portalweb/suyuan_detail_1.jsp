<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String dimenno = request.getParameter("dimenno");
dimenno = dimenno==null ? "" : dimenno;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-产品溯源查询结果</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/jquery.cookie.js" type="text/javascript"></script>

<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/suyuan_detail.css"/>
<script type="text/javascript">

//溯源查询数字输入
$(function() {
	var dimenno = $('#dimenno').val();
	var dimennoUrl = "http://trace1.gdahsi.org/checkJyEbCode_.action?ebcode_="+dimenno;
	$("Iframe").attr("src",dimennoUrl);		
	/*
	if(dimenno!=''){
		var dimennoUrl = "http://trace1.gdahsi.org/checkJyEbCode_.action?ebcode_="+dimenno;
		$("Iframe").attr("src",dimennoUrl);		
	}else{
		$("#suyuan_text").html("<img src=\"../static/image/portalweb/defaultPic.jpg\"/>");
	}
	*/
	//$(window.frames["myiframes"].document).find("#saveBtn").click();
	//$(window.frames["myiframes"].document).find("INDEX").css('font-size:','14px');

	/*$("Iframe").load(function() { 
		alert("111");
		//$(window.frames["myiframes"].document).find("#saveBtn").click();
		$('#myiframes')[0].contentWindow.checkFunction();
	}); */
	
	
});
</script>
</head>
  
<body>
<DIV>
<input id="dimenno" type="hidden" value="<%=dimenno %>"/>
<jsp:include page="head.jsp"><jsp:param value="sycx" name="navckeckId"/></jsp:include>
<DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>溯源结果</font></div>
                <div class="suyuan_content">
                	<div class="suyuan_text" id="suyuan_text" align="center">
                    <Iframe id="myiframes" src="" scrolling="yes"  frameborder="0" height="100%" width="100%" style="font-size:14px !important"></iframe>
                    </div>
                </div>
            </div>
            
            <div class="CENTER_content_right">
            	
			<!-- 两个信息列表  -->
                <jsp:include page="news_info.jsp"></jsp:include>

            </div>
        </div>
        <div class="clear"></div>
    </DIV>
 <jsp:include page="bottom.jsp"></jsp:include>
 </DIV>
</body>
</html>
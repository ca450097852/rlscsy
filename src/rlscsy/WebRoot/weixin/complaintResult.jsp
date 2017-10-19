<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String cid = request.getParameter("cid") == null ? "" : request.getParameter("cid") ;

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广东省农产品质量安全监管追溯平台-投诉举报</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>		
<script src="<%=basePath %>weixin/js/complaintResult.js" type="text/javascript"></script>		
<link type="text/css" rel="stylesheet" href="<%=basePath %>weixin/css/weixin.css"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no" /> 
		
</head>
  
<body>
<input id="cid" type="hidden" value="<%=cid %>"/>

<div id="INDEX">
	<DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="images/tousu.png" /> <font>投诉举报</font></div>
                    <div class="complaint_result" id="complaint_result">
                		
                	</div>
            </div>
            <div class="clear"></div>
        </div>
    </DIV>
	
    <div class="BOTTOM">
    	广东省农业厅版权所有       技术支持:广州薪火网络科技有限公司 
    </div>         	
 </div>
</body>
</html>

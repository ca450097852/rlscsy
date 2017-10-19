<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String typecode = request.getParameter("typecode");
typecode = typecode==null ? "":typecode;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-举报投诉</title>
<link href="${basePath}static/js/easyui-1.3.4/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<link href="${basePath}static/js/easyui-1.3.4/themes/default/combo.css" rel="stylesheet" type="text/css"/>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/news_list.css"/>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/complaint.css"/>
	<script src="${pageContext.request.contextPath}/static/js/xheditor-1.2.1/jquery/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/hontek/complaint/jquery.validate.js" type="text/javascript"></script>
	<script src="<%=basePath %>static/js/hontek/portalweb/allComplaint_list.js"　type="text/javascript"></script>
</head>
  
<body>
<DIV>
<jsp:include page="head.jsp"><jsp:param value="compt" name="navckeckId"/></jsp:include>
<DIV class="CENTER">
       <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font id="weizhi"></font></div>
                <div class="news_list" >
                	<ul id="complaint_list">
                    	<!-- 信息列表部份 -->
                    </ul>
                </div>
                <div class="page">
                <input name="total" type="hidden" id="total"/>
                <input name="pageNum" value="1" type="hidden" id="pageNum"/>
                <input id="basePath" type="hidden" value="<%=basePath %>"/>
                <input id="typecode" type="hidden" value="<%=typecode %>"/>
                <span id="pageTools">第1页/共37页  <a href="javascript:void(0)" onclick="goPage();">首页</a>  <a>上一页</a>  <a>下一页</a>  <a>尾页</a>  跳转至:<select name="" onchange="goPage(this.value)">
                  <option value="" selected="selected">1</option>
                </select>
                </span>
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

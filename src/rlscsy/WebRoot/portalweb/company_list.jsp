<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/company/";

String entName = request.getParameter("entName");
entName = entName==null ? "" : entName;
entName = java.net.URLDecoder.decode(entName , "UTF-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-加盟企业</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>		
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/jmqy.css"/>
<script src="<%=basePath %>static/js/hontek/portalweb/company_list.js"　type="text/javascript"></script>
</head>
  
<body>
<DIV>
<jsp:include page="head.jsp"><jsp:param value="jmqy" name="navckeckId"/></jsp:include>
 <DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font id="weizhi">企业名录</font></div>
                <div class="qiye_list" id="qiye_list">
                	<!-- 信息列表部份 -->
                	
                </div>
                
                <div class="page">
                <input name="total" type="hidden" id="total"/>
                <input name="pageNum" value="1" type="hidden" id="pageNum"/>
                <input id="basePath" type="hidden" value="<%=basePath %>"/>
                <input id="entName" type="hidden" value="<%=entName %>"/>
                <input id="filePath" type="hidden" value="<%=filePath %>"/>               
                
                <span id="pageTools">第1页/共37页  <a href="javascript:void(0)" onclick="goPage();">首页</a>  <a>上一页</a>  <a>下一页</a>  <a>尾页</a>  跳转至:<select name="" onchange="goPage(this.value)">
                  <option value="" selected="selected">1</option>
                </select>
                </span>
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

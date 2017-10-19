<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String cid = request.getParameter("cid");
cid = cid==null ? "" : cid;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-举报投诉</title>
<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/complaint.css"/>
	<script src="${pageContext.request.contextPath}/static/js/xheditor-1.2.1/jquery/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="<%=basePath %>static/js/hontek/portalweb/complaint_detail.js"　type="text/javascript"></script>
</head>
  
<body>
<DIV>
<input id="cid" type="hidden" value="<%=cid %>"/>
<jsp:include page="head.jsp"><jsp:param value="compt" name="navckeckId"/></jsp:include>
<DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>投诉举报详情</font></div>
                <form name="entForm" id="entForm"  method="post" enctype="multipart/form-data">
	                <div class="complaint_content">
	                	<dl class="div_line01" style="hight:50px;">
	                        <dt><strong>标题</strong></dt>
	                        <dd id="complaint_title" ></dd>
	                        <div class="clear"></div>
	                    </dl>
	                   
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>举报时间</strong></dt>
	                        <dd id="complaint_crttime" ></dd>
	                        <div class="clear"></div>
	                    </dl>
	                     <!-- 
	                    <dl class="div_line02" style="hight:50px;">
	                        <dt>举报人电话</dt>
	                        <dd></dd>
	                        <div class="clear"></div>
	                    </dl>
	                     -->
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>所在区域</strong></dt>
	                        <dd id="complaint_entName"></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <!-- -<dl class="div_line02" style="hight:50px;">
	                        <dt>区域:</dt>
	                        <dd><input name="complaint.entId" id="entId" /></dd>
	                        <div class="clear"></div>
	                    </dl> 
	                    -->
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>处理状态</strong></dt>
	                        <dd id="complaint_sts"></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    
	                    <dl class="div_line03">
	                        <dt class="hei"><strong>举报内容</strong></dt>
	                        <dd >
	                        <textarea id="complaint_content" cols="66" rows="4" readonly="readonly"></textarea>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <!-- 工单处理结果 -->
	                    <dl class="div_line03" style="hight:100px;display: none;" id="show_finalResult">
	                        <dt><strong>处理结果</strong></dt>
	                        <dd>
	                        <textarea id="complaint_finalResult" cols="66" rows="4" readonly="readonly"></textarea>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <!-- 举报处理结果 -->
	                    <dl class="div_line03" style="hight:100px;display: none;" id="show_remark">
	                        <dt class="hei"><strong>处理结果</strong></dt>
	                        <dd >
	                        <textarea id="complaint_remark" cols="66" rows="4" readonly="readonly"></textarea>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    
                </div>
                </form>
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

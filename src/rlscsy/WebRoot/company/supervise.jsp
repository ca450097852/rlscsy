<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("enterprse");

if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}

EntStyle companyStyle = session.getAttribute("companyStyle")==null?null:(EntStyle)session.getAttribute("companyStyle");
String styleFile = "blue";
if(companyStyle!=null){
	if(companyStyle.getScCss()!=null && !"".equals(companyStyle.getScCss())){
		styleFile = companyStyle.getScCss();
	}
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>监管信息管理</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<link href="<%=basePath %>static/js/easyui-1.3.4/themes/default/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/supervise.js"></script>

<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.weitianxie table td{line-height:25px;text-align: center;}
.combo{
background-color: #fff;
border-color: #07aa94;
}
#proImgInfo td{line-height:20px;text-align: left;}

#proImgList table td input {
    border: 1px solid #ccc;
    height: 25px;
    width: 300px;
}

</style>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>监管信息</li><%--
    <li>监管列表</li>
    --%></ul>
    </div>
    
    <div class="rightinfo">
    <div id='proDiv'>
	    <table id="proTab" class="tablelist">
	    	<thead>
	    	<tr>
	        <!-- <th><input name="" type="checkbox" value="" onchange="selectEvent(this.checked);"/></th> -->
	        <th>信息标题</th>
	        <th>监管时间</th>
	        <th>信息内容</th>
	        <th>操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        	<tr>
	        		<td colspan="4" align="center">
	        			数据加载中...
	        		</td>
	        	</tr>
	        </tbody>
	    </table>
    
   
	    <div class="pagin">
	    	<div class="message">共<i class="blue" id="total">0</i>条记录，当前显示第&nbsp;<i class="blue" id="page">0</i>&nbsp;页</div>
	        <ul class="paginList">
	        </ul>
	    </div>
	    
    </div>
    
    <div class="center_content3" style="display: none;">
	    <div class="formbody">
	    
	    <div class="formtitle"><span>监管信息</span></div>
	    <table class="formtable">
        	<tr>
        		<td class="form_label" width="150px">信息标题</td>
        		<td class="form_value" id="supTitle"></td>
        	</tr>
        	<tr>
        		<td class="form_label">监管时间</td>
        		<td class="form_value" id="supTime"></td>
        	</tr>
        	<tr>
        		<td class="form_label">监管信息</td>
        		<td  class="form_value" id="supContents"></td>
        	</tr>
        	<!-- <tr>
        		<td class="form_label">企业回复</td>
        		<td class="form_value"><textarea id="supCompanyContents" name="" cols="" rows="" class="textinput"></textarea></td>
        	</tr> -->
        </table>
	    <ul class="forminfo">
	   <!--  
	   	<li><label>信息标题</label><label id="supTitle"></label></li>
	    <li><label>监管时间</label><label id="supTime"></label></li>
	    <li><label>监管信息</label><label id="supContents"></label></li>
	    <li><label>企业回复</label><textarea id="supCompanyContents" name="" cols="" rows="" class="textinput"></textarea></li>
	     -->
	    <li><label>&nbsp;</label><!-- <input id="subBut" name="" type="button" class="btn" onclick="companyRep();" value="确认保存"/> -->
	    	<label>&nbsp;</label><input name="" type="button" class="btn" onclick="exitContent();" value="取消"/>
	    </li>
	    </ul>
	    
	    
	    </div>
    </div>
    
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
        <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

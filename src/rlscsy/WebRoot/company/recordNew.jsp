<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("loginCompany");

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
<title>产品种类</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/recordNew.js"></script>

<style type="text/css">
.point{margin:30px 30px;border:1px solid #d0dee5;padding:10px;background:#fff;}
.point font{line-height:30px;color:#F90;font-size:16px;background:url("images/point.png") no-repeat;padding-left:30px;height:30px;display:block;}
.point p{line-height:30px;margin:0 0 0 30px;}
</style>

<script type="text/javascript">
	$(function(){
		$.ajax( {
			url : 'useguide_findUseguideList.action',
			data : {'useguide.ugNo':'zlsz'},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result&&result.length==1){
					var useguide = result[0];					
					var html = '<font>'+useguide.title+'</font>'+useguide.contents;
					$('#useguide').html(html);
				}
				
			}
		});

	});	
</script>
<style type="text/css">
.weitianxie table td{line-height:25px;text-align: center;}
/*
.combo{
background-color: #fff;
border-color: #cccccc;
}
*/
#proImgInfo td{line-height:20px;text-align: left;}

#proImgList table td input {
    border: 1px solid #ccc;
    height: 25px;
    width: 300px;
}

.iconlist {
	border-top: 1px solid #3eafe0;
    border-bottom: 1px solid #3eafe0;
    height: 0px;
    /*cursor: pointer;*/
}


.iconlist li {
    float: left;
    margin-bottom: 15px;
    margin-right: 25px;
    margin-top: 25px;
    text-align: center;
    padding: 10px 10px 10px 0;
    width: 100px;
    cursor: pointer;
}

.iconlist li:HOVER{

	background: #fff none repeat scroll 0 0;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 2px 3px 5px #fafafa;
    cursor: pointer;
    float: left;
    margin-bottom: 15px;
    margin-right: 25px;
    margin-top: 25px;
   	padding: 10px 10px 10px 0;
   	width: 100px;
    text-align: center;
}

.iconlist:HOVER {
    background-color: #d2f4fe;
}

.treeBtn{
	background-color: #3f97c9;
    color: #fff;
    height: 25px;
    margin: 0;
    padding: 0;
    width: 35px;
    cursor: pointer;
}

.treeBtn:HOVER {
	color: #BEBEBE;
}

.setProName .text {
    border: 1px solid #ccc;
    height: 25px;
    margin: 2px;
    padding-left: 5px;
    width: 390px;
}
</style>


</head>


<body style="overflow: hidden">
    <div>
    	<div id="cc" class="easyui-layout" align="center" style="width:750px;height:500px;vertical-align: middle;margin:10;float: left;">

			<div data-options="region:'west',split:false" title="请选择产品种类" style="width:300px;">
				<div style="padding: 5px;background-color: #3f97c9;position: absolute;width: 100%;">
					<input type="text" id="s_typeName" style="border: 1px solid #81c0c0;height: 20px;width: 185px;padding: 3px;"/>
					<input type="button" onclick="toSearch()" class="treeBtn" style="border-right: 1px solid #fff;" value="搜索"/>
					<input type="button" onclick="toReset()" class="treeBtn" value="重置"/>
				</div>
				
				<div id="nodata" style="background-color: #e0e0e0;padding: 50px;text-align: center;display: none;margin-top:40px;">
					对不起没有搜索到您想要的种类，请拨打电话13725198000联系管理员协调处理谢谢！
				</div>
				
				<ul id="type_tree" style="margin-top:40px;">
				</ul>
			</div>
			<div data-options="region:'center',title:'操作'" align="center" id="option_col" style="width:150px;vertical-align: middle;">
				<br/><br/>
		    	<a href="javascript:void(0)" id="userlist_reset" iconcls="icon-add" class="easyui-linkbutton" onclick="addSelect()">添加选择项</a><br/><br/>
				<a href="javascript:void(0)" id="userlist_sub" iconcls="icon-remove" class="easyui-linkbutton" onclick="deleteSelect()">移除选择项</a><br/><br/><br/><br/>
				
		    	<a href="javascript:void(0)" id="saveBtn" iconcls="icon-ok" class="easyui-linkbutton" data-options="disabled:true" onclick="saveSelect()">保存</a><br/><br/>
		    	
		    	
			</div>
			<div data-options="region:'east',split:false" title="已创建产品种类" style="width:300px;">
				<ul id="my_tree">
				
				</ul>
			</div>
		</div>
    
       	<div style="width: 310px;height: 300px;float: left;">
	   		<div class="point" id="useguide">
	           	
	        </div>	
	    </div>
    
    </div>
    
    
    
    
	<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
    <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

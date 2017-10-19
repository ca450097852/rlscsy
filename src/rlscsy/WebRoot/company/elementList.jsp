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

String recId = request.getParameter("recId");

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
<title>无标题文档</title>

<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"></script>
<script language="JavaScript" src="../static/js/layer/layer.js"></script>
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	

	layer.tips('单击这里返回档案列表','#returnUrl',{
   	    tips: 1,
   	    time:5000
   	})
    

	//获取要素
	$.post('record_getElements.action','recId=<%=recId %>',function(result){
		if(result){
			for(var i=0;i<result.length;i++){
				var el = result[i];
				if(el){
					var srcImg = 'images/l01.png';
					if(el.elementIcon!=''){
						srcImg = '/nytsyFiles/element/'+el.elementIcon;
					}
					var content = '<li onclick="openDialog(\''+el.elementUrl+'?recId=<%=recId %>\',\''+el.elementName+'\')">\
							    <span><img src="'+srcImg+'" /></span>\
							    <h2><a href="#">'+el.elementName+'</a></h2>\
							    </li>';
					$('.imglist').append(content);
				}
			}
		}
	},'JSON');
})	


function openDialog(url,title){
	var index = layer.open({
		title:title,
	    type: 2,
	    shift: 5,
	    maxmin: false,
	    area: ['700px', '530px'],
	    fix: false, //不固定
	    content: url,
	    success:function(){
			layer.tips('单击这里退出','.layui-layer-setwin',{
		   	    tips: 1
		   	});
	    }
	});
	layer.full(index);
}
</script>

<style type="text/css">

.imglist li span {
    height: 126px;
    margin: 23px;
    width: 168px;
}
</style>

</head>


<body>

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li>首页</li>
	    <li><a href="record.jsp" id="returnUrl">档案管理</a></li>
	    <li>要素列表</li>
	    </ul>
    </div>
    
    <div class="rightinfo">
    
    
    
    <ul class="imglist">
    
    
    
    </ul>
    
    </div>
    

</body>

</html>

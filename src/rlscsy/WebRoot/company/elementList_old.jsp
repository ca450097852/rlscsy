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
<title>溯源要素</title>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"></script>
<script language="JavaScript" src="../static/js/layer/layer.js"></script>

<script language="javascript">
$(function(){
    $('.loginbox0').css({'position':'absolute','left':($(window).width()-810)/2});
	$(window).resize(function(){  
    $('.loginbox0').css({'position':'absolute','left':($(window).width()-810)/2});
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
					$('.loginlist').append('<li><a onclick="openDialog(\''+el.elementUrl+'?recId=<%=recId %>\',\''+el.elementName+'\');"><img src="'+srcImg+'" /><p>'+el.elementName+'</p></a></li>');
				}
			}
		}
	},'JSON');
    
});

function openDialog(url,title){
	var index = layer.open({
		title:title,
	    type: 2,
	    shift: 5,
	    maxmin: false,
	    area: ['700px', '530px'],
	    fix: false, //不固定
	    content: url
	});
	layer.full(index);
}



	
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light1.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li><a href="#">首页</a></li>
	    <li><a href="record.jsp" id="returnUrl">档案管理</a></li>
	    <li><a href="#">要素列表</a></li>
	    </ul>
    </div>
    
    <div class="loginbody1">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox0">
    
    <ul class="loginlist">
    <!-- 
    <li><a href="login1.html"><img src="images/l01.png"  alt="电子监察系统"/><p>教育电子监察<br />管理系统</p></a></li>
    <li><a href="login2.html"><img src="images/l02.png"  alt="电子监察系统"/><p>高校采购与招标<br />管理系统</p></a></li>
    <li><a href="login3.html"><img src="images/l03.png"  alt="电子监察系统"/><p>高校科研经费<br />管理系统统</p></a></li>
    <li><a href="login4.html"><img src="images/l04.png"  alt="电子监察系统"/><p>教育信息化建设工程<br />项目管理系统</p></a></li>
     -->
    </ul>
    
    
    </div>
    
    </div>
</body>

</html>


<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

EntStyle companyStyle = session.getAttribute("companyStyle")==null?null:(EntStyle)session.getAttribute("companyStyle");
String styleFile = "blue";
String bgColor="#1c77ac";
if(companyStyle!=null){
	if(companyStyle.getScCss()!=null && !"".equals(companyStyle.getScCss())){
		styleFile = companyStyle.getScCss();
	}
}

System.out.println("styleFile====="+styleFile);

if("orange".equals(styleFile)){
	bgColor="#df7611";
}else if("green".equals(styleFile)){
	bgColor="#378d01";
}

Object obj = session.getAttribute("entStyle_QT");
String bottomInfo = "版权所有@广州薪火网络科技有限公司";
if(obj!=null){
	EntStyle entStyle = (EntStyle)obj;
	if(entStyle.getBottomInfo()!=null && !"".equals(entStyle.getBottomInfo())){
		bottomInfo = entStyle.getBottomInfo();
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="robots" content="noindex,nofollow"/>
<meta name="robots" content="noarchive"/>
<!-- 屏蔽-->
<title>企业用户登录</title>
<meta content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" name="viewport"/>
<meta content="yes" name="apple-mobile-web-app-capable"/>
<meta content="black" name="apple-mobile-web-app-status-bar-style"/>
<meta content="IE=9; IE=EDGE" http-equiv="X-UA-Compatible"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>company/css/bootstrap.min.css"/> 
<link rel="stylesheet" type="text/css" href="<%=basePath %>company/css/new.css"/>  
<link rel="stylesheet" type="text/css" href="<%=basePath %>company/css/font-awesome.min.css"/>  
  
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<link href="<%=basePath %>company/<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=basePath %>company/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>company/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>company/js/jquery.scrollUp.js"></script>
<script type="text/javascript" src="<%=basePath %>company/js/validate.js"></script>
<script type="text/javascript" src="<%=basePath %>company/js/beau.js"></script>
<script type="text/javascript" src="<%=basePath %>company/js/login.js"></script>
<script language="javascript">
	$(function(){

		var msg = '${msg }';
		if(msg!=''){
			layer.msg(msg);
		}
		
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	    $('.loginbox').css({'position':'absolute','top':($(window).height()-500)/2});
	    
		$(window).resize(function(){  
		    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		    $('.loginbox').css({'position':'absolute','top':($(window).height()-500)/2});
		    
    	}) ;

	    reloadCode();
    	
	});  
	//监听回车键事件
	 document.onkeydown = function (event) {
        var e = event || window.event;
        if (e && e.keyCode == 13) { //回车键的键值为13
           	checkLogin();//调用登录按钮的登录事件
        }
    }; 
	//重新获取验证码
    function reloadCode() {
        $('#imgCode').attr('src', '<%=basePath%>jsp/login/SecurityCode.jsp?' + Math.random());
    }
	function checkLogin() {
		<%--
			txt = $('#entName');
	        if (txt.val() == "") {
	        	layer.tips('请输入企业号！','#entName');
	            txt.focus();
	            return false;
	        }
		--%>
	       var nametxt = $('#tbLoginName');
	        if (nametxt.val() == "") {
	            layer.tips('请输入登录名！','#tbLoginName');
	            nametxt.focus();
	            return false;
	        }


	        txt = $('#tbPassword');
	        if (txt.val() == "") {
	        	layer.tips('请输入密码！','#tbPassword');
	            txt.focus();
	            return false;
	        }


	        txt = $('#validCode');
	        if (txt.val() == "") {
	        	layer.msg('请输入验证码！');
	            txt.focus();
	            return false;
	        }else{
	        	$.post('<%=basePath%>jsp/login/login_ajax.jsp', 'code='+txt.val(), function(result) {
					if(result==0){
						layer.msg('验证码错误，请重新输入！');
						reloadCode();
						return false;
					}else{
						layer.msg('正在登录...');
						var form = document.forms[0];
				         form.action="complogin.action";
				         form.method="post";
				         form.submit();
					    return true;
					         
					}
				}, "TEXT");
	        }	        
	    }
</script> 
</head>
<body>
<div class="dashboard-container">
  <div class="col-lg-4 col-md-4 col-md-offset-4">
    <div class="validation-summary-errors alert alert-block alert-danger" style="display: none;"></div>
    <div class="sign-in-container">
      <form method="post" class="login-wrapper" >
        <div class="header">
          <div class="row">
          	<div class="login-logo"><img src="<%=basePath %>company/images/logo_01.png" alt="Gold International"/></div>
            <div class="col-md-12 col-lg-12">
              <h3 class="text-center">企业会员登录</h3>
              
            </div>
          </div>
        </div>
        <div class="content">
          <div class="form-group">
            <label for="username">用户名</label>
            <span class="field-validation-valid"></span>
            <input class="form-control" maxlength="20" id="tbLoginName" name="companyUser.account" placeholder="用户名" type="text" value=""/>
          </div>
          <div class="form-group">
            <label for="username">登录密码</label>
            <span class="field-validation-valid"></span>
            <input class="form-control" maxlength="20" id="tbPassword" name="companyUser.password" placeholder="密码" type="password"/>
          </div>
          <div class="form-group">
            <label for="username">验证码</label>
            <span class="field-validation-valid"></span>
            <div>
              <div class="col-md-4" style="padding-left:0">
                <input class="form-control" id="validCode" placeholder="验证码" type="text" value=""/>
              </div>
              <div class="col-md-8"><img id='imgCode' src="<%=basePath %>company/images/index.png" alt="" height="34" class="code_img" />&nbsp;&nbsp;<a href="#" onclick="reloadCode()">看不清楚？</a></div>
            </div>
          </div>
          <div class="actions">
            <input class="btn btn-success" name="Login" type="button" onclick="checkLogin();" value="登录"/>
            <a href="<%=basePath %>" class="link">返回首页</a>
            <div class="clearfix"></div>
          </div>
        </div>
      </form>
      <div class="center-align-text" style="margin-top:20px; color: white;">广州薪火网络科技有限公司</div>
    </div>
  </div>
</div>
</body>
</html>
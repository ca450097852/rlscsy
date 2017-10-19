<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<title><%=session.getAttribute("systemName") %>-监管用户登录</title>
	<base href="<%=basePath%>"> 
    <link rel="stylesheet" href="<%=basePath%>static/css/style.css" type="text/css"></link>
   	<script src="<%=basePath%>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script src="<%=basePath%>static/js/cloud.js"　type="text/javascript"></script>
	<script language="JavaScript" src="<%=basePath%>static/js/layer/layer.js"></script>
	
	<script language="JavaScript" type="text/javascript">


	$(function() {
		
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	    $('.loginbox').css({'position':'absolute','top':($(window).height()-500)/2});
	    
		$(window).resize(function(){  
		    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		    $('.loginbox').css({'position':'absolute','top':($(window).height()-500)/2});
		    
    	}) 
    	
		reloadCode();

		var msg = '${msg }';
		if(msg!=''){
			layer.msg(msg);
		}
	});

	//重新获取验证码
	function reloadCode() {
		$('#imgCode').attr('src','<%=basePath%>jsp/login/SecurityCode.jsp?' + Math.random());
    }

    function checkLogin() {
       var nametxt = $('#tbLoginName');
        if (nametxt.val() == "") {
            //$("#oTip").text('请输入登录名！');
            layer.msg('请输入登录名！');           
            nametxt.focus();
            return false;
        }
        
        <%--
        txt = $('#entName');
        if (txt.val() == ""&&nametxt.val() != "administrator") {
            $("#oTip").text('请输入机构号！');
            txt.focus();
            return false;
        }
		--%>
		
        txt = $('#tbPassword');
        if (txt.val() == "") {
        	layer.msg('请输入密码！');
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
			         form.action="login_login.action";
			         form.method="post";
			         form.submit();
				}
			}, "TEXT");
        }
       
       return true;
    }


    function detectCapsLock(event) {
        var e = event || window.event;
        var o = e.target || e.srcElement;
        var oTip = o.nextSibling;

        var keyCode = e.keyCode || e.which; // 按键的keyCode
        var isShift = e.shiftKey || (keyCode == 16) || false; // shift键是否按住

        var o = document.getElementById('oTip');
        if (((keyCode >= 65 && keyCode <= 90) && !isShift)  // Caps Lock 打开，且没有按住shift键
            || ((keyCode >= 97 && keyCode <= 122) && isShift)  // Caps Lock 打开，且按住shift键
            ) {
            o.style.display = '';
            $("#oTip").text('大写锁定键被按下，请注意大小写');
        }
        else {
            o.style.display = 'none';
        }
    }

    function addFavorite(sURL, sTitle) {

        try { window.external.addFavorite(sURL, sTitle); } catch (e) {
            try {
                window.sidebar.addPanel(sTitle, sURL, "");
            } catch (e) {
                alert("浏览器收藏没有成功，请使用Ctrl+D进行添加!");
            }
        }
    }
    
    function onLogin(){
    	checkLogin();
    }


</script>

	</head>
	
	<body style="background-color:#1c77ac; background-image:url(<%=basePath %>static/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录<%=session.getAttribute("systemName") %></span>    
    <ul>
    <li><a href="<%=basePath %>">返回首页</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <%--<span class="systemlogo"></span>--%>
    
    <div class="loginbox loginbox1">
  <form name="form1" method="post" action="" id="form1">
    
    <ul>
	    <%--<li><input id="entName" name="user.entName" type="text" class="loginuser" value=""/></li>--%>
	    <li><input id="tbLoginName" name="user.userName" placeholder="请输入用户名" type="text" class="loginuser" value=""/></li>
	    <li><input id="tbPassword" name="user.password" placeholder="请输入密码" type="password" class="loginpwd" value=""/></li>
	    <li class="yzm">
	    	<span><input id="validCode" name="" type="text" value="" /></span><img class="style1" id="imgCode" onclick="javascript:reloadCode();" title="看不清" alt="看不清" src="<%=basePath %>jsp/login/SecurityCode.jsp" /> 
	    </li>
	    <li><input name="" type="button" class="loginbtn" value="登录"  onclick="onLogin()"  /></li>
    </ul>
    </form>
    
    </div>   
    </div>
    
    
    <div class="loginbm">
		<%=bottomInfo %>
	 </div>
	
    
</body>

</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>广州市肉类蔬菜流通追溯管理平台- 登录</title>
		<base href="<%=basePath%>"> 
        <link rel="stylesheet" href="${basePath}static/css/login/login1.css" type="text/css"></link>
    	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script language="JavaScript">

    //重新获取验证码
    function reloadCode() {
        $('#imgCode').attr('src', '<%=basePath%>jsp/login/SecurityCode.jsp?' + Math.random());
    }

    function checkLogin() {
       var nametxt = $('#tbLoginName');
        if (nametxt.val() == "") {
            $("#oTip").text('请输入登录名！');
            nametxt.focus();
            return false;
        }

        txt = $('#entName');
        if (txt.val() == ""&&nametxt.val() != "administrator") {
            $("#oTip").text('请输入机构ID！');
            txt.focus();
            return false;
        }

        txt = $('#tbPassword');
        if (txt.val() == "") {
            $("#oTip").text('请输入密码！');
            txt.focus();
            return false;
        }
        if (document.getElementById("recode").style.display != "none") {
            txt = $('#validCode');
            if (txt.val() == "") {
                $("#oTip").text('请输入验证码！');
                txt.focus();
                return false;
            }else{
            	$.post('<%=basePath%>jsp/login/login_ajax.jsp', 'code='+txt.val(), function(result) {
						if(result==0){
							$("#oTip").text('验证码错误，请重新输入！');
							reloadCode();
							return false;
						}else{
							$("#oTip").text('正在登录...');
							var form = document.forms[0];
					         form.action="login_login.action";
					         form.method="post";
					         form.submit();
						}
					}, "TEXT");
            }
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
	<body>
	   <form name="form1" method="post" action="" id="form1">

    <div class="mainContent">
   		<div class="loginhead" style="margin:0;padding:0">
            <div class="logo" style="margin:0;padding:0">
                <img src="<%=basePath %>static/image/login/login.jpg" /></div>
        </div>
        <div class="greenl">
        </div> 
        <div class="loginbody" id="loginBody" style="display:block">
            <table class="logintb" cellpadding="0" cellspacing="0">
                    <tr>
                        <td colspan="2" class="ltitle">                
                            <img class="titleImg1" src="<%=basePath %>static/image/login/login_title.jpg" />
                        </td>
                    </tr>
                	<tr>                    
                        <td colspan="2">
                            	机&nbsp;&nbsp;&nbsp;构:<input class="username textInput" id="entName" type="text" name="user.entName" value="" placeholder="请输入机构账号" id="tbLoginName" />
                        </td>
                    </tr>
                    <tr>
                    <!--${user.userName }-->                        
                    	<td colspan="2">
                            	用户名:<input class="username textInput" type="text" name="user.userName" value="" placeholder="请输入用户名" id="tbLoginName" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                        	   密&nbsp;&nbsp;&nbsp;码:<input class="pwd textInput" type="password" name="user.password" placeholder="请输入密码" value="" id="tbPassword"
                                onkeypress="detectCapsLock(event)" />
                        </td>
                    </tr>
                    <tr id="recode">
                        <td colspan="2">
                           	 验证码:<input class="recode textInput" type="text" name="yzm" placeholder="请输入验证码" value="1231" id="validCode" maxlength="4" />
                            <img class="style1" id="imgCode" onclick="javascript:reloadCode();" title="看不清" alt="看不清"
                                src="<%=basePath %>jsp/login/SecurityCode.jsp" />
                           </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="btns" style="padding-left:70px">
							<input class="login_bt" type="submit" value="登录" onclick="onLogin(); return false" />
							<input class="cancel_bt" type="reset" value="取消" onclick="javascript: history.go(-1);"/>           
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                        <span id="oTip" style="color: Red;font: 12px/1.231 Tahoma;">${msg }</span>
                        </td>
                    </tr>
                </table>
        </div>
        <div class="loginbotm">
            <a href="<%=basePath %>" target="_blank" class="aboutUs">溯源网站</a>|
            <a href="<%=basePath %>" target="_blank"  class="aboutUs">帮助中心</a>|
            <a href="javascript:addFavorite('<%=basePath %>', '广东省农产品质量安全监管追溯平台')" class="aboutUs">加入收藏</a>
            <br />
            最佳尺寸1024×768DPI&nbsp;&nbsp;&nbsp;推荐浏览器 <a href="http://download.firefox.com.cn/releases/webins2.0/official/zh-CN/Firefox-latest.exe"
                title="点击下载火狐浏览器" target="_blank">火狐</a>| <a href="http://www.google.com/chrome?hl=zh-cn"
                    target="_blank">谷歌</a><br />
                     <script type="text/javascript">
                         var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
                         document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fd796fdb8c57000115377e0cc55ab56f0' type='text/javascript'%3E%3C/script%3E"));
            </script>
		广东省农业厅版权所有       技术支持:广州薪火网络科技有限公司
        </div>
    </div>
    </form>
	
	</body>
</html>

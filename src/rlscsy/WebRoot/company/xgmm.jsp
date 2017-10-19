<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


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
<title>广州市肉类蔬菜流通追溯管理平台-修改密码</title>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/default/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
<script src="<%=basePath %>static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
    
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <DIV class="CENTER">
        <div class="center_content">
			<div class="weitianxie">
            	<div class="point">
                	<font>安全小提示</font>
                    <p>1.请谨慎进行此次操作，在修改密码前请先确认您所操作的机器是安全的</p>
                    <p>2.为了保护您账号的安全，请不要将密码设置为其他网站（如：其他游戏、邮箱、聊天工具等）相同的密码</p>
                    <p>3.为保护您的账号安全，请一定仔细设置您的员工的权限，同时请妥当保管您的账号安全一定要做足，以避免发生重大的经济损失！</p>
                </div>
            	<table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="td_title">原密码：</td>
                    <td><input type="password" id="pass_old"/></td>
                  </tr>
                  <tr>
                    <td>新密码：</td>
                    <td><input type="password" id="pass_n1" /></td>
                  </tr>
                  <tr>
                    <td>确认密码：</td>
                    <td><input type="password" id="pass_n2" /></td>
                  </tr>
                </table>
                <div class="btn_area" style="width:99px;">
                	<input  name="" type="button" class="btn" onclick="savePas();" value="保 存"/>
                </div>
                <div class="btn_area" style="display:none;"><a class="btn">重 置</a></div>
            </div>
        </div>
        <div class="clear"></div>
    </DIV>
</DIV>
</body>
</html>
<script type="text/javascript">

$(function(){
	parent.layer.closeAll();
});

function savePas(){
	var po = $('#pass_old').val();
	var n1 = $('#pass_n1').val();
	var n2 = $('#pass_n2').val();
	if(po==''){
		//$.messager.show( {title : '提示',msg : '请输入原密码'});
		layer.msg('请输入原密码');
		return;
	}
	if(n1==''){
		//$.messager.show( {title : '提示',msg : '请输入新密码'});
		layer.msg('请输入新密码');
		return;
	}
	if(n1.length<6){
		//$.messager.show( {title : '提示',msg : '密码长度不能小于六位，请重新输入'});
		layer.msg('密码长度不能小于六位，请重新输入');
		return;
	}
	if(n1!=''&&n1!=n2){
		//$.messager.show( {title : '提示',msg : '密码输入不一致，请重新输入'});
		layer.msg('密码输入不一致，请重新输入');
		return;
	}
	var condition = {};
	condition['password']=po;
	condition['newPassword']=n1;
	$.post('user_updateLoginUserPass.action',condition,function(result){
		try{
			f_timeoutToCompany(result);	
		}catch(e){
		}
		//$.messager.show( {title : '提示',msg : result});
		layer.msg(result);
		$('input[type="password"]').val("");
	},'TEXT');
}
</script>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>广州市肉类蔬菜流通追溯管理平台-账号信息</title>

<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/default/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
<script src="<%=basePath %>static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
    
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<style type="text/css">
.combo{
background-color: #fff;
border-color: #07aa94;
}
</style>
</head>

<body>
<DIV>
	<jsp:include page="head.jsp"></jsp:include>
    <DIV class="CENTER">
    	<div class="title_step">
        	<div class="step01_check"><a class="font_check">账号信息</a></div>
            <div class="step05"></div>
        </div>
        <div class="center_content">
            <div class="baocunhou">
            	<table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="td_title">登录账号：</td>
                    <td><span id="s_userName"></span></td>
                  </tr>
                  <tr>
                    <td>昵称：</td>
                    <td><span id="s_nickname"></span></td>
                  </tr>
                  <tr>
                    <td>性别：</td>
                    <td><span id="s_sex"></span></td>
                  </tr>
                  <tr>
                    <td>出生日期：</td>
                    <td><span id="s_birthDate"></span></td>
                  </tr>
                  <tr>
                    <td>年龄：</td>
                    <td><span id="s_age"></span></td>
                  </tr>
                  <tr>
                    <td>固定电话：</td>
                    <td><span id="s_tel"></span></td>
                  </tr>
                  <tr>
                    <td>手机号码：</td>
                    <td><span id="s_phone"></span></td>
                  </tr>
                  <tr>
                    <td>详细地址：</td>
                    <td><span id="s_addr"></span></td>
                  </tr>
                  <tr>
                    <td>电子邮箱：</td>
                    <td><span id="s_email"></span></td>
                  </tr>
                  <tr>
                    <td style="vertical-align:text-top;">用户简介：</td>
                    <td><span id="s_intrest"></span></td>
                  </tr>
                </table>
                <div class="btn_area2" style="width:99px;"><a class="btn" onclick="updateUser();">修 改</a> </div>
            </div>
			<div class="weitianxie" style="display:none">
			<form action="" id="userForm" method="post">
            	<table border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="td_title">登录账号：</td>
                    <td><input name="user.userName" readonly="readonly"/>(只读)</td>
                  </tr>
                  <tr>
                    <td>昵称：</td>
                    <td><input name="user.nickname"/></td>
                  </tr>
                  <tr>
                    <td>性别：</td>
                    <td>
                    	<input type="radio" value="0" name="dsex" style="width:20px;height:15px;" id="r_boy" onchange="$('#sSex').val(this.value)"/>男    
                    	<input type="radio" value="1" name="dsex" style="width:20px;height:15px;" id="r_girl" onchange="$('#sSex').val(this.value)"/>女
                    	<input type="hidden" name="user.sex" id='sSex'/>
                    </td>
                  </tr>
                  <tr>
                    <td>出生日期：</td>
                    <td><input name="user.birthDate"/></td>
                  </tr>
                  <tr>
                    <td>年龄：</td>
                    <td><input name="user.age"/></td>
                  </tr>
                  <tr>
                    <td>固定电话：</td>
                    <td><input name="user.tel"/></td>
                  </tr>
                  <tr>
                    <td>手机号码：</td>
                    <td><input name="user.phone"/></td>
                  </tr>
                  <tr>
                    <td>详细地址：</td>
                    <td><input name="user.addr"/></td>
                  </tr>
                  <tr>
                    <td>电子邮箱：</td>
                    <td><input name="user.email"/></td>
                  </tr>
                  
                  <tr>
                    <td style="vertical-align:text-top;">用户简介：</td>
                    <td><textarea name="user.intrest" cols="" rows="3" style="width: 500px"></textarea></td>
                  </tr>
                </table>
                <div id="h_value" style="display: none"></div>
                </form>
                <div class="btn_area">
                	<a class="btn" onclick="submitUser();">保 存</a>
                	<a class="btn" onclick="cancelSubmit();" style="margin-left: 10px;">取消</a>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </DIV>
    <DIV class="BOTTOM">
    	广东省农业厅版权所有 技术支持:广州薪火网络科技有限公司
    </DIV>
</DIV>
</body>
</html>

<script type="text/javascript">
$(function(){
	initUserInfo();
});
var guser;
function initUserInfo(){
	$('.baocunhou').show();
	$('.weitianxie').hide();
	$.post('user_getLoginUserInfo.action','',function(result){
		guser = result[0];
		var tmp = result[0];
		for(var item in tmp){
			//alert(item);
			$('#s_'+item).html(tmp[item]);

			if(item=='sex'){
				if(tmp[item]=='1'){
					$('#s_'+item).html('女');
				}else{
					$('#s_'+item).html('男');
				}
			}
		}
		
	},'JSON')
}

function updateUser(){

	$('.baocunhou').hide();
	$('.weitianxie').show();
	//$('#userForm').form('reset');
	for(var item in guser){
		var tmp = $('[name="user.'+item+'"]');

		if(item=='sex'){
			if(guser[item]==0){
				$('#r_boy').attr('checked','checked');
			}else if(guser[item]==1){
				$('#r_girl').attr('checked','checked');
			}
		}
		if(tmp.length!=0){
			tmp.val(guser[item]);
		}else{
			$('#h_value').append('<input type="hidden" name="user.'+item+'" value="'+guser[item]+'"/>');
		}
	}
}

function submitUser(){

	//提交
	$('#userForm').form('submit', {
		url : 'user_updateUser.action',
		onSubmit : function(param) {
		    return $(this).form('validate');//对数据进行格式化
		},
		success : function(result) {    	
			$.messager.show({ title : '提示', msg : result });   	
			initUserInfo();
		}       		
	});
}

function cancelSubmit(){
	$('.baocunhou').show();
	$('.weitianxie').hide();
}

function changeSex(check){
	alert(check);
}
	
</script>

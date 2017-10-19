<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 如果使用IE浏览器，优先使用IE高版本 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" ></meta>
<!-- 如果使用双核浏览器，优先使用极速模式 -->
<meta name="renderer" content="webkit"></meta>
<title><%=session.getAttribute("systemName") %>-企业注册</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<!-- 
<link href="<%=basePath %>static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
 -->
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>

<script type="text/javascript" src="js/register.js"></script>

<link type="text/css" rel="stylesheet" href="css/validform.css"/>
<script type="text/javascript" src="js/Validform_v5.3.js"></script>

</head>

<body >
<div class="container">
  
  <jsp:include page="head1.jsp" />
  
  <div class="register">
    <div class="register_content rggistration_cont">
    	<form name="entForm" id="entForm" class="entForm" method="post" enctype="multipart/form-data">
                	<input type="hidden" id="issubmit" value="0">
                	<input type="hidden" name="enterprise.flag" value="3">
				    <input type="hidden" name="enterprise.integrityRecord" value=""> 
				    <input type="hidden" name="enterprise.isReported" value="0">
				   	<input type="hidden" name="enterprise.sysCode" value="086020">
				   	<input type="hidden" name="enterprise.sts" value="0">
				   	<input type="hidden" name="enterprise.seq" value="5">
				   	<input type="hidden" name="enterprise.townRsts" id="townRsts" value="">
				   	<input type="hidden" name="enterprise.provinceRsts" id="provinceRsts" value="">
				   	<!--用于保存企业名称和账号验证是否通过 -->
				   	<input type="hidden" id="name_yanzheng">
				   	<input type="hidden" id="account_yanzheng">
				   	<!-- 区域字段 -->
				   	<input type="hidden" name="enterprise.areaId" id="areaId">
				   	<!-- 所属行政机构字段 （选择区域时控制）-->
				   	<input type="hidden" name="enterprise.parentId" id="parentId" value="0">
				   	<!-- 档案类型  （选择分类时控制）-->
				   	<input type="hidden" name="enterprise.recordType" id="recordType" value="1">
				   	<table width="100%" border="0">
				   	
				   	<tr>
				   		<td><label><font class="important">*</font>行政区域</label></td>
				   		<td id="quyu"></td>
				   		<td align="left" style="width: 173px;"></td>
				   	</tr>
				   	
				   	<tr>
				   		<td><label><font class="important">*</font>企业名称</label></td>
				   		<td><input type="text" name="enterprise.name" id="name" class="inputxt" onchange="isNameOnlyone();" onblur="isNameOnlyone();" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"/></td>
				   		<td align="left" id="name_msg" style="width: 173px;"></td>
				   	</tr>
				   	
				   	<tr>
				   		<td><label><font class="important">*</font>用户名</label></td>
				   		<td><input type="text" name="enterprise.account" id="account" class="inputxt" onchange="isAccountOnlyone();" onblur="isAccountOnlyone();" onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"/></td>
				   		<td align="left" id="account_msg" style="width: 173px;"></td>
				   	</tr>
				   	
				   	<tr>
				   		<td><label><font class="important">*</font>密码</label></td>
				   		<td><input type="password" name="enterprise.password" id="password" class="inputxt" datatype="*6-15" errormsg="密码范围在6~15位之间！" nullmsg="请输入密码！" onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"/></td>
				   		<td align="left" style="width: 173px;"></td>
				   	</tr>
				   	
				   	<tr>
				   		<td><label><font class="important">*</font>企业法人</label></td>
				   		<td><input type="text" name="enterprise.legalPerson" id="legalPerson" class="inputxt" datatype="*" sucmsg="企业法人验证通过！" nullmsg="请输入企业法人！"  onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"/></td>
				   		<td align="left" style="width: 173px;"></td>
				   	</tr>
				   	<tr>
				   		<td><label><font class="important">*</font>企业类型</label></td>
				   		<td  id="entType_html"></td>
				   		<td align="left" style="width: 173px;"></td>
				   	</tr>
				   	<!-- 
				   	<tr>
				   		<td><label><font class="important">*</font>是否批次追溯</label></td>
				   		<td>
				   		否：<input name="enterprise.isbatch" type="radio" value="0" checked="checked"  style="width: 10px;"/>&nbsp;&nbsp;
				   		是：<input name="enterprise.isbatch" type="radio" value="1"  style="width: 10px;"/>&nbsp;&nbsp;
				   		</td>
				   		<td align="left" style="width: 173px;"></td>
				   	</tr>
				   	 -->
				   	<tr>
				   		<td  id="orgCode_title"><label><font class="important">*</font>统一社会信用代码</label></td>
				   		<td  id="orgCode_html"><input type="text" name="enterprise.orgCode" id="orgCode" class="inputxt" datatype="*" sucmsg="统一社会信用代码验证通过！" nullmsg="请输入统一社会信用代码！" onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"/></td>
				   		<td align="left" id="orgCode_msg" style="width: 173px;"></td>
				   	</tr>
				   	<tr>
				   		<td><label><font class="important">*</font>联系电话</label></td>
				   		<td><input type="text" name="enterprise.tel" id="tel" maxlength="25" class="inputxt" datatype="*" placeholder="例：0750-8888888" sucmsg="联系电话验证通过！" nullmsg="请输入电话号码！" onkeyup="this.value=this.value.replace(/[^\d-]/g,'')" /></td>
				   		<td align="left"></td>
				   	</tr>
				   	<tr>
				   		<td><label><font class="important">*</font>注册地址</label></td>
				   		<td><input type="text" name="enterprise.regAddr" id="regAddr"  class="inputxt" datatype="*"  sucmsg="注册地址验证通过！" nullmsg="请输入注册地址！"/></td>
				   		<td align="left"></td>
				   	</tr>
				   	<tr>
				   		<td><label>经营地址</label></td>
				   		<td><input type="text" name="enterprise.manageAddr" id="manageAddr" class="inputxt"  datatype="*"  sucmsg="经营地址验证通过！" nullmsg="请输入经营地址！"/></td>
				   		<td align="left"></td>
				   	</tr>
				   	<tr>
				   		<td><label><font class="important">*</font>手机号码</label></td>
				   		<td><input type="text" name="enterprise.mobile" id="mobile" maxlength="25" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="inputxt" datatype="m"  sucmsg="手机号码验证通过！" nullmsg="请输入手机号码！" /></td>
				   		<td align="left"></td>
				   	</tr>
				   	
				   	<tr>
				   		<td><label>企业网址</label></td>
				   		<td><input type="text" name="enterprise.domName" id="domName" class="inputxt" datatype="url"  sucmsg="企业网址验证通过！" nullmsg="请输入企业网址！" onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"/></td>
				   		<td align="left"></td>
				   	</tr>
				   	<tr>
				   		<td><label>电子邮箱</label></td>
				   		<td><input type="text" name="enterprise.email" id="email" maxlength="30" class="inputxt" datatype="e" sucmsg="电子邮箱验证通过！" nullmsg="请输入电子邮箱！" onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"/></td>
				   		<td align="left"></td>
				   	</tr>
				   	<tr>
				   		<td><label><font style="color: red">*</font>企业简介</label></td>
				   		<td>
				   		<textarea name="enterprise.intro" id="intro" cols="20" rows="" tip="请输入500字内的简介信息！" style="font-size: 12px;" datatype="*" altercss="gray" class="gray" onblur="checkIntro();">请输入500字内的简介信息！</textarea>
				   		</td>
				   		<td align="left"></td>
				   	</tr>
				   					   					   	
				   	</table>
                     
                </form>
                    <div class="mt50" align="center" style="margin-top: 30px">
                    <!-- <button class="reset" id="clearform"  style="cursor: pointer">重置</button>
                    <button class="submit"  id="subForm"  style="cursor: pointer" onclick="formsubmit();">提交</button> -->
                   <a href="javascript:void(0)" id="clearform" class="reset" style="cursor: pointer;width: 100px;height: 35px;font-size: 12px;color: white; ">重置</a>
                    <a href="javascript:void(0)" id="subForm" class="submit"  style="cursor: pointer;width: 100px;height: 35px;font-size: 12px;color: white;" onclick="formsubmit();" >提交</a>
                    
                    <button class="btn" id="clearform" style="background:#39F" onclick="location.reload(true);">重置</button>
    				 <button class="btn" id="subForm" style="background:#EF5665" onclick="formsubmit();" >提交</button>
                    </div>
      
    </div>
  </div>
  <jsp:include page="bottom1.jsp" />
</div>

<style>
.rggistration_cont tr {
    line-height: 45px;
}
.rggistration_cont td input {
    border: 1px solid #dddddd;
    border-radius: 2px;
    line-height: 25px;
    padding: 4px 2px;
    width: 350px;
}
.rggistration_cont td textarea {
    border: 1px solid #dddddd;
    padding: 4px 2px;
    width: 350px;
}
.rggistration_cont td label {
    display: inline-block;
    margin-right: 30px;
    text-align: right;
    width: 160px;
}

.register_content {
    margin: 6px 160px;
    width: 700px;
}
</style>
</body>
</html>

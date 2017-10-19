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
<style >
.forRemove{
	width: 175px;
	height: 35px;
	border: 1px solid #ddd;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	overflow: hidden;
}
</style>

</head>

<body >
	<div class="container ">
		<!-- head -->
  		<jsp:include page="head1.jsp" ><jsp:param value="index" name="navckeckId"/></jsp:include>
  		<!-- head end -->
			<div class="main clearfix">
				<div class="main-content">
				<div class="left-box clearfix">
					<h3 class="page-title"><span>用户注册</span></h3>
					<div class="big-box-content">
						<div class="register">
							<div class="register_content">
					<form name="entForm" id="entForm" class="entForm" method="post" enctype="multipart/form-data">
                	<input type="hidden" id="issubmit" value="0">
				   	<input type="hidden" name="company.area" id="area"><!-- 区域字段 -->
				   	<input type="hidden" name="company.parentId" id="parentId"><!-- 所属节点字段 -->
				   
				   	<label class="labels">
      					<p class="inp_title"><span class="important">*</span>行政区域</p>
				   		<span id="quyu"></span>
				   		<span align="left" style="width: 173px;"></span>
				   	</label>
				   	
				   	<label class="labels">
				   		<p class="inp_title"><span class="important">*</span>所属生产节点</p>
				   		<span id="nodeSelect"></span>
				   		<span align="left" style="width: 173px;"></span>
				   	</label>
				   	
				   	<label class="labels" style='white-space: nowrap;'><td>
				   		<p class="inp_title"><span class="important">*</span>企业名称</p>
				   		<input type="text" name="company.name" id="name" class="inp" onchange="isNameOnlyone();" onblur="isNameOnlyone();" onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"/>
				   		<span align="left" id="name_msg"  ></span>
				   	</label>
				   
				   	<label class="labels" style='white-space: nowrap;'>
				   		<p class="inp_title"><span class="important">*</span>用户名</p>
				   		<input type="text" name="company.account" id="account" class="inp" onchange="isAccountOnlyone();" onblur="isAccountOnlyone();" onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"/></td>
				   		<span align="left" id="account_msg" style="width: 173px;"></span>
				   	 </label>
				   		   	
				   <label class="labels" style='white-space: nowrap;'>
				   		<p class="inp_title"><span class="important">*</span>密码</p>
				   		<input type="password" name="company.password" id="password" class="inp" datatype="*6-15" errormsg="密码范围在6~15位之间！" nullmsg="请输入密码！" onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"/>
				   		<span align="left" style="width: 173px;"></span>
				   	</label>
				   	
				  <label class="labels" style='white-space: nowrap;'>
				   		<p class="inp_title"><span class="important">*</span>企业法人</p>
				   		<input type="text" name="company.corporate" id="corporate" class="inp" datatype="*" sucmsg="企业法人验证通过！" nullmsg="请输入企业法人！"  onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')"/>
				   		<span align="left" style="width: 173px;"></span>
				   	</label>
				   	
				   <div class="radio-group" style='white-space: nowrap;'>
      				<p class="inp_title"><span class="important">*</span>企业性质</p>
				   		<div class="radio-group-content">
				   		企业：<input name="company.nature" type="radio" checked="checked" value="1" onclick="checkZzjg(this.value);"  style="width: 10px;"/>&nbsp;
				   		个体户：<input name="company.nature" type="radio" value="2" onclick="checkZzjg(this.value);"  style="width: 10px;"/>
				   		</div>
				   		<span align="left" style="width: 173px;"></span>
				   	</div>
				   	
				   	
				   		<label class="labels" >
				   		<p class="inp_title"><span class="important">*</span>经营类型</p>
				   			<select name="company.comType" id="comType"  class="select" datatype="n" nullmsg="请选择经营类型！" errormsg="请选择经营类型！">
				   				<option value='' selected="selected">--请选择--</option>
						      	<option value='1'>生猪批发商</option>
						      	<option value='2'>肉菜批发商</option>
						      	<option value='3'>肉菜零售商</option>
						      	<option value='4'>配送企业</option>
						      	<option value='5'>其他</option>
				   			</select>
				   		<span align="left" style="width: 173px;"></span>
				   	</label>
				   
				   	<label class="labels" style='white-space: nowrap;'>
				   		<p class="inp_title" id="orgCode_title"><span class="important">*</span>注册号或身份证号</p>
				   		<span  id="orgCode_html"><input type="text" name="company.regCode" id="regCode" class="inp" datatype="*" sucmsg="注册号或身份证号验证通过！" nullmsg="请输入注册号或身份证号！" onkeyup="this.value=this.value.replace(/(^\s*)|(\s*$)/g,'')"/></span>
				   		<span align="left" id="orgCode_msg" style="width: 173px;"></span>
				   	</label>	
				   		
				   	<label class="labels" style='white-space: nowrap;'>
				   		<p class="inp_title"><span class="important">*</span>手机号码</p>
				   		<input type="text" name="company.phone" id="phone" maxlength="25" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="inp" datatype="m"  sucmsg="手机号码验证通过！" nullmsg="请输入手机号码！" />
				   		<span align="left"></span>
				   	</label>
				   		
				   	<label class="labels" style='white-space: nowrap;'>
				   		<p class="inp_title"><span class="important">*</span>地址</p>
				   		<input type="text" name="company.addr" id="addr"  class="inp" datatype="*"  sucmsg="地址验证通过！" nullmsg="请输入地址！"/>
				   		<span align="left"></td>
				   </label>
				   
				  	<label class="labels" style='white-space: nowrap;'>
				   		<p class="inp_title">联系人</p>
				   		<input type="text" name="company.linkMan" id="linkMan" class="inp" /></span>
				   		<span align="left"></span>
				   	</label>
				   	
				   <label class="labels" style='white-space: nowrap;'>
				   		<p class="inp_title">电子邮箱</label></p>
				   		<td><input type="text" name="company.email" id="email" maxlength="30" class="inp"  /></span>
				   		<td align="left"></td>
				   	</label>
				   	
				   	<label class="labels" style='white-space: nowrap;'>
				   		<p class="inp_title"><span class="important">*</span>企业简介</p>
				   		<textarea name="company.introduction" id="introduction" cols="20" rows="" tip="请输入500字内的简介信息！" style="font-size: 12px;" datatype="*" altercss="gray" class="textarea" onblur="checkIntro();">请输入500字内的简介信息！</textarea>
				   		<span align="left"></span>
				   	</label>
                     
                </form>
      
        <div class="button-group">
								<button class="btn-reset" onclick="location.reload(true);">重置</button>
								<button class="btn-submit" onclick="formsubmit();">提交</button>
      </div>
      	
							</div>
						</div>
					</div>
				</div>
				<div class="right-box clearfix">
					<div class="tips">	
					    <h4>注册须知</h4>
					    <p>1.请填报准确的信息</p>
					    <p>2.标注<span class="important">*</span>为必填。</p>
					</div>
				</div>
</div>
</div>
</div>

  <!--footer -->
  <jsp:include page="bottom1.jsp" />
  <!--footer -->
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-企业注册</title>

<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/complaint.css"/>

<script src="<%=basePath %>static/js/hontek/portalweb/jquery-1.8.3.min.js"　type="text/javascript"></script>


<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	

<script src="<%=basePath %>static/js/hontek/portalweb/register.js"　type="text/javascript"></script>

<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/validform.css"/>
<script type="text/javascript" src="<%=basePath %>static/js/hontek/portalweb/Validform_v5.3.js"></script>


</head>
  
<body>
<DIV>
<jsp:include page="head.jsp"><jsp:param value="jmqy" name="navckeckId"/></jsp:include>
<DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>企业注册:</font></div>
                <form name="entForm" id="entForm" class="entForm" method="post" action="mbent_addCompany2.action" enctype="multipart/form-data">
                	<!-- 隐藏域字段 -->
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
				   	<input type="hidden" name="enterprise.recordType" id="recordType">
				   	<div id="auditEnt">
				   	<!-- 指定审核机构 （选择区域时控制）-->
				   	</div>
	                <div class="complaint_content">
	                	<dl class="div_line01" style="hight:50px;">
	                        <dt style="text-align: right"><strong>行政区域</strong><font style="color: red">*</font></dt>
	                        <dd id="quyu">
							</dd>
							<dd>
	                        <div></div>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>企业名称</strong><font style="color: red">*</font></dt>
	                        <dd>
	                        <input type="text" name="enterprise.name" id="name" style="width: 250px;"  class="inputxt" onchange="isNameOnlyone();" onmouseout="isNameOnlyone();"/>
	                        </dd>
	                        <dd id="name_msg">
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>企业帐号</strong><font style="color: red">*</font></dt>
							<dd>
							<input type="text" name="enterprise.account" id="account" style="width: 250px;" class="inputxt" onchange="isAccountOnlyone();" onmouseout="isAccountOnlyone();"/>
							</dd>
							<dd  id="account_msg">
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                     <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>登录密码</strong><font style="color: red">*</font></dt>
							<dd>
							<input type="password" name="enterprise.password" id="password" style="width: 250px;"  class="inputxt" datatype="*6-15" errormsg="密码范围在6~15位之间！" nullmsg="请输入密码！" />
							</dd>
							<dd>
	                        <div></div>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>企业法人</strong><font style="color: red">*</font></dt>
	                        <dd>
	                        <input type="text" name="enterprise.legalPerson" id="legalPerson" style="width: 250px;" class="inputxt" datatype="*" sucmsg="企业法人验证通过！" nullmsg="请输入企业法人！" />
	                        </dd>
	                        <dd>
	                        <div></div>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>企业类型</strong><font style="color: red">*</font></dt>
	                        <dd id="entType_html">
	                        <!-- <select name="enterprise.entType" id="entType_id"  style="width: 200px;">
								</select>
							 -->
								
							</dd>
	                        <div class="clear"></div>
	                    </dl>
	                    
	                     <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>主要产品类别</strong><font style="color: red">*</font></dt>
	                        <dd>
	                      	 <input type="text" name="enterprise.proType" id="proType" class="inputxt"  style="width:258px;height:25px;line-height: 25px;"  onmouseout="isSelect();" />
							</dd>
							<dd id="proType_msg">
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>组织机构代码</strong></dt>
	                        <dd><input type="text" name="enterprise.orgCode" id="orgCode"  style="width: 250px;" class="inputxt"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>注册地址</strong><font style="color: red">*</font></dt>
	                        <dd>
	                        <input type="text" name="enterprise.regAddr" id="regAddr" style="width: 250px;"  class="inputxt" datatype="*" sucmsg="企业注册地址验证通过！" nullmsg="请输入企业注册地址！"/>
	                        </dd>
	                        <dd>
	                        <div></div>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>经营地址</strong></dt>
	                        <dd><input type="text" name="enterprise.manageAddr" id="manageAddr" style="width: 250px;" class="inputxt"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>联系电话</strong><font style="color: red">*</font></dt>
	                        <dd>
	                        <input type="text" name="enterprise.tel" id="tel" style="width: 250px;" maxlength="25" class="inputxt" datatype="*" sucmsg="联系电话验证通过！" nullmsg="请输入电话号码！"/>
	                        </dd>
	                        <dd>
	                        <div></div>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>手机号码</strong></dt>
	                        <dd><input type="text" name="enterprise.mobile" style="width: 250px;" maxlength="25" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" class="inputxt"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>企业网址</strong></dt>
	                        <dd><input type="text" name="enterprise.domName" id="domName" style="width: 250px;" class="inputxt"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>电子邮箱</strong></dt>
	                        <dd><input type="text" name="enterprise.email" id="email" style="width: 250px;" class="inputxt"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    
	                    <dl class="div_line02">
	                        <dt style="height: 80px;line-height: 80px;"><strong>企业简介</strong></dt>
	                        <dd><textarea name="enterprise.intro" cols="20" rows="" style="width: 550px;height: 75px;"></textarea></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <div class="div_btn">
	                        <span class="btn" id="subForm"  style="cursor: pointer" onclick="formsubmit()">提交</span>
	                        <span class="btn" id="clearform"  style="cursor: pointer">重置</span>
	                        
	                    </div>
                </div>
                </form>
            </div>
            
            
            
            <div class="CENTER_content_right">
            <!-- 右边列表 -->
            <jsp:include page="news_info.jsp"></jsp:include>
            
            </div>
        </div>
        <div class="clear"></div>
    </DIV>
    <div class="clear" style="height: 30px;"></div>
 <jsp:include page="bottom.jsp"></jsp:include>
 </DIV>
</body>
</html>

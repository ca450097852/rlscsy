<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.sys.pojo.TsEnterprise"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object obj = session.getAttribute("enterprse");
String baseEntId = "";
if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}else{
	TsEnterprise enterprise = (TsEnterprise)obj;
	if(enterprise!=null){
		baseEntId = enterprise.getEntId()==0?"":enterprise.getEntId()+"";
	}
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

<link href="<%=styleFile %>/css/all_style.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/jquery.cookie.js" type="text/javascript"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
<script src="<%=basePath %>static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
    

<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>

<script type="text/javascript" src="<%=basePath %>static/js/xheditor-1.2.1/xheditor-1.2.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/xheditor-1.2.1/xheditor_lang/zh-cn.js"></script>

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />


<script type="text/javascript" src="<%=basePath %>company/js/companyStyle.js"></script>

</head>

<body>
<input type="hidden" id="jsessionid" value="<%=session.getId() %>"/>
<input type="hidden" id="baseEntId" value="<%=baseEntId %>"/>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>企业信息</li>
    <li>风格设置</li>
    </ul>
    </div>
<DIV>
    <div style="width: 100%" >
    	<table style="width: 100%">
    		<tr>
    		<td style="width: 65%;">
    			<DIV class="CENTER" style="width: 800px;">
		        <div class="center_content3">
		            
		            <div class="baocunhou" style="display:none;margin: 10px;" id="companyStyle">
		            	
		            	<ul class="classlist">
						    <!-- <li>
							    <span><img src="images/img04.png" /></span>
							    <div class="lright">
							    <h2>门户风格设置</h2>
							    <p>课程数：共35章<br />已完成：7章<br />学习中：6章</p>
							    <a href="javascript:void(0)" class="enter" onclick="styleEdit(1)">进入设置</a>
							    </div>
						    </li> -->
						    
						    <li>
							    <span><img src="images/img07.png" /></span>
							    <div class="lright">
							    <h2>风格设置</h2>
							    <!-- <p>课程数：共35章<br />已完成：7章<br />学习中：6章</p> -->
							    <a href="javascript:void(0)" class="enter" onclick="styleEdit(2)">进入设置</a>
							    </div>
						    </li>	
    					</ul>
						
		            </div>
		            
		            
					<div class="weitianxie" style="display:none;" id="infoTable">
					<form id="add_form" method="post" enctype="multipart/form-data">
						<div id="hiddenValue" style="display: none">
							<input type="hidden" name="entStyle.esId" >
				   			<input type="hidden" name="entStyle.entId" >
				   			<input type="hidden" name="entStyle.scType" >
				   			<input type="hidden" name="entStyle.logoImage"  id="id_logoImage">
				   			<input type="hidden" name="entStyle.userId" >
				   			<input type="hidden" name="entStyle.createTime" >
				   			<input type="hidden" name="entStyle.loginView" >
						</div>
		            	<table class="formtable" cellspacing="1" cellpadding="0">
		                  
		                  <tr>
		                    <td align="center" colspan="2"><h1 id="html_title">门户风格设置</h2></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">头部logo图片：</td>
		                    <td class="form_value">
		                    	<input type="file" id="uploadify"/> 
		                    	<span id="fileQueue"></span>
		                    </td>
		                  </tr>
		                  
		                  <tr>
						      <td class="form_label"></td>
						      <td class="form_value" align="left"><font color="red">logo图片最佳规格为：410*70像素</font></td>
						  </tr>
						  
		                  <tr>
		                    <td class="form_label">选择风格：</td>
		                    <td class="form_value">
		                    <input class="easyui-combobox" name="entStyle.scId" style="width:300px;" id="entStyle_scId"/>
		                    </td>
		                  </tr>
		                    
		                 <tr>
		                    <td class="form_label" style="width: 15%">底部信息：</td>
		                    <td class="form_value" style="width: 35%">
		                    <input class="easyui-validatebox" type="text" name="entStyle.bottomInfo" data-options="validType:'length[0,50]'"  style="width: 99%"/>
		                    </td>
		                  </tr>
		                  
		                   <!-- <tr>
		                    <td class="form_label" style="width: 15%">登录界面：</td>
		                    <td class="form_value" style="width: 35%">
		                    <input class="easyui-validatebox" type="text" name="entStyle.loginView" data-options="validType:'length[0,50]'" />
		                    </td>
		                  </tr> -->
		                  
		                  <tr>
		                    <td class="form_label" style="vertical-align:text-top;">备注：</td>
		                    <td class="form_value"><textarea class="easyui-validatebox" name="entStyle.remark" rows="3" data-options="validType:'length[0,500]'" style="width: 99%"></textarea></td>
		                  </tr>
		                  
		                </table>
		                
		                </form>
		                <div class="btn_area">
		                	<input  name="" type="button" class="btn" onclick="submitForm();" value="保　存"/>
		                	<input  name="" type="button" class="btn" onclick="returnToInfo();" value="返　回"/>
		                </div>
		            </div>
		            
		            
		        </div>
		           
		         
		        
		        <div class="clear"></div>
		    </DIV>

    		</td>
    		<td style="width: 35%;margin-top: 0px;">
    		<!-- 提示信息 -->
		        <div style="width: 90%;height:auto;float: right;">
			   		<div class="point" id="useguide">
			        </div>
		    	</div>

    		</td>
    		</tr>
    	</table>
    </div>
    
    
    <input type="hidden" id="basePath" value="<%=basePath %>"/>
    
</DIV>
</body>
</html>


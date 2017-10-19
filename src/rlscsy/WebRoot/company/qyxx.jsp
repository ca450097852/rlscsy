<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.company.pojo.Company"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object obj = session.getAttribute("loginCompany");

if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}

Company loginCompany = (Company)obj;


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

<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
<script src="<%=basePath %>static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
    
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=basePath %>company/js/qyxx.js"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>

<script type="text/javascript" src="<%=basePath %>static/js/xheditor-1.2.1/xheditor-1.2.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/xheditor-1.2.1/xheditor_lang/zh-cn.js"></script>

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

</head>

<body>
<input type="hidden" id="jsessionid" value="<%=session.getId() %>"/>

<DIV>
    <div style="width: 100%" >
    	<table style="width: 100%">
    		<tr>
    		<td style="width: 65%;">
    			<DIV class="CENTER" style="width: 800px;">
		        <div class="center_content3">
		            <div class="baocunhou" style="display:none;" id="companyInfo">
		            	<div id="psInfo" style="width:100%;margin:10 auto;text-align: center;font-size: 25px;display:none;">888995</div>
						 <table class="formtable" cellspacing="1" cellpadding="0">
		                  <tr>
		                    <td class="form_label" style="width: 15%">企业名称：</td>
		                    <td class="form_value" style="width: 35%"><span id="s_name"></span></td>
		                    <td class="form_label" style="width: 15%">经营者类型：</td>
		                    <td class="form_value" style="width: 35%"><span id="s_comType"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">所属区域：</td>
		                    <td class="form_value"><span id="s_areaName"></span></td>
		                    <td class="form_label">所属节点：</td>
		                    <td class="form_value"><span id="s_nodeName"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">企业性质：</td>
		                    <td class="form_value"><span id="s_nature"></span></td>
		                    <td class="form_label">经营者编码：</td>
		                    <td class="form_value"><span id="s_comCode"></span></td>
		                  </tr>
		                  
		                  <tr style="display: none;" id="isnode">
		                    <td class="form_label">节点类型：</td>
		                    <td class="form_value"><span id="s_flag"></span></td>
		                    <td class="form_label">节点编码：</td>
		                    <td class="form_value"><span id="s_nodeCode"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">联系人：</td>
		                    <td class="form_value"><span id="s_linkMan"></span></td>
		                    <td class="form_label">手机号码：</td>
		                    <td class="form_value"><span id="s_phone"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">电子邮箱：</td>
		                    <td class="form_value"><span id="s_email"></span></td>
		                    <td class="form_label">状态：</td>
		                    <td class="form_value"><span id="s_state"></span></td>
		                  </tr>
		                 
		                  <tr>
		                    <td class="form_label">法人：</td>
		                    <td class="form_value"><span id="s_corporate"></span></td>
		                    <td class="form_label">注册号或身份证号：</td>
		                    <td class="form_value"><span id="s_regCode"></span></td>
		                  </tr>
		                 
		                  <tr>
		                    <td class="form_label">营业执照或注册号：</td>
		                    <td class="form_value"><span id="s_licenseNum"></span></td>
		                    <td class="form_label">备案日期：</td>
		                    <td class="form_value"><span id="s_recordDate"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">营业执照扫描件：</td>
		                    <td class="form_value"><span id="s_licenseImg"></span></td>
		                    <td class="form_label">企业地址：</td>
		                    <td class="form_value"><span id="s_addr"></span></td>
		                  </tr>
		                   
		                  <tr>
		                    <td class="form_label">企业logo：</td>
		                    <td class="form_value" colspan="3"><span id="s_comLogo"></span></td>
		                  </tr>
		                  <tr>
		                    <td class="form_label" style="vertical-align:text-top;">简介：</td>
		                    <td class="form_value" colspan="3"><span id="s_introduction"></span></td>
		                  </tr>
		                </table>
		                <div class="btn_area">
		                	<input  name="" id="updateBtn" type="button" class="btn" onclick="updateEnt();" value="修 改"/>
		                </div>
		            </div>
		            
		            
		            
		            
					<div class="weitianxie" style="display:none;" id="infoTable">
					<form id="add_form" method="post" enctype="multipart/form-data">
						<div id="hiddenValue" style="display: none">
							
						</div>
		            	<table class="formtable" cellspacing="1" cellpadding="0">
		                  <tr>
		                    <td class="form_label" style="width: 15%">企业名称：</td>
		                    <td class="form_value" style="width: 35%">
		                    <input class="easyui-validatebox" type="text" name="company.name" data-options="required:true,validType:'length[0,50]'" />
		                    </td>
		                    <td class="form_label" style="width: 15%">经营者类型：</td>
		                    <td class="form_value" style="width: 35%">
		                    <select class="easyui-combobox"   name="company.comType" id="comType" style="width:300px;">
						      	<option value=''>--请选择--</option>
						      	<option value='1'>生猪批发商</option>
						      	<option value='2'>肉菜批发商</option>
						      	<option value='3'>肉菜零售商</option>
						      	<option value='4'>配送企业</option>
						      	<option value='5'>其他</option>
						      </select>
		                    </td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">所属区域：</td>
		                    <td class="form_value">
		                   		<input name="company.area" id="area" style="width:300px;"/>
		                    </td>
		                    <td class="form_label">所属节点：</td>
		                    <td class="form_value">
		                    <input  name="company.parentId" id="parentId" data-options="required:true" style="width:300px;">
							</td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">企业性质：</td>
		                    <td class="form_value">
		                    <select class="easyui-combobox"   name="company.nature" id="nature" style="width:300px;">
							      	<option value=''>--请选择--</option>
							      	<option value='1'>企业</option>
							      	<option value='2'>个体户</option>
						      </select>
		                    
		                    </td>
		                    <td class="form_label">地址：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="company.addr"  style="width:300px">
		                    </td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">联系人：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="company.linkMan" data-options="validType:'length[0,50]'" />
		                    </td>
		                   <td class="form_label">手机号码：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="company.phone" data-options="validType:'length[0,30]'"  />
		                    </td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">电子邮箱：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="company.email" data-options="validType:'email'" />
		                    </td>
		                    <td class="form_label">状态：</td>
		                    <td class="form_value">
			                    <select class="easyui-combobox" name="company.state" id="state" data-options="required:true" style="width:300px;"  >
							      	<option value='1' selected="selected">使用</option>
							      	<option value='2'>停用</option>
							      </select>
		                    </td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">法人：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="company.corporate"  />
		                    </td>
		                    <td class="form_label">注册号或身份证号：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="company.regCode" />
							</td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">营业执照或注册号：</td>
		                    <td class="form_value" >
		                    <input class="easyui-validatebox" type="text" name="company.licenseNum" data-options="validType:'length[0,30]'" />
		                    </td>
		                    <td class="form_label">备案日期：</td>
		                    <td class="form_value">
				   				<input class="easyui-datebox" type="text" name="company.recordDate" id="recordDate" style="width:300px">
							</td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">营业执照扫描件：</td>
		                    <td class="form_value" colspan="3">
		                    	<input type="hidden" name="company.licenseImg"  id="licenseImg" />
		                    	<input type="file" id="uploadify"/> 
		                    	<span id="fileQueue"></span>
		                    </td>
		                  </tr>
		                   
		                  <tr>
		                    <td class="form_label">企业logo：</td>
		                    <td class="form_value" colspan="3">
		                    	<input type="hidden" name="company.comLogo"  id="comLogo" />
		                    	<input type="file" id="uploadify_logo"/> 
		                    	<span id="fileQueue_logo"></span>
		                    </td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label" style="vertical-align:text-top;">简介：</td>
		                    <td class="form_value" colspan="3"><textarea id="xh_editor" class="easyui-validatebox" name="company.introduction" rows="5" data-options="validType:'length[0,2000]',required:true" style="width: 99%"></textarea></td>
		                  </tr>
		                </table>
		                </form>
		                <div class="btn_area">
		                	<input  name="" type="button" class="btn" onclick="submitForm();" value="保 存"/>
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


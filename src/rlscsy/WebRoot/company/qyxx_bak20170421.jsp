<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.sys.pojo.TsEnterprise"%>
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

TsEnterprise enterprise = (TsEnterprise)obj;

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
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>企业信息</li>
    <li>基本信息</li>
    </ul>    
    <!-- 提示信息 -->
    <!-- <div style="float: right; margin-top: 10px;margin-right: 150px">
    	<a href="#" title="1、请完善企业简介信息，突出您企业产品的优势及特点，让消费者更多了解您的企业。" class="easyui-tooltip" style="color: #F90;font-size: 16px"><img src="images/point.png" alt="" />操作提示</a>
    </div> -->
    </div>
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
		                    <td class="form_label" style="width: 15%">简称：</td>
		                    <td class="form_value" style="width: 35%"><span id="s_simpleName"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">所属区域：</td>
		                    <td class="form_value"><span id="s_areaId"></span></td>
		                    <td class="form_label">企业法人：</td>
		                    <td class="form_value"><span id="s_legalPerson"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">企业类型：</td>
		                    <td class="form_value"><span id="s_entType"></span></td>
		                    <td class="form_label">统一社会信用代码：</td>
		                    <td class="form_value"><span id="s_orgCode"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">注册地址：</td>
		                    <td class="form_value"><span id="s_regAddr"></span></td>
		                    <td class="form_label">经营地址：</td>
		                    <td class="form_value"><span id="s_manageAddr"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">联系电话：</td>
		                    <td class="form_value"><span id="s_tel"></span></td>
		                    <td class="form_label">手机号码：</td>
		                    <td class="form_value"><span id="s_mobile"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">邮政编码：</td>
		                    <td class="form_value"><span id="s_postCode"></span></td>
		                    <td class="form_label">电子邮箱：</td>
		                    <td class="form_value"><span id="s_email"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">企业网址：</td>
		                    <td class="form_value" colspan="3"><span id="s_domName"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">企业logo：</td>
		                    <td class="form_value"><span id="s_logoUrl"></span></td>
		                    <td class="form_label">是否批次追溯：</td>
		                    <td class="form_value"><span id="s_isbatch"></span></td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label" style="vertical-align:text-top;">简介：</td>
		                    <td class="form_value" colspan="3"><span id="s_intro"></span></td>
		                  </tr>
		                </table>
		                <div class="btn_area">
		                	<input  name="" id="updateBtn" type="button" class="btn" onclick="updateEnt();" value="修 改"/>
		                	<!-- <input  name="" id="dimemmo" type="button" class="btn" onclick="showDimenno();" value="查看二维码"/> -->
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
		                    <input class="easyui-validatebox" type="text" name="enterprise.name" data-options="required:true,validType:'length[0,50]'" />
		                    </td>
		                    <td class="form_label" style="width: 15%">简称：</td>
		                    <td class="form_value" style="width: 35%">
		                    <input class="easyui-validatebox" type="text" name="enterprise.simpleName" data-options="validType:'length[0,25]'" />
		                    </td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">所属区域：</td>
		                    <td class="form_value">
		                   <input name="enterprise.areaId" id="areaId_id" style="width:300px;"/>
		                    </td>
		                    <td class="form_label">企业法人：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="enterprise.legalPerson" data-options="required:true,validType:'length[0,10]'" />
							</td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">企业类型：</td>
		                    <td class="form_value">
		                    <input class="easyui-combobox" 
										name="enterprise.entType"
										style="width:300px;"
										id="entType_id"
										data-options="
												url:'enttype_getEntTypeToSelect.action',
												method:'get',
												valueField:'id',
												textField:'text',
												required:'true',
												editable: false,
												required:true
									  "/>
		                    
		                    </td>
		                    <td class="form_label">组织机构代码：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="enterprise.orgCode" id="t_orgCode" data-options="validType:'length[0,30]',required:true" />
		                    </td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">注册地址：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="enterprise.regAddr" data-options="required:true,validType:'length[0,50]'" />
		                    </td>
		                    <td class="form_label">经营地址：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="enterprise.manageAddr" data-options="required:true,validType:'length[0,50]'" />	
		                    </td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">联系电话：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="enterprise.tel" data-options="required:true,validType:'length[0,30]'" />
		                    </td>
		                    <td class="form_label">手机号码：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="enterprise.mobile" data-options="validType:'length[0,30]'"  />
		                    </td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">邮政编码：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="enterprise.postCode" data-options="validType:'ZIP'" />
		                    </td>
		                    <td class="form_label">电子邮箱：</td>
		                    <td class="form_value">
		                    <input class="easyui-validatebox" type="text" name="enterprise.email" data-options="validType:'email'"/>
							</td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">企业网址：</td>
		                    <td class="form_value" >
		                    <input class="easyui-validatebox" type="text" name="enterprise.domName" data-options="validType:'length[0,60]'" />
		                    </td>
		                    <td class="form_label">是否批次追溯：</td>
		                    <td class="form_value">
				   				<select name="enterprise.isbatch" style="width:100px;">
							      	<option value="0">否</option>
							      	<option value="1">是</option>
							      </select>
							</td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label">企业logo：</td>
		                    <td class="form_value" colspan="3">
		                    	<input type="hidden" name="enterprise.logoUrl" id="st_logoUrl"/>
		                    	<input type="file" id="uploadify"/> 
		                    	<span id="fileQueue"></span>
		                    </td>
		                  </tr>
		                  
		                  <tr>
		                    <td class="form_label" style="vertical-align:text-top;">简介：</td>
		                    <td class="form_value" colspan="3"><textarea id="xh_editor" class="easyui-validatebox" name="enterprise.intro" rows="5" data-options="validType:'length[0,2000]',required:true" style="width: 99%"></textarea></td>
		                  </tr>
		                </table>
		                </form>
		                <div class="btn_area">
		                	<input  name="" type="button" class="btn" onclick="submitForm();" value="保 存"/>
		                </div>
		            </div>
		            
		            
		            <div align="center" title="二维码信息" id="div_dim" style="display:none">
			       		<div style="height:90px;width: 100%" align="center">
				         	<br/>
				         	<span id="sh_info"></span>	
				         	<br/>	 
				         </div>
				         <!--startprint-->
				         <div style="height:250px;width: 100%" align="center">
				         	<span id="code_Img"></span>
				         </div>
				         <!--endprint-->
				         <div style="height:50px;width: 100%" align="center">
				         	<input type="hidden" id="Dimenno_Img">
				         	<a href="javascript:void(0)" style="background-color: silver " class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="downloadCodeImg()">下  载</a>&nbsp;&nbsp;
				         	<a href="javascript:void(0)" style="background-color: silver " class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="downloadCodeImg2()">下载二维码图片</a>&nbsp;&nbsp;
							<a href="javascript:void(0)" style="background-color: silver " class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="downloadCodeImg3()">下载二维码高清大图</a>&nbsp;&nbsp;					
							<!-- <a href="javascript:void(0)" style="background-color: silver " class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" onclick="doPrint()">打  印</a> -->
				         </div>
				         
				         <div class="btn_area" style="width: 99px;margin-left: 345px;">
				         	<input  name="" type="button" class="btn" onclick="returnToCompanyInfo();" value="返回"/>
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


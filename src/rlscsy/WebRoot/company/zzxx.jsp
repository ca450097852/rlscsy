<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.sys.pojo.TsEnterprise"%>
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
    
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script language="JavaScript" src="<%=basePath %>/company/js/zzxx.js"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>

<style type="text/css">

.combo-arrow{vertical-align: middle;}
.formtable {
    background-color: none;
    width: 100%;
}
.tools{padding: 10px;}
</style>
</head>

<body>

<DIV>
<DIV>

	
					<DIV class="CENTER">
			        <div class="center_content3" >
			        	<div class="tools">
					    	<ul class="toolbar">
					        <li class="click" onclick="addZizhi();"><span><img src="images/t01.png" /></span>添加</li>
					        </ul>
					    </div>
						<div class="weitianxie" style="display: none"> 
								 <div style="width: 100%" >
							    	<table style="width: 100%">
							    		<tr>
							    		<td style="width: 65%;">
							    			<form id="zizhiForm" method="post">
							    				<!-- <input type="hidden" value="1" name="zizhi.state"/> -->
										            	<table class="formtable" border="0" cellspacing="0" cellpadding="0">
										                  <tr>
										                    <td class="form_label">资质类型：</td>
										                    <td class="form_value">
											                    <select style="width: 300px;" class="easyui-combobox" id="typeName" name="zizhiType.typeName" data-options="required:true" onchange="zizhiTypeEvent(this.value,null);">
											                   		<option value=''>----请选择----</option>   
												                    <option value='龙头企业类'>龙头企业类</option>   
																    <option value='认证类'>认证类</option>   
																    <option value='示范合作社'>示范合作社</option>   
																    <option value='示范区、场'>示范区、场</option>   
																    <option value='家庭农场'>家庭农场</option> 
																    <option value='其他'>其他</option> 
											                    </select>
										                    </td>
										                  </tr>
										                  <tr id="levelTr" style="display: none;">
										                    <td class="form_label">资质级别：</td>
										                    <td class="form_value" id="level" style="vertical-align: middle;">
											                   
										                    </td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">证书名称：</td>
										                    <td class="form_value"><input name="zizhi.zizhiName" class="easyui-validatebox"  data-options="required:true,validType:'length[0,100]'" /></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label">颁发单位：</td>
										                    <td class="form_value"><input name="zizhi.awardUnit" class="easyui-validatebox" data-options="validType:'length[0,50]'"  /></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label">颁发时间：</td>
										                    <td class="form_value"><input name="zizhi.awardTime" id="zizhi_awardTime" class="easyui-datebox"  data-options="required:true" style="width: 300px;"/></td>
										                  </tr>
										                    <td class="form_label" style="vertical-align:text-top;">备注：</td>
										                    <td class="form_value"><textarea class="easyui-validatebox" name="zizhi.remark" id="zz_remark" data-options="validType:'length[0,500]'" style="width: 300px;"></textarea></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" style="vertical-align:text-top;">附件：</td>
										                    <td class="form_value"><input type="file" name="uploadify" id="uploadify" />
										                    <div id="fileQueue"></div>
										                    <div id="fileList" style="display: none;"></div>
										                    <div id="h_value"></div>
										                    </td>
										                  </tr>
										                </table>
										                </form>
										                
							    		
							    		</td>
							    		<td style="width: 35%;">
							    		<!-- 提示信息 -->
									        <div style="width: 350px;height: 300px;float: right;">
										   		<div class="point" id="useguide">
										        </div>
									    	</div>
							
							    		</td>
							    		</tr>
							    	</table>
										                
							    </div>
							<div class="btn_area" style="margin-bottom:30px;margin-left: 142px;">
					                <input  name="" type="button" class="btn" onclick="submitZizhiForm();" value="保 存"/>
					                <input  name="" type="button" class="btn" onclick="cancelSubmit();" value="取消"/>
					        </div>
						
			            </div>
			            <div id="zzsList">
			            
			            </div>
			        </div>
			        <div class="clear"></div>
			    </DIV>
    		
   
    <input type="hidden" id="jsessionid" value="<%=session.getId() %>"/>
</DIV>

	<!-- 信息供应商 -->
	<div id="applyWin" class="easyui-window" title="操作申请" data-options="iconCls:'icon-save',closed:true" style="width:500px;height:250px;padding:10px;top:150px">
		 <div class="easyui-panel" style="width:460px">
         <div align="center">
		    <form id="audit_form" method="post">
				<table class="formtable" cellspacing="1" cellpadding="0">
				    
				    <tr height="40px">
					    <td class="form_label">资质名称：</td>
						<td class="form_value">
							<input name="zizhiAuditRecord.zname" id="zizhiAuditRecord_zname" class="easyui-validatebox"  readonly="readonly" style="border: 1px solid  #ccc; width: 300px"/>
					    </td>
				    </tr>
				    
				    <tr height="40px">
					    <td class="form_label">申请原因：</td>
						<td class="form_value">
					      <textarea class="easyui-validatebox" rows="3"  id="applyCause" name="zizhiAuditRecord.applyCause" data-options="validType:'length[0,150]'" style="width:100%;border: 1px solid #ccc; "></textarea>
					    </td>
				    </tr>		
				    		    
				    <input type="hidden" id="zizhiAuditRecord_zid" name="zizhiAuditRecord.zid"/>				    
			    </table>
		    </form>
       </div>
	   <div style="text-align:center;padding:5px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitApplyForm()">提 交</a>&nbsp;&nbsp;&nbsp;
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="$('#applyWin').window('close')">关 闭</a>
	   </div>
    </div>
	</div>

</body>
</html>

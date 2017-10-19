<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.sys.pojo.TsEnterprise"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@page import="com.hontek.company.pojo.Company"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object obj = session.getAttribute("loginCompany");
Company company = (Company)obj;
if(obj==null){
	response.sendRedirect("ajaxSessionTimeoutToCom.action");
	return;
}
String log="/nytsyFiles/company/"+company.getComLogo();

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
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>

<style type="text/css">

</style>


<script>
$(function(){

	init();
	 parent.layer.closeAll();
	$("a#toComLogo").fancybox();
	$("#uploadify_logo").uploadify({
		'swf'      		: '../uploadify/uploadify.swf',
		'fileObjName' 	: 'uploadify',
		'uploader' 		: 'company_uploadLogo.action;jsessionid='+$('#jsessionid').val(),
		'buttonText'    : '上传头像',
		'height'		: 20,
		'width'			: 100,
		'fileSizeLimit'	: 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID'       : 'fileQueue_logo',
		'multi'			: false,
		'removeCompleted' : false,
		'itemTemplate' : '<div id="\${fileID}" class="uploadify-queue-item">\
            <div class="uploadcancel">\
	            <a href="" style="background: rgba(0, 0, 0, 0) url(\'../uploadify/da.jpg\') no-repeat scroll 0 0;" id="pre_\${fileID}">预览</a>\
                <a onclick="$(\'#comLogo\').val(\'\');" href="javascript:$(\'#\${instanceID}\').uploadify(\'cancel\', \'\${fileID}\')">X</a>\
            </div>\
            <span class="fileName">\${fileName} (\${fileSize})</span><span class="data"></span>\
        </div>',
		'onQueueComplete' : function(queueData) {
			//alert(queueData.uploadsSuccessful + ' files were successfully uploaded.');
			//showZizhiAttachFile(zid)
		},
		'onUploadSuccess' : function(file, data, response) {
			$('#comLogo').val(data);
			$("a#pre_"+file.id).attr('href','/nytsyFiles/company/'+data);
        	$("a#pre_"+file.id).fancybox();
		},
		'onSelect':function(){
			$("#fileQueue_logo").html();
		}
	});
	
});

function init(){
$.post('webcompany_getLoginCompany.action?tt='+Math.floor(Math.random()*20)+1,'',function(result){
		if(result){
			var company = result[0];
			$("#comName").html(company.name);
			$("#logoImg").attr("src","/nytsyFiles/company/"+company.comLogo);
			$("#toComLogo").attr("href","/nytsyFiles/company/"+company.comLogo);
			
		}
		
	},'JSON');
	
		//指引信息
	$.ajax( {
		url : 'useguide_findUseguideList.action',
		data : {'useguide.ugNo':'avatarupload'},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result&&result.length==1){
				var useguide = result[0];					
				var html = '<font>'+useguide.title+'</font>'+useguide.contents;
				$('#useguide').html(html);
			}
			
		}
	});


}

function submitForm(){
	$('#logoForm').form('submit', {
		url : 'webcompany_updateCompanyLogo.action?tt='+Math.floor(Math.random()*20)+1,
		onSubmit : function(result) {
				return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			
			layer.msg(result);
			
			init();
		}
});
}


</script>
</head>

<body>

			        <div class="center_content3" >
								 <div style="width: 100%" >
							    	<table style="width: 100%">
							    		<tr>
							    		<td style="width: 65%;">
							    			<form id="logoForm" method="post">
										            	<table class="formtable" border="0" cellspacing="0" cellpadding="0">
										                  <tr>
										                    <td class="form_label" width="150">企业名称：</td>
										                    <td class="form_value"><span id="comName"></span></td>
										                  </tr>
										                  <tr>
										                    <td class="form_label" width="150">当前头像：</td>
										                    <td class="form_value"><span><a id="toComLogo" href=""><img id="logoImg" width="100px" src="" /></a></span></td>
										                  </tr>
										                  <tr>
										                   <td class="form_label">上传头像：</td>
		                  								 <td class="form_value" >
		                    							<input type="hidden" name="company.comLogo"  id="comLogo" />
		                    							<input type="hidden" value="<%=company.getComId() %>" name="company.comId" />
		                    							<input type="file" id="uploadify_logo"/> 
		                    							<span id="fileQueue_logo"></span>
		                    							</td>
										                  </tr>
										                </table>
										                </form>
							    		</td>
							    		<!--<td style="width: 35%;">
							    		 提示信息 -->
							    		 <td style="width: 35%;">
							    		<!-- 提示信息 -->
									        <div style="width: 350px;height: 300px;margin-top:30px;float: right;">
										   		<div class="point" id="useguide">
										        </div>
									    	</div>
							
							    		</td>
							    		</tr>
							    	</table>
										                
							    </div>
							<div class="btn_area" style="margin-bottom:30px;margin-left: 142px;">
					                <input  name="" type="button" class="btn" onclick="submitForm();" value="保存"/>
					        </div>
						
			            </div>
			          
   
    <input type="hidden" id="jsessionid" value="<%=session.getId() %>"/>


</body>
</html>

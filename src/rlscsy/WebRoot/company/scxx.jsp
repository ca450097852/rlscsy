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
<title>广州市肉类蔬菜流通追溯管理平台-资质信息</title>

<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/jquery.cookie.js"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
<script src="<%=basePath %>static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>
    
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/default/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>

<style type="text/css">
.combo{
background-color: #fff;
border-color: #07aa94;
}
.tools{padding: 10px;}
</style>
</head>

<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>首页</li>
    <li>生产管理</li>
    </ul>
    </div>
<DIV>
<DIV>
    <DIV class="CENTER">
        <div class="center_content3">
        	<div class="tools">
		    	<ul class="toolbar">
		        <li class="click" onclick="addProduction();"><span><img src="images/t01.png" /></span>添加</li>
		        </ul>
		    </div>
        	
			<div class="weitianxie" style="display: none">
			<form action="" id="productionForm" method="post">
            	<table class="formtable" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td class="form_label" style="width:150px;">产品描述：</td>
                    <td class="form_value"><textarea class="easyui-validatebox" name="production.productinfo" data-options="required:true" rows="5" style="width: 500px"></textarea></td>
                  </tr>
                  <tr>
                    <td class="form_label" style="width:90px;">生产许可证号：</td>
                    <td class="form_value"><input name="production.license" class="easyui-validatebox" data-options="validType:'length[0,50]'"  style="width: 300px"/></td>
                  </tr>
                  <tr>
                    <td class="form_label">附件类型：</td>
                    <td class="form_value">
                    <select id="appType" style="width:300px;">
                    	<option value="1">产品图片</option>
                    	<option value="0">生产许可证扫描件</option>
                    </select>
                    </td>
                  </tr>
                  <tr>
                    <td class="form_label" style="vertical-align:text-top;">附件：</td>
                    <td class="form_value">
                    <input type="file" name="uploadify1" id="uploadify" />
                    <div id="fileQueue"></div>
                    <div id="fileList" style="display: none;"></div>
                    <div id="h_value" style="display: none;"></div>
                    </td>
                  </tr>
                </table>
                </form>
                <div class="btn_area" style="margin-bottom:30px;">
                	<input  name="" type="button" class="btn" onclick="submitProForm();" value="保 存"/>
                	<input  name="" type="button" class="btn" onclick="initProList();" value="取消"/>
                </div>
            </div>
            
            <div id="proList"></div>
        </div>
         <input type="hidden" id="jsessionid" value="<%=session.getId() %>"/>
        <div class="clear"></div>
    </DIV>
    <input type="hidden" id="compangRsts" value="<%=enterprise.getCompanyRsts() %>"/>
</DIV>
</body>
</html>
<script type="text/javascript">
var index = 0;
var optType = 0;
$(function(){
	$("#uploadify").uploadify({
		'swf'      		: '../uploadify/uploadify.swf',
		'fileObjName' 	: 'uploadify',
		'uploader' 		: 'productionAppendix_uploadFile.action;jsessionid='+$('#jsessionid').val(),
		'formData'		: {'appType':'1'},
		'buttonText'    : '上传附件',
		'height'		: 20,
		'width'			: 100,
		'fileSizeLimit'	: 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID'       : 'fileQueue',
		'multi'			: true,
		'removeCompleted' : false,		
		'onUploadSuccess' : function(file, data, response) {
			$('#fileList').append("<div id='d_"+file.id+"'></div>");
			$('#d_'+file.id).append("<input type='hidden' name='proAppList["+index+"].appName' value='"+file.name+"'/>");
			$('#d_'+file.id).append("<input type='hidden' name='proAppList["+index+"].path' value='"+data+"'/>");
			$('#d_'+file.id).append("<input type='hidden' name='proAppList["+index+"].appType' value='"+$('#appType').val()+"'/>");
			index++;
		}
	});

	initProList();

	parent.layer.closeAll();
});

var gResult;
function initProList(){
	$('.weitianxie').hide();
	$('#proList').show();
	$('#proList').html('');
	$.post('production_findProductionAndappend.action','',function(result){
		if(result.length>0){
			gResult = result;
			for(var i=0;i<result.length;i++){
				var pro = result[i];
				var content = '<div class="baocunhou" id="div_'+pro.proId+'">\
				            	<div class="caozuo"><a class="xiugai" onclick="updateProduction('+pro.proId+')">修改</a><a class="shanchu" onclick="deleteProduction('+pro.proId+')">删除</a></div>\
				            	<table class="formtable">\
				                  <tr>\
				                    <td class="form_label" style="width:250px;">产品描述：</td>\
				                    <td class="form_value">'+pro.productinfo+'</td>\
				                  </tr>\
				                  <tr>\
				                    <td class="form_label">生产许可证号：</td>\
				                    <td class="form_value">'+pro.license+'</td>\
				                  </tr>\
				                  <tr>\
				                    <td class="form_label" style="vertical-align:text-top;">图片附件：</td>\
				                    <td class="form_value" id="img_'+pro.proId+'">';
				    					imgList = pro.appList;
				    					for(var j=0;j<imgList.length;j++){
				    						var appdixUrl = "/nytsyFiles/production/"+imgList[j].path;
											var appdixName = imgList[j].appName;
											content+= '<a rel="pr_group_'+i+'" href="'+appdixUrl+'" title="'+appdixName+'"><img src="'+appdixUrl+'" width="50px;" height="50px;" style="margin-right:20px"/></a>';
					    				}
				    					content += '</td>\
				                  </tr>\
				                </table>\
				            </div>';
				$('#proList').append(content);
				if(i==result.length-1){
					var companyRsts = $('#compangRsts').val();
					//未提交跟退回
					/*
					if(companyRsts==0||companyRsts==3){
						$("#div_"+pro.proId).append('<div class="btn_area2" style="width:99px;"><a class="btn" onclick="submitAud();">提 交</a></div>');
					}else{
						$("#div_"+pro.proId).append('<div class="btn_area2" style="width:99px;"><a class="btn" href="wcym.jsp">下一步</a></div>');
					}*/
				}
				
			    $("a[rel=pr_group_"+i+"]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});			
			}
		}else{
			addProduction();
		}
	},'JSON')
}


function submitProForm(){
	if($('#productionForm').form('validate')==false){
		layer.msg('验证没有通过!');
		return;
	}   	
	layer.msg('数据处理中，请稍后....', {icon: 16,time: 200000});
	var formUrl = 'production_addProductionAndAppend.action';

	if(optType==0){
		formUrl = 'production_addProductionAndAppend.action';
	}else{
		formUrl = 'production_updateProductionAndAppend.action';
	}
	
	//提交
	$('#productionForm').form('submit', {
		url : formUrl,
		onSubmit : function(param) {
		    return $(this).form('validate');//对数据进行格式化
		},
		success : function(result) {    	
			//$.messager.show({ title : '提示', msg : result });   	
       		layer.closeAll();
			layer.msg(result);
       		//关闭进度条
       		//parent.$.messager.progress('close');	
       		initProList();
		}       		
	});     		        		       	 
}

function addProduction(){
	$('.weitianxie').show();
	$('#proList').hide();
	$('#fileQueue').html("");
	$('#fileList').html("");
	$('#h_value').html("");
	$('#productionForm').form('reset');
	optType = 0;
	index = 0;
}

function deleteProduction(id){
	var index = layer.confirm('确定删除信息?', {
	    btn: ['确定','取消'], //按钮
	    shade: false //不显示遮罩
	}, function(){
	    layer.close(index);
	    $.post('production_deleteProduction.action', 'ids='+id, function(result) {
			layer.msg(result);
			$('#div_'+id).remove();
			if($('.baocunhou').length==0){
				addProduction();
			}				
		}, "TEXT");
	}, function(){
	    layer.close(index);
	});
}

function updateProduction(id){
	for(var i=0;i<gResult.length;i++){
		if(gResult[i].proId==id){
			//alert(gResult[i].productinfo);
			addProduction();
			optType = 1;
			$('textarea[name="production.productinfo"]').val(gResult[i].productinfo);
			$('input[name="production.license"]').val(gResult[i].license);

			$('#h_value').append('<input type="hidden" name="production.proId" value="'+gResult[i].proId+'"/>');
			$('#h_value').append('<input type="hidden" name="production.crttime" value="'+gResult[i].crttime+'"/>');
			$('#h_value').append('<input type="hidden" name="production.userId" value="'+gResult[i].userId+'"/>');
			$('#h_value').append('<input type="hidden" name="production.entId" value="'+gResult[i].entId+'"/>');
			
			var appendixList = gResult[i].appList;
			for(var j=0;j<appendixList.length;j++){
				var tObj = appendixList[j];
				
				var itemTemplate ='';
				itemTemplate += '<div id="udiv_'+tObj.appId+'" class="uploadify-queue-item">\<div class="uploadcancel">';				
				var t1 = '<a href="javascript:void(0)" onclick="removeAppd(\''+tObj.appId+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+tObj.appName+'</span><span class="data"></span>\</div>';						
				itemTemplate = itemTemplate+t1;
				$('#fileQueue').append(itemTemplate);
				$('#fileList').append("<div id='d_"+tObj.appId+"'></div>");

				$('#d_'+tObj.appId).append("<input type='hidden' name='proAppList["+index+"].appName' value='"+tObj.appName+"'/>");
				$('#d_'+tObj.appId).append("<input type='hidden' name='proAppList["+index+"].path' value='"+tObj.path+"'/>");
				$('#d_'+tObj.appId).append("<input type='hidden' name='proAppList["+index+"].appType' value='"+tObj.appType+"'/>");
				index++;

			}
			
			break;
		}
	}
}

function removeAppd(fileID){
	$('#d_'+fileID).remove();
	$('#udiv_'+fileID).remove();
}

/**
 * 提交审核
 * @return
 */
function submitAud(){
	$.post('ent_submitAud.action','',function(result){
		if('unFull'==result){
			$.messager.show({ title : '提示', msg : '对不起，你的企业信息没有填写完整，请及时补充。谢谢！' });
		}else if('true'==result){
			//$.messager.show({ title : '提示', msg : '提交成功，请耐心等待审核结果！' });
			window.location.href='wcym.jsp';
		}
	},'TEXT');
}
</script>
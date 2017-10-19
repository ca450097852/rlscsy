<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
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
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>


<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" id="easyuiTheme" rel="stylesheet" type="text/css"/>	

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>

<style type="text/css">
.savebt{
	background-color: #3f97c9;
    border-radius: 6px;
    color: #fff;
    cursor: pointer;
    font-size: 14px;
    height: 26px;
    width: 61px;
}
.btdisabled{
background-color: #d0d0d0;
}
</style>

<script type="text/javascript">
parent.layer.closeAll();
var gData;
$.post('proTypeQrcode_getLoginProTypeQrcode.action','',function(result){
	if(result){
		gData = result;
		for(var i=0;i<result.length;i++){
			var qrcode = result[i];
			var content = '<tr '+(i%2==0?"":"class=\"odd\"")+'>\
        	<td>'+qrcode.typeName+'</td>\
		    <td><input name="agriInput.agriName" class="c_chandi" ptqId="'+qrcode.ptqId+'" index="'+i+'" id="in_'+qrcode.ptqId+'" style="width: 500px;height: 30px;" type="text" value="'+qrcode.chandi+'" maxlength="50" data-options="required:true" /></td>\
	        <td>\
	        <input type="button" class="savebt btdisabled" id="bt_'+qrcode.ptqId+'" index="'+i+'" disabled="disabled" value="保存" onclick="saveChandi('+qrcode.ptqId+')"/>\
	        </td>\
	        </tr> ';
	        
	     $('#proTypeTab').append(content);

	     $('#in_'+qrcode.ptqId).validatebox({});
		}

		$('.c_chandi').bind('keyup',function(e){
			var ptqId = $(this).attr('ptqId');
			var index = $(this).attr('index');
			var val = $(this).val();
			var row = gData[index];
			if(val!=''&&row.chandi!=val){
				$('#bt_'+ptqId).removeClass('btdisabled');
				$('#bt_'+ptqId).attr("disabled",false); 
			}else{
				$('#bt_'+ptqId).addClass('btdisabled');
				$('#bt_'+ptqId).attr("disabled","disabled"); 
			}
		})
		$('.c_chandi').bind('blur',function(e){
			var ptqId = $(this).attr('ptqId');
			var index = $(this).attr('index');
			var val = $(this).val();
			var row = gData[index];
			if(val!=''&&row.chandi!=val){
				layer.tips('填写完后，请保存', '#bt_'+ptqId,{tips: 2});
			}
		})
	}
},'JSON');

function saveChandi(ptqId){
	var chandi = $('#in_'+ptqId).val();
	var index = layer.msg('数据处理中，请稍后....', {icon: 16,time: 50000});
	$.post('record_saveChandi.action',{'proTypeQrcode.ptqId':ptqId,'proTypeQrcode.chandi':chandi},function(result){
		layer.close(index);
		layer.msg(result);
		var index = $('#bt_'+ptqId).attr("index"); 
		var row = gData[index];
		row.chandi = chandi;
		$('#bt_'+ptqId).addClass('btdisabled');
		$('#bt_'+ptqId).attr("disabled","disabled"); 
	},'TEXT');

	
}

	$(function(){
		$.ajax( {
			url : 'useguide_findUseguideList.action',
			data : {'useguide.ugNo':'cdbz'},
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

	});	
</script>
</head>


<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li>产品种类</li>
    	<li>产地标注</li>
	    </ul>
	</div>
	   
    <div class="rightinfo">
    
    <div id='proDiv' style="float: left;">
	    <table id="proTypeTab" class="tablelist" style="width: 700px;">
	    	<thead>
	    	<tr>
	    	<th width="150px">产品种类名称</th>	    	
	        <th width="300px">产地地址</th>
	        <th width="100px">操作</th>
	        </tr>
	        </thead>
	        <tbody>
	        </tbody>
	    </table>
    
	    
    </div>
    <div style="width: 330px;height: 500px;float: left;margin-left: 20px;">
   		<div class="point" style="margin: 0px;" id="useguide">
           	
        </div>

    </div>
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
    <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

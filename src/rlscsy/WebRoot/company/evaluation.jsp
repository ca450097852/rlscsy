<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.sys.pojo.EntStyle"%>
<%@page import="com.hontek.sys.pojo.TsEnterprise"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object obj = session.getAttribute("enterprse");
TsEnterprise enterprise = (TsEnterprise)obj;
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
<title>企业评价</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>

<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

var gEval;

$(function(){
	$.post('evaluation_findEvaluationPagerList.action','evaluation.companyId=<%=enterprise.getEntId()%>',function(result){
		if(result){
			if(result.total>0){//有数据
				var rows = result.rows;
				var eval = rows[0];
				gEval = eval;
				$('#evalContents').val(eval.evalContents);
				if(eval.companyContents!=''){
					$('#companyContents').val(eval.companyContents);
					$('#companyContents').attr('disabled',true);
					return;
				}
				$('.btn').show();
			}else{//没有数据
				$('#evalContents').val('暂无评价');
				$('#companyContents').attr('disabled',true);
				$('.btn').hide();
			}
		}
	},'JSON')
	parent.layer.closeAll();
});

function submitForm(){
	var companyContents = $('#companyContents').val();

	if(companyContents==''){
		layer.tips('请输入回复内容', '#companyContents');
		return;
	}

	
	var params = {};

	for(var item in gEval){
		params['evaluation.'+item] = gEval[item];
	}
	params['evaluation.companyContents'] = companyContents;

	$.post('evaluation_updateEvaluationCompany.action',params,function(result){
		layer.msg(result);
		$('#companyContents').attr('disabled',true);
		$('.btn').hide();
	},'TEXT');

	
	
}


</script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li>监管信息</li>
    <li>信用评价</li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="center_content3">
	    <div class="formbody">
	    <div class="formtitle"><span>企业评价</span></div>
	    
	    <table class="formtable">
	    	<tr>
	    		<td class="form_label">评价内容</td>
	    		<td class="form_value"><textarea name="" cols="" rows="" id="evalContents" class="textinput" disabled="disabled"></textarea></td>
	    	</tr>
	    	<tr>
	    		<td class="form_label">企业回复</td>
	    		<td class="form_value"><textarea name="" cols="" rows="" id="companyContents" class="textinput"></textarea></td>
	    	</tr>
	    </table>
	    
		    <ul class="forminfo">
			    <li><label>&nbsp;</label><input type="button" class="btn" style="display: none" onclick="submitForm();" value="提交"/>
			    </li>
		    </ul>
	    
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

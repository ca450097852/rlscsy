<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String elementDir = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/element/";
String productionDir = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/production/";
String proimgDir = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/proimg/";
String zizhiDir = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/zizhi/";

String dimenno = request.getParameter("dimenno");

String validCode = null;
Object obj = session.getAttribute("validCode");
if(obj!=null){
	validCode = (String)obj;
}else{
	validCode = "1";
}
%>


<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title><%=session.getAttribute("systemName") %></title>

	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>weixin/tabs/css/demo.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>weixin/tabs/css/tabs.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>weixin/tabs/css/tabstyles.css" />
	
	<link href="<%=basePath%>weixin/tabs/css/trace.css" rel="stylesheet" type="text/css">
	
    <script src="<%=basePath%>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
    <script src="<%=basePath%>static/js/layer-v3.0.1/layer.js" type="text/javascript"></script>  
    
    <script>
	    function validCode(){
	    	var validCode = $('#v_validCode').val();
	    	if(validCode==''){
	    		$('#validCodeMsg').html('请输入四位验证码');
	    	}else{
	    		$.ajax({
	    			url:'validCode_updateValidCode.action',
	    			type:'post',
	    			data:{'validCode.dimenno':$('#dimenno').val(),'validCode.validCode':validCode},
	    			success:function(result){
	    				$('#validCodeMsg').html(result);
	    			}
	    		});
	    	}
	    }
    </script>
	
	<style type="text/css">
		.btn-sure
		{  margin-top:32px; height:40px;
		    text-decoration:none;  
		    background:#5bb75b;  
		    color:#f2f2f2;  
		      
		    padding: 7px 27px 10px 27px;  
		    font-size:16px;  
		    font-family: 微软雅黑,宋体,Arial,Helvetica,Verdana,sans-serif;  
		    font-weight:bold;  
		    border-radius:3px;  
		      
		    -webkit-transition:all linear 0.30s;  
		    -moz-transition:all linear 0.30s;  
		    transition:all linear 0.30s;  
		      
		    }  
	</style>
	 
  </head>
  
<body>

<input type="hidden" id="elementDir" value="<%=elementDir%>">
<input type="hidden" id="productionDir" value="<%=productionDir%>">
<input type="hidden" id="proimgDir" value="<%=proimgDir%>">
<input type="hidden" id="zizhiDir" value="<%=zizhiDir%>">
<input type="hidden" id="basePath" value="<%=basePath%>">
<input type="hidden" id="dimenno" value="<%=dimenno%>">
<input type="hidden" id="validCode" value="<%=validCode%>">


<section>
	<div class="tabs tabs-style-circlefill">
		<nav style="background: #2CC185;color: #fff;padding: 10px;">
			<h3>二维码验证</h3>
		</nav>
		<div class="content-wrap" id="section_html">
			<section id="section-circlefill-1" style="height: 300px;padding: 10px;">
				<input type="text" id="v_validCode" placeholder="请输入二维码标签上的四位验证码" maxlength="4" style="width:70%;padding:9px;">
				<a class="btn-sure" href="javascript:validCode()">确 定</a>
				<br><span id="validCodeMsg" style="position: relative;top: 20px;"></span>
			
			</section>
		</div><!-- /content -->
	</div><!-- /tabs -->
</section>


</body>
</html>

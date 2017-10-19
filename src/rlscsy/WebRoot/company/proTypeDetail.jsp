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
<title>种类信息</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>

<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>	
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>

<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/proTypeDetail.js"></script>
<script type="text/javascript" src="js/geo.js"></script>

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>

<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />



<style type="text/css">
.weitianxie table td{line-height:25px;text-align: center;}
.combo .combo-text {
    border: 0 none;
    font-size: 12px;
    margin: 0;
    padding: 0 2px;
    vertical-align: top;
}
#proImgInfo td{line-height:20px;text-align: left;}

#proImgList table td input {
    border: 1px solid #ccc;
    height: 25px;
    width: 300px;
}

</style>
<style type="text/css">
.point2{border:1px solid #d0dee5;padding:5px;background:#fff;}
.point2 font{line-height:30px;color:#F90;font-size:16px;background:url("images/point.png") no-repeat;padding-left:30px;height:30px;display:block;}
.point2 p{line-height:30px;margin:0 0 0 30px;}
</style>

<script type="text/javascript">
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
					$('#useguide2').html(html);
				}
				
			}
		});

	});	
</script>

</head>


<body>
<input type="hidden" id="jsessionid" value="<%=session.getId() %>"> 
<input type="hidden" id="basePath" value="<%=basePath %>"> 
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li>产品种类</li>
	    <li>种类信息</li>
	    </ul>
	</div>
	
    <div class="rightinfo">
    <!-- 
    <div class="tools">
    
    	<ul class="toolbar">
        	<li class="click" onclick="addRecord();"><span><img src="images/t01.png" /></span>添加</li>
        </ul>
        
    </div>
    -->
     
    <div id='proDiv'>
	    <table id="proTab" class="tablelist">
	    	<thead>
		    	<tr>
			        <th width="100px">种类名称</th>
			        <th width="100px">是否认证</th>        
			        <th width="100px">年商品量(吨)</th>
			        <th width="100px">上市期</th>
			        <th width="100px">主要销售地</th>
			        <th width="100px">品牌名称</th>
			        <th width="100px">操作</th>
		        </tr>
	        </thead>
	        <tbody>
	        </tbody>
	    </table>
   		<!-- 
	    <div class="pagin">
	    	<div class="message">共<i class="blue" id="total">0</i>条记录，当前显示第&nbsp;<i class="blue" id="page">0</i>&nbsp;页</div>
	        <ul class="paginList"></ul>
	    </div>
	    -->
    </div>
    
    <div class="center_content3" style="display: none;">
	    <div class="formbody">
	    
	    <div class="formtitle"><span>添加</span></div>
	    
	    <div style="width: 100%" >
	    		<table style="width: 100%">
	    		<tr>
	    		<td style="width: 65%;">
	    			<form id="recordForm" method="post">
				    	<input type="hidden" name="proTypeQrcode.ptqId" id="ptqId"/>				    	
				    	<table class="formtable">
			                  <tr>
			                    <td class="form_label">是否认证</td>
			                    <td class="form_value">
			                    	<select class="easyui-combobox" style="width:300px;" name="proTypeQrcode.certificate" id="certificate">
			                    		<option value="5">无</option>
			                    		<option value="1">有机</option>
			                    		<option value="2">绿色</option>
			                    		<option value="3">无公害产品</option>
			                    		<option value="4">地理标志认证</option>
			                    	</select>
			                    </td>
			                  </tr>
        					<tr>
			                    <td class="form_label" style="width: 150px">年商品量</td>
			                    <td class="form_value"><input name="proTypeQrcode.quantity" id="quantity" type="text" class="easyui-validatebox" style="width:300px;" maxlength="50" data-options="required:true" />(吨)</td>
			                </tr>
        					<tr>
			                    <td class="form_label" style="width: 150px">上市期</td>
			                    <td class="form_value"><input name="proTypeQrcode.listed" id="listed" type="text" class="easyui-validatebox" style="width:300px;" maxlength="50" data-options="required:true" /></td>
			                </tr>
			                
			                
			                <tr>
			                    <td class="form_label" style="width: 150px">品牌特性</td>
			                    <td class="form_value">
			                    	<textarea name="proTypeQrcode.proDesc" class="easyui-validatebox" style="width:300px;height:80px;" data-options="required:true" ></textarea>
			                    </td>
			                </tr>
			                
			                <tr>
			                    <td class="form_label" style="width: 150px">品牌名称</td>
			                    <td class="form_value"><input name="proTypeQrcode.brandName" type="text" class="easyui-validatebox" style="width:300px;" maxlength="50" data-options="required:true" /></td>
			                </tr>
			                
			                
			                
			                <tr>
			                    <td class="form_label">主要销售地</td>
			                    <td class="form_value">
			                    	<select class="easyui-combobox" style="width:300px;" name="proTypeQrcode.salearea" id="salearea" data-options="required:true">
			                    		<option value="内销">内销</option>
			                    		<option value="出口">出口</option>			                    		
			                    	</select>
			                   </td> 
			               </tr>
			               <tr>
			                    <td class="form_label">种类图片</td>
			                    <td class="form_value">
			                    	<!-- 上传图片 -->
			                    	<input type="hidden" name="proTypeQrcode.typeImg" id="typeImg"/>
			                    	<table width="100%;" style="margin:0;">
						    			<tr>
						    				<td  width="120"><input type="file" id="uploadify"/></td>
						    				<td><div id="fileQueue"></div></td>
						    			</tr>
						    		</table>
						    		<!-- 上传图片 -->
			                   </td> 
			               </tr>
        		  </table>        	

				    	
				    </form>
	    		</td>
	    		<td style="width: 35%;">
	    		<!-- 提示信息 -->
			        <div style="width: 350px;height: auto;float: right;">
				   		<div class="point" id="useguide">
				        </div>
			    	</div>
	
	    		</td>
	    		</tr>
	    	</table>
				                
	    </div>
	     <ul class="forminfo">
		    <li><input id="subBut" name="" type="button" class="btn" onclick="submitForm();" value="确认保存"/>
		    	<label>&nbsp;</label><input name="" type="button" class="btn" onclick="exitContent();" value="取消"/>
		    </li>
	    </ul>
	    
	    
	    </div>
    </div>
    
    </div>
    
    <div id="chandiDialog" class="easyui-dialog" title="产地标注" style="width:650px;height:400px;" data-options="closed:true,modal:true"><%--   
    <div class="tools">
    
    	<ul class="toolbar">
        	<li class="click" onclick="addRecord();"><input> <span><img src="images/t01.png" /></span>添加</li>
        </ul>
        
    </div>
    
     <table id="chandiTab" class="tablelist">
	    	<thead>
		    	<tr>
			        <th width="50px">序号</th>
			        <th width="300px">产地地址</th>        
			        <th width="100px">操作</th>
		        </tr>
	        </thead>
	        <tbody>
	        </tbody>
	    </table>
	    	 --%>
	    	 
	   <table id="cdDataGrid"></table>  
   	   <div id="tb" style="height:auto">
   	   		   	   
   	   		<table class="formtable" cellspacing="1" cellpadding="0">
   	   			<tr>
   	   				<td class="form_value" colspan="4">
   	   					<div class="point2" id="useguide2"></div>
   	   				</td>
   	   			
   	   			</tr>
   	   		
   	   			<tr style="height: 30px">
   	   				<td class="form_label">
   	   					产地：	   	   				
		            </td>
		            <td class="form_value">
		            	<select class="select" name="province" id="s1" style="border: 1px;">
			              <option></option>
			            </select>
			            <select class="select" name="city" id="s2" style="border: 1px;">
			              <option></option>
			            </select>
			            <select class="select" name="town" id="s3" style="border: 1px;">
			              <option></option>
			            </select>
			            <input id="cdptqId"  type="hidden" />
		            </td>
		            
   	   				<td class="form_label">规模：</td>
   	   				<td class="form_value"><input type="text" id="scale" style="width: 150px;border: 1px" /></td>
   	   			</tr>
   	   			<tr style="height: 30px">
   	   				<td class="form_value" colspan="4" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="appendChandi()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick='$("#chandiDialog").dialog("close")'>退出</a>   	   				   	   				
   	   				</td>
   	   			</tr>
   	   		</table>	
 
	   </div>
	    
	</div>  
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
    <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
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
String ptaId = request.getParameter("ptaId");
String areaType = request.getParameter("areaType");

String protype="种植";
String massifName = "地块";
if("2".equals(areaType)){
	massifName = "场地";
	protype="养殖";
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
<title>地块信息</title>
<link type="text/css" rel="stylesheet" href="<%=styleFile %>/css/all_style.css"/>

<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>	
<link href="<%=basePath %>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<script type="text/javascript" src="js/massif.js"></script>

<link href="<%=styleFile %>/css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=basePath %>uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" href="<%=basePath %>uploadify/uploadify.css" type="text/css"></link>

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

</head>


<body>
<input type="hidden" id="jsessionid" value="<%=session.getId() %>"> 
<input type="hidden" id="basePath" value="<%=basePath %>"> 
<input type="hidden" id="_ptaId" value="<%=ptaId %>"> 

	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
	    <li>产品种类</li>
	    <li>基地信息</li>
	    <li><%=massifName%>信息</li>
	    </ul>
	</div>
	
    <div class="rightinfo">
    <div class="tools">    
    	<ul class="toolbar">
        	<li class="click" onclick="addRecord();"><span><img src="images/t01.png" /></span>添加<%=massifName%></li>
        </ul>      
    </div>
     
    <div id='proDiv'>
	    <table id="proTab" class="tablelist">
	    	<thead>
		    	<tr>
			        <th width="100px"><%=massifName%>名称</th>
			        <th width="100px"><%=massifName%>面积</th>        
			        <th width="100px"><%=protype%>品种</th>
			        <th width="100px"><%=protype%>时间</th>
			        <th width="100px">收获时间</th>
			        <th width="100px">上期<%=protype%>品种</th>
			        <th width="100px"><%=massifName%>平面图</th>
			        <th width="100px">操作</th>
		        </tr>
	        </thead>
	        <tbody>
	        </tbody>
	    </table>
    </div>
    
    <div class="center_content3" style="display: none;">
	    <div class="formbody">
	    
	    <div class="formtitle"><span>添加</span></div>
	    
	    <div style="width: 100%" >
	    		<table style="width: 100%">
	    		<tr>
	    		<td style="width: 65%;">
	    			<form id="recordForm" method="post">
				    	<input type="hidden" name="massif.ptaId" id="ptaId" value="<%=ptaId%>"/>		
				    	<input type="hidden" name="massif.maId" id="maId"/>				    
				    	<input type="hidden" name="massif.maImg" />				    	
				    		
				    	<table class="formtable">
				    		<tr>
			                    <td class="form_label"><%=massifName%>名称</td>
			                    <td class="form_value"><input name="massif.maName" id="maName" type="text" class="easyui-validatebox" style="width:400px;" maxlength="50" data-options="required:true" /></td>
			                  </tr>
			                  <tr>
			                    <td class="form_label"><%=protype %>品种</td>
			                    <td class="form_value">
			                    		<input id="ptqId" name="massif.ptqId" style="width:400px;">  
			                </tr>
			                <tr>
			                    <td class="form_label" style="width: 150px"><%=massifName%>面积</td>
			                    <td class="form_value"><input name="massif.maAcreage" id="maAcreage" type="text" class="easyui-validatebox" style="width:400px;" maxlength="50" data-options="required:true" /></td>
			                </tr>
			                <tr>
			                    <td class="form_label" style="width: 150px"><%=protype%>时间</td>
			                    <td class="form_value"><input name="massif.startTime" id="startTime" type="text" class="easyui-datebox" style="width:400px;" maxlength="50" /></td>
			                </tr>
			               <tr>
			                    <td class="form_label" style="width: 150px">采收时间</td>
			                    <td class="form_value"><input name="massif.getTime" id="getTime" type="text" class="easyui-datebox" style="width:400px;" maxlength="50" /></td>
			                </tr>
			                <tr>
			                    <td class="form_label" style="width: 150px">上期<%=protype %>品种</td>
			                    <td class="form_value">
											<input id="state" class="easyui-validatebox" name="massif.state" style="width:400px;">   											   
								</td>
			                </tr>
			                
			                <tr>
			                    <td class="form_label" style="width: 150px"><%=massifName%>平面图</td>
			                    
				    			<td class="form_value">
				    				<table style="float:none;margin:0;width: 345px;">
							    		<tr>
							    			<td style="width: 106px;"><input type="file" id="maImg"/></td>
							    			<td style="vertical-align: top">
							    				<div id="prev" class="uploadify-button disabled" style="height: 20px; line-height: 20px; width: 100px;">
							    					<span class="uploadify-button-text"><a id="a_pre" style="color: #808080">预览</a></span>
							    				</div>
							    			</td>
							    		</tr>
							    		
							    		<tr >
						    				<td id="fileQueue" colspan="2"></td>
							    		</tr>
							    	</table>
				    			</td>


			                </tr>
        		  </table>        	

				    	
				    </form>
	    		</td>
	    		<td style="width: 35%;">
	    			<!-- 提示信息 
			        <div style="width: 350px;height: auto;float: right;">
				   		<div class="point" id="useguide">
				        </div>
			    	</div>
					-->
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
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	<input type="hidden" id="h_entId" value="<%=((LoginUser)session.getAttribute("loginUser")).getEntId() %>"/>
    <input type="hidden" id="sessionId" value="<%=session.getId() %>"/>
</body>

</html>

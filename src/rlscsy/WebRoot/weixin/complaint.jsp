<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广东省农产品质量安全监管追溯平台-投诉举报</title>
<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>		
<script src="<%=basePath %>weixin/js/complaint.js" type="text/javascript"></script>		
<link type="text/css" rel="stylesheet" href="<%=basePath %>weixin/css/weixin.css"/>

<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no" /> 
		
</head>
  
<body>
<div id="INDEX">
	<DIV class="CENTER">
        <div class="CENTER_content">
        	<div class="CENTER_content_left">
            	<div class="product_title"><img src="images/tousu.png" /> <font>投诉举报</font></div>
            	<form name="complaintForm" id="complaintForm"  method="post" enctype="multipart/form-data">
            	
	                <div class="complaint_content">	                
	                    <dl class="div_line02">
	                        <dt><img src="images/icon02.png" /></dt>
	                        <dd>
							<select name="complaint.typeNo" id="typeNo">
			                	  <option value="">--请选择举报类型--</option>
			                	  <option value="1">追溯企业信息不符</option>	
				                  <option value="2">追溯产品信息不符</option>
			                </select>
							</dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line02 no_bor_top">
	                        <dt><img src="images/icon04.png" /></dt>
	                        <dd>
	                        	<select name="complaint.entId" id="entId"  style="height:30px; width: 200px; margin:6px 0 0 10px; ">
								</select>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line02 no_bor_top">
	                        <dt><img src="images/icon02.png" /></dt>
	                        <dd><input placeholder="请输入您的称呼,此项必须填写!" name="complaint.userName" id="username"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line02 no_bor_top">
	                        <dt><img src="images/icon03.png" /></dt>
	                        <dd><input placeholder="请输入您的电话号码,此项必须填写!" name="complaint.phone" id="phone"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                	<dl class="div_line02 no_bor_top">
	                        <dt><img src="images/icon02.png" /></dt>
	                        <dd><input placeholder="请输入您的通讯地址" name="complaint.userAddr" id="userAddr"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                	<dl class="div_line02 no_bor_top">
	                        <dt><img src="images/icon01.png" /></dt>
	                        <dd><input placeholder="请输入标题,此项必须填写!" name="complaint.title" id="title"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line02 no_bor_top">
	                        <dt><img src="images/icon02.png" /></dt>
	                        <dd><input placeholder="请输入举报产品名称" name="complaint.proName" id="proName"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line02 no_bor_top">
	                        <dt><img src="images/icon02.png" /></dt>
	                        <dd><input placeholder="请输入举报企业名称" name="complaint.companyName" id="companyName"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	
	                    
	                    <div class="div_line03 no_bor_top">
	                    	<input name="upload" type="file" title="上传附件"/>
	                    </div>
	                    <div class="div_line04 no_bor_top">
	                    	<textarea name="complaint.content" id="content" cols="" rows="" placeholder="请输入投诉内容,此项必须填写!"></textarea>
	                    </div>
	                </div>
	                <div class="div_btn" onclick="formsubmit();">提交</div>
                </form>
            </div>
            <div class="clear"></div>
        </div>
    </DIV>
	
    <div class="BOTTOM">
    	广东省农业厅版权所有       技术支持:广州薪火网络科技有限公司 
    </div>         	
 </div>
</body>
</html>

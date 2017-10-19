<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广州市肉类蔬菜流通追溯管理平台-举报投诉</title>

<link type="text/css" rel="stylesheet" href="<%=basePath %>static/css/portalweb/complaint.css"/>
	<script src="${pageContext.request.contextPath}/static/js/xheditor-1.2.1/jquery/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/hontek/complaint/jquery.validate.js" type="text/javascript"></script>

	<script type="text/javascript">
		var htmlTree = "<option value=\"-1\" selected=\"selected\">请选择区域</option>";//得到区域树
		$(function(){
			
			$.ajax({
				url:'compfor_getEntTree.action',
				async : false,
				type : 'post',
				dataType : 'json',
				success : function(result) {
				explaint(result,0);
				$("#entId").html(htmlTree);
				htmlTree = "<option value=\"-1\" selected=\"selected\">请选择区域</option>";
			}
			});


			$("#clearform").click(function(){
				$("#entForm").form("clear");
			});
			
		});
		//解析区域数据;
		function explaint(data,tem){
			var vo = {};
			var nb = "";
			for(var j = 0;j<tem;j++){ 
				nb = nb+"&nbsp;&nbsp;";
			}
			for(var i=0;i<data.length;i++){
				vo = data[i];
				htmlTree = htmlTree+"<option value=\""+vo.id+"\">"+nb+vo.text+"</option>";
				if(vo.children.length>0){
					explaint(vo.children,tem+1);
				}
			}
			
		}
		
		
			//表单验证 提交表单
		function formsubmit(){

			
			if($("#typeNo").val()==""){
				alert("请选择举报类型!");
				$("#typeNo").focus();
				return ;
			}

			if($("#title").val()==""){
				alert("举报标题不可为空!");
				$("#title").focus();
				return ;
			}
			if($("#entId").val()=="-1"){
				alert("请选择区域!");
				$("#entId").focus();
				return ;
			}
			if($("#username").val()==""){
				alert("举报人不可为空!");
				$("#username").focus();
				return ;
			}
			if($("#phone").val()==""){
				alert("举报人电话不可为空!");
				$("#phone").focus();
				return ;
			}
			if($("#content").val()==""){
				alert("举报内容不可为空!");
				$("#content").focus();
				return ;
			}
			$('#entForm').form('submit', {
				url : 'compfor_addComplaint.action',
				success : function(result) {
					//$('#entForm').form("clear");
					document.location.href="complaint_save.jsp";
				}
    		});
		}
	</script>
</head>
  
<body>
<DIV>
<jsp:include page="head.jsp"><jsp:param value="compt" name="navckeckId"/></jsp:include>
<DIV class="CENTER">
        <div class="CENTER_content">
        	
        	
        	<div class="CENTER_content_left">
        		<!-- 须知 -->
        	
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>举报须知:</font></div>
                	<div class="complaint_content">
                	1. 保证所述事件与事实相符，并承担投诉不实责任；请您在投诉时填写真实的资料，以便我们和您联系。
					<br>
					2. 您所举报的问题类型必须是举报类型中所包含的举报类型才能进行举报，其它类型举报概不受理。
					<br>
					3. 本站所有投诉信息均为网友（投诉者）个人意见，并不代表本站观点。
					<br>
					4. 投诉中心对投诉者信息进行严格保密。
					<br>
					5. 标注*为必填。
		           </div>
            
        		<!-- 须知 -->
            	<div class="product_title"><img src="<%=basePath %>static/image/portalweb/icon.png" /> <font>投诉举报:</font></div>
                	<form name="entForm" id="entForm"  method="post" enctype="multipart/form-data">
	                <div class="complaint_content">
	                	<dl class="div_line01" style="hight:50px;">
	                        <dt><strong>举报类型</strong><font style="color: red">*</font></dt>
	                        <dd>
	                        	<select name="complaint.typeNo" id="typeNo" style="width: 200px">
	                        		<option value="">--请选择类型--</option>
	                        		<option value="1">追溯企业信息不符</option>	
	                        		<option value="2">追溯产品信息不符</option>	                           
	                        	</select>
	                        </dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01" style="hight:50px;">
	                        <dt><strong>标题</strong><font style="color: red">*</font></dt>
	                        <dd><input type="text" name="complaint.title" id="title" style="width: 550px;" maxlength="45"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>举报产品名称</strong></dt>
	                        <dd><input type="text" name="complaint.proName" id="proName" style="width: 550px;" maxlength="45"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>举报企业名称</strong></dt>
	                        <dd><input type="text" name="complaint.companyName" id="companyName" style="width: 550px;" maxlength="45"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>举报人</strong><font style="color: red">*</font></dt>
	                        <dd><input type="text" name="complaint.userName" id="username" style="width: 550px;" maxlength="25"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>举报人电话</strong><font style="color: red">*</font></dt>
	                        <dd><input type="text" name="complaint.phone" id="phone" style="width: 550px;" maxlength="25" onkeyup="this.value=this.value.replace(/[^\d]/g,'')"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>通讯地址</strong></dt>
	                        <dd><input type="text" name="complaint.userAddr" id="userAddr" style="width: 550px;" maxlength="50"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    
	                    <dl class="div_line02" style="hight:50px;">
	                        <dt><strong>区域</strong><font style="color: red">*</font></dt>
	                        <dd><select name="complaint.entId" id="entId" style="width: 200px;margin:6px 0 0 10px;">
								</select>
							</dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line01 no_border" style="hight:50px;">
	                        <dt><strong>举报附件</strong></dt>
	                        <dd><input type="file" name="upload" class="w300"/></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <dl class="div_line02">
	                        <dt class="hei"><strong>填报内容</strong><font style="color: red">*</font></dt>
	                        <dd><textarea name="complaint.content" id="content" cols="60" rows=""></textarea></dd>
	                        <div class="clear"></div>
	                    </dl>
	                    <div class="div_btn">
	                        <span class="btn" id="subForm"  style="cursor: pointer" onclick="formsubmit();">提交</span>
	                        <span class="btn" id="clearform"  style="cursor: pointer">重置</span>
	                    </div>
                </div>
                </form>
            </div>
            
            <div class="CENTER_content_right">
            <!-- 右边列表 -->
            <jsp:include page="complaint_info.jsp"></jsp:include>
            
            </div>
        </div>
        <div class="clear"></div>
    </DIV>
    <div class="clear" style="height: 30px;"></div>
 <jsp:include page="bottom.jsp"></jsp:include>
 </DIV>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<title>广州肉菜溯源|投诉举报</title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link href="css/product-slide.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script src="<%=basePath %>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/easyui-1.3.4/src/jquery.form.js" type="text/javascript"></script>
<script src="<%=basePath %>static/js/hontek/complaint/jquery.validate.js" type="text/javascript"></script>
<script language="JavaScript" src="<%=basePath %>static/js/layer/layer.js"></script>
		<script type="text/javascript">
		var htmlTree = "<option value=\"-1\" selected=\"selected\">请选择区域</option>";//得到区域树
		$(function(){
			//alert($("#mainEntId").val());
			$.ajax({
				url:'compfor_getEntTreeByAreaId.action',
				async : false,
				type : 'post',
				dataType : 'json',
				success : function(result) {
				explaint(result,0);
				$("#entId").html(htmlTree);
				htmlTree = "<option value=\"-1\" selected=\"selected\">请选择区域</option>";
			}
			});

	var info ={};
	info["info.rsts"]=1;
	info["info.typeName"]="应急管理";	
	
	$.ajax( {
		url : "portal_Info_findNewsList.action",
		async : false,
		data : info,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			var total = result.total;
			total = parseInt(total);
			var htm1 = "";
			var title = "";
			var time = "";
			var infoId = "";
			var content = "";
			for ( var i = 0; i < result.rows.length; i++) {
				
					title = result.rows[i].title;
					content = result.rows[i].content;
					if(title.length>51){title=title.substring(0,50)+"...";}
					time = result.rows[i].crttime;
					if(time.length>11){time=time.substring(0, 10);}
					infoId = result.rows[i].infoId;
					if(i>3){
						htm1="<div style='display:none' id='ul"+i+"'><li><a target=\"_blank\" href=\"newsdetail.jsp?typecode=news&infoId="+infoId+"\">"+title+"</a><span>    ("+time+")</span></li></div>";
					}else{
						htm1="<div id='ul"+i+"'><li><a target=\"_blank\" href=\"newsdetail.jsp?typecode=news&infoId="+infoId+"\">"+title+"</a><span>    ("+time+")</span></li></div>";
					}
				
					$("#infotitle").append(htm1);
					
			}
			
			
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
				layer.confirm('请选择举报类型！', {icon: 3, title:'提示'}, function(index){
				    layer.close(index);
				});
				$("#typeNo").focus();
				return ;
			}

			if($("#title").val()==""){
				layer.confirm('举报标题不可为空！', {icon: 3, title:'提示'}, function(index){
				    layer.close(index);
				});
				$("#title").focus();
				return ;
			}
			if($("#entId").val()=="-1"){
				layer.confirm('请选择区域！', {icon: 3, title:'提示'}, function(index){
				    layer.close(index);
				});
				$("#entId").focus();
				return ;
			}
			if($("#username").val()==""){
				layer.confirm('举报人不可为空！', {icon: 3, title:'提示'}, function(index){
				    layer.close(index);
				});
				$("#username").focus();
				return ;
			}
			if($("#phone").val()==""){
				layer.confirm('举报人电话不可为空！', {icon: 3, title:'提示'}, function(index){
				    layer.close(index);
				});
				$("#phone").focus();
				return ;
			}
			if($("#content").val()==""){
				layer.confirm('举报内容不可为空！', {icon: 3, title:'提示'}, function(index){
				    layer.close(index);
				});
				$("#content").focus();
				return ;
			}
			/* $('#entForm').form('submit', {
				url : 'compfor_addComplaint.action',
				success : function(result) {
					$('#entForm').form("reset");
					document.location.href="complaint_save.jsp";
				}
    		}); */
    		$.ajax({
				url : 'compfor_addComplaint.action?uploadFileName='+$("#uploadFileName").val(),
		        type: "POST",
		        async : false,
		        data: $('#entForm').serialize(),
		        success : function(result) {
					document.location.href="complaint_save.jsp";
				}
		        });
		}
	</script>
	</head>
<body>
<div class="container ">
			
				<jsp:include page="head1.jsp" ><jsp:param value="news" name="navckeckId"/></jsp:include>
			<div class="main clearfix">
				<div class="main-content">
				<div class="left-box clearfix">
					<h3 class="page-title"><span>投诉举报</span></h3>
					<div class="left-box-content">
						
						<div class="complain-page">
	<form name="entForm" id="entForm"  method="post" enctype="multipart/form-data">  
      <label class="labels">
      <p class="inp_title"><span class="important">*</span>举报类型</p>
     <select name="complaint.typeNo" id="typeNo" class="select">
		        <option value="" selected="selected">--请选择类型--</option>
		        <option value="1">追溯企业信息不符</option>
		        <option value="2">追溯产品信息不符</option>
      </select>
      </label>
      <label class="labels">
	      	<p class="inp_title"><span class="important">*</span>行政区域</p>
			<select name="complaint.entId" id="entId"  class="select"> </select>
				
      </label>
      <label class="labels">
	      <p class="inp_title">举报产品名称</p>
	     <input name="complaint.proName" id="proName"  class="inp"/>
      </label>
	      <label class="labels">
      <p class="inp_title">举报企业名称</p>
     <input name="complaint.companyName" id="companyName" class="inp"/>
      </label>
      <label class="labels">
      <p class="inp_title"><span class="important">*</span>举报人</p>
      <input name="complaint.userName" id="username" class="inp"/>
      </label>
      <label class="labels">
      <p class="inp_title"><span class="important">*</span>举报人电话</p>
      <input name="complaint.phone" id="phone" class="inp"/>
      </label>
      <label class="labels">
      <p class="inp_title">通讯地址</p>
      <input name="complaint.userAddr" id="userAddr" class="inp"/>
      </label>
      <label class="labels">
      <p class="inp_title">举报附件</p>
     <input  type="file" name=complaint.appName class="inp" id="uploadFileName"/>
      </label>
      <label class="labels">
      <p class="inp_title"><span class="important">*</span>填报内容</p>
     <textarea  name="complaint.content" id="content" cols="" rows="" class="textarea" style="vertical-align:top;"></textarea>
      </label>
       </form>
      <div class="button-group">
      	<button class="button btn-reset" onclick="location.reload(true);">重置</button>
      <button class="button btn-submit" onclick="formsubmit();">提交</button>
     
      </div>
      
    </div>
					</div>
				</div>
				<div class="right-box clearfix">
					<div class="tips">	
					    <h4>举报须知</h4>
					    <p>1.您所举报的问题类型必须是举报类型中所包含的举报类型才能进行举报，其它类型举报概不受理。</p>
					    <p>2.保证所述事件与事实相符，并承担投诉不实责任；请您在投诉时填写真实的资料，以便我们和您联系。</p>
					    <p>3.本站所有投诉信息均为网友（投诉者）个人意见，并不代表本站观点。</p>
					    <p>4.投诉中心对投诉者信息进行严格保密。</p>
					    <p>5.标注<span class="important">*</span>为必填。</p>
					</div>
				</div>
				<div class="right-box clearfix">
					<div class="tips">	
					    <h4>应急管理</h4>
					    <ul class="right-box-list" id="infotitle">
					    	
					    </ul>
					</div>
				</div>
				</div>
				</div>
			
		<jsp:include page="bottom1.jsp" ><jsp:param value="news" name="navckeckId"/></jsp:include>
			
		</div>
</body>
</html>


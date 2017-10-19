<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/element/";

String recId = request.getParameter("recId");
String objId = request.getParameter("objId");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>档案信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}<%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>	
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	<!-- 图片显示 -->
	<link rel="stylesheet" type="text/css" href="${basePath}static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	<script src="${basePath}static/js/fancybox/jquery.mousewheel-3.0.4.pack.js" type="text/javascript" ></script>
	<script src="${basePath}static/js/fancybox/jquery.fancybox-1.3.4.pack.js" type="text/javascript" ></script>
	
	<script type="text/javascript">
		$(function(){

			var data = {"objElement.objId":<%=objId%>,"objElement.objTypeId":3};								

			$.ajax({
				   type: "POST",
				   url: "objElement_findObjElementList.action",
				   async: false,
				   data: data,
				   dataType:"JSON",
				   success: function(result){
					for(var j=0;j<10;j++){
						var exists = $('#tabs').tabs('exists', 0);  
						if(exists){
							$('#tabs').tabs('close', 0);  
						}else{
							break;
						}
					}
					
					if(result.total>0){
						for(var j=0;j<result.total;j++){
							var elem = result.rows[j];				
							var elementName = elem.elementName;								
							$('#tabs').tabs('add',{    
							    title:elementName,
							    selected: j==0,		// 选中第一个    
							    content:'<iframe src="jsp/element/'+elem.elementUrl+'?recId='+<%=recId%>+'" width="99%" height="99%" frameborder="0"></iframe>'    
							});  				
						}			
						
					}else{
						$('#tabs').tabs('add',{    
						    title:'提示',    
						    content:'<div>该档案暂无信息!</div>'    
						});  
					}
				     									     									     
				   }
				});				
		});
	
	</script>
	
  </head>
  <body style="padding: 0px">
     <input type="hidden" id="filePath" value="<%=filePath %>">
       
	<div id="tabs" class="easyui-tabs" data-options="fit:true">   
	   
	</div>  
   
  </body>
</html>

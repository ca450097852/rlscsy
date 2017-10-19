<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String supId = request.getParameter("supId");
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
	<script src="<%= basePath%>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){			
			var cond = {};
			cond["ids"]=<%=supId%>;
			$.post('mbsupervise_findSuperviseListById.action',cond,function(result){
				var ht = "";			
				for(var i=0;i<result.length;i++){
					var supervise = result[i];
					ht+=supervise.contents;
				}
				$('#main').html(ht);				
			},'JSON');
			
		
		});
	</script>
</head>

<body>
	<div id="main"></div>
</body>
</html>

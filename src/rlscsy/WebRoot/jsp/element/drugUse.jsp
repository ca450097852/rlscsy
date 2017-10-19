<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String recId = request.getParameter("recId");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>投入品使用记录</title>
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
	
	<script type="text/javascript">
		$(function(){
			//表格数据			
			$('#drugUseDatagrid').datagrid({  
				  //title:'投入品使用记录',
				  iconCls : 'icon-table',
			      url:'drugUse_findDrugUseList.action',//url调用Action方法  
			      queryParams:{"drugUse.recId":<%=recId%>},
			      loadMsg : '数据装载中......',  			      
			      fit:true,			    
			      striped : true,//设置为true将交替显示行背景。  			       
			      fitColumns:true,//允许表格自动缩放，以适应父容器  
			      columns:[[
							{field:'drugName',title:'药品名称',width:100,align:'center'},
							{field:'useObject',title:'防治对象',width:100,align:'center'},
							{field:'useWay',title:'使用方式',width:50,align:'center'},			
							{field:'useDosage',title:'稀释浓度',width:50,align:'center'},			
							{field:'stopDate',title:'使用量',width:50,align:'center'},			
							{field:'useMan',title:'使用人',width:50,align:'center'},
							{field:'useDate',title:'使用日期',width:50,align:'center'},			
							{field:'safeDay',title:'安全隔离天数',width:50,align:'center'},			
							{field:'safeDate',title:'安全采收期',width:100,align:'center',formatter: function(value,row,index){
								if(value){
									var arr = value.split("-");
								    var starttime = new Date(arr[0], (parseInt(arr[1])-1), arr[2]);
								    var starttimes = starttime.getTime();							    
									var date = new Date();
								    var lktimes = date.getTime();								    									
									if(lktimes>starttimes){
										return "<font color=green>"+value+"</font>";
									}else{
										return "<font color=red>"+value+"</font>";
									}									
								}								 	
							}}	
				      ]],
		          pagination : true,//分页  
		          rownumbers : true,//行数  				          
		          onLoadSuccess : function(data) {
					f_timeout(data);
				  },
			 	  onClickRow: function(rowIndex, rowData){
		 			$(this).datagrid('unselectAll');
		 			$(this).datagrid('selectRow',rowIndex);
		 		  }
			});

		});
	
	</script>
	
  </head>
  <body>
   <!-- 列表 -->
   <table id="drugUseDatagrid"></table>  
   
  </body>
</html>

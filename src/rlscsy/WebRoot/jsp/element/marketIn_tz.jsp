<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/element/";

String ptbId = request.getParameter("ptbId");

System.out.println("ptbId="+ptbId);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>投入品购买记录</title>
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
			
			//表格数据			
			$('#datagrid').datagrid({  
				 // title:'投入品购买记录',
				  iconCls : 'icon-table',
			      url:'animalInInfo_findList.action',//url调用Action方法  
			      queryParams:{"animalInInfo.ptbId":<%=ptbId%>},
			      loadMsg : '数据装载中......',  			      
			      fit:true,			    
			      striped : true,//设置为true将交替显示行背景。  			       
			      fitColumns:true,//允许表格自动缩放，以适应父容器  
			      columns:[[
							{field:'butcherFacId',title:'屠宰厂编码',width:50,align:'center'},							
							{field:'butcherFacName',title:'屠宰厂名称',width:100,align:'center'},
							{field:'inDate',title:'进场日期',width:100,align:'center'},
							{field:'sellerId',title:'货主编码',width:50,align:'center'},
							{field:'sellerName',title:'货主名称',width:100,align:'center'},
							{field:'quarantineId',title:'产地检疫证号',width:100,align:'center'},
							{field:'quarantineNum',title:'进场数量',width:50,align:'center'},	
							{field:'farmName',title:'养殖场名称',width:100,align:'center'},
							{field:'price',title:'采购价',width:50,align:'center'}
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
     <input type="hidden" id="filePath" value="<%=filePath %>">
  
   <!-- 列表 -->
   <table id="datagrid"></table>  
   
  </body>
</html>

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
    <title>销售信息</title>
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
			$('#saleinfoDatagrid').datagrid({  
				  //title:'销售信息',
				  iconCls : 'icon-table',
			      url:'saleinfo_findSaleinfoList.action',//url调用Action方法  
			      queryParams:{"saleinfo.recId":<%=recId%>},
			      loadMsg : '数据装载中......',  			      
			      fit:true,			    
			      striped : true,//设置为true将交替显示行背景。  			       
			      fitColumns:true,//允许表格自动缩放，以适应父容器  
			      columns:[[
							{field:'listed',title:'上市日期',width:100,align:'center'},
							{field:'saleUser',title:'销售人',width:50,align:'center'},
							{field:'salearea',title:'主要销售地',width:100,align:'center'},
							{field:'sgCompany',title:'收购厂家',width:100,align:'center'},
							{field:'sgUser',title:'收购人',width:50,align:'center'},							
							{field:'packing',title:'产品包装',width:50,align:'center'},			
							{field:'tranId',title:'交易凭证号',width:100,align:'center'}
							
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
   <table id="saleinfoDatagrid"></table>  
   
  </body>
</html>

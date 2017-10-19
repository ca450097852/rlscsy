<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/nytsyFiles/element/";

String recId = request.getParameter("recId");

System.out.println("recId="+recId);
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
			var filePath = $("#filePath").val();
			
			//表格数据			
			$('#agriinputDatagrid').datagrid({  
				 // title:'投入品购买记录',
				  iconCls : 'icon-table',
			      url:'agriInput_findAgriInputList.action',//url调用Action方法  
			      queryParams:{"agriInput.recId":<%=recId%>},
			      loadMsg : '数据装载中......',  			      
			      fit:true,			    
			      striped : true,//设置为true将交替显示行背景。  			       
			      fitColumns:true,//允许表格自动缩放，以适应父容器  
			      columns:[[
							{field:'typeName',title:'投入品类别',width:50,align:'center'},							
							{field:'agriName',title:'投入品名称',width:100,align:'center'},
							{field:'agriCompany',title:'生产厂家',width:100,align:'center'},
							{field:'buyUser',title:'购买人',width:50,align:'center'},
							{field:'buyAddr',title:'销售商',width:100,align:'center'},
							{field:'buyDate',title:'购买时间',width:100,align:'center'},
							{field:'buyNum',title:'购买数量',width:100,align:'center',formatter : function(value,row,index){ 	
								if(value!=''){									
									return value+" "+row.buyUnit;
								}								
							}},			
							{field:'agriImg',title:'包装图片',width:100,align:'center',
								formatter : function(value,row,index){ 	
									if(value!=''){
										var ht = "";				
										var app_group = "app_group"+row.agriId;									
										ht+= "<a rel=\""+app_group+"\" href=\""+filePath+value+"\" title=\""+row.agriName+"\">查看图片</a>";										
										return ht;										
									}else{
										return '没有图片';
									}								
								}
							}
				      ]],
		          pagination : true,//分页  
		          rownumbers : true,//行数  				          
		          onLoadSuccess : function(data) {
					f_timeout(data);
					if(data.total>0){						
						for(var i=0; i< data.total;i++){
							var rowX = data.rows[i];
							if(rowX.agriImg!=''){
								var app_group = "app_group"+rowX.agriId;
								$("a[rel="+app_group+"]").fancybox({});
							}							
						}						
					}
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
   <table id="agriinputDatagrid"></table>  
   
  </body>
</html>

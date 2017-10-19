<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta content="text/html; charset=UTF-8" http-equiv="content-type">
	<meta content="text/javascript" http-equiv="Content-Script-Type">
	<meta content="text/css" http-equiv="Content-Style-Type">
	<link href="<%=basePath%>static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath%>static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="<%=basePath%>static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/jquery.cookie.js"></script>
	<link href="<%=basePath%><%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="<%=basePath%>static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="<%=basePath%>static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>	
	<script src="<%=basePath%>static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(function() {
			//表格数据
			$('#productTable').datagrid({  
				  title:'溯源统计列表',
				  iconCls : 'icon-flag_green',
			      pageSize : 20,//默认选择的分页是每页5行数据  
			      pageList : [ 10,20,30,50],//可以选择的分页集合  
			      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
			      striped : true,//设置为true将交替显示行背景。  
			      //collapsible : true,//显示可折叠按钮  
			      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
			      url:'scanCount_findScanCountTable.action',//url调用Action方法  
			      loadMsg : '数据装载中......',  			      
			      fit:true,			      
			      //singleSelect:true,//为true时只能选择单行  
			      fitColumns:true,//允许表格自动缩放，以适应父容器  
			      remoteSort : false,  
			      columns:[[
						{field:'comName',title:'企业名称',width:200,align:'center'},			
						{field:'dimenno',title:'二维码',width:150,align:'center'},
						{field:'count',title:'扫码数量',width:100,align:'center'}
			      ]],
		          pagination : true,//分页  
		          rownumbers : true,//行数  
		          
		          onLoadSuccess : function(data) {
					//f_timeout(data);
				 },
		 		 onClickRow: function(rowIndex, rowData){
		 			$(this).datagrid('unselectAll');
		 			$(this).datagrid('selectRow',rowIndex);
		 		}/* 		,
				view: detailview,
			    detailFormatter:function(index,row){
			        return '<div style="padding:2px"><table class="ddv"></table></div>';
			    },
			    onExpandRow: function(index,row){
			     var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
			     
			   	 var queryParams = {"scanCount.entId":row.entId};
			        
			        ddv.datagrid({
			            url:'scanCount_findScanCountTable2.action',
			            queryParams:queryParams,
			            fitColumns:true,
			            singleSelect:true,
			            rownumbers:true,
			            loadMsg:'数据加载中。。。',
			            height:'auto',
			            columns:[[
			                //{field:'entName',title:'企业名称',width:200,align:'center'},			
							{field:'dimenno',title:'二维码',width:200,align:'center'},
							{field:'print_code_count',title:'预计打码数',width:100,align:'center'},						
							{field:'count',title:'扫码数量',width:100,align:'center'}
			               
			            ]],
			            onResize:function(){
			                $('#productTable').datagrid('fixDetailRowHeight',index);
			            },
			            onLoadSuccess:function(){
			                setTimeout(function(){
			                    $('#productTable').datagrid('fixDetailRowHeight',index);
			                },0);
			            }
			        });
			        $('#productTable').datagrid('fixDetailRowHeight',index);
			    } */
			}); 	
		});
		
		function find(){
			var entName = $("#entName").val();
			//var dimenno = $("#dimenno").val();		
			var datas = {"scanCount.entName":entName};	

			$('#productTable').datagrid("reload",datas);

		}
		
		function resetValue(){
			$("#entName").val('');
			//$("#dimenno").val('');	
			find();
		}
		
	</script>
	    
    
  </head>
  <body style="padding: 0px" >
		
		 <div id="tb">
				&nbsp;&nbsp;企业名称: <input class="easyui-validatebox" style="width:200px" id="entName">
				<!-- &nbsp;&nbsp;二维码: <input class="easyui-validatebox" style="width:200px" id="dimenno"> -->
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find()">统计</a>
				&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="resetValue()">重置</a>
		</div> 
		
  		 <table id="productTable"></talbe>  
  	
	   		   
  </body>
</html>

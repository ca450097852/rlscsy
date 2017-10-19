<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String recId = request.getParameter("recId");
String flagStr = request.getParameter("flag");
boolean flag = "out".equals(flagStr);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>生产节点信息</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>	
	<link href="${basePath}<%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
	
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/browser.js"></script>
	<script type="text/javascript" src="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>static/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	
	
	<script type="text/javascript">
		$(function(){
			//表格数据			
			$('#nodeinfoDatagrid').datagrid({  
				  //title:'生产节点信息',
				  iconCls : 'icon-table',
			      url:'nodeinfo_findNodeinfoList.action',//url调用Action方法  
			      queryParams:{"nodeinfo.recId":<%=recId%>},
			      loadMsg : '数据装载中......',  			      
			      fit:true,			    
			      striped : true,//设置为true将交替显示行背景。  			       
			      fitColumns:true,//允许表格自动缩放，以适应父容器  
			      columns:[[
			      			{field:'nodeImg',title:'节点图片',width:100,align:'center',
								formatter: function(value,row,index){
									var niName = row.niName==null?"":"节点名称为：《"+row.niName+"》";
									if(value!=null&&value!=""){
										var fpath = "/nytsyFiles/element/";
										return "<a rel=\"previewImg\" href=\""+fpath+value+"\" title=\""+niName+"\"><img alt=\"预览\" src=\""+fpath+value+"\" style=\"width:100px; height:50px\"></a>";
									}else{
										return "无";
									}
								}
							},
							{field:'niName',title:'节点名称',width:100,align:'center'},
							{field:'crttime',title:'时间',width:120,align:'center'},
							{field:'remark',title:'备注',width:100,align:'center'}
				      ]],
		          pagination : true,//分页  
		          rownumbers : true,//行数  				          
		          onLoadSuccess : function(data) {
					f_timeout(data);
					
					<%
					if(flag){
						%>
						$("a[rel=previewImg]").click(function(){
							$('#chimgDiv', window.parent.document).html($(this).parent().html());
							parent.showChildImg();
							return false;
						});
						<%
					}else{
						%>
						$("a[rel=previewImg]").fancybox({
							'transitionIn'		: 'none',
							'transitionOut'		: 'none',
							'titlePosition' 	: 'over',
							'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
								return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
							}
						});
						<%
					}
					%>
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
   <table id="nodeinfoDatagrid"></table>  
   
  </body>
</html>

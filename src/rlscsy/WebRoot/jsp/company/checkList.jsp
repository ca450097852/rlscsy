<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.hontek.comm.base.LoginUser"%>
<%@page import="com.hontek.comm.util.DateUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
LoginUser obj = (LoginUser)session.getAttribute("loginUser");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>企业管理 - 监管信息发布</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${basePath}static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="${basePath}static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<script type="text/javascript" src="${basePath}static/js/jquery.cookie.js"></script>
	<link href="${basePath}<%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>	
	<script src="${basePath}static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="${basePath}static/js/easyui-1.3.4/easyuiExpand.js"　type="text/javascript"></script>		
	<script src="${basePath}static/js/hontek/comm/hontek.js"　type="text/javascript"></script>	
	    	
	<script type="text/javascript">
		$(function(){
			//表格数据			
			$('#checkTable').datagrid({  
				  //title:'检验记录',
				  iconCls : 'icon-table',
			      url:'checkinfo_findCheckinfoList.action',//url调用Action方法  
			      //queryParams:{"checkinfo.recId":},
			      loadMsg : '数据装载中......',  			 
			      toolbar:"#tb",
			      fit:true,			    
			      striped : true,//设置为true将交替显示行背景。  			       
			      fitColumns:true,//允许表格自动缩放，以适应父容器  
			      columns:[[
							{field:'checkName',title:'报告名称',width:100,align:'center'},
							{field:'checkNum',title:'报告编号',width:100,align:'center'},
							{field:'checkTime',title:'检验时间',width:100,align:'center'},
							{field:'checkUnit',title:'检验单位',width:150,align:'center'},	
							{field:'checkResult',title:'检验结果',width:150,align:'center'},		
							{field:'entName',title:'检测对象',width:100,align:'center'}
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
		
		
		function submitCheckinfoForm(){
			$('#checkinfoForm').form('submit', {
				url : 'checkinfo_addCheckinfo.action',
				onSubmit : function(result) {
					return $(this).form('validate');// 对数据进行格式化
				},
				success : function(result) {
					$.messager.show({ title : '提示', msg : result });   	
					$('#checkinfoForm').form('reset');
				}
			});
		}
		
		
		function appendCheck(){
			
			$("#checkWin").window("open");
		}

		var dataGrid;
		var items = [];
		function setObj(){
			$("#entTab").window("open");
			dataGrid = $('#list_enterprise').datagrid( {
				url : 'ent_findCompanyList.action',
				queryParams: {"enterprise.isReported": '0'},
				toolbar : "#tb1", // 在添加 增添、删除、修改操作的按钮要用到这个
				loadMsg : '数据加载中...',
				pageSize : 10,// 默认选择的分页是每页10行数据
				pageList : [ 10, 20, 30, 50 ],// 可以选择的分页集合
				nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
				striped : true,// 设置为true将交替显示行背景。
				fit : true,
				fitColumns: true,
				pagination : true,
				rownumbers : true,
				remoteSort : false,
				singleSelect:true,
				columns : [ [ 
				{
					field : 'entId',
					checkbox : true
				},
				{field : 'name',title : '企业名称',width : 200,halign:'center'}, 
				{field : 'tel',title : '联系电话',width : 60,align : 'center'}
				] ],
				onLoadSuccess:function(data){
				 	f_timeout(data);				 	
			 	},
			 	onDblClickRow:function(rowIndex, rowData){
			 		$("#ent_id").val(rowData.entId);
			 		$("#ent_name").val(rowData.name);
			 		$("#entTab").window("close");
			 	}
			});			
		}
		
	</script>
    
  </head>
  <body>
  
	<table id="checkTable" ></table>
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="appendCheck()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateCheck()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeCheck()">删除</a>	
	 </div>
	
	<div  id="checkWin" class="easyui-window" title="检测信息"	 data-options="iconCls:'icon-table',modal:true,closed:true" style="width:550px;height:400;">
		<form action="" id="checkinfoForm" method="post">
			<input type="hidden" name="ids" />
			<table class="formtable" cellspacing="1" cellpadding="0">
				<tr>
					<td class="form_label">检测名称:</td>
					<td class="form_value"><input type="text" class="easyui-validatebox" data-options="required:true" id="" name="checkinfo.checkName" style="width: 300px"></td>
				</tr>
				<tr>
					<td class="form_label">检测编号:</td>
					<td class="form_value">
						<input type="text" class="easyui-validatebox" data-options="required:true" id="" name="checkinfo.checkNum"  style="width: 300px">
					</td>
				</tr>
				<tr>
					<td class="form_label">检测单位:</td>
					<td class="form_value"><input type="text" class="easyui-validatebox" data-options="required:true" id="" name="checkinfo.checkUnit" style="width: 300px"></td>
				</tr>
				<tr>
					<td class="form_label">检测时间:</td>
					<td class="form_value">
						<input type="text" class="easyui-datebox" data-options="required:true" id="" name="checkinfo.checkTime" value="<%=DateUtil.formatDate() %>">
					</td>
				</tr>
				<tr>
					<td class="form_label">检测对象:</td>
					<td class="form_value">
						<input type="text" class="easyui-validatebox" data-options="required:true" id="ent_name" name="checkinfo.entName" style="width: 250px">
						<input type="hidden"  id="ent_id" name="checkinfo.entId" style="width: 200px">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="setObj()">设置检测对象</a>
						
					</td>
				</tr>
				<tr>
					<td class="form_label">检测结果:</td>
					<td class="form_value" ><textarea id="xh_editor" name="checkinfo.checkResult" style="width: 99%;height: 100px"></textarea> 
					
					</td>
				</tr>
					
				<tr>
					<td class="form_value" colspan="2" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitCheckinfoForm()" data-options="iconCls:'icon-ok'" id="btnSupervise">提交</a>
						&nbsp;	&nbsp;	&nbsp;	&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick='$("#checkWin").window("close")' data-options="iconCls:'icon-no'" id="btnSupervise">关闭</a>

					</td>
				</tr>				
			</table>
		</form>
	</div>
	
	<div id="entTab" class="easyui-window" title="设置检测对象"
	 data-options="iconCls:'icon-table',modal:true,closed:true" style="width:600px;height:450;">
		 	<table id="list_enterprise">
   			</table> 
   			<div id="tb1" style="height:auto;padding: 5px 0;">
				企业名称: <input class="easyui-validatebox" style="width:150px" id="nameSearch">&nbsp;&nbsp;
				地区: <select class="easyui-combobox" style="width:200px;" id="areaId_id">
					      </select>&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="find()">搜 索</a>&nbsp;&nbsp;
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="clearSearch();">重 置</a>
			</div>
	</div>	
	
	
	
  </body>
</html>

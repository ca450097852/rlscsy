<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>投入品类型列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="static/js/easyui-1.3.4/themes/icon.css" rel="stylesheet" type="text/css"/>
	<link href="static/js/easyui-1.3.4/demo/demo.css" rel="stylesheet" type="text/css"/>
	<script src="static/js/jquery/jquery-1.8.0.min.js"　type="text/javascript"></script>
	<link href="<%=session.getAttribute("entStyle")==null?"static/js/easyui-1.3.4/themes/default/easyui.css":((com.hontek.sys.pojo.EntStyle)(session.getAttribute("entStyle"))).getScCss() %>" id="easyuiTheme" rel="stylesheet" type="text/css"/>
	<script src="static/js/easyui-1.3.4/jquery.easyui.min.js"　type="text/javascript"></script>
	<script src="static/js/easyui-1.3.4/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
	<script src="static/js/hontek/comm/hontek.js"　type="text/javascript"></script>
  </head>
  <body>
   <!-- 类型列表 -->
   <table id="list_type">
   </table>  
    <!-- list_enttype 工具栏 -->
    <div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="update()">修改</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<div>
				&nbsp;&nbsp;类型名称: <input class="easyui-validatebox" style="width:120px" id="name" name="typeName">
				&nbsp;&nbsp;备注: <input class="easyui-validatebox" style="width:120px" id="remark" name="remarks">
				&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="submit_search" onclick="find()">搜 索</a>
		</div>
	</div>
	
	<!--  -->
	<div id="add" class="easyui-window" title="" data-options="iconCls:'icon-save',resizable:false" style="width:500px;height:300px;padding:10px;top:150px">
		 <div class="easyui-panel" style="width:460px">
         <div align="center">
		    <form id="add_form" method="post">
		    	<input type="hidden" id="typeId" name="agriType.typeId"/>		    
				<table class="formtable" cellspacing="1" cellpadding="0">
				    <tr height="40px">
				        <td class="form_label">类型名称：</td>
					    <td class="form_value">
					      <input class="easyui-validatebox" type="text" id="typeName" name="agriType.typeName" data-options="required:true" style="width:250px">
					    </td>
				    </tr>
				    <tr height="40px">
				        <td class="form_label">排序号：</td>
					    <td class="form_value">
					      <input class="easyui-numberbox" type="text" id="seq" name="agriType.seq" style="width:250px">
					    </td>
				    </tr>
				    <tr height="40px">
				        <td class="form_label">状态：</td>
					    <td class="form_value">
					    
					    <select id="agriType_state" name="agriType.state"  class="easyui-combobox" data-options="panelHeight: 'auto'" style="width:250" data-options="required:true" >
								<option selected="selected" value="1">启用</option>
								<option value="2">停用</option>
							</select>					    
					    </td>
				    </tr>
				    <tr height="40px">
				        <td class="form_label">备注：</td>
					    <td class="form_value">
					      <textarea class="easyui-validatebox" rows="5" cols="45" id="remarks" name="agriType.remarks" ></textarea>
					    </td>
				    </tr>
			    </table>
		    </form>
       </div>
	   <div style="text-align:center;padding:5px">
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-save" onclick="submitForm()">提 交</a>&nbsp;&nbsp;&nbsp;
		    <a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-cancel" onclick="clearForm()">关 闭</a>
	   </div>
    </div>
	</div>
  </body>
  
  <script type="text/javascript">
  
  var dataGrid;
  var formUrl = '';
	$(function() {
	     $('#add').window('close');
		 
		 dataGrid = $('#list_type').datagrid({  
		    url:'agriType_findAgriTypeList.action',
			toolbar:"#tb",    //在添加 增添、删除、修改操作的按钮要用到这个  
			title: '投入品类别管理',
			iconCls: 'icon-enterprise',
			loadMsg:'数据加载中...',
		    nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
		    striped : true,//设置为true将交替显示行背景。
			fit:true,
			fitColumns: true,
			singleSelect:true,
			pagination:true,
			rownumbers:true,
			remoteSort : false,
			columns:[[
				{field:'typeName',title:'类型名称',width:100,align:'center'},
				{field:'seq',title:'排序号',width:100,align:'center'},
				{field:'state',title:'状态',width:100,align:'center',formatter: function(value,row,index){ 
					if(value==1){
						return "启用";
					}else{
						return "停用";
					}
				}},
				{field:'remarks',title:'备注',width:100,align:'center'},
				
			]],
			onLoadSuccess:function(data){
			 	f_timeout(data);
	 		},
	 		onClickRow: function(rowIndex, rowData){
		 		$(this).datagrid('unselectAll');
		 		$(this).datagrid('selectRow',rowIndex);
		 	}
		});			 
		 
	});
	
	// 显示
	function append(){
		//$('#add').window('open');
		 // 情况表单数据
		 $('#add_form').form('reset');
		 formUrl = 'agriType_addAgriType.action';
		 $("#add").dialog("open").dialog('setTitle', '添加投入品类型');
	}
	
	// 修改
	function update(){
		var rows = $('#list_type').datagrid("getSelections");
		var leng = rows.length;
		if(leng==0){
		    $.messager.alert('提示','请选择你需要修改的记录!','info');
		}else{
		    if(leng>1){
		        $.messager.alert('提示','只能修改一条记录!','info');
		        return false;
		    }else{
				 formUrl = 'agriType_updateAgriType.action';
		         var agriType = rows[0];
				 $('#add_form').form('reset');
		         
		         $("input[name='agriType.typeId']").val(agriType.typeId);
		         $("input[name='agriType.typeName']").val(agriType.typeName);
				 $("#seq").numberbox("setValue",agriType.seq);
				 $("#agriType_state").combobox("setValue",agriType.state);

				 $("textarea[name='agriType.remarks']").val(agriType.remarks);
				 
				 $('#add_form').form('validate');
				 $('#add').window('open').dialog('setTitle', '修改投入品类型');
				 
		    }
		}
	}
	
	// 隐藏
	function clearForm(){
     $('#add').window('close');
  }		
	
	// 删除
	function removeit(){
		var rows = $("#list_type").datagrid("getSelections");
		 //判断是否选择行
		if (!rows || rows.length == 0) {
			$.messager.alert('提示', '请选择要删除的记录!', 'info');
			return false;
		}else{
		    var temp;   //循环给提交删除参数赋值
			$.each(rows, function (i, n) {
				if (i == 0) {
					temp =  n.typeId;
				} else {
					temp += "," + n.typeId;
				}
			}); 
			$.messager.confirm('提示', '是否删除选中数据?', function (r){
				if (!r) {
					return;
			    }else{
			         parent.$.messager.progress({
						title : '提示',
						text : '数据处理中，请稍后....'
			         });
			         $.ajax({
			         	  url:'agriType_deleteAgriType.action',
			         	  data:'ids='+temp,
			         	  type:'post',
			         	  dataType:'text',
			         	  success : function(result) {
			         		   parent.$.messager.progress('close');
							   $.messager.show({ title : '提示', msg : result});
							   dataGrid.datagrid('reload');
						  }    
			         });
			    }
			});    
		}
	}
	
	// 搜索
	function find(){
	   var typeName = $('#name').val();
	   var remarks = $('#remark').val();		       
	   var agriType = {};
	   agriType["agriType.typeName"] = typeName;
	   agriType["agriType.remarks"] = remarks;
	   dataGrid.datagrid('load',agriType); // 点击搜索
	   
	}
	
  // 提交表
  function submitForm(){
  	if($('#add_form').form('validate')==false){
  		$.messager.show({ title : '提示', msg : '录入验证未通过!'});
  		return;	
  	}
       parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	   });
       $('#add_form').form('submit', {
		url : formUrl,
		onSubmit : function(result) { 
		    return $(this).form('validate');//对数据进行格式化
		},
		success : function(result) {
		    $('#add').window('close');			
			parent.$.messager.progress('close');
			$.messager.show({ title : '提示', msg : result });
			dataGrid.datagrid('reload');
	}
});
   
  }
  
  </script>
  
</html>

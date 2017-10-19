$(function(){
	//表格数据
	$('#useguideDataGrid').datagrid({  
		  title:'操作指引信息',
		  iconCls : 'icon-table',
	      pageSize : 15,//默认选择的分页是每页5行数据  
	      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
	      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	      striped : true,//设置为true将交替显示行背景。  
	      //collapsible : true,//显示可折叠按钮  
	      toolbar:"#tb_useguide",//在添加 增添、删除、修改操作的按钮要用到这个  
	      url:'useguide_findUseguidePagerList.action',//url调用Action方法  
	      loadMsg : '数据装载中......',  			      
	      fit:true,			
	      toolbar: '#tb',
	      //singleSelect:true,//为true时只能选择单行  
	      fitColumns:true,//允许表格自动缩放，以适应父容器  
	      remoteSort : false,  
	      columns:[[
				{field:'title',title:'指引标题',width:300,align:'center'},			
				//{field:'contents',title:'信息内容',width:300,halign:'center'},
				{field:'ugNo',title:'指引编号',width:200,align:'center'},
				{field:'action',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
					var t1='&nbsp;&nbsp;<a href=\'javascript:void(0);\' onclick=\'deleteUseguide('+index+');\'>删除</a>'
					return t1;
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

/********************************指引信息开始*****************************************/
var useguideFormUrl = '';
function appendUseguide(){
	$("#useguide_title").val('');
	$("#xh_editor").val('');	
	$("#uploadList").html('');
	$("#useguide_ugId").val('');
	$("#useguide_ugNo").val('');
	
	$("#useguide_ugNo").attr("readonly",false)//将input元素设置为readonly

	
	$('#useguideForm').form('validate');
	useguideFormUrl = 'useguide_addUseguide.action';
	$('#useguideWin').window('open').window('maximize').window('setTitle', '添加指引信息');;
}


function updateUseguide(){
	var useguide = $('#useguideDataGrid').datagrid("getSelected");		
	if(useguide==null){
		$.messager.show( {title : '提示',msg : '请在列表中选择您要修改的指引信息!'});
		return;
	}
	
	$("#useguide_ugNo").attr("readonly",true)//将input元素设置为readonly
	
	$("input[name='useguide.ugId']").val(useguide.ugId);
	$("input[name='useguide.ugNo']").val(useguide.ugNo);
	$("input[name='useguide.title']").val(useguide.title);
	$("textarea[name='useguide.contents']").val(useguide.contents);
	$('#useguideForm').form('validate');
	useguideFormUrl = 'useguide_updateUseguide.action';
	$('#useguideWin').window('open').window('maximize').window('setTitle', '修改指引信息');
}

function deleteUseguide(i){
	var useguide = $('#useguideDataGrid').datagrid("getRows")[i];		
	var id = useguide.supId;	
	$.messager.confirm('提示', '是否删除选中数据?', function(r) {
		if (r) {
			$.ajax( {
				url : 'useguide_deleteUseguide.action',
				data : 'ids='+ id,
				type : 'post',
				dataType : 'text',
				success : function(result) {
					$.messager.show( {title : '提示',msg : result});
					$('#useguideDataGrid').datagrid('reload');
				}
			});
		}
	});
}

function submitUseguideForm(){	
	
	$("#xh_editor").val();
	$('#useguideForm').form('submit', {
		url : useguideFormUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			$.messager.show({ title : '提示', msg : result });   					
			//隐藏提交按钮
			$('#useguideWin').window('close');
			$('#useguideDataGrid').datagrid('reload');
		}
	});
}


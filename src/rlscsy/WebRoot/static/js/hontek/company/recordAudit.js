var formUrl = '';
$(function() { 	 	
	//表格数据
	$('#zizhiAuditRecordDataGrid').datagrid({  
		  title:'档案审核',
		  iconCls : 'icon-table',
	      pageSize : 15,//默认选择的分页是每页5行数据  
	      pageList : [ 10, 15,20,30, 50 ],//可以选择的分页集合  
	      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	      striped : true,//设置为true将交替显示行背景。  
	      //collapsible : true,//显示可折叠按钮  
	      url:'record_getAuditRecord.action',//url调用Action方法  
	      //toolbar:"#tb",
	      loadMsg : '数据装载中......',  			      
	      fit:true,			      
	      //singleSelect:true,//为true时只能选择单行  
	      fitColumns:true,//允许表格自动缩放，以适应父容器  
	      remoteSort : false,  
	      columns:[[
				{field:'entName',title:'企业名称',width:200,align:'left'},					
				{field:'recName',title:'档案名称',width:150,align:'left'},			
				{field:'objTypeId',title:'档案类型',width:100,align:'center',
					formatter: function(value,row,index){ 
						//普通1；三品一标2；种植类3；畜牧类4；批次5
						var displayValue = '';
						if(value==1){
							displayValue = '主体档案';
						}else if(value==2){
							displayValue = '加工类档案';
						}else if(value==3){
							displayValue = '种植类档案';
						}else if(value==4){
							displayValue = '畜牧类档案';
						}else if(value==5){
							displayValue = '批次档案';
						}
						return displayValue;
					}
				},
				{field:'action',title:'操作',width:50,align:'center',formatter: function(value,row,index){ 					
					var t1='<a href=\'javascript:void(0);\' onclick=\'recordWin('+index+');\'>审核</a>'										
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
	
	parent.$.messager.progress('close');
});
	

var GROW;
function recordWin(index){
	var row = $('#zizhiAuditRecordDataGrid').datagrid('getRows')[index];
	
	GROW = row;
	
	$("#recordframe").attr('src',"jsp/element/records.jsp?recId="+row.recId+"+&objId="+row.ptqId); 
	
	$('#showRecord').window('open');
}


function updateAuditState(type){
	
	$.messager.confirm('提示', '确定要继续操作?', function(r) {
		if (r) {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			$.ajax({
				url : 'record_updateAuditState.action',
				data : {'typeId':type,'record.objTypeId':GROW.objTypeId,'record.objId':GROW.objId},
				type : 'post',
				dataType : 'text',
				success : function(result) {
					parent.$.messager.progress('close');
					$.messager.show({
								title : '提示',
								msg : result
							});
					$('#showRecord').window('close');
					 $('#zizhiAuditRecordDataGrid').datagrid('reload');
				}
			});
		}
	});
	
}

	
		
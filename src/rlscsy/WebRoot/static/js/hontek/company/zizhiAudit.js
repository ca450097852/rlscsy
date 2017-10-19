var formUrl = '';
$(function() { 	 	
	//表格数据
	$('#zizhiAuditRecordDataGrid').datagrid({  
		  title:'资质证书列表',
		  iconCls : 'icon-table',
	      pageSize : 15,//默认选择的分页是每页5行数据  
	      pageList : [ 10, 15,20,30, 50 ],//可以选择的分页集合  
	      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	      striped : true,//设置为true将交替显示行背景。  
	      //collapsible : true,//显示可折叠按钮  
	      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	      url:'zizhiAudit_findZizhiAuditRecordPagerList.action',//url调用Action方法  
	      //toolbar:"#tb",
	      loadMsg : '数据装载中......',  			      
	      fit:true,			      
	      //singleSelect:true,//为true时只能选择单行  
	      fitColumns:true,//允许表格自动缩放，以适应父容器  
	      remoteSort : false,  
	      frozenColumns : [ [ {  
	           field : 'ck',  
	           checkbox : true  
	      } ] ],
	      columns:[[
				{field:'entName',title:'企业名称',width:200,align:'center'},					
				{field:'zname',title:'证书名称',width:150,align:'center'},			
				{field:'applyCause',title:'申请原因',width:150,align:'center'},
				{field:'applyTime',title:'申请时间',width:100,align:'center'},
				{field:'auditState',title:'审核状态',width:100,align:'center',
					formatter: function(value,row,index){ 
						var displayValue = '';
						if(value==1){
							displayValue = '审核通过'
						}else if(value==2){
							displayValue = '暂停'
						}else if(value==3){
							displayValue = '不通过'
						}else{
							displayValue = '待审核'
						}
						return displayValue;
					}
				},
				{field:'action',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
					var t1='<a href=\'javascript:void(0);\' onclick=\'updateZizhi('+index+');\'>审核</a>'										
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
			
	function batAudit(){

		// getSelections
		var arr = $('#zizhiAuditRecordDataGrid').datagrid("getSelections");
		if (arr.length == 0) {
			$.messager.alert('提示', '请选择要操作的资质信息!', 'question');
			return;
		}
		var obj = new Array();
		for (i = 0; i < arr.length; i++) {
			obj.push(arr[i].auditId);
		}
		var ids = obj.join(',');
		$.messager.confirm('提示', '确定要继续操作?', function(r) {
					if (r) {
						$.ajax({
									url : 'zizhiAudit_updateBatZizhiAuditRecord.action',
									data : 'ids=' + ids,
									type : 'post',
									dataType : 'text',
									success : function(result) {
										parent.$.messager.progress('close');
										$.messager.show({
													title : '提示',
													msg : result
												});
										$('#zizhiAuditRecordDataGrid').datagrid("reload");
									}
								});
					}
				});

	}
	
	/**
	 * 修改
	 * @return
	 */
	function updateZizhi(index){		
			var zizhiAuditRecord = $('#zizhiAuditRecordDataGrid').datagrid("getRows")[index];		
					
			$('#zizhiAuditRecordForm').form("reset");				
								
			$("input[name='zizhiAuditRecord.auditId']").val(zizhiAuditRecord.auditId);
			$("input[name='zizhiAuditRecord.entId']").val(zizhiAuditRecord.entId);
			$("input[name='zizhiAuditRecord.zid']").val(zizhiAuditRecord.zid);
			$("input[name='zizhiAuditRecord.entName']").val(zizhiAuditRecord.entName);
			//$("#auditState").combobox('setValue',zizhiAuditRecord.auditState);
			$("input[name='zizhiAuditRecord.zname']").val(zizhiAuditRecord.zname);
			$("input[name='zizhiAuditRecord.applyTime']").val(zizhiAuditRecord.applyTime);
			$("input[name='zizhiAuditRecord.applyCause']").val(zizhiAuditRecord.applyCause);
			$("input[name='zizhiAuditRecord.opinion']").val(zizhiAuditRecord.opinion);
			
			$('#zizhiAuditRecordForm').form("validate");
			$("#zizhiAuditRecordDlg").window("open").window('setTitle', '审核资质证书');

		
	}
	
	//提交
    function submitForm(){
    	if($('#zizhiAuditRecordForm').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	  	
		//提交
		$('#zizhiAuditRecordForm').form('submit', {
			url : "zizhiAudit_updateZizhiAuditRecord.action",
			onSubmit : function(param) {
			    return $(this).form('validate');//对数据进行格式化
			},
			success : function(result) {    	
				$.messager.show({ title : '提示', msg : result });   	
				//重新加载数据
	       		$('#zizhiAuditRecordDataGrid').datagrid("reload");  		       		
	       		//关闭进度条
	       		parent.$.messager.progress('close');	
		       	$('#zizhiAuditRecordDlg').window('close');
			}       		
		});     		        		       	 
    }


		
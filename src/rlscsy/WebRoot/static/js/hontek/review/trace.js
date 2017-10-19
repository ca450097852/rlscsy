var formUrl = '';
$(function() { 	 	
	
	$("#proId").combogrid({    
			    panelWidth:500,    
			    idField:'proId',    
			    textField:'proName',    
			    url:'pro_findProductList.action',    
			    columns:[[    
			        {field:'proName',title:'产品名称',width:150},    
			        {field:'manufacturer',title:'生产商',width:180},    
			        {field:'entName',title:'接入用户',width:180}
			    ]],
			    pagination : true,//分页  
          		rownumbers : true,//行数
          		onClickRow : function(rowIndex, rowData){
          			//alert(rowIndex+"==="+rowData.sysCode);
          			$("input[name='trace.sysCode']").val(rowData.sysCode);
          		},
          		onLoadSuccess : function(data) {
					f_timeout(data);
				 }
			});  
	
	//表格数据
	$('#traceDataGrid').datagrid({  
		  title:'溯源信息列表',
	      pageSize : 15,//默认选择的分页是每页5行数据  
	      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
	      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	      striped : true,//设置为true将交替显示行背景。  
	      //collapsible : true,//显示可折叠按钮  
	      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	      url:'trace_findTraceList.action',//url调用Action方法  
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
				{field:'title',title:'标题',width:100,align:'center'},			
				{field:'sysName',title:'所属系统',width:100,align:'center'},
				{field:'remark',title:'说明',width:100,align:'center'},
				{field:'crttime',title:'录入时间',width:50,align:'center'},
				{field:'url',title:'查看',width:50,align:'center',formatter: function(value,row,index){ 
					return "<a href='"+value+"' target='_blank' >后台</a>&nbsp;&nbsp;&nbsp;<a href='"+row.purl+"' target='_blank' >前端</a>";
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
			
	/**
	 * 添加
	 * @return
	 */
	function appendTrace(){

		$('#traceForm').form("reset");
		$('#in_value').html("");
		formUrl = 'trace_addTrace.action';
		$("#traceDlg").window("open").window('setTitle', '添加溯源信息');
	}	
	
	
	/**
	 * 修改
	 * @return
	 */
	function updateTrace(){		
		var arr = $('#traceDataGrid').datagrid("getSelections");
		var lg = arr.length;
		if(lg==0){
			$.messager.alert('提示','请选择需要修改的信息!','question');
		}else if(lg!=1){
			$.messager.alert('提示','对不起，一次只能修改一条信息，请重新选择!','question');
		}else{
			var trace = arr[0];
			
			formUrl = 'trace_updateTrace.action';
			
			$('#traceForm').form("reset");				
			$("#in_value").html($("#h_value").html());
			
			$("#proId").combogrid('setValue',trace.proId);
			$("input[name='trace.title']").val(trace.title);
			$("input[name='trace.purl']").val(trace.purl);
			$("input[name='trace.url']").val(trace.url);
			$("#ct").val(trace.content);
			$("#re").val(trace.remark);
			
			$("input[name='trace.sysCode']").val(trace.sysCode);
			$("input[name='trace.tid']").val(trace.tid);
			
			
			$('#traceForm').form("validate");
			$("#traceDlg").window("open").window('setTitle', '修改溯源系统账号');

		}	
	}
	
	//提交
    function submitForm(){
    	if($('#traceForm').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	
		//提交
		$('#traceForm').form('submit', {
			url : formUrl,
			onSubmit : function(param) {
			    return $(this).form('validate');//对数据进行格式化
			},
			success : function(result) {    	
				$.messager.show({ title : '提示', msg : result });   	
				//重新加载数据
	       		$('#traceDataGrid').datagrid("reload");  		       		
	       		//关闭进度条
	       		parent.$.messager.progress('close');	
		       	$('#traceDlg').window('close');
			}       		
		});     		        		       	 
    }
	
	
	//删除
	function removeTrace(){					
		var arr = $('#traceDataGrid').datagrid("getSelections");
		if(arr.length==0){
			$.messager.alert('提示','请选择要删除的信息!','question');
			return;
		}
		var obj = new Array();
		for(i=0;i<arr.length;i++){
			obj.push(arr[i].tid);
		}
		var ids = obj.join(',');
		$.messager.confirm('提示', '确定删除选择信息?', function(r){
			if (r){
				$.post('trace_deleteTrace.action', 'ids='+ids, function(result) {
					$.messager.show({ title : '提示', msg : result });
					$('#traceDataGrid').datagrid("reload"); 
				}, "TEXT");				 
			}
		});
	}
	
		
        
        


		

		
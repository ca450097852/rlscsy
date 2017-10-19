var formUrl = '';
$(function() { 	 	
	//表格数据
	$('#levelDataGrid').datagrid({  
		  title:'级别信息列表',
		  iconCls : 'icon-flag_red',
	      pageSize : 15,//默认选择的分页是每页5行数据  
	      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
	      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	      striped : true,//设置为true将交替显示行背景。  
	      //collapsible : true,//显示可折叠按钮  
	      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	      url:'level_findLevel.action',//url调用Action方法  
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
				{field:'levelTitle',title:'级别名称',width:200,align:'center'},			
				{field:'seq',title:'排序',width:150,align:'center'},
				{field:'typeClass',title:'类型',width:150,align:'center',formatter: function(value,row,index){ 					
					if(value==1){
						return "级别";
					}else if(value==2){
						return "认证";
					}else{
						return "--";
					}					
				}},
				{field:'remarks',title:'备注',width:250,align:'center'}
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
	
	 
	$("#uploadify").uploadify({
		'swf'      		: 'uploadify/uploadify.swf',
		'fileObjName' 	: 'uploadify',
		'uploader' 		: 'tongbao_uploadFile.action;jsessionid='+$('#jsessionid').val(),
		'buttonText'    : '上传附件',
		'height'		: 20,
		'width'			: 100,
		'multi'			: false,
		'fileSizeLimit'	: 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID'       : 'fileQueue',
		'multi'			: false,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {		
			$("#appdUrl").val(data);						
			$("#appdName").val(file.name);							
		}
	});
	
});
	/**
	 * 删除附件
	 * @return
	 */
	function removeAppd(){
		$.post('tongbao_deleteFile.action', 'fileName='+$("#appdUrl").val(), function(result) {
			//$.messager.show({ title : '提示', msg : result });
			$("#fileQueue").html('');
			$("#appdUrl").val('');						
			$("#appdName").val('');	
			
		}, "TEXT");				 			
	}
			
	/**
	 * 添加
	 * @return
	 */
	function appendTongbao(){
		$('#levelForm').form("reset");
		formUrl = 'level_addLevel.action';
		
		
		$("#levelDlb").window("open").window('setTitle', '添加级别信息');
	}	
	
	/**
	 * 修改
	 * @return
	 */
	function updateTongbao(){		
		var arr = $('#levelDataGrid').datagrid("getSelections");
		var lg = arr.length;
		if(lg==0){
			$.messager.alert('提示','请选择需要修改的信息!','question');
		}else if(lg!=1){
			$.messager.alert('提示','对不起，一次只能修改一条信息，请重新选择!','question');
		}else{
			var level = arr[0];
			
			formUrl = 'level_updateLevel.action';
			
			$('#levelForm').form("reset");				
			
			$("input[name='level.levelId']").val(level.levelId);
			$("input[name='level.levelTitle']").val(level.levelTitle);
			$("#seq").numberbox('setValue',level.seq);
			$("#remarks").val(level.remarks);
			$("#typeClass").val(level.typeClass);
			

			$('#levelForm').form("validate");
			$("#levelDlb").window("open").window('setTitle', '修改级别信息');

		}	
	}
	
	//提交
    function submitForm(){
    	if($('#levelForm').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	  	
		//提交
		$('#levelForm').form('submit', {
			url : formUrl,
			onSubmit : function(param) {
			    return $(this).form('validate');//对数据进行格式化
			},
			success : function(result) {    	
				$.messager.show({ title : '提示', msg : result });   	
				//重新加载数据
	       		$('#levelDataGrid').datagrid("reload");  		       		
	       		//关闭进度条
	       		parent.$.messager.progress('close');	
		       	$('#levelDlb').window('close');
			}       		
		});     		        		       	 
    }
	
	
	//删除
	function removeTongbao(){
		var arr = $('#levelDataGrid').datagrid("getSelections");
		if(arr.length==0){
			$.messager.alert('提示','请选择要删除的信息!','question');
			return;
		}
		var obj = new Array();
		for(i=0;i<arr.length;i++){
			obj.push(arr[i].levelId);
		}
		var ids = obj.join(',');
		$.messager.confirm('提示', '确定删除选择信息?', function(r){
			if (r){
				$.post('level_deleteLevels.action', 'ids='+ids, function(result) {
					$.messager.show({ title : '提示', msg : result });
					$('#levelDataGrid').datagrid("reload"); 
				}, "TEXT");				 
			}
		});
	}

		
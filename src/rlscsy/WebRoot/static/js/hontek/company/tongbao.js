var formUrl = '';
$(function() { 	 	
	//表格数据
	$('#tongbaoDataGrid').datagrid({  
		  title:'通报处罚企业列表',
		  iconCls : 'icon-flag_red',
	      pageSize : 15,//默认选择的分页是每页5行数据  
	      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
	      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	      striped : true,//设置为true将交替显示行背景。  
	      //collapsible : true,//显示可折叠按钮  
	      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	      url:'tongbao_findTongbaoPagerList.action',//url调用Action方法  
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
				{field:'entName',title:'企业名称',width:150,align:'center'},			
				{field:'title',title:'标题',width:200,align:'center'},
				{field:'crttime',title:'通报时间',width:100,align:'center'},
				{field:'sts',title:'状态',width:100,align:'center',formatter: function(value,row,index){ 					
					if(value==0){
						return "待审";
					}else{
						return "发布";
					}					
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
		$('#tongbaoForm').form("reset");
		$('#in_value').html("");
		formUrl = 'tongbao_addTongbao.action';
		
		$("#appdUrl").val('');						
		$("#appdName").val('');	
		
		$("#tongbaoDlg").window("open").window('setTitle', '添加通报企业信息');
	}	
	
	/**
	 * 发布
	 * @return
	 */
	function publishTongbao(){
		var arr = $('#tongbaoDataGrid').datagrid("getSelections");
		var lg = arr.length;
		if(lg==0){
			$.messager.alert('提示','请选择需要发布的信息!','question');
		}else if(lg!=1){
			$.messager.alert('提示','对不起，一次只能发布一条信息，请重新选择!','question');
		}else{
			var tongbao = arr[0];
			if(tongbao.sts!=1){
				$.post('tongbao_publishTongbao.action', 'ids='+tongbao.tid, function(result) {
					$.messager.show({ title : '提示', msg : result });
					$('#tongbaoDataGrid').datagrid("reload"); 
				}, "TEXT");	
			}else{
				$.messager.alert('提示','信息已发布!','question');
			}	
		}
	}
	/**
	 * 修改
	 * @return
	 */
	function updateTongbao(){		
		var arr = $('#tongbaoDataGrid').datagrid("getSelections");
		var lg = arr.length;
		if(lg==0){
			$.messager.alert('提示','请选择需要修改的信息!','question');
		}else if(lg!=1){
			$.messager.alert('提示','对不起，一次只能修改一条信息，请重新选择!','question');
		}else{
			var tongbao = arr[0];
			
			formUrl = 'tongbao_updateTongbao.action';
			
			$('#tongbaoForm').form("reset");				
			$("#in_value").html($("#h_value").html());
			
			$("input[name='tongbao.tid']").val(tongbao.tid);
			$("input[name='tongbao.entName']").val(tongbao.entName);
			$("input[name='tongbao.title']").val(tongbao.title);
			$("input[name='tongbao.crttime']").val(tongbao.crttime);
			$("input[name='tongbao.userName']").val(tongbao.userName);
			$("textarea[name='tongbao.content']").val(tongbao.content);
			$("textarea[name='tongbao.remark']").val(tongbao.remark);
			$("#sts").combobox('setValue',tongbao.sts);
			
			$("#appdUrl").val(tongbao.appdUrl);						
			$("#appdName").val(tongbao.appdName);			
			
			if(tongbao.appdUrl){
				var itemTemplate = '<div id="${fileID}" class="uploadify-queue-item">\<div class="cancel">';				
				var t1='<a href=\'javascript:void(0);\' onclick=\'preview("'+tongbao.appdUrl+'",2);\' style=\'background: url("uploadify/da.jpg") 0 0 no-repeat;float:right\' title=\'预览\'></a>';			
				var fn = tongbao.appdName;
				if(tongbao.appdName==''){
					fn = tongbao.appdUrl;
				}
				var t2 = '<a href="javascript:void(0)" onclick="removeAppd()" title=\'删除\'>X</a>\</div>\<span class="fileName">'+fn+'</span><span class="data"></span>\</div>';
					
				itemTemplate = itemTemplate+t1+t2;
					
				$("#fileQueue").html(itemTemplate);
			}

			$('#tongbaoForm').form("validate");
			$("#tongbaoDlg").window("open").window('setTitle', '修改通报企业信息');

		}	
	}
	
	//提交
    function submitForm(){
    	if($('#tongbaoForm').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	  	
		//提交
		$('#tongbaoForm').form('submit', {
			url : formUrl,
			onSubmit : function(param) {
			    return $(this).form('validate');//对数据进行格式化
			},
			success : function(result) {    	
				$.messager.show({ title : '提示', msg : result });   	
				//重新加载数据
	       		$('#tongbaoDataGrid').datagrid("reload");  		       		
	       		//关闭进度条
	       		parent.$.messager.progress('close');	
		       	$('#tongbaoDlg').window('close');
			}       		
		});     		        		       	 
    }
	
	
	//删除
	function removeTongbao(){					
		var arr = $('#tongbaoDataGrid').datagrid("getSelections");
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
				$.post('tongbao_deleteTongbao.action', 'ids='+ids, function(result) {
					$.messager.show({ title : '提示', msg : result });
					$('#tongbaoDataGrid').datagrid("reload"); 
				}, "TEXT");				 
			}
		});
	}
	
		
        
        
	/**
	 * 预览窗口
	 * @param path
	 * @return
	 */
	function preview(path){
		window.open ($("#h_path").val()+'/jsp/company/preview.jsp?imgPath=tongbao/'+path,'图片预览','height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no')
	}

		

		
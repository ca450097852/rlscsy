var formUrl = '';
$(function() { 	 	
	//表格数据
	$('#productionDataGrid').datagrid({  
		  title:'生产信息列表',
		  iconCls : 'icon-table',
	      pageSize : 15,//默认选择的分页是每页5行数据  
	      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
	      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	      striped : true,//设置为true将交替显示行背景。  
	      //collapsible : true,//显示可折叠按钮  
	      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	      url:'production_findProductionPagerList.action',//url调用Action方法  
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
				{field:'productinfo',title:'产品情况',width:400,align:'center'},			
				{field:'license',title:'产品许可证',width:150,align:'center'},
				{field:'entName',title:'企业名称',width:150,align:'center'},
				{field:'crttime',title:'录入时间',width:100,align:'center'},
				{field:'action',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
					var t1='<a href=\'javascript:void(0);\' onclick=\'attachWin('+index+');\'>附件管理</a>'										
					return t1;
				}}
	      ]],
          pagination : true,//分页  
          rownumbers : true,//行数  
          
          onLoadSuccess : function(data) {
			f_timeout(data);
		 }
	}); 	
	
	parent.$.messager.progress('close');
});
			
	/**
	 * 添加
	 * @return
	 */
	function appendProduction(){
		$('#productionForm').form("reset");
		$('#in_value').html("");
		formUrl = 'production_addProduction.action';
		$("#productionDlg").window("open").window('setTitle', '添加生产信息');
	}	
	
	
	/**
	 * 修改
	 * @return
	 */
	function updateProduction(){		
		var arr = $('#productionDataGrid').datagrid("getSelections");
		var lg = arr.length;
		if(lg==0){
			$.messager.alert('提示','请选择需要修改的信息!','question');
		}else if(lg!=1){
			$.messager.alert('提示','对不起，一次只能修改一条信息，请重新选择!','question');
		}else{
			var production = arr[0];
			
			formUrl = 'production_updateProduction.action';
			
			$('#productionForm').form("reset");				
			$("#in_value").html($("#h_value").html());

			$("input[name='production.proId']").val(production.proId);
			$("input[name='production.entId']").val(production.entId);
			$("input[name='production.license']").val(production.license);
			$("input[name='production.crttime']").val(production.crttime);
			$("input[name='production.userId']").val(production.userId);
			$("textarea[name='production.productinfo']").val(production.productinfo);
			
			$('#productionForm').form("validate");
			$("#productionDlg").window("open").window('setTitle', '修改生产信息');

		}	
	}
	
	//提交
    function submitForm(){
    	if($('#productionForm').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	  	
		//提交
		$('#productionForm').form('submit', {
			url : formUrl,
			onSubmit : function(param) {
			    return $(this).form('validate');//对数据进行格式化
			},
			success : function(result) {    	
				$.messager.show({ title : '提示', msg : result });   	
				//重新加载数据
	       		$('#productionDataGrid').datagrid("reload");  		       		
	       		//关闭进度条
	       		parent.$.messager.progress('close');	
		       	$('#productionDlg').window('close');
			}       		
		});     		        		       	 
    }
	
	
	//删除
	function removeProduction(){					
		var arr = $('#productionDataGrid').datagrid("getSelections");
		if(arr.length==0){
			$.messager.alert('提示','请选择要删除的信息!','question');
			return;
		}
		var obj = new Array();
		for(i=0;i<arr.length;i++){
			obj.push(arr[i].proId);
		}
		var ids = obj.join(',');
		$.messager.confirm('提示', '确定删除选择信息?', function(r){
			if (r){
				$.post('production_deleteProduction.action', 'ids='+ids, function(result) {
					$.messager.show({ title : '提示', msg : result });
					$('#productionDataGrid').datagrid("reload"); 
				}, "TEXT");				 
			}
		});
	}
	
		
        
        

	/**
	 * 
	 * @param index
	 * @return
	 */
	function attachWin(i){
		var production = $('#productionDataGrid').datagrid("getRows")[i]; 
		var proId = production.proId;
		$("#uploadify").uploadify({
			'swf'      		: 'uploadify/uploadify.swf',
			'fileObjName' 	: 'uploadify',
			'uploader' 		: 'productionAppendix_uploadFile.action;jsessionid='+$('#jsessionid').val(),
			'formData'		: {'proId':proId,'appType':'1'},
			'buttonText'    : '上传附件',
			'height'		: 20,
			'width'			: 100,
			'multi'			: false,
			'fileSizeLimit'	: 1024,
			'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
	        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
			'queueID'       : 'fileQueue',
			'multi'			: true,
			'removeCompleted' : false,
			'onUploadSuccess' : function(file, data, response) {		

			}
		});
		
		$("#uploadify2").uploadify({
			'swf'      		: 'uploadify/uploadify.swf',
			'fileObjName' 	: 'uploadify',
			'uploader' 		: 'productionAppendix_uploadFile.action;jsessionid='+$('#jsessionid').val(),
			'formData'		: {'proId':proId,'appType':'0'},
			'buttonText'    : '上传附件',
			'height'		: 20,
			'width'			: 100,
			'multi'			: false,
			'fileSizeLimit'	: 1024,
			'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
	        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
			'queueID'       : 'fileQueue2',
			'multi'			: true,
			'removeCompleted' : false,
			'onUploadSuccess' : function(file, data, response) {		

			}
		});
		
		$.post('productionAppendix_findProductionAppendixPagerList.action', 'proId='+proId, function(result) {
			if(result&&result.total>0){
				var itemTemplate ='';				
				var itemTemplate2 ='';			
				for(var i = 0; i<result.total;i++){
					var appd = result.rows[i];
					var fn = appd.appName;
					if(appd.appName==''){
						fn = appd.path;
					}
					
					if(appd.appType==0){
						itemTemplate2 += '<div id="d_'+appd.appId+'" class="uploadify-queue-item">\<div class="cancel">';				
						var t1='<a href=\'javascript:void(0);\' onclick=\'preview("'+appd.path+'");\' style=\'background: url("uploadify/da.jpg") 0 0 no-repeat;float:right\' title=\'预览\'></a>';			
						var t2 = '<a href="javascript:void(0)" onclick="removeAppd('+appd.appId+',\''+appd.path+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+fn+'</span><span class="data"></span>\</div>';						
						itemTemplate2 = itemTemplate2+t1+t2;
					}else{
						itemTemplate += '<div id="d_'+appd.appId+'" class="uploadify-queue-item">\<div class="cancel">';				
						var t1='<a href=\'javascript:void(0);\' onclick=\'preview("'+appd.path+'");\' style=\'background: url("uploadify/da.jpg") 0 0 no-repeat;float:right\' title=\'预览\'></a>';			
						var t2 = '<a href="javascript:void(0)" onclick="removeAppd('+appd.appId+',\''+appd.path+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+fn+'</span><span class="data"></span>\</div>';						
						itemTemplate = itemTemplate+t1+t2;						
					}												
				}
				$("#fileQueue").html(itemTemplate);
				$("#fileQueue2").html(itemTemplate2);
			}			
		}, "JSON");		
		$("#attachWin").window('open').window('setTitle','生产信息-附件管理');
	}
		
	/**
	 * 预览窗口
	 * @param path
	 * @return
	 */
	function preview(path){
		window.open ($("#h_path").val()+'/jsp/company/preview.jsp?imgPath=production/'+path,'图片预览','height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no')
	}
	
	function removeAppd (id,path){
		$.post('productionAppendix_deleteFile.action', 'fileName='+path, function(result) {
			$("#d_"+id).remove();
		}, "TEXT");
	}
		
var formUrl = '';
$(function() { 	 	
	//表格数据
	$('#zizhiDataGrid').datagrid({  
		  title:'资质证书列表',
		  iconCls : 'icon-table',
	      pageSize : 15,//默认选择的分页是每页5行数据  
	      pageList : [ 10, 15,20,30, 50 ],//可以选择的分页集合  
	      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	      striped : true,//设置为true将交替显示行背景。  
	      //collapsible : true,//显示可折叠按钮  
	      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	      url:'zizhi_findZizhiPagerList.action',//url调用Action方法  
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
				{field:'zizhiName',title:'证书名称',width:150,align:'center'},			
				{field:'appType',title:'证书类型',width:100,align:'center',
					formatter: function(value,row,index){ 
						var displayValue = '';
						if(value==1){
							displayValue = '资质文件'
						}else if(value==2){
							displayValue = '营业执照'
						}else if(value==3){
							displayValue = '企业荣誉'
						}else{
							displayValue = '其它证书'
						}
						return displayValue;
					}
				},					
				{field:'awardUnit',title:'颁发单位',width:150,align:'center'},
				{field:'awardTime',title:'颁发时间',width:100,align:'center'},
				{field:'remark',title:'备注',width:200,align:'center'},
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
	function appendZizhi(){
		$('#zizhiForm').form("reset");
		$('#in_value').html("");
		formUrl = 'zizhi_addZizhi.action';
		$("#zizhiDlg").window("open").window('setTitle', '添加资质证书');
	}	
	
	
	/**
	 * 修改
	 * @return
	 */
	function updateZizhi(){		
		var arr = $('#zizhiDataGrid').datagrid("getSelections");
		var lg = arr.length;
		if(lg==0){
			$.messager.alert('提示','请选择需要修改的信息!','question');
		}else if(lg!=1){
			$.messager.alert('提示','对不起，一次只能修改一条信息，请重新选择!','question');
		}else{
			var zizhi = arr[0];
			
			formUrl = 'zizhi_updateZizhi.action';
			
			$('#zizhiForm').form("reset");				
			$("#in_value").html($("#h_value").html());
								
			$("input[name='zizhi.id']").val(zizhi.id);
			$("input[name='zizhi.entId']").val(zizhi.entId);
			$("#appType").combobox('setValue',zizhi.appType);
			$("input[name='zizhi.zizhiName']").val(zizhi.zizhiName);
			$("input[name='zizhi.grantUnit']").val(zizhi.grantUnit);
			$("input[name='zizhi.awardUnit']").val(zizhi.awardUnit);
			$("#zizhi_awardTime").datebox('setValue',zizhi.awardTime);
			$("textarea[name='zizhi.remark']").val(zizhi.remark);
			
			$('#zizhiForm').form("validate");
			$("#zizhiDlg").window("open").window('setTitle', '修改资质证书');

		}	
	}
	
	//提交
    function submitForm(){
    	if($('#zizhiForm').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	  	
		//提交
		$('#zizhiForm').form('submit', {
			url : formUrl,
			onSubmit : function(param) {
			    return $(this).form('validate');//对数据进行格式化
			},
			success : function(result) {    	
				$.messager.show({ title : '提示', msg : result });   	
				//重新加载数据
	       		$('#zizhiDataGrid').datagrid("reload");  		       		
	       		//关闭进度条
	       		parent.$.messager.progress('close');	
		       	$('#zizhiDlg').window('close');
			}       		
		});     		        		       	 
    }
	
	
	//删除
	function removeZizhi(){					
		var arr = $('#zizhiDataGrid').datagrid("getSelections");
		if(arr.length==0){
			$.messager.alert('提示','请选择要删除的信息!','question');
			return;
		}
		var obj = new Array();
		for(i=0;i<arr.length;i++){
			obj.push(arr[i].id);
		}
		var ids = obj.join(',');
		$.messager.confirm('提示', '确定删除选择信息?', function(r){
			if (r){
				$.post('zizhi_deleteZizhi.action', 'ids='+ids, function(result) {
					$.messager.show({ title : '提示', msg : result });
					$('#zizhiDataGrid').datagrid("reload"); 
				}, "TEXT");				 
			}
		});
	}
	
	/**
	 * 
	 * @param zid
	 * @return
	 */
	function attachWin(i){
		var zizhi = $('#zizhiDataGrid').datagrid("getRows")[i]; 
		//console.info(zizhi.id);
		var zid = zizhi.id;
		var zname = zizhi.zizhiName;
		$("#uploadify").uploadify({
			'swf'      		: 'uploadify/uploadify.swf',
			'fileObjName' 	: 'uploadify',
			'uploader' 		: 'zizhiAppendix_uploadFile.action;jsessionid='+$('#jsessionid').val(),
			'formData'		: {'zid':zid},
			'buttonText'    : '上传附件',
			'height'		: 20,
			'width'			: 100,
			'fileSizeLimit'	: 1024,
			'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
	        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
			'queueID'       : 'fileQueue',
			'multi'			: true,
			'removeCompleted' : false,
			'onUploadSuccess' : function(file, data, response) {		

			}
		});
		
		$.post('zizhiAppendix_findZizhiAppendixPagerList.action', 'zid='+zid, function(result) {
			if(result&&result.total>0){
				var itemTemplate ='';
				for(var i = 0; i<result.total;i++){
					var appd = result.rows[i];
					//console.info(appd);
					itemTemplate += '<div id="d_'+appd.appId+'" class="uploadify-queue-item">\<div class="cancel">';				
					var t1='<a href=\'javascript:void(0);\' onclick=\'preview("'+appd.path+'");\' style=\'background: url("uploadify/da.jpg") 0 0 no-repeat;float:right\' title=\'预览\'></a>';			
					var fn = appd.appName;
					if(appd.appName==''){
						fn = appd.path;
					}
					var t2 = '<a href="javascript:void(0)" onclick="removeAppd('+appd.appId+',\''+appd.path+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+fn+'</span><span class="data"></span>\</div>';						
					itemTemplate = itemTemplate+t1+t2;							
				}
				$("#fileQueue").html(itemTemplate);
			}			
		}, "JSON");		
		$("#attachWin").window('open').window('setTitle',zname+'-附件管理');
	}
		
	/**
	 * 预览窗口
	 * @param path
	 * @return
	 */
	function preview(path){
		window.open ($("#h_path").val()+'/jsp/company/preview.jsp?imgPath=zizhi/'+path,'图片预览','height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no')
	}
	
	function removeAppd (id,path){
		$.post('zizhiAppendix_deleteFile.action', 'fileName='+path, function(result) {
			$("#d_"+id).remove();
		}, "TEXT");
	}

		
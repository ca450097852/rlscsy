var formUrl = '';
var oldValue = '';

$(function() { 	 	
	//表格数据
	$('#tabdataGrid').datagrid({  
		  title:'风格列表',
	      iconCls : 'icon-computer',  
	      pageSize : 15,//默认选择的分页是每页5行数据  
	      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
	      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	      striped : true,//设置为true将交替显示行背景。  
	      //collapsible : true,//显示可折叠按钮  
	      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	      url:'styleconfig_findPage.action',//url调用Action方法  
	      loadMsg : '数据装载中......',  			      
	      fit:true,			      
	      //singleSelect:true,//为true时只能选择单行  
	      fitColumns:true,//允许表格自动缩放，以适应父容器  
	      remoteSort : false,  
	      sortName:"scType",
	      sortOrder:"asc",
	      frozenColumns : [ [ {  
	           field : 'ck',  
	           checkbox : true  
	      } ] ],
	      columns:[[
				{field:'scImg',title:'风格预览',width:50,align:'center',
					formatter: function(value,row,index){
						var scName = row.scName==null?"":"风格名称为：《"+row.scName+"》";
						if(value!=null&&value!=""){
							var fpath = "/nytsyFiles/styleconfig/";
							return "<a rel=\"previewImg\" href=\""+fpath+value+"\" title=\""+scName+"\"><img alt=\"预览\" src=\""+fpath+value+"\" style=\"width:100px; height:50px\"></a>";
						}else{
							return "无";
						}
					}
				},
				{field:'scName',title:'风格名称',width:100,align:'left'},	
				{field:'scCss',title:'样式文件',width:150,align:'left'},			
				{field:'scType',title:'分类',width:50,align:'center',sortable:true,
					formatter: function(value,row,index){ 					
						if(value==1){
							return "门户";
						}else if(value==2){
							return "企业会员";
						}else if(value==3)
							return "监管";
					}},
				{field:'state',title:'状态',width:50,align:'center',
					formatter: function(value,row,index){ 					
						if(value==1){
							return "使用";
						}else{
							return "<font color=red>停用</font>";
						}
					}
				},			
				{field:'createTime',title:'创建时间',width:100,align:'center'},
				{field:'remark',title:'备注',width:150,align:'left'}
	      ]],
          pagination : true,//分页  
          rownumbers : true,//行数  
          
          onLoadSuccess : function(data) {
			f_timeout(data);
			
			$("a[rel=previewImg]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
			
		 }
	}); 	
	
	parent.$.messager.progress('close');
	
	
	
	$("#scImgFile").uploadify({
		'swf' : 'uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'styleconfig_uploadFile.action;jsessionid='+ $('#sessionId').val(),
		'buttonText' : '上传图片',
		'height' : 20,
		'width' : 100,
		'multi' : false,
		'fileSizeLimit' : 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID' : 'fileQueue',
		'multi' : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			if(data){
				var fpath = "/nytsyFiles/styleconfig/";
				$('input[name="styleConfig.scImg"]').val(data);
				var img_html = "<a rel=\"previewscImg\" href=\""+fpath+data+"\"><img alt=\"预览\" src=\""+fpath+data+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
				$("#fileQueue").html(img_html);
				$("a[rel=previewscImg]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
			}
		}
	});
	
	
	$("#scCssFile").uploadify({
		'swf' : 'uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'styleconfig_uploadFile.action;jsessionid='+ $('#sessionId').val(),
		'buttonText' : '上传附件',
		'height' : 20,
		'width' : 100,
		'multi' : false,
		'fileSizeLimit' : 1024,
		'fileTypeDesc' : '支持格式:css',
		'fileTypeExts' : '*.css',
		'queueID' : 'fileQueue',
		'multi' : true,
		'removeCompleted' : false,
		'onUploadSuccess' : function(file, data, response) {
			$('input[name="styleConfig.scCss"]').val(data);
		}
	});
	
});
			
	/**
	 * 添加
	 * @return
	 */
	function appendData(){
		$('#interAccountForm').form("reset");
		$('#in_value').html("");
		$("#fileQueue").html('');
		formUrl = 'styleconfig_addStyleConfig.action';
		$("#interAccountDlg").window("open").window('setTitle', '添加风格');
	}	
	
	
	/**
	 * 修改
	 * @return
	 */
	function updateInterAccount(){		
		var arr = $('#tabdataGrid').datagrid("getSelections");
		var lg = arr.length;
		if(lg==0){
			$.messager.alert('提示','请选择需要修改的信息!','question');
		}else if(lg!=1){
			$.messager.alert('提示','对不起，一次只能修改一条信息，请重新选择!','question');
		}else{
			var styleConfig = arr[0];
			
			formUrl = 'styleconfig_updateStyleConfig.action';
			
			$('#interAccountForm').form("reset");				
			$('#in_value').html('');
			$("#fileQueue").html('');
			for(var item in styleConfig){
				
				if($('[name="styleConfig.'+item+'"]').length==0){
					$('#in_value').append('<input type="hidden" name="styleConfig.'+item+'" value="'+styleConfig[item]+'"/>');
				}else{
					if($($('[name="styleConfig.'+item+'"]')[0]).hasClass('combo-value')){
						var id = $($('[name="styleConfig.'+item+'"]')[0]).parent().prev().attr('id');
						$('#'+id).combobox('setValue',styleConfig[item]);
					}else{
						$($('[name="styleConfig.'+item+'"]')[0]).val(styleConfig[item]);
					}
				}
			}
			
			
			if(styleConfig.scImg){
				var fpath = "/nytsyFiles/styleconfig/";
				$('input[name="styleConfig.scImg"]').val(styleConfig.scImg);
				var img_html = "<a rel=\"previewscImg\" href=\""+fpath+styleConfig.scImg+"\"><img alt=\"预览\" src=\""+fpath+styleConfig.scImg+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
				$("#fileQueue").html(img_html);
				$("a[rel=previewscImg]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
			}
			
			
			$('#interAccountForm').form('validate');
			
			$("#interAccountDlg").window("open").window('setTitle', '修改风格');

		}	
	}
	
	//提交
    function submitForm(){
    	if($('#interAccountForm').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	  	
		//提交
		$('#interAccountForm').form('submit', {
			url : formUrl,
			onSubmit : function(param) {
			    return $(this).form('validate');//对数据进行格式化
			},
			success : function(result) {    	
				$.messager.show({ title : '提示', msg : result });   	
				//重新加载数据
	       		$('#tabdataGrid').datagrid("reload");  		       		
	       		//关闭进度条
	       		parent.$.messager.progress('close');	
		       	$('#interAccountDlg').window('close');
			}       		
		});     		        		       	 
    }
	
	
	//删除
	function removeInterAccount(){					
		var arr = $('#tabdataGrid').datagrid("getSelections");
		if(arr.length==0){
			$.messager.alert('提示','请选择要删除的信息!','question');
			return;
		}
		var obj = new Array();
		for(i=0;i<arr.length;i++){
			obj.push(arr[i].scId);
		}
		var ids = obj.join(',');
		$.messager.confirm('提示', '确定删除选择信息?', function(r){
			if (r){
				$.post('styleconfig_deleteSc.action', 'ids='+ids, function(result) {
					$.messager.show({ title : '提示', msg : result });
					$('#tabdataGrid').datagrid("reload"); 
				}, "TEXT");				 
			}
		});
	}
	
	function find(){
		var scName = $('#s_scName').val();
		var scType = $('#s_scType').combobox('getValue');
		var state = $('#s_state').combobox('getValue');
		var params = {};
		params['styleConfig.scName'] = scName;
		params['styleConfig.scType'] = scType;
		params['styleConfig.state'] = state;
		$('#tabdataGrid').datagrid("load",params); 
	}
	
	function clearSearch(){
		$('#s_scName').val('');
		$('#s_scType').combobox('setValue','');
		$('#s_state').combobox('setValue','');
		
		$('#tabdataGrid').datagrid("load",{}); 
	}
		     
		
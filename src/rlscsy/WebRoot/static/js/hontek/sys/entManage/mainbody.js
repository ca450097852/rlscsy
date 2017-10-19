var formUrl = '';
var oldValue = '';

$(function() { 	 	
	//表格数据
	$('#tabdataGrid').datagrid({  
		  title:'主体列表',
	      iconCls : 'icon-computer',  
	      pageSize : 15,//默认选择的分页是每页5行数据  
	      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
	      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
	      striped : true,//设置为true将交替显示行背景。  
	      //collapsible : true,//显示可折叠按钮  
	      toolbar:"#tb",//在添加 增添、删除、修改操作的按钮要用到这个  
	      url:'entExpand_findPager.action',//url调用Action方法  
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
	            {field : 'parentName',title : '上级组织',width : 150,align : 'left'}, 
				{field:'name',title:'主体名称',width:100,align:'left'},			
				{field:'ischarge',title:'是否为收费用户',width:50,align:'center',
					formatter: function(value,row,index){ 					
						if(value==1){
							return "是";
						}else if(value==2){
							return "否";
						}
					}},
				{field:'mbType',title:'主体类型',width:50,align:'center',
					formatter: function(value,row,index){ 					
						if(value==1){
							return "农业局";
						}else if(value==2){
							return "协会";
						}else if(value==3)
							return "企业";
					}
				},			
				{field : 'areaName',title : '区域',width : 100,align : 'center'}, 
				{field : 'typeName',title : '分类',width : 100,align : 'center'}, 
				{field:'expired',title:'费用到期时间',width:100,align:'center'},
				{field:'ontrialStart',title:'试用开始时间',width:100,align:'center'},
				{field:'ontrialEnd',title:'试用结束时间',width:100,align:'center'},
				{field:'createTime',title:'创建时间',width:100,align:'center'}
	      ]],
          pagination : true,//分页  
          rownumbers : true,//行数  
          
          onLoadSuccess : function(data) {
			f_timeout(data);
		 }
	}); 	
	
	parent.$.messager.progress('close');
	
	$('input[name="enterprise.ischarge"]').click(function(){
		if($(this).val()==1){
			$('#ontrial').hide();
			$('#_expired').datebox('enable');
			$('#_expired').datebox('enableValidation');
			
			$('#_ontrialStart').datebox('disableValidation');
			$('#_ontrialEnd').datebox('disableValidation');
		}else{
			$('#ontrial').show();
			$('#_expired').datebox('disable');
			$('#_expired').datebox('disableValidation');
			
			$('#_ontrialStart').datebox('enableValidation');
			$('#_ontrialEnd').datebox('enableValidation');
		}
	});
	
	
	 //二维码设置--（头部logo图片上传）
	 $("#toplogoFile").uploadify({
			'swf' : 'uploadify/uploadify.swf',
			'fileObjName' : 'uploadify',
			'uploader' : 'tagStyleUp_uploadFile.action;jsessionid='+$('#jsessionid').val(),
			'buttonText' : '上传logo',
			'height' : 20,
			'width' : 100,
			'multi' : false,
			'fileSizeLimit' : 1024,
			'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
	        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
			'queueID' : 'toplogofileQueue',
			'multi' : true,
			'removeCompleted' : false,
			'onUploadSuccess' : function(file, data, response) {
				if(data){
					var fpath = "/nytsyFiles/entstyle/";
					$('input[name="tagStyle.toplogo"]').val(data);
					var img_html = "<a rel=\"previewtoplogo\" href=\""+fpath+data+"\"><img alt=\"预览\" src=\""+fpath+data+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
					
					if(img_html){
						$("#toplogofileQueue").html(img_html);						
						$("a[rel=previewtoplogo]").fancybox({
							'transitionIn'		: 'none',
							'transitionOut'		: 'none',
							'titlePosition' 	: 'over',
							'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
								return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
							}
						});
						
					}					
				}
			}
		});
	 

	 //二维码设置--（二维码logo图片上传）
	 $("#codelogoFile").uploadify({
			'swf' : 'uploadify/uploadify.swf',
			'fileObjName' : 'uploadify',
			'uploader' : 'tagStyleUp_uploadFile.action;jsessionid='+$('#jsessionid').val(),
			'buttonText' : '上传logo',
			'height' : 20,
			'width' : 100,
			'multi' : false,
			'fileSizeLimit' : 1024,
			'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
	        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
			'queueID' : 'codelogofileQueue',
			'multi' : true,
			'removeCompleted' : false,
			'onUploadSuccess' : function(file, data, response) {
				if(data){
					var fpath = "/nytsyFiles/entstyle/";
					$('input[name="tagStyle.codelogo"]').val(data);
					var img_html = "<a rel=\"previewcodelogo\" href=\""+fpath+data+"\"><img alt=\"预览\" src=\""+fpath+data+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
					
					if(img_html){
						$("#codelogofileQueue").html(img_html);						
						$("a[rel=previewcodelogo]").fancybox({
							'transitionIn'		: 'none',
							'transitionOut'		: 'none',
							'titlePosition' 	: 'over',
							'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
								return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
							}
						});
						
					}					
				}
			}
		});
	
});
			
	/**
	 * 添加
	 * @return
	 */
	function appendData(){
		$('#add_form').form("reset");
		$('#in_value').html('');
		formUrl = 'ent_addMainBody.action';
		$('#_account').prop('oldValue','');
		
		$('#userTr').show();
		
		$('input[name="enterprise.ischarge"][value="1"]').click();
		
		
		$('#ontrial').hide();
		$('#_expired').datebox('enable');
		$('#_expired').datebox('enableValidation');
		
		$('#_ontrialStart').datebox('disableValidation');
		$('#_ontrialEnd').datebox('disableValidation');
		
		
		$("#areaId").combotree({
			url:'ent_getEntAreaTree.action?tt='+Math.floor(Math.random()*20)+1,
			method:'get',
			required:true,
			onLoadSuccess:function(node, data){
				if(data){
					if(data[0].checked){
						$("#areaId").combotree("setValue",data[0].id);
					}
				}
				f_timeout(data);			
			}
		});
		
		$("#parentId").combotree({
			url:'ent_getEntTree.action?tt='+Math.floor(Math.random()*20)+1,
			method:'get',
			required:true,
			onLoadSuccess:function(node, data){
			if(data){
				if(data[0].checked){
					$("#parentId").combotree("setValue",data[0].id);
				}
			}
			f_timeout(data);			
			},
			onSelect: function(node){
				var entId = node.id;  // 在用户点击的时候提示			
				$.ajax({
					  url: "ent_getEntLevel.action?entId="+entId,
					  cache: false,
					  success: function(level){
						if(level==2){
							$("#provinceRsts").val("0");
						}else if(level==3){
							$("#cityRsts").val("0");
						}else if(level==4){
							$("#areaRsts").val("0");
						}else if(level==5){
							$("#townRsts").val("0");
						}
					  }
				});	
				//指定审核机构
				$.ajax({
					  url: "ent_getAuditEntTree.action?entId="+entId,
					  cache: false,
					  success: function(auditEntHTML){
						$("#auditEnt").html(auditEntHTML);
					  }
				});			
			}

		});		
		
	    $('#entType_id').combobox({    
	        url:'enttype_getEntTypeToSelect.action?tt='+Math.floor(Math.random()*20)+1,    
	        valueField:'id',    
	        textField:'text',
	        required:true,
	        editable:false
	    });
		
		
		
		
		$("#interAccountDlg").window("open").window('setTitle', '添加主体');
	}	
	
	
	/**
	 * 选择控制
	 * @param size
	 * @param i
	 * @return
	 */
	function checkFlag(size,i){
		//alert(size+"------"+i);
		var after = i+1;
		if(after<size){
			var afterCheck =$("#ck"+after).is(":checked");
			if(afterCheck==true){
				$("#ck"+i).attr("checked","checked");
			}		
		}
		
		if(after<=size){
			var thisCheck =$("#ck"+i).is(":checked");
			if(thisCheck==true){
				for(var s =i;s>0;s--){
					$("#ck"+s).attr("checked","checked");
				}
			}
		}
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
			$('#userTr').hide();
			var enterprise = arr[0];
			
			$('#_account').attr('oldValue',enterprise.account);
			
			formUrl = 'ent_updateMainBody.action';
			
			$('#add_form').form("reset");				
			
			for(var item in enterprise){
				if(item!='ischarge'){
					
					if($('[name="enterprise.'+item+'"]').length==0){
						$('#in_value').append('<input type="hidden" name="enterprise.'+item+'" value="'+enterprise[item]+'"/>');
					}else{
						if($($('[name="enterprise.'+item+'"]')[0]).hasClass('combo-value')){
							var id = $($('[name="enterprise.'+item+'"]')[0]).parent().prev().attr('id');
							//$('#'+id).combobox('setValue',enterprise[item]);
							if($($('[name="enterprise.'+item+'"]')[0]).parent().hasClass('datebox')){
								$('#'+id).datebox('setValue',enterprise[item]);
							}
							
						}else{
							if(item=='validCode' || item=='showCode' || item=='auditPro' || item=='auditBatch'){
								$($('[name="enterprise.'+item+'"][value="'+enterprise[item]+'"]')).attr("checked","checked");
							}else
								$($('[name="enterprise.'+item+'"]')[0]).val(enterprise[item]);
								
						}
					}
				}
			}
			
			$('input[name="enterprise.ischarge"][value="'+enterprise.ischarge+'"]').click();
			
			$("#areaId").combotree({
				url:'ent_getEntAreaTree.action?tt='+Math.floor(Math.random()*20)+1,
				method:'get',
				required:true,
				onLoadSuccess:function(node, data){
					if(data){
						$("#areaId").combotree("setValue",enterprise.areaId);
					}
					f_timeout(data);			
				}
			});
			
			$("#parentId").combotree({
				url:'ent_getEntTree.action?tt='+Math.floor(Math.random()*20)+1,
				method:'get',
				required:true,
				onLoadSuccess:function(node, data){
				if(data){
					$("#parentId").combotree("setValue",enterprise.parentId);
				}
				f_timeout(data);			
				},
				onSelect: function(node){
					var entId = node.id;  // 在用户点击的时候提示			
					$.ajax({
						  url: "ent_getEntLevel.action?entId="+entId,
						  cache: false,
						  success: function(level){
							if(level==2){
								$("#provinceRsts").val("0");
							}else if(level==3){
								$("#cityRsts").val("0");
							}else if(level==4){
								$("#areaRsts").val("0");
							}else if(level==5){
								$("#townRsts").val("0");
							}
						  }
					});	
					//指定审核机构
					$.ajax({
						  url: "ent_getAuditEntTree.action?entId="+entId,
						  cache: false,
						  success: function(auditEntHTML){
							$("#auditEnt").html(auditEntHTML);
						  }
					});			
				}

			});		
			
		    $('#entType_id').combobox({    
		        url:'enttype_getEntTypeToSelect.action?tt='+Math.floor(Math.random()*20)+1,    
		        valueField:'id',    
		        textField:'text',
		        required:true,
		        editable:false,
				onLoadSuccess:function(node, data){
					$('#entType_id').combobox("setValue",enterprise.entType);
					f_timeout(data);			
				}
		    });
			
			
			$('#add_form').form('validate');
			
			$("#interAccountDlg").window("open").window('setTitle', '修改风格');

		}	
	}
	
	//提交
    function submitForm(){
    	if($('#add_form').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	parent.$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	  	
		//提交
		$('#add_form').form('submit', {
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
				$.post('enterprise_deleteSc.action', 'ids='+ids, function(result) {
					$.messager.show({ title : '提示', msg : result });
					$('#tabdataGrid').datagrid("reload"); 
				}, "TEXT");				 
			}
		});
	}
	
	function find(){
		var name = $('#s_name').val();
		var ischarge = $('#s_ischarge').combobox('getValue');
		var mbType = $('#s_mbType').combobox('getValue');
		var params = {};
		params['enterprise.name'] = name;
		params['enterprise.ischarge'] = ischarge;
		params['enterprise.mbType'] = mbType;
		$('#tabdataGrid').datagrid("load",params); 
	}
	
	function clearSearch(){
		$('#s_name').val('');
		$('#s_ischarge').combobox('setValue','');
		$('#s_mbType').combobox('setValue','');
		
		$('#tabdataGrid').datagrid("load",{}); 
	}
		     
	// 删除
	function removeit() {
		var rows = $("#tabdataGrid").datagrid("getSelections");
		// 判断是否选择行
		if (!rows || rows.length == 0) {
			$.messager.alert('提示', '请选择要删除的记录!', 'info');
			return false;
		} else {
			var temp; // 循环给提交删除参数赋值
			$.each(rows, function(i, n) {
				if (i == 0) {
					temp = n.entId;
				} else {
					temp += "," + n.entId;
				}
			});
			$.messager.confirm('提示', '是否删除选中数据?', function(r) {
				if (!r) {
					return;
				} else {
					parent.$.messager.progress( {
						title : '提示',
						text : '数据处理中，请稍后....'
					});
					$.ajax( {
						url : 'ent_deleteEnterprise.action',
						data : 'list_enterprise=' + temp,
						type : 'post',
						dataType : 'text',
						success : function(result) {
							parent.$.messager.progress('close');
							$.messager.show( {
								title : '提示',
								msg : result
							});
							$("#tabdataGrid").datagrid('reload');
						}
					});
				}
			});
		}
	}
	//自动补全域名
	function autoComplete(event){
		var val = $(event).val();
		if(val!=''){
			if(val.indexOf('.')==-1){
				$(event).val(val+".hontek.com.cn");
			}
		}
	}
	
	function styleConfig(){
		var arr = $('#tabdataGrid').datagrid("getSelections");
		var lg = arr.length;
		if(lg==0){
			$.messager.alert('提示','请选择需要设置风格的主体!','question');
		}else if(lg!=1){
			$.messager.alert('提示','对不起，一次只能设置一主体，请重新选择!','question');
		}else{
			var row = arr[0];
			$('#styleConfigDlg').window('open');
			$('#ifStyleConfig').attr('src',$('#basePath').val()+'jsp/sys/entStyleConfig.jsp?entId='+row.entId);
		}
	}
	
	
	//主体二维码标签风格设置
	var tagFormUrl="tagStyle_addTagStyle.action";
	function tagStyleDlg(){
		var arr = $('#tabdataGrid').datagrid("getSelections");
		var lg = arr.length;
		if(lg==0){
			$.messager.alert('提示','请选择需要设置二维码标签风格的主体!','question');
		}else if(lg!=1){
			$.messager.alert('提示','对不起，一次只能设置一主体，请重新选择!','question');
		}else{
			var row = arr[0];
			var entId = row.entId;
			
			getTagStyleInfo(entId);//查找当前主体的二维码风格设置
			
			//复选框的单选效果
			$('.scImg_checkbox').click(function(){
				if($(this).is(':checked')){
					$('.scImg_checkbox').attr('checked',false);
					$(this).attr('checked',true);
					$("input[name='tagStyle.tsType']").val($(this).val());
					//alert(baseScId);
				}else{
					$("input[name='tagStyle.tsType']").val("");
					$('.scImg_checkbox').attr('checked',false);
				}
			});
			
			$('#tagStyleDlg').window('open');
		}

	}
	
	//查找出主体二维码标签风格表中数据
	function getTagStyleInfo(entId){
		$.ajax( {
			url : 'tagStyle_getTagStyleInfo.action?entId='+entId,
			async : false,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result!=null&&result!=""){
					var tagStyle = result;
					tagFormUrl="tagStyle_updateTagStyle.action";
		   			$("input[name='tagStyle.tsId']").val(tagStyle.tsId);
		   			$("input[name='tagStyle.tsType']").val(tagStyle.tsType);
		   			$("input[name='tagStyle.entId']").val(tagStyle.entId);
		   			$("input[name='tagStyle.toplogo']").val(tagStyle.toplogo);
		   			$("input[name='tagStyle.codelogo']").val(tagStyle.codelogo);
		   			$("input[name='tagStyle.userId']").val(tagStyle.userId);
		   			$("input[name='tagStyle.createTime']").val(tagStyle.createTime);
		   			$("input[name='tagStyle.remark']").val(tagStyle.remark);
		   			
		   			$("input[name='tagStyle.tagSize'][value="+tagStyle.tagSize+"]").attr("checked",true); 
		   			
		   			$('.scImg_checkbox').attr('checked',false);
		   			$("#tsType"+tagStyle.tsType).attr("checked",true);
		   			
		   			if(tagStyle.toplogo&&tagStyle.toplogo!=''){
						var fpath = "/nytsyFiles/entstyle/";
						$('input[name="tagStyle.toplogo"]').val(tagStyle.toplogo);
						var img_html = "<a rel=\"previewtoplogo\" href=\""+fpath+tagStyle.toplogo+"\"><img alt=\"预览\" src=\""+fpath+tagStyle.toplogo+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
						$("#toplogofileQueue").html(img_html);
						$("a[rel=previewtoplogo]").fancybox({
							'transitionIn'		: 'none',
							'transitionOut'		: 'none',
							'titlePosition' 	: 'over',
							'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
								return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
							}
						});
					}else{
						$("#toplogofileQueue").html("");
					}
		   			
		   			if(tagStyle.codelogo&&tagStyle.codelogo!=''){
						var fpath = "/nytsyFiles/entstyle/";
						$('input[name="tagStyle.codelogo"]').val(tagStyle.codelogo);
						var img_html = "<a rel=\"previewcodelogo\" href=\""+fpath+tagStyle.codelogo+"\"><img alt=\"预览\" src=\""+fpath+tagStyle.codelogo+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
						$("#codelogofileQueue").html(img_html);
						$("a[rel=previewcodelogo]").fancybox({
							'transitionIn'		: 'none',
							'transitionOut'		: 'none',
							'titlePosition' 	: 'over',
							'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
								return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
							}
						});
					}else{
						$("#codelogofileQueue").html("");
					}
		   			
				}else{
					tagFormUrl="tagStyle_addTagStyle.action";
					$("#tagStyleForm").form("reset");
					$('.scImg_checkbox').attr('checked',false);
		   			$("#tsType1").attr("checked",true);
		   			$("input[name='tagStyle.entId']").val(entId);
				}	
			}
		});
	}	
	
	
	//提交二维码风格
    function submitTagStyleForm(){
    	
    	console.info("tagFormUrl="+tagFormUrl);
    	var tsType = $("input[name='tagStyle.tsType']").val();
    	if(tsType==""){
    		$.messager.show({ title : '提示', msg : '请选择风格!' });  
    		return;
    	}
    	
    	if($('#tagStyleForm').form('validate')==false){
    		$.messager.show({ title : '提示', msg : '必填验证没有通过!' });  
    		return;
    	}   	
    	$.messager.progress({
			title : '提示',
			text : '数据处理中，请稍后....'
		});
    	  	
		//提交
		$('#tagStyleForm').form('submit', {
			url : tagFormUrl,
			onSubmit : function(param) {
			    return $(this).form('validate');//对数据进行格式化
			},
			success : function(result) {
				$.messager.show({ title : '提示', msg : result });   	
				$.messager.progress('close');	
			}
		});           		       	 
    }
	
	
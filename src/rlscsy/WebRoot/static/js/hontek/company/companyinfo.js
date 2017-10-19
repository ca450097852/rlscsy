$(function() {	
	var entParam = {};
	entParam["enterprise.flag"]='3';
	 parent.$.messager.progress('close');
	 
	 
	 
	 $("#companyTab").tabs({
		 onSelect: function(title,index){
			var target = this;
			//选择不是第一个选项卡页
			if(index>0){
				var zizhiEntId = $("#zizhiEntId").val();
				var proEntId = $("#proEntId").val();
								
				if(zizhiEntId==null||zizhiEntId==''||proEntId==null||proEntId==''){
					$("#companyTab").tabs('select',0);//选择到第一个
					$.messager.show({ title : '提示', msg : '请先填写企业信息并保存!' });   	
					return;
				}
				if(title=='资质信息'){
					//表格数据
					$('#zizhiDataGrid').datagrid({  
						  title:'资质证书列表',
						  iconCls : 'icon-table',
					      pageSize : 15,//默认选择的分页是每页5行数据  
					      pageList : [ 10, 15,20,30, 50 ],//可以选择的分页集合  
					      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
					      striped : true,//设置为true将交替显示行背景。  
					      //collapsible : true,//显示可折叠按钮  
					      toolbar:"#tb_zizhi",//在添加 增添、删除、修改操作的按钮要用到这个  
					      url:'zizhi_findZizhiPagerList.action',//url调用Action方法  
					      queryParams:{"zizhi.entId":zizhiEntId},
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
								{field:'zizhiName',title:'证书名称',width:200,align:'center'},			
								{field:'appType',title:'证书类型',width:100,align:'center',
									formatter: function(value,row,index){ 
										var displayValue = '';
										if(value==1){
											displayValue = '资质文件'
										}else if(value==2){
											displayValue = '营业执照'
										}else if(value==3){
											displayValue = '企业荣誉'
										}else if(value==4){
											displayValue = '其它证书'
										}else{
											displayValue = '组织机构扫描件'
										}
										return displayValue;
									}
								},					
								{field:'awardUnit',title:'颁发单位',width:150,align:'center'},
								//{field:'awardTime',title:'颁发时间',width:100,align:'center'},
								//{field:'remark',title:'备注',width:200,align:'center'},
								{field:'action',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
									var t1='<a href=\'javascript:void(0);\' onclick=\'attachWin('+index+');\'>附件管理</a>'										
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
				}else if(title=='生产信息'){
					//表格数据
					$('#productionDataGrid').datagrid({  
						  title:'生产信息列表',
						  iconCls : 'icon-table',
					      pageSize : 15,//默认选择的分页是每页5行数据  
					      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
					      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
					      striped : true,//设置为true将交替显示行背景。  
					      //collapsible : true,//显示可折叠按钮  
					      toolbar:"#tb_pro",//在添加 增添、删除、修改操作的按钮要用到这个  
					      url:'production_findProductionPagerList.action',//url调用Action方法  
					      queryParams:{"production.entId":proEntId},
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
								//{field:'entName',title:'企业名称',width:150,align:'center'},
								//{field:'crttime',title:'录入时间',width:100,align:'center'},
								{field:'action',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
									var t1='<a href=\'javascript:void(0);\' onclick=\'attachWin1('+index+');\'>附件管理</a>'										
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
				}else if(title=='二维码信息'){
					$.post("ent_getLoginCompanyDimenno.action","",function(result){
						if(result[0]){
							var obj = result[0];
							if(obj.companyRsts!='4'){
								$('#companyTab').tabs('select',0);
								$.messager.show({ title : '提示', msg : '审核未通过，不能查看二维码信息!' });  
								return;
							}
							//$("#sh_info").html(obj.AudResult);
							$("#code_Img").html("<img src='/nytsyFiles/qrcode/"+obj.dimImg+"' />");
							$("#Dimenno_Img").val(obj.dimImg);
						}
					},"JSON");
				}else if(title='审核信息'){
					//表格数据
					$('#auditRecordDataGrid').datagrid({  
						  title:'审核信息记录表',
						  iconCls : 'icon-table',
					      pageSize : 15,//默认选择的分页是每页5行数据  
					      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
					      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
					      striped : true,//设置为true将交替显示行背景。  
					      //collapsible : true,//显示可折叠按钮  
					      //toolbar:"#tb_pro",//在添加 增添、删除、修改操作的按钮要用到这个  
					      url:'auditRecord_findAuditRecordPagerList.action',//url调用Action方法  
					      queryParams:{"auditRecord.entId":$("#hrentId").val()},
					      loadMsg : '数据装载中......',  			      
					      fit:true,			      
					      //singleSelect:true,//为true时只能选择单行  
					      fitColumns:true,//允许表格自动缩放，以适应父容器  
					      remoteSort : false,  
					      /*frozenColumns : [ [ {  
					           field : 'ck',  
					           checkbox : true  
					      } ] ],*/
					      columns:[[
								{field:'entName',title:'企业名称',width:150,halign:'center'},			
								{field:'auditEntName',title:'审核机构',width:100,halign:'center'},
								{field:'auditUserName',title:'审核人员',width:50,align:'center'},
								{field:'auditState',title:'审核状态',width:50,align:'center',formatter: function(value,row,index){ 					
									var t1='';
									if(value==2){
										t1='<font color=blue>暂停</font>';
									}else if(value==3){
										t1='<font color=red>退回</font>';
									}else if(value==4){
										t1='<font color=green>通过</font>';
									}
									return t1;
								}},
								{field:'auditOpinion',title:'审核意见',width:100,align:'center'},
								{field:'auditTime',title:'审核时间',width:50,align:'center'},

								{field:'action',title:'操作',width:50,align:'center',formatter: function(value,row,index){ 					
									var t1='<a href=\'javascript:void(0);\' onclick=\'attachWin1('+index+');\'>查看</a>'										
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
				}
			}
		 }
	});	 
	 init();
});


	

/**
 * 预览窗口
 * @param path
 * @return
 */
function previewEnt(path){
	window.open ($("#h_path").val()+'/jsp/company/preview.jsp?imgPath=company/'+path,'图片预览','height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no')
}

function removeEntAppd (id,path){
	$.post('ent_deleteFile.action', 'fileQueueFileName='+path, function(result) {
		$("#d_logo").remove();
	}, "TEXT");
}

// 初始化
function init() {
	$("#companyTab").tabs('select',0);//选择到第一个
	$("#zizhiEntId").val('');
	$("#proEntId").val('');
	$("#fileQueueLogo").html('');
	$("#logoUrl").val('');
	var enterprise = {};
	
	//获取企业信息
	
	
	$.post('ent_getLoginEntInfo.action','',function(result){
		
		f_timeout(result);
		
		enterprise = result[0];
		
		
		$('#oprerate').attr("value", "2");
		
		$("#zizhiEntId").val(enterprise.entId);
		$("#proEntId").val(enterprise.entId);
		
		$("#areaId").combotree({
			url:'ent_getEntAreaTree.action?tt='+Math.floor(Math.random()*20)+1,
			method:'get',
			required:true,
			onLoadSuccess:function(node, data){
				if(data){
					$("#areaId").combotree("setValue",data[0].id);
				}
				f_timeout(data);			
			}
		});
							
		$("input[name='enterprise.account']").val(enterprise.account);
					
		$('#entType_id').combobox('setValue', enterprise.entType);
		
		$("input[name='enterprise.sts']").val(enterprise.sts);
		$("input[name='enterprise.flag']").val(enterprise.flag);
		$("input[name='enterprise.legalPerson']").val(enterprise.legalPerson);
		$("input[name='enterprise.orgCode']").val(enterprise.orgCode);

		$("input[name='enterprise.entCode']").val(enterprise.entCode);
		$("input[name='enterprise.name']").val(enterprise.name);
		$("input[name='enterprise.regAddr']").val(enterprise.regAddr);
		$("input[name='enterprise.manageAddr']").val(enterprise.manageAddr);

		$("input[name='enterprise.simpleName']").val(enterprise.simpleName);
		$("input[name='enterprise.tel']").val(enterprise.tel);
		$("input[name='enterprise.postCode']").val(enterprise.postCode);
		$("input[name='enterprise.domName']").val(enterprise.domName);
		$("input[name='enterprise.sign']").val(enterprise.sign);
		$("input[name='enterprise.email']").val(enterprise.email);
		$("input[name='enterprise.seq']").val(enterprise.seq);
		$("input[name='enterprise.logoUrl']").val(enterprise.logoUrl);
		$("input[name='enterprise.sign']").val(enterprise.sign);
		$("textarea[name='enterprise.intro']").val(enterprise.intro);
		$("input[name='enterprise.entId']").val(enterprise.entId);
		$("input[name='enterprise.crtUserId']").val(enterprise.crtUserId);

		$("input[name='enterprise.isReported']").val(enterprise.isReported);
		$("input[name='enterprise.sysCode']").val(enterprise.sysCode);
		
		$("input[name='enterprise.parentId']").val(enterprise.parentId);
		$("input[name='enterprise.entLevel']").val(enterprise.entLevel);
		$("input[name='enterprise.townRsts']").val(enterprise.townRsts);
		$("input[name='enterprise.areaRsts']").val(enterprise.areaRsts);
		$("input[name='enterprise.cityRsts']").val(enterprise.cityRsts);
		$("input[name='enterprise.provinceRsts']").val(enterprise.provinceRsts);
		$("input[name='enterprise.mobile']").val(enterprise.mobile);
		
		$("input[name='enterprise.companyRsts']").val(enterprise.companyRsts);
		
		if(enterprise.logoUrl!=''){
			var itemTemplate ='';
			itemTemplate += '<div id="d_logo" class="uploadify-queue-item">\<div class="cancel">';				
			var t1='<a href=\'javascript:void(0);\' onclick=\'previewEnt("'+enterprise.logoUrl+'");\' style=\'background: url("uploadify/da.jpg") 0 0 no-repeat;float:right\' title=\'预览\'></a>';			
			var t2 = '<a href="javascript:void(0)" onclick="removeEntAppd(\''+enterprise.logoUrl+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+enterprise.logoUrl+'</span><span class="data"></span>\</div>';						
			itemTemplate = itemTemplate+t1+t2;
			$("#fileQueueLogo").html(itemTemplate);
		}
			
		$("#uploadifyLogo").uploadify({
			'swf'      		: 'uploadify/uploadify.swf',
			'fileObjName' 	: 'fileQueue',
			'uploader' 		: 'ent_uploadFile.action;jsessionid='+$('#jsessionid').val(),
			'buttonText'    : '上传附件',
			'height'		: 20,
			'width'			: 100,
			'fileSizeLimit'	: 1024,
			'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
	        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
			'queueID'       : 'fileQueueLogo',
			'multi'			: false,
			'removeCompleted' : false,		
			'onUploadSuccess' : function(file, data, response) {
				$("#fileQueueLogo").html('');
				var itemTemplate ='';
				itemTemplate += '<div id="d_logo" class="uploadify-queue-item">\<div class="cancel">';				
				var t1='<a href=\'javascript:void(0);\' onclick=\'previewEnt("'+data+'");\' style=\'background: url("uploadify/da.jpg") 0 0 no-repeat;float:right\' title=\'预览\'></a>';			
				var t2 = '<a href="javascript:void(0)" onclick="removeEntAppd(\''+data+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+file.name+'</span><span class="data"></span>\</div>';						
				itemTemplate = itemTemplate+t1+t2;
				$("#fileQueueLogo").html(itemTemplate);
				$("#logoUrl").val(data);
			}
		});
		$("#add_form").form("validate");
		//审核状态
		if(enterprise.companyRsts==0||enterprise.companyRsts==3){
			$('#subA').show();
			$('#savB').show();
		}
		if(enterprise.companyRsts!=4){
			$("#companyTab").tabs('close','二维码信息');
		}
		
		if(enterprise.companyRsts==0){
			$("#companyTab").tabs('close','审核信息');
		}
		
		if(enterprise.companyRsts==1||enterprise.companyRsts==2||enterprise.companyRsts==4){
			$('#uploadifyLogo').remove();
			if(enterprise.logoUrl==''){
				$('#tr_logo').hide();
			}
		}
		
		
		
	},'JSON');
}




// 提交表单
function submitForm() {
	// oprerate=1 --> 添加 ， oprerate=2 -- > 修改
	var op = $('#oprerate').attr("value");
	if (op == 1) {
			$('#add_form').form('submit', {
				url : 'ent_addCompany.action',
				onSubmit : function(result) {
					return $(this).form('validate');// 对数据进行格式化
			},
			success : function(result) {
				if($.isNumeric(result) ){
					//保存成功后返回id
					$("#zizhiEntId").val(result);
					$("#proEntId").val(result);
					$.messager.show({ title : '提示', msg : "保存成功!"});   						
				}else{
					$.messager.show({ title : '提示', msg : result });   	
				}				
			}
		});
	} else {
		$('#add_form').form('submit', {
			url : 'ent_updateEnterprise.action',
			onSubmit : function(result) {
				return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			$.messager.show( {
				title : '提示',
				msg : result
			});
		}
	});
	}
}

var ziZhiformUrl = '';
/**
 * 添加
 * @return
 */
function appendZizhi(){
	var zizhiEntId = $("#zizhiEntId").val();
	$('#zizhiForm').form("reset");
	$("#zizhiEntId").val(zizhiEntId);
	
	$('#in_value_zizhi').html("");
	ziZhiformUrl = 'zizhi_addZizhi.action';
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
		
		ziZhiformUrl = 'zizhi_updateZizhi.action';
		
		$('#zizhiForm').form("reset");				
		$("#in_value_zizhi").html($("#h_value_zizhi").html());
							
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
function submitZizhiForm(){	
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
		url : ziZhiformUrl,
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
	showZizhiAttachFile(zid);
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
		'onQueueComplete' : function(queueData) {
			//alert(queueData.uploadsSuccessful + ' files were successfully uploaded.');
			showZizhiAttachFile(zid)
		}
	});
		
	$("#attachWin").window('open').window('setTitle',zname+'-附件管理');
}
/**
 * 显示上传的资质附件
 * @param zid
 * @return
 */	
function showZizhiAttachFile(zid){	
	$("#fileQueue").html('');
	$.post('zizhiAppendix_findZizhiAppendixPagerList.action', 'zid='+zid, function(result) {
		if(result&&result.total>0){
			var itemTemplate ='';
			for(var i = 0; i<result.rows.length;i++){
				var appd = result.rows[i];
				itemTemplate += '<div id="d_'+appd.appId+'" class="uploadify-queue-item">\<div class="cancel">';				
				var t1='<a href=\'javascript:void(0);\' onclick=\'previewZizhi("'+appd.path+'");\' style=\'background: url("uploadify/da.jpg") 0 0 no-repeat;float:right\' title=\'预览\'></a>';			
				var fn = appd.appName=='' ? appd.path : appd.appName;
				var t2 = '<a href="javascript:void(0)" onclick="removeZizhiAppd('+appd.appId+',\''+appd.path+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+fn+'</span><span class="data"></span>\</div>';						
				itemTemplate = itemTemplate+t1+t2;							
			}
			$("#fileQueue").html(itemTemplate);
		}			
	}, "JSON");	
}

/**
 * 预览窗口
 * @param path
 * @return
 */
function previewZizhi(path){
	window.open ($("#h_path").val()+'/jsp/company/preview.jsp?imgPath=zizhi/'+path,'图片预览','height=600,width=800,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no')
}

function removeZizhiAppd (id,path){
	$.post('zizhiAppendix_deleteFile.action', 'fileName='+path, function(result) {
		$("#d_"+id).remove();
	}, "TEXT");
}


/**************************************************生产信息***********************************************************************/
var formUrl = '';
/**
 * 添加
 * @return
 */
function appendProduction(){
	var proEntId = $("#proEntId").val();
	$('#productionForm').form("reset");
	$("#proEntId").val(proEntId);
	
	$('#in_value_pro').html("");
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
		$("#in_value_pro").html($("#h_value_pro").html());

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
function submitProForm(){
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
function attachWin1(i){
	var production = $('#productionDataGrid').datagrid("getRows")[i]; 

	var proId = production.proId;
	$("#uploadify1").uploadify({
		'swf'      		: 'uploadify/uploadify.swf',
		'fileObjName' 	: 'uploadify',
		'uploader' 		: 'productionAppendix_uploadFile.action;jsessionid='+$('#jsessionid').val(),
		'formData'		: {'proId':proId,'appType':'1'},
		'buttonText'    : '上传附件',
		'height'		: 20,
		'width'			: 100,
		'fileSizeLimit'	: 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID'       : 'fileQueue1',
		'multi'			: true,
		'removeCompleted' : false,
		'onQueueComplete' : function(queueData) {
			showProAttachFile(proId)
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
		'fileSizeLimit'	: 1024,
		'fileTypeDesc'  : '支持格式:jpg/gif/jpeg/png',  
        'fileTypeExts'  : '*.jpg;*.gif;*.jpeg;*.png',
		'queueID'       : 'fileQueue2',
		'multi'			: true,
		'removeCompleted' : false,
		'onQueueComplete' : function(queueData) {
			showProAttachFile(proId)
		}
	});		
	$("#attachWin1").window('open').window('setTitle','生产信息-附件管理');
}
/**
 * 显示上传的生产附件
 * @param proId
 * @return
 */
function showProAttachFile(proId){
	$("#fileQueue1").html('');
	$("#fileQueue2").html('');
	$.post('productionAppendix_findProductionAppendixPagerList.action', 'proId='+proId, function(result) {
		if(result&&result.total>0){
			var itemTemplate ='';				
			var itemTemplate2 ='';			
			for(var i = 0; i<result.total;i++){
				var appd = result.rows[i];
				var fn = appd.appName;
				if(appd.appName==''){fn = appd.path;}				
				if(appd.appType==0){
					itemTemplate2 += '<div id="d_'+appd.appId+'" class="uploadify-queue-item">\<div class="cancel">';				
					var t1='<a href=\'javascript:void(0);\' onclick=\'preview("'+appd.path+'");\' style=\'background: url("uploadify/da.jpg") 0 0 no-repeat;float:right\' title=\'预览\'></a>';			
					var t2 = '<a href="javascript:void(0)" onclick="removeAppd('+appd.appId+',\''+appd.path+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+fn+'</span><span class="data"></span>\</div>';						
					itemTemplate2 = itemTemplate2+t1+t2;
				}else if(appd.appType==1){
					itemTemplate += '<div id="d_'+appd.appId+'" class="uploadify-queue-item">\<div class="cancel">';				
					var t1='<a href=\'javascript:void(0);\' onclick=\'preview("'+appd.path+'");\' style=\'background: url("uploadify/da.jpg") 0 0 no-repeat;float:right\' title=\'预览\'></a>';			
					var t2 = '<a href="javascript:void(0)" onclick="removeAppd('+appd.appId+',\''+appd.path+'\')" title=\'删除\'>X</a>\</div>\<span class="fileName">'+fn+'</span><span class="data"></span>\</div>';						
					itemTemplate = itemTemplate+t1+t2;						
				}												
			}
			$("#fileQueue1").html(itemTemplate);
			$("#fileQueue2").html(itemTemplate2);
		}			
	}, "JSON");
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
/**
 * 提交审核
 * @return
 */
function submitAud(){
	var isValid = $('#add_form').form('validate');
	if(isValid){
		/*$.post('zizhi_isCreateZizhi.action','',function(result){
			if('false'==result){
				$.messager.show({ title : '提示', msg : '请在资质信息页面添加营业执照信息，并添加营业执照扫描件' });
			}else{*/
				$.post('ent_submitAud.action','',function(result){
					if('unFull'==result){
						$.messager.show({ title : '提示', msg : '对不起，你的企业信息没有填写完整，请及时补充。谢谢！' });
					}else if('true'==result){
						$.messager.show({ title : '提示', msg : '提交成功，请耐心等待审核结果！' });
						$('#subA').hide();
						$('#savB').hide();
						$('#uploadifyLogo').remove();
					}
				},'TEXT');
			//}
		//},'TEXT');
	}else{//企业信息资料不完整
		$.messager.show({ title : '提示', msg : '对不起，你的企业信息没有填写完整，请及时补充。谢谢！' });
	}
}

//下载二维码组合图片
function downloadCodeImg(){
	var img = $("#Dimenno_Img").val();
	window.location.href="download.action?fileName="+img;
}
// 下载二维码图片-四方图
function downloadCodeImg2(){
	var img = $("#Dimenno_Img").val();
	img=img.substring(0,img.indexOf(".png"));
	img=img+"_2.png"
	window.location.href="download.action?fileName="+img;
}
// 下载二维码高清大图片-四方图
function downloadCodeImg3(){
	var img = $("#Dimenno_Img").val();
	img=img.substring(0,img.indexOf(".png"));
	img=img+"_3.png"
	window.location.href="download.action?fileName="+img;
}
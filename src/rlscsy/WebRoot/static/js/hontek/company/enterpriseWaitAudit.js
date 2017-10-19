var dataGrid; // 列表
$(function() {	
	dataGrid = $('#list_enterprise').datagrid( {
		url : 'ent_findWaitAuditCompanyList.action',
		queryParams: {"enterprise.companyRsts": '1,2'},
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '企业管理',
		iconCls : 'icon-house',
		loadMsg : '数据加载中...',
		pageSize : 15,// 默认选择的分页是每页10行数据
		pageList : [ 10, 15,20, 30, 50 ],// 可以选择的分页集合
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
		striped : true,// 设置为true将交替显示行背景。
		fit : true,
		fitColumns: true,
		// singleSelect:true,
		pagination : true,
		rownumbers : true,
		remoteSort : false,
		// collapsible : true,//显示可折叠按钮
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, 
		//{field : 'areaName',title : '所属区域',width : 80,align : 'center'}, 
		//{field : 'parentName',title : '所属行政机构',width : 120,align:'center'}, 
		{field : 'name',title : '企业名称',width : 200,halign:'center'}, 
		{field : 'regAddr',title : '注册地址',width : 200,halign:'center'}, 
		{field : 'tel',title : '联系电话',width : 100,align : 'center'}, 
		{field : 'legalPerson',title : '法人代表',width : 80,align : 'center'}, 
		//{field : 'typeName',title : '企业分类',width : 80,align : 'center'}, 	
		/*{field : 'townRsts',title : '镇审核',width : 50,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);	
			}
		},*/
		{field : 'areaRsts',title : '区审核',width : 50,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);	
			}
		},
		{field : 'cityRsts',title : '市审核',width : 50,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);	
			}
		},
		/*{field : 'provinceRsts',title : '省审核',width : 50,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);	
			}
		},*/
		{field : 'companyRsts',title : '企业状态',width : 80,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);
			}
		},
		{field : 'entId',title : '操作',width : 50,align : 'center',
			formatter : function(value, row, index) {
				var e = "<a href='javascript:void(0)' onclick='update("+index+")'>审核</a>&nbsp;&nbsp;";
				
				return e;			
			}
		}
		] ],
		onLoadSuccess:function(data){
		 	f_timeout(data);
	 	},
 		onClickRow: function(rowIndex, rowData){
 			$(this).datagrid('unselectAll');
 			$(this).datagrid('selectRow',rowIndex);
 		}
	}); 
	 
	 $("#companyTab").tabs({
		 onSelect: function(title,index){
			var target = this;
			//选择不是第一个选项卡页
			if(index>0){
				var zizhiEntId = $("#zizhiEntId").val();
				//var proEntId = $("#proEntId").val();
				var auditRecordEntId =$("#auditRecordEntId").val();

				if(zizhiEntId==null||zizhiEntId==''){
					$("#companyTab").tabs('select',0);//选择到第一个
					$.messager.show({ title : '提示', msg : '请先填写企业信息并保存!' });   	
					return;
				}
				if(index==1){
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
										}else if(value==5){
											displayValue = '组织机构代码证'
										}else{
											displayValue = '其它证书'
										}
										return displayValue;
									}
								},		
								{field:'state',title:'状态',width:100,align:'center',
									formatter: function(value,row,index){ 
										var displayValue = '';
										if(value==1){
											displayValue = '正常'
										}else if(value==2){
											displayValue = '申请修改中'
										}else if(value==3){
											displayValue = '同意修改'
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
				}else if(index==2){/*
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
					      columns:[[
								{field:'productinfo',title:'产品描述',width:400,align:'center'},			
								{field:'license',title:'产品许可证号',width:150,align:'center'},
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
				*/
					//表格数据
					$('#auditRecordDataGrid').datagrid({  
						  title:'审核信息记录表',
						  iconCls : 'icon-table',
					      pageSize : 20,//默认选择的分页是每页5行数据  
					      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
					      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
					      striped : true,//设置为true将交替显示行背景。  
					      //collapsible : true,//显示可折叠按钮  
					      //toolbar:"#tb_pro",//在添加 增添、删除、修改操作的按钮要用到这个  
					      url:'auditRecord_findAuditRecordPagerList.action',//url调用Action方法  
					      queryParams:{"auditRecord.entId":auditRecordEntId},
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
								{field:'auditTime',title:'审核时间',width:100,align:'center'}/*,

								{field:'action',title:'操作',width:50,align:'center',formatter: function(value,row,index){ 					
									var t1='<a href=\'javascript:void(0);\' onclick=\'attachWin1('+index+');\'>查看</a>'										
									return t1;
								}}*/
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
				}/*else if(index==4){
					//表格数据
					$('#superviseDataGrid').datagrid({  
						  title:'监管信息记录表',
						  iconCls : 'icon-table',
					      pageSize : 15,//默认选择的分页是每页5行数据  
					      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
					      nowrap : true,//设置为true，当数据长度超出列宽时将会自动截取  
					      striped : true,//设置为true将交替显示行背景。  
					      //collapsible : true,//显示可折叠按钮  
					      toolbar:"#tb_supervise",//在添加 增添、删除、修改操作的按钮要用到这个  
					      url:'supervise_findSupervisePagerList.action',//url调用Action方法  
					      queryParams:{"supervise.entId":auditRecordEntId},
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
								{field:'title',title:'信息标题',width:300,align:'center'},			
								//{field:'contents',title:'信息内容',width:300,halign:'center'},
								{field:'supTime',title:'检查时间',width:100,align:'center'},
								{field:'action',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
									var t1='<a href=\'javascript:void(0);\' onclick=\'showSupervise('+index+');\'>查看</a>';
										t1+='&nbsp;&nbsp;<a href=\'javascript:void(0);\' onclick=\'deleteSupervise('+index+');\'>删除</a>'
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
				}*/
			}
		 }
	});	  
	 
});

/********************************监管信息开始*****************************************/
var superviseFormUrl = '';
function appendSuperVise(){
	$("#supervise_title").val('');
	$("#xh_editor").val('');	
	$("#btnSupervise").show();
	$("#uploadList").html('');
	$("#supervise_supId").val('');
	$('#superviseForm').form('validate');
	superviseFormUrl = 'supervise_addSupervise.action';
	$('#addSupervise').window('open').window('setTitle', '添加监管信息');;
}

function showSupervise(i){
	var supervise = $('#superviseDataGrid').datagrid("getRows")[i];		
	$("input[name='supervise.title']").val(supervise.title);
	$("input[name='supervise.supTime']").val(supervise.supTime);
	$("textarea[name='supervise.contents']").val(supervise.contents);
	$("#btnSupervise").hide();
	$('#superviseForm').form('validate');
	$('#addSupervise').window('open').window('setTitle', '查看监管信息');;
}

function updateSuperVise(){
	var supervise = $('#superviseDataGrid').datagrid("getSelected");		
	if(supervise==null){
		$.messager.show( {title : '提示',msg : '请在列表中选择您要修改的监管信息!'});
		return;
	}
	$("input[name='supervise.supId']").val(supervise.supId);
	$("input[name='supervise.supEnt']").val(supervise.supEnt);
	$("input[name='supervise.supUser']").val(supervise.supUser);
	$("input[name='supervise.supCode']").val(supervise.supCode);
	$("input[name='supervise.title']").val(supervise.title);
	$("input[name='supervise.supTime']").val(supervise.supTime);
	$("textarea[name='supervise.contents']").val(supervise.contents);
	$("#btnSupervise").show();
	$('#superviseForm').form('validate');
	superviseFormUrl = 'supervise_updateSupervise.action';
	$('#addSupervise').window('open').window('setTitle', '修改监管信息');
}

function deleteSupervise(i){
	var supervise = $('#superviseDataGrid').datagrid("getRows")[i];		
	var id = supervise.supId;	
	$.messager.confirm('提示', '是否删除选中数据?', function(r) {
		if (r) {
			$.ajax( {
				url : 'supervise_deleteSupervise.action',
				data : 'ids='+ id,
				type : 'post',
				dataType : 'text',
				success : function(result) {
					$.messager.show( {title : '提示',msg : result});
					$('#superviseDataGrid').datagrid('reload');
				}
			});
		}
	});
}

function submitSuperviseForm(){	
	
	$("#xh_editor").val();
	$('#superviseForm').form('submit', {
		url : superviseFormUrl,
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			$.messager.show({ title : '提示', msg : result });   					
			//隐藏提交按钮
			$('#addSupervise').window('close');
			$('#superviseDataGrid').datagrid('reload');
		}
	});
}

/********************************监管信息结束*****************************************/



function submitAuditForm(){
	
	var auditstate = $("input[name='auditRecord.state']:checked").val();
	var auditOpinion = $("#auditOpinion").val();
	if(auditOpinion==''||auditOpinion==undefined){
		$.messager.show({ title : '提示', msg : '请填写您的审核意见!' });   
		return;
	}
	$('#auditRecordForm').form('submit', {
		url : 'auditRecord_addAuditRecord.action',
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			$.messager.show({ title : '提示', msg : result });   			
			
			//隐藏提交按钮
			$("#btnAudit").hide();	
			$('#auditRecordDataGrid').datagrid('reload');
			$('#list_enterprise').datagrid('reload');

		}
	});
}


function getAuditState(sts){
	//0待提交，1待审核，2暂停，3不通过，4通过；默认为空
	var auditeState = '';
	if(sts==''){
		auditeState = '----';
	}else if(sts==0){
		auditeState = '待提交';
	}else if(sts==1){
		auditeState = '待审核';
	}else if(sts==2){
		auditeState = '暂停';
	}else if(sts==3){
		auditeState = '退回';
	}else if(sts==4){
		auditeState = '通过';
	}
	return auditeState;
}



// 修改
function update(i) {
	$("#companyTab").tabs('select',0);//选择到第一个
	$("#zizhiEntId").val('');
	//$("#proEntId").val('');
	$("#fileQueueLogo").html('');
	$("#logoUrl").val('');
	
	//显示提交按钮
	$("#btnAudit").show();	
	
	var enterprise = $('#list_enterprise').datagrid("getRows")[i];		

	$('#oprerate').attr("value", "2");
	
	$("#zizhiEntId").val(enterprise.entId);
	//$("#proEntId").val(enterprise.entId);
	$("#auditRecordEntId").val(enterprise.entId);
	$("#supervise_entId").val(enterprise.entId);
	$("#auditRecordEntName").val(enterprise.name);

	$("#areaId").val(enterprise.areaName);
	
	$("input[name='enterprise.account']").val(enterprise.account);
					
	$('#companyRsts').val(getAuditState( enterprise.companyRsts));
	
	$('#entType_id').val(enterprise.typeName);
	
	$("input[name='enterprise.flag']").val(enterprise.flag);
	$("input[name='enterprise.legalPerson']").val(enterprise.legalPerson);
	$("input[name='enterprise.orgCode']").val(enterprise.orgCode);

	$("input[name='enterprise.entCode']").val(enterprise.entCode);
	$("input[name='enterprise.name']").val(enterprise.name);
	$("input[name='enterprise.regAddr']").val(enterprise.regAddr);
	$("input[name='enterprise.manageAddr']").val(enterprise.manageAddr);

	$("input[name='enterprise.simpleName']").val(enterprise.simpleName);
	$("input[name='enterprise.tel']").val(enterprise.tel);
	$("input[name='enterprise.mobile']").val(enterprise.mobile);

	$("input[name='enterprise.postCode']").val(enterprise.postCode);
	$("input[name='enterprise.domName']").val(enterprise.domName);
	$("input[name='enterprise.sign']").val(enterprise.sign);
	$("input[name='enterprise.email']").val(enterprise.email);
	$("input[name='enterprise.seq']").val(enterprise.seq);
	$("input[name='enterprise.logoUrl']").val(enterprise.logoUrl);
	$("input[name='enterprise.sign']").val(enterprise.sign);
	$("textarea[name='enterprise.intro']").val(delHtmlTag(enterprise.intro));
	$("input[name='enterprise.entId']").val(enterprise.entId);
	$("input[name='enterprise.crtUserId']").val(enterprise.crtUserId);

	$("input[name='enterprise.isReported']").val(enterprise.isReported);
	$("input[name='enterprise.sysCode']").val(enterprise.sysCode);
				

	$("#add_form").form("validate");
	$('#add').window('open').window('setTitle', '审核企业信息');			
}


//去掉html标签
function delHtmlTag(str){
	  return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
}
// 隐藏
function clearForm(id) {
	if(id==1){
		$('#add').window('close');
	}else{
		$('#show').window('close');
	}
}


//搜索
function find() {
	var entSearch = $('#entSearch').val();
	var name = $('#nameSearch').val();
	var companyRstsSearch = $('#companyRstsSearch').combobox('getValue');
	
	var enterprise = {};
	if(entSearch!=""){enterprise["enterprise.parentName"]=entSearch};
	if(name != ""){enterprise["enterprise.name"]=name};
	if (companyRstsSearch != "") {enterprise["enterprise.companyRsts"]=companyRstsSearch}	

	dataGrid.datagrid('load', enterprise); // 点击搜索
}

//重置
function clearSearch(){
	$('#entSearch').val("");
	$('#nameSearch').val("");
	$('#companyRstsSearch').combobox('setValue','');
	var enterprise = {};

	dataGrid.datagrid('load',enterprise);
}



/**
 * 
 * @param zid
 * @return
 */
function attachWin(i){
	var zizhi = $('#zizhiDataGrid').datagrid("getRows")[i]; 
	var zid = zizhi.id;
	var zname = zizhi.zizhiName;
	showZizhiAttachFile(zid);
		
	$("#attachWin").window('open').window('setTitle',zname+'-附件管理');
}
/**
 * 显示上传的资质附件
 * @param zid
 * @return
 */	
function showZizhiAttachFile(zid){	
	var filePath = $("#filePath").val();
	$("#zizhiFile").html('');
	$.post('zizhiAppendix_findZizhiAppendixPagerList.action', 'zid='+zid, function(result) {
		if(result&&result.total>0){
			var html ='';
			for(var i = 0; i<result.total;i++){
				var appd = result.rows[i];
				if(appd){
					var path = filePath+"zizhi/"+appd.path;
					var appName = appd.appName;
					html +='<a rel="zizhi_group" href="'+path+'" title="'+appName+'"><img border="0" style="margin:5px" alt="" width="150" height="150" src="'+path+'" /></a>';							
				}				
			}				
			$("#zizhiFile").html(html);				
			$("a[rel=zizhi_group]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
		}else{
			$("#zizhiFile").html('暂无附件!');
		}			
	}, "JSON");	
}

/**************************************************生产信息***********************************************************************/

/**
 * 
 * @param index
 * @return
 */
function attachWin1(i){
	var production = $('#productionDataGrid').datagrid("getRows")[i]; 
	var filePath = $("#filePath").val();

	$("#productionFile").html('');
	$("#licenceFile").html('');

	var proId = production.proId;
	$.post('productionAppendix_findProductionAppendixPagerList.action', 'proId='+proId, function(result) {
		if(result&&result.total>0){
			var itemTemplate ='';				
			var itemTemplate1 ='';			
			for(var i = 0; i<result.total;i++){
				var appd = result.rows[i];
				var appName = appd.appName;
				var path = filePath+"production/"+appd.path;
				
				if(appd.appType==0){
					itemTemplate1 +='<a rel="licence_group" href="'+path+'" title="'+appName+'"><img border="0" style="margin:5px" alt="" width="150" height="150" src="'+path+'" /></a>';							
				}else if(appd.appType==1){
					itemTemplate +='<a rel="production_group" href="'+path+'" title="'+appName+'"><img border="0" style="margin:5px" alt="" width="150" height="150" src="'+path+'" /></a>';							
				}												
			}
			if(itemTemplate==''){
				itemTemplate = '暂无附件!';
			}else{
				$("#productionFile").html(itemTemplate);
				$("a[rel=production_group]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
			}
			if(itemTemplate1==''){itemTemplate1 = '暂无附件!';}else{
				$("#licenceFile").html(itemTemplate1);
				$("a[rel=licence_group]").fancybox({
					'transitionIn'		: 'none',
					'transitionOut'		: 'none',
					'titlePosition' 	: 'over',
					'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
						return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
					}
				});
			}
		}else{
			$("#productionFile").html('暂无附件!');
			$("#licenceFile").html('暂无附件!');
		}			
	}, "JSON");
	
	$("#attachWin1").window('open').window('setTitle','生产信息-附件管理');
}
	


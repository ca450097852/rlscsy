var dataGrid; // 列表
var isbatch = 0;
var auditRecordEntId = 0;
$(function() {	
	dataGrid = $('#list_enterprise').datagrid( {
		url : 'ent_findCompanyList.action',
		queryParams: {"enterprise.companyRsts": '4',"enterprise.validCode":1},
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '企业信息列表',
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
		columns : [ [ 
		{field : 'name',title : '企业名称',width : 200,halign:'center'}, 
		{field : 'regAddr',title : '注册地址',width : 200,halign:'center'}, 
		{field : 'tel',title : '联系电话',width : 100,align : 'center'}, 
		{field : 'legalPerson',title : '法人代表',width : 60,align : 'center'}, 
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
		{field : 'companyRsts',title : '企业状态',width : 60,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);
			}
		},
		{field : 'entId',title : '操作',width : 100,align : 'center',
			formatter : function(value, row, index) {
				var e = "<a href='javascript:void(0)' onclick='update("+index+")'>查看详细</a>";
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
	 
	 $.ajax({
		 url:'compfor_getEntTreeByAreaId.action',
		 type:'post',
		 dataType:'json',
		 success:function(result){
			 if(result){
				 result.splice(0, 0, {id:-1,text:'--请选择--'});  
				 $("#areaId").combotree({
						data:result,
						value:-1
					});
			 }
		 }
	 });
	 
});



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
	
	var w = $(window).width()-15;
	
	var enterprise = dataGrid.datagrid('getRows')[i];
	isbatch = enterprise.isbatch;
	auditRecordEntId = enterprise.entId;
	
	$("#recordDiv").html("");							
	//isbatch==0 不是批次追溯					
	if(isbatch==0){
		$("#recordDiv").html('<div id="pMain" class="easyui-panel" ><table id="recordTable"></table></div>');
		
		$("#pMain").panel({width:w,height:'500',collapsible:true,collapsed:false});
		
		$("#recordTable").datagrid({  
			  title:'生产档案列表',
			  iconCls : 'icon-table',
		      pageSize : 15,//默认选择的分页是每页5行数据  
		      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
		      nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取  
		      striped : true,//设置为true将交替显示行背景。  
		      url:'record_findRecordList.action',//url调用Action方法  
		      queryParams:{"entId":auditRecordEntId},
		      loadMsg : '数据装载中......',  			      
		      fit:true,			      
		      fitColumns:true,//允许表格自动缩放，以适应父容器  
		      remoteSort : false,
		      columns:[[
					{field:'recName',title:'档案名称',width:200,align:'center'},			
					{field:'crttime',title:'创建时间',width:100,align:'center'},
					{field:'recSts',title:'审核状态',width:50,align:'center',formatter: function(value,row,index){ 					
							if(value==''||value==0){
								return '待审核';
							}else if(value==1){
								return '<font color=green>正常</font>';
							}else if(value==2){
								return '<font color=red>异常</font>';
							}
					}},
					{field:'recId',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
						var t1='<a href=\'javascript:void(0);\' onclick=\'recordWin('+row.objId+','+row.objTypeId+');\'>生成二维码图片</a>   <a href=\'javascript:void(0);\' onclick=\'recordWin2('+row.objId+','+row.objTypeId+');\'>查看生成记录</a>   ';										
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
		
	}else{
		//批次追溯
		//首先显示主体档案							
		$.ajax({
			   type: "POST",
			   url: "record_findRecordList.action",
			   async: false,
			   data:  {"record.objId":auditRecordEntId,"record.objTypeId":1} ,
			   dataType:"JSON",
			   success: function(records){
				if(records.total==1||records.total=='1'){												
					var record = records.rows[0];							
					var recId = record.recId;	//
					var data = {"objElement.objId":auditRecordEntId,"objElement.objTypeId":1};								
					var recTabId = "recordtab0";																 								
					var recordHtml ='<div id="p" class="easyui-panel" title="主体档案" ><a id="btn" href="javascript:void(0);" class="easyui-linkbutton" onclick="recordWin('+record.objId+',1);" data-options="iconCls:\'icon-add\'" style="margin:8px;">生成主体二维码图片</a> <a  href="javascript:void(0);" class="easyui-linkbutton" onclick="recordWin2('+record.objId+',1);" data-options="iconCls:\'icon-add\'" style="margin:8px;">查看生成记录</a></div>';								
					$("#recordDiv").append(recordHtml);								
					$("#p").panel({width:w,collapsible:true,collapsed:false});								
					$('#btn').linkbutton();
				}
			   }
		});
		
		

		//显示批次档案
		//查询有几个种类，然后显示种类的批次档案信息
		$.ajax({
			   type: "POST",
			   url: "proTypeQrcode_findProTypeQrcodeList.action",
			   async: false,
			   data: {"proTypeQrcode.entId":auditRecordEntId},
			   dataType:"JSON",
			   success: function(result){
		     		for(var j=0;j<result.length;j++){
		     			var proTypeQrcode = result[j];
		     			var ptqId = proTypeQrcode.ptqId;
		     			//种类面板
						var typeTabId = "typetab"+ptqId;																 								
						var recordHtml ='<div id="p'+ptqId+'" class="easyui-panel" title="'+proTypeQrcode.typeName+'档案信息"><table id="'+typeTabId+'" class="easyui-datagrid" data-options="fit:true"></table></div>';								
						$("#recordDiv").append(recordHtml);			
						
						$("#p"+ptqId).panel({width:w,height:'200',collapsible:true,collapsed:false});								

						$("#"+typeTabId).datagrid({  
							  title:'批次信息列表'+typeTabId,
							  iconCls : 'icon-table',
						      pageSize : 15,//默认选择的分页是每页5行数据  
						      pageList : [ 10, 15,20,30,50],//可以选择的分页集合  
						      nowrap : false,//设置为true，当数据长度超出列宽时将会自动截取  
						      striped : true,//设置为true将交替显示行背景。  
						      url:'proTypeBatch_findProTypeBatch.action',//url调用Action方法  
						      queryParams:{"proTypeBatch.ptqId":ptqId},
						      loadMsg : '数据装载中......',  			      
						      fit:true,			      
						      fitColumns:true,//允许表格自动缩放，以适应父容器  
						      remoteSort : false,
						      columns:[[
									{field:'batchName',title:'批次名称',width:200,align:'center'},			
									{field:'batchTime',title:'批次生产时间',width:100,align:'center'},
									{field:'dimenno',title:'批次编码',width:150,align:'center'},
									{field:'batchState',title:'审核状态',width:50,align:'center',formatter: function(value,row,index){ 					
											if(value==''||value==0){
												return '待审核';
											}else if(value==1){
												return '<font color=green>正常</font>';
											}else if(value==2){
												return '<font color=red>异常</font>';
											}
									}},
									{field:'recId',title:'操作',width:100,align:'center',formatter: function(value,row,index){ 					
										var t1='<a href=\'javascript:void(0);\' onclick=\'recordWin('+row.ptbId+',5);\'>生成二维码图片</a>   <a href=\'javascript:void(0);\' onclick=\'recordWin3('+row.dimenno+');\'>生成记录</a>';										
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
			});				
	}
	
	$('#add').window('open').window('setTitle', '查看企业信息').window("maximize");	

}


function recordWin2(objId,typeId){
	$('#recordTable').datagrid( {
		url : 'validCodeRecord_findPagerList2.action',
		queryParams: {"objId": objId,"typeId": typeId},
		//title : '二维码生成记录列表',
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
		columns : [ [ 
		{field : 'dimenno',title : '二维码',width : 200,halign:'center'}, 
		{field : 'createCount',title : '生成数量',width : 200,halign:'center'}, 
		{field : 'creatTime',title : '生成时间',width : 150,align : 'center'},

		] ],
		onLoadSuccess:function(data){
		 	f_timeout(data);			 	
		 	$("#recordWin").window("open");
	 	},
 		onClickRow: function(rowIndex, rowData){
 			$(this).datagrid('unselectAll');
 			$(this).datagrid('selectRow',rowIndex);
 		}
	});
	
}


function recordWin3(dimenno){
	
	$('#recordTable').datagrid( {
		url : 'validCodeRecord_findPagerList.action',
		queryParams: {"validCodeRecord.dimenno": dimenno},
		//title : '二维码生成记录列表',
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
		columns : [ [ 
		{field : 'dimenno',title : '二维码',width : 200,halign:'center'}, 
		{field : 'createCount',title : '生成数量',width : 200,halign:'center'}, 
		{field : 'creatTime',title : '生成时间',width : 150,align : 'center'},

		] ],
		onLoadSuccess:function(data){
		 	f_timeout(data);			 	
		 	$("#recordWin").window("open");
	 	},
 		onClickRow: function(rowIndex, rowData){
 			$(this).datagrid('unselectAll');
 			$(this).datagrid('selectRow',rowIndex);
 		}
	});
}

// 搜索
function find() {
	var entSearch = $('#entSearch').val();
	var name = $('#nameSearch').val();
	var companyRstsSearch = $('#companyRstsSearch').combobox('getValue');
/*	var enterpriseisReported = $('#enterpriseisReported').combobox('getValue');*/
	var enterprise = {};
	if(entSearch!=""){enterprise["enterprise.parentName"]=entSearch};
	if(name != ""){enterprise["enterprise.name"]=name};
	if (companyRstsSearch != "") {enterprise["enterprise.companyRsts"]=companyRstsSearch};
	/*if (enterpriseisReported != "") {enterprise["enterprise.isReported"]=enterpriseisReported};*/
	var areaId = $("#areaId").combotree('getValue');
	if(areaId!=-1){
		enterprise["enterprise.areaId"]=areaId;
	}


	dataGrid.datagrid('load', enterprise); // 点击搜索
}

//重置
function clearSearch(){
	$('#entSearch').val("");
	$('#nameSearch').val("");
	$('#companyRstsSearch').combobox('setValue','');
	$('#enterpriseisReported').combobox('setValue','0');
//	var enterpriseisReported = $('#enterpriseisReported').combobox('getValue');
	var enterprise = {};
	//if (enterpriseisReported != "") {enterprise["enterprise.isReported"]=enterpriseisReported};
	
	$("#areaId").combotree('setValue',-1);
	
	dataGrid.datagrid('load',enterprise);
}

function recordWin(objId,typeId){
	$('#add_form').form('reset');
	$('#_objId').val(objId);
	$('#_typeId').val(typeId);
	$('#_entId').val(auditRecordEntId);
	
	$("#setQrcodeWin").window('open').window("center");
}

function submintForm(){
	
	if($('#add_form').form('validate')==false){
		$.messager.alert('提示','请检查必填项是否已填写!','question');
		return;
	}   
	
	var count = $('#_count').numberbox('getValue');
	if(count>5000){
		$.messager.alert('提示','单次生成不得超过5000张','question');
		return;
	}
	
	
	parent.$.messager.progress({
		title : '提示',
		text : '数据处理中，请稍后....'
	});
	$('#add_form').form('submit', {
		url : 'validCode_addValidCode.action',
		onSubmit : function(result) { 
		    return $(this).form('validate');//对数据进行格式化
		},
		success : function(result) {
			parent.$.messager.progress('close');
			//$.messager.show({ title : '提示', msg : result });
			
			if(result=='fail'){
				$.messager.show({ title : '提示', msg :'操作失败，请稍后重试' });
			}else{
				//window.open(result); 
				//$('#downlink').attr('href',result);
				//$('#downlink').click();
				$.messager.show({ title : '提示', msg :'生成成功！' });
				window.location.href=result;
			}
			
			$('#setQrcodeWin').window('close');
		}
	});
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

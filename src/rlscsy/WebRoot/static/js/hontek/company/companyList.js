var dataGrid; // 列表
var filePath;
var entIdStr;
var queryParams = {};
var formUrl = "company_addCompany.action";
var comType;
var parentId=-1;
$(function() {
	filePath = $("#filePath").val();
	entIdStr = $("#entIdStr").val();
	
    $('#tt').tree({
    	 url:'company_findNodeCompanyList.action' ,
		onClick: function(node){
			if(node.attributes&&node.attributes.isNode){
				parentId = node.id;
				find();
			}
			
		} 
	});
	
	queryParams['company.parentId']= -1;
	dataGrid = $('#list_companyNode').datagrid( {
		url : 'company_findCompanyPagerList.action',
		queryParams: queryParams,
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '经营企业列表',
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
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}] ],
		columns : [[ 
					{field : 'name',title : '企业名称',width : 120,halign:'left'}, 
					{field : 'comType',title : '经营类型',width : 80,align : 'center',
						formatter : function(value, row, index) {
							return getComType(value);
						}
					},
					/*{field : 'flag',title : '节点类型',width : 80,align : 'center',
						formatter : function(value, row, index) {
							return getFlag(value);
						}
					},*/
					{field : 'linkMan',title : '联系人',width : 80,align:'center'}, 
					{field : 'phone',title : '手机号码',width : 100,align : 'center'}, 
					{field : 'regTime',title : '注册时间',width : 120,align : 'center'}, 
					{field : 'state',title : '状态',width : 60,align : 'center',
						formatter : function(value, row, index) {
							return getState(value);
						}
					},
					{field : 'comId',title : '操作',width :90,align : 'center',
						formatter : function(value, row, index) {
							var e = "<a href='javascript:void(0)' onclick='showDetail("+index+")'>查看详细</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick='showRecord("+index+")'>档案管理</a>";
							return e;
						}
					}
		]],
		onLoadSuccess:function(data){
		 	f_timeout(data);
	 	},
 		onClickRow: function(rowIndex, rowData){
 			$(this).datagrid('unselectAll');
 			$(this).datagrid('selectRow',rowIndex);
 		}
	});
	
	

	$("#uploadify").uploadify({
		'swf' : 'uploadify/uploadify.swf',
		'fileObjName' : 'uploadify',
		'uploader' : 'company_uploadFile.action;jsessionid='+ $('#sessionId').val(),
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
				var filePath = "/nytsyFiles/company/";
				$('input[name="company.licenseImg"]').val(data);
				var img_html = "<a rel=\"previewImg\" href=\""+filePath+data+"\"><img alt=\"预览\" src=\""+filePath+data+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
				$("#fileQueue").html(img_html);
				$("a[rel=previewImg]").fancybox({
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
	
	
	 $("#companyTab").tabs({
		 onSelect: function(title,index){
			var target = this;
			//选择不是第一个选项卡页
			if(index>0){
				var zizhiEntId = $("#zizhiEntId").val();
				//var proEntId = $("#proEntId").val();
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
								/*{field:'state',title:'状态',width:100,align:'center',
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
								},	*/	
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
				}
			}
		 }
	});	 
	 
});

//设置状态
function setState(num){
	var stopname ='';
	if(num==1){
		stopname = '确定要设为使用吗?';
	}else if(num==2){
		stopname = '确定要暂停使用吗?';
	}
	var rows = dataGrid.datagrid('getSelections');
	var leng = rows.length;
	if (leng == 0) {
		$.messager.alert('提示', '请选择你需要操作的记录!', 'info');
	}else{
		var temp; // 循环给提交删除参数赋值
		$.each(rows, function(i, n) {
			if (i == 0) {
				temp = n.comId;
			} else {
				temp += "," + n.comId;
			}
		});
		$.messager.confirm('提示',stopname, function(r) {
			if (!r) {
				return;
			} else {
				$.messager.progress( {
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.ajax( {
					url : 'company_updateCompanyState.action',
					data : {"ids":temp,"state":num},
					type : 'post',
					dataType : 'text',
					success : function(result) {
						$.messager.progress('close');
						$.messager.show( {
							title : '提示',
							msg : result
						});
						dataGrid.datagrid('reload');
					}
				});
			}
		});
	}
	
}



//添加
function append() {
	formUrl ="company_addCompany.action";
	$('#add_form').form("reset");
	
	$(".nodeAnCom").show();
	
	$("input[name='company.entId']").val(entIdStr);//
	
	
	
	$("#area").combotree({
		url:'ent_getEntAreaTree2.action',
		method:'get',
		required:true,
		onLoadSuccess:function(node, data){		
			f_timeout(data);
		},
		onSelect:function(node){
			var area = node.id;
			$('#parentId').combobox('reload','company_getCompanyToSelect.action?area='+area);  
			$("#parentId").combobox('setValue','');
		}
	});
	
	$("#parentId").combobox({
		url:'company_getCompanyToSelect.action',
		valueField: 'id',    
        textField: 'text',
		method:'get',
		required:true,
		onSelect:function(record){
			var tarea = $("#area").combotree('getValue');
			if(!tarea){
				$("#parentId").combobox('setValue','');
				$.messager.alert('提示', '请先选择地区!', 'info');
			}
		}
	});
	
	
	$("#area").combotree('setValue','');
	$("#parentId").combobox('setValue','');
	
	$("#add").dialog("open").dialog('setTitle', '添加经营企业').dialog("maximize");
}

// 修改
function update() {
	
	var rows = dataGrid.datagrid('getSelections');
	var leng = rows.length;
	if (leng == 0) {
		$.messager.alert('提示', '请选择你需要修改的记录!', 'info');
	}else if (leng >1) {
		$.messager.alert('提示', '只能选择一条记录进行修改!', 'info');
	}else{
		formUrl ="company_updateCompany.action";
		var company = rows[0];
		
		$('#add_form').form("reset");
		
		$("#add_form").form("load",company);
		
		for(var item in company){
			var text = company[item]==null?"":company[item];
			var obj = $("input[name='company."+item+"']");
			if(obj){
				obj.val(text);
			}
			if(item=='state'||item=='flag'||item=='nature'||item=='comType'){
				$("#"+item).combobox('setValue',text);
			}
			if(item=='introduction'){
				$("#"+item).val(text);
			}
			if(item=='recordDate'){
				$("#"+item).datebox('setValue',text);
			}
		}
		
		
		if(company.licenseImg){
			var fpath = "/nytsyFiles/company/";
			$('input[name="company.licenseImg"]').val(company.licenseImg);
			var img_html = "<a rel=\"previewImg\" href=\""+fpath+company.licenseImg+"\"><img alt=\"预览\" src=\""+fpath+company.licenseImg+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
			$("#fileQueue").html(img_html);
			$("a[rel=previewImg]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
		}
		
		$("#parentId").combobox({
			url:'company_getCompanyToSelect.action?area='+company.area,
			valueField: 'id',    
	        textField: 'text',  
			method:'get',
			required:true
		});

		$("#area").combotree({
			url:'ent_getEntAreaTree2.action',
			method:'get',
			required:true,
			onLoadSuccess:function(node, data){		
				f_timeout(data);
			},
			onSelect:function(node){
				var area = node.id;
				$('#parentId').combobox('reload','company_getCompanyToSelect.action?area='+area);  
			}
		});
		
		$("#area").combotree('setValue',company.area);
		$("#parentId").combobox('setValue',company.parentId);
		
		//禁用验证
		$("input[name='company.account']").validatebox('disableValidation');
		$(".nodeAnCom").hide();
		
		$("#add").dialog("open").dialog('setTitle', '修改经营企业').dialog("maximize");
		
	}
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

// 搜索
function find() {
	var name = $('#s_name').val();
	var comType = $('#s_comType').combobox('getValue');
	var state = $('#s_state').combobox('getValue');
		
	queryParams = {};
	queryParams['company.parentId']=parentId;
	
	if(name!=''){
		queryParams["company.name"]=name;
	}
	if(comType!=''){
		queryParams["company.comType"]=comType;
	}
	if(state!=''){
		queryParams["company.state"]=state;
	}

	dataGrid.datagrid('load', queryParams); // 点击搜索
}

//重置
function clearSearch(){
	$('#s_name').val("");
	$('#s_comType').combobox('setValue','');
	$('#s_state').combobox('setValue','');
	
	queryParams = {};
	parentId=-1;
	queryParams['company.parentId']=parentId;
	
	dataGrid.datagrid('load',queryParams);
}

function removeit(){
	var rows = dataGrid.datagrid("getSelections");
	if (!rows || rows.length == 0) {// 判断是否选择行
		$.messager.alert('提示', '请选择要删除的记录!', 'info');
		return false;
	} else {
		var temp; // 循环给提交删除参数赋值
		$.each(rows, function(i, n) {
			if (i == 0) {
				temp = n.comId;
			} else {
				temp += "," + n.comId;
			}
		});
		$.messager.confirm('提示','确定要删除选中记录吗？', function(r) {
			if (!r) {
				return;
			} else {
				$.messager.progress( {
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.ajax( {
					url : 'company_deleteCompany.action',
					data : {"ids":temp},
					type : 'post',
					dataType : 'text',
					success : function(result) {
						$.messager.progress('close');
						$.messager.show( {
							title : '提示',
							msg : result
						});
						dataGrid.datagrid('reload');
					}
				});
			}
		});
	}

}




//提交表格
function submitForm() {
	
	var name = $("input[name='company.name']").val();
	
	if(name==''){
		$.messager.alert('提示', '请输入名称!', 'info');
		return;
	}
	if ($('#add_form').form('validate') == false) {
		$.messager.show({title : '提示',msg : '输入验证没有通过!'});
		return;
	}
	$.messager.progress( {title : '提示',text : '数据处理中，请稍后....'});
	$('#add_form').form('submit', {
		url : formUrl,
		onSubmit : function(result) {
			return $(this).form('validate');//对数据进行格式化
		},
		success : function(result) {
			$.messager.progress('close');
			$.messager.show( {title : '提示',msg : result});
			
			dataGrid.datagrid('reload');
			$('#add_form').form("reset");
			closeWin('add');
		}
	});
}



function showRecord(index){
	var company = dataGrid.datagrid("getRows")[index];	
	comType = company.comType;
	$("#recordData").html('');								

	if(company){
		var queryParams ={};
		queryParams['proTypeBatch.entId']=company.comId;
		$('#recordDataGrid').datagrid( {
			url : 'proTypeBatch_findProTypeBatch.action',
			queryParams: queryParams,
			//toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
			//title : '批次列表',
			iconCls : 'icon-house',
			loadMsg : '数据加载中...',
			pageSize : 10,// 默认选择的分页是每页10行数据
			pageList : [ 10,20, 30, 50, 100 ],// 可以选择的分页集合
			nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
			striped : true,// 设置为true将交替显示行背景。
			fit : true,
			fitColumns: true,
			// singleSelect:true,
			pagination : true,
			rownumbers : true,
			remoteSort : false,
			// collapsible : true,//显示可折叠按钮
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			}] ],
			columns : [[ 
						{field : 'batchName',title : '批次名称',width : 200,halign:'left'}, 
						{field : 'batchTime',title : '批次生成时间',width : 150,align:'center'}, 
						{field : 'dimenno',title : '批次二维码',width : 150,align : 'center'}, 
						{field : 'comId',title : '操作',width :100,align : 'center',
							formatter : function(value, row, index) {
								var e = "<a href='javascript:void(0)' onclick='showRecordeDetail("+index+")'>查看详细</a>&nbsp;&nbsp;";
								return e;
							}
						}
			]],
			onLoadSuccess:function(data){
			 	f_timeout(data);
		 	},
	 		onClickRow: function(rowIndex, rowData){
	 			$(this).datagrid('unselectAll');
	 			$(this).datagrid('selectRow',rowIndex);
	 		}
		});				
	}
	$('#recordWin').window('open').window("maximize");  // open a window    
}
/**
 * 详细
 */
function showDetail(index){
	var company = dataGrid.datagrid("getRows")[index];
	if(company){	
		$("#zizhiEntId").val(company.comId);
		for(var item in company){
			var obj = $("#d_"+item);
			var text = company[item]==null?"":company[item];
			if(obj){
				if(item=='state'){
					obj.html(getState(text));
				}else if(item=='flag'){
					obj.html(getFlag(text));
				}else if(item=='nature'){
					obj.html(getNature(text));
				}else if(item=='comType'){
					obj.html(getComType(text));
				}else{
					obj.html(text);
				}
			}
		}
		

		if(company.licenseImg){
			var fpath = "/nytsyFiles/company/";
			var img_html = "<a rel=\"previewImg\" href=\""+fpath+company.licenseImg+"\"><img alt=\"预览\" src=\""+fpath+company.licenseImg+"\" style=\"width:45px\">&nbsp;<span>点击预览！</span></a>";
			$("#d_fileQueue").html(img_html);
			$("a[rel=previewImg]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
		}
		
		
		$("#showWin").dialog("open").dialog('setTitle', '查看详细信息');
	}
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

//获取状态
function getState(value){
	var res = '无';
	if(value=='1'){
		res = '使用';
	}else if(value=='2'){
		res = '停用';
	}
	return res;
}

//获取节点类型
function getFlag(value){
	var res = '无';//1代表屠宰企业、2代表批发企业、3代表菜市场、4代表超市、5代表团体消费单位、6代表其他
	if(value=='1'){
		res = '屠宰企业';
	}else if(value=='2'){
		res = '批发企业';
	}else if(value=='3'){
		res = '菜市场';
	}else if(value=='4'){
		res = '超市';
	}else if(value=='5'){
		res = '团体消费单位';
	}else if(value=='6'){
		res = '其他';
	}
	return res;
}

//企业性质
function getNature(value){
	var res = '无';
	if(value=='1'){
		res = '企业';
	}else if(value=='2'){
		res = '个体户';
	}
	return res;
}

//经营者类型
function getComType(value){
	var res = '无';//主要分生猪批发商、肉类蔬菜批发商、肉类蔬菜零售商、配送企业、其他等类型
	if(value=='1'){
		res = '生猪批发商';
	}else if(value=='2'){
		res = '肉菜批发商';
	}else if(value=='3'){
		res = '肉菜零售商';
	}else if(value=='4'){
		res = '配送企业';
	}else if(value=='5'){
		res = '其他';
	}
	return res;
}


function showRecordeDetail(i){
	var row = $('#recordDataGrid').datagrid("getRows")[i];
	var dimenno = row.dimenno;
	var ptbId = row.ptbId;
	//alert(comType);
	var recTabId = "recordtab"+dimenno;																 								
	var recordHtml ='<div id="p'+dimenno+'" class="easyui-panel" data-options="fit:true" style="height:100%;" title="'+row.batchName+'"><div id="'+recTabId+'" class="easyui-tabs" data-options="fit:true">';								
	
	if(comType==1){
		recordHtml+='<div title="生猪进厂信息"><iframe src="jsp/element/marketIn_tz.jsp?ptbId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>'+
								'<div title="检验检疫信息"><iframe src="jsp/element/quarantine.jsp?ptbId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>'+
								'<div title="交易信息"><iframe src="jsp/element/meatOutInfoBase_tz.jsp?ptbId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>';
	}else if(comType==2){
		recordHtml+='<div title="批发进场信息"><iframe src="jsp/element/marketIn_pf.jsp?ptbId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>'+
							'<div title="检验检疫信息"><iframe src="jsp/element/qmarketDetectionInfo.jsp?ptbId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>'+
							'<div title="交易信息"><iframe src="jsp/element/meatOutInfoBase_pf.jsp?ptbId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>';
	}else if(comType==3){
		recordHtml+='<div title="零售进场信息"><iframe src="jsp/element/marketIn_ls.jsp?ptbId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>'+
							'<div title="销售汇总信息"><iframe src="jsp/element/retailMarketTranInfoSumm.jsp?ptbId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>';
	}else if(comType==4){
			recordHtml+='<div title="超市进场信息"><iframe src="jsp/element/marketIn_cs.jsp?ptbId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>';
	}else if(comType==5){
			recordHtml+='<div title="肥料使用记录"><iframe src="jsp/element/fertilizerUse.jsp?recId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div> '+
									  '<div title="农药使用记录"><iframe src="jsp/element/agriUse.jsp?recId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>'+
									  '<div title="生产节点"><iframe src="jsp/element/nodeinfo.jsp?recId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>'+
									  '<div title="检验检疫信息"><iframe src="jsp/element/checkinfo.jsp?recId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>'+										  
									  '<div title="交易信息"><iframe src="jsp/element/saleinfo.jsp?recId='+ptbId+'" width="99%" height="99%" frameborder="0"></iframe></div>';
	}
	
	$("#recordData").html(recordHtml+'</div></div>');								
	$('#'+recTabId).tabs({ border:false});  
	
			
	
}
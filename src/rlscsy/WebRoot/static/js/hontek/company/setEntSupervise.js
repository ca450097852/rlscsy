var dataGrid; // 列表
var isbatch = 0;
$(function() {	
	dataGrid = $('#list_enterprise').datagrid( {
		url : 'ent_findCompanyList.action',
		queryParams: {"enterprise.companyRsts": '4'},
		toolbar : "#tb", // 在添加 增添、删除、修改操作的按钮要用到这个
		title : '企业监管设置',
		iconCls : 'icon-house',
		loadMsg : '数据加载中...',
		pageSize : 15,// 默认选择的分页是每页10行数据
		pageList : [ 10, 15,20, 30, 50 ],// 可以选择的分页集合
		nowrap : true,// 设置为true，当数据长度超出列宽时将会自动截取
		striped : true,// 设置为true将交替显示行背景。
		fit : true,
		fitColumns: true,
		pagination : true,
		rownumbers : true,
		remoteSort : false,
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
		{field : 'entId',title : '操作',width : 80,align : 'center',
			formatter : function(value, row, index) {
				var e = "<a href='javascript:void(0)' onclick='setEntSupervise("+index+")'>设置监管机构</a>";
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
	 
	 $.ajax({
		 url:'ent_findSuperviseEnt.action',
		 dataType:'json',
		 success:function(data){
			 if(data){
				 for(var i=0;i<data.length;i++){
					 $('#superviseEnt').append('<option value="'+data[i].entId+'">'+data[i].name+'</option>');
				 }
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


// 搜索
function find() {
	var entSearch = $('#entSearch').val();
	var name = $('#nameSearch').val();
	var companyRstsSearch = $('#companyRstsSearch').combobox('getValue');
	var enterprise = {};
	if(entSearch!=""){enterprise["enterprise.parentName"]=entSearch};
	if(name != ""){enterprise["enterprise.name"]=name};
	if (companyRstsSearch != "") {enterprise["enterprise.companyRsts"]=companyRstsSearch};
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


function setEntSupervise(index){
	var row = dataGrid.datagrid('getRows')[index];
	if(row){
		$('#s_ent_id').val(row.entId);
		$('#s_entName').html(row.name);
		$('#superviseEnt').val(row.parentId);
		$('#addSupervise').window('open');
	}
}

function submitSuperviseForm(){
	var entId = $('#s_ent_id').val();
	var parentId = $('#superviseEnt').val();
	
	$.ajax({
		url:'ent_updateSuperviseEnt.action',
		data:{'enterprise.entId':entId,'enterprise.parentId':parentId},
		type:'post',
		success:function(data){
			$.messager.show({ title : '提示', msg : data });
			$('#addSupervise').window('close');
			dataGrid.datagrid('reload');
		}
	});
}

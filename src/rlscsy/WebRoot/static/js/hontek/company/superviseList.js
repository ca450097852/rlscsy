$(function(){
	
});

var flag = true;
var dataGrid;
var items = [];
function setObj(){
	$("#entTab").window("open");
	dataGrid = $('#list_enterprise').datagrid( {
		url : 'ent_findCompanyList.action',
		queryParams: {"enterprise.isReported": '0'},
		toolbar : "#tb1", // 在添加 增添、删除、修改操作的按钮要用到这个
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
		{
			field : 'entId',
			checkbox : true
		},
		{field : 'parentName',title : '所属行政机构',width : 150,align:'center'}, 
		{field : 'name',title : '企业名称',width : 200,halign:'center'}, 
		{field : 'legalPerson',title : '法人代表',width : 60,align : 'center'}, 
		{field : 'tel',title : '联系电话',width : 60,align : 'center'}, 
		{field : 'companyRsts',title : '企业状态',width : 60,align : 'center',
			formatter : function(value, row, index) {
				return getAuditState(value);
			}
		}
		] ],
		onLoadSuccess:function(data){
		 	f_timeout(data);
		 	
		 	for(var j=0;j<items.length;j++){
		 		var item = items[j];
	 			var cb = $('input:checkbox[value="'+item.entId+'"]');
	 			cb.attr("checked","checked");
	 			var tr = cb.parent().parent().parent();
	 			tr.addClass('datagrid-row-selected');
	 		}
		 	
	 	},
	 	onSelect:function(rowIndex, rowData){
	 		items.push({entId:rowData.entId,name:rowData.name});
	 		initSelect();
	 	},
	 	onUnselect:function(rowIndex, rowData){
	 		for(var i=0;i<items.length;i++){
	 			var item = items[i];
	 			if(item.entId==rowData.entId){
	 				items.splice(i, 1);
	 				return;
	 			}
	 		}
	 		initSelect();
	 	},
	 	onSelectAll:function(rows){
	 		for(var i = 0;i<rows.length;i++){
	 			var row = rows[i];
	 			var fg = checkisEx(row.entId);
	 			if(!fg){//不存在
	 				items.push({entId:row.entId,name:row.name});
	 			}
	 		}
	 		initSelect();
	 	},
	 	onUnselectAll:function(rows){
	 		for(var i=0;i<rows.length;i++){
	 			removeItem(rows[i].entId);
	 		}
	 		initSelect();
	 	}
	});
	if(flag){
		$("#areaId_id").combotree({
			url:'ent_getEntAreaTree.action?tt='+Math.floor(Math.random()*20)+1,
			method:'get',
			onLoadSuccess:function(node, data){
				flag = false;
			}
		});
	}
	
}

function initSelect(){
	$('#objList').html('');
	$('#objList').append('<option>已选择'+items.length+'个企业</option>');
	for(var i=0;i<items.length;i++){
		var content = '<option>'+items[i].name+'</option>';
		$('#objList').append(content);
	}
}

function selectOne(){
	$("#objList option:first").prop("selected", 'selected');
}

//判断是否存在
function checkisEx(entId){
	for(var i=0;i<items.length;i++){
			var item = items[i];
			if(item.entId==entId){
				return true;
			}
	}
	return false;
}

function removeItem(entId){
	for(var i=0;i<items.length;i++){
			var item = items[i];
			if(item.entId==entId){
				items.splice(i, 1);
				return;
			}
	}
}

function submitSuperviseForm(){
	var content = $("#xh_editor").val();
	
	if(content==''){
		$.messager.show({ title : '提示', msg : '请输入信息内容' });
		return;
	}
	
	if(items.length==0){
		$.messager.show({ title : '提示', msg : '请设置发送对象' });
		return;
	}
	
	var ids = [];
	
	for(var i=0;i<items.length;i++){
		var item = items[i];
		ids.push(item.entId);
	}
	
	$('input[name="ids"]').val(ids.join(','));
	
	$('#superviseForm').form('submit', {
		url : 'supervise_addSuperviseList.action',
		onSubmit : function(result) {
			return $(this).form('validate');// 对数据进行格式化
		},
		success : function(result) {
			$.messager.show({ title : '提示', msg : result });   	
			items = [];
			$('#superviseForm').form('reset');
		}
	});
}



//搜索
function find() {
	var name = $('#nameSearch').val();
	var areaId = $("#areaId_id").combotree('getValue');
	
	var enterprise = {"enterprise.isReported":0};
	if(name != ""){enterprise["enterprise.name"]=name};
	if (areaId != "") {enterprise["enterprise.areaId"]=areaId}	

	dataGrid.datagrid('load', enterprise); // 点击搜索
}

//重置
function clearSearch(){
	$('#nameSearch').val("");
	$('#areaId_id').combobox('setValue','');
	var enterprise = {"enterprise.isReported":0};

	dataGrid.datagrid('load',enterprise);
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